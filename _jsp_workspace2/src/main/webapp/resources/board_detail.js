console.log("board_detail.js in!!!")
console.log(bnoVal);

async function getCommentListFromServer(bno){
	try{
		
		const resp=await fetch("/cmt/list/"+bno);
		const result=await resp.json();
		return result;
		
		
	}catch(error){
		console.log(error);
	}
}

function spreadCommentList(result){
	console.log("comment List>>"+result);
	let div=document.getElementById("commentLine");
	div.innerHTML="";
	for(let i=0;i<result.length;i++){
		let html=`<div>`;
		html+=`<div>${result[i].cno},${result[i].bno}`;
		html+=`<div>`;
		html+=`<input type="text" class="cmtText" value="${result[i].content}">`;
        if(result[i].writer==UserID){
	       html+=`<button type="button" data-cno="${result[i].cno}" class="cmtModBtn">수정</button>`
	       html+=`<button type="button" data-cno="${result[i].cno}" class="cmtDelBtn">삭제</button><br>`
}

html+=`</div></div><br><hr>`;
div.innerHTML+=html;
	}
}

function printCommentList(bno){
	getCommentListFromServer(bno).then(result=>{
		console.log(result);
		if(result.length>0){
			spreadCommentList(result);
		}
		else{
			let div=document.getElementById("commentLine");
			div.innerHTML=`<div>comment가 없습니다</div>`;
		}
	})
}


document.getElementById('cmtAddBtn').addEventListener('click',()=>{
	const cmtText= document.getElementById('cmtText').value;
	if(cmtText==null || cmtText==''){
		alert('댓글을 입력해주세요');
		return false;
	}else{
		let cmtData={
			bno:bnoVal,
			writer:document.getElementById('cmtWriter').value,
			content:cmtText
		};
		postCommentToServer(cmtData).then(result=>{
			console.log(result);
			if(result>0){
				alert('댓글 등록 성공 ');
				document.getElementById('cmtText').value='';
			}
			printCommentList(bnoVal);
			
		})
	}
})


async function postCommentToServer(cmtData){
	try{
		const url="/cmt/post";
		const config={
			method:'post',
			headers:{
				'Content-Type' :'application/json; charset=utf-8'
			},
			body:JSON.stringify(cmtData)
		};
		
		const resp=await fetch(url,config);
		const result=await resp.text();
		return result;
		
	}catch(error){
		console.log(error);
	}
}

async function updateCommentFromServer(cnoVal,cmtText){
	try{
		const url="/cmt/modify";
		const config={
			method:'post',
			headers:{
				'Content-Type' : 'application/json;charset=utf-8'
			},
			body:JSON.stringify({cno:cnoVal,content:cmtText})
		}
		
		const resp=await fetch(url,config);
		const result=await resp.text();
		return result;
		
		
	}catch(error){
		console.log(error);
	}
}

async function removeCommentFromServer(cnoVal){
	try{
		
		const url='/cmt/remove? cnoVal='+cnoVal;
		const resp=await fetch(url);
		const result=resp.text();
		return result;
		
		
	}catch(error){
		console.log(error);
	}
}




document.addEventListener('click',(e)=>{
	console.log(e.target);
	if(e.target.classList.contains('cmtDelBtn')){
		let cnoVal = e.target.dataset.cno;
		console.log(cnoVal);
		removeCommentFromServer(cnoVal).then(result=>{
			if(result>0){
				alert('댓글삭제 성공');
				printCommentList(bnoVal);
			}
		})
	}
})

if(e.target.classList.contains('cmtModBtn')){
	let cnoVal=e.target.dataset.cno;
	console.log(cnoVal);
	let div=e.target.closest('div');
	let cmtText=div.querySelector('.cmtText').value;
	console.log(cmtText);
	updateCommentFromServer(cnoVal,cmtText).then(result=>{
		if(result>0){
			alert('수정 성공');
			printCommentList(bnoVal);
		}
	})
}