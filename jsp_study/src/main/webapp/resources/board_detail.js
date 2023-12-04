console.log("board_detail.js in~!!!");
console.log(bnoVal);

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
    const cmtText=document.getElementById('cmtText').value;
    if(cmtText==null || cmtText==''){
        alert('댓글을 입력해주세요');
        return false;
    }else{
      //댓글 등록
      let cmtData={
        bno: bnoVal,
        writer: document.getElementById('cmtWriter').value,
        content:cmtText
      };
      //댓글 등록 비동기 통신 호출
      postCommentToServer(cmtData).then(result=>{
        console.log(result);
        if(result >0){
            alert('댓글 등록 성공');
        }
        //댓글 출력
        
      })
   
    }
}) 

async function postCommentToServer(cmtData){
    try {
      const url= "/cmt/post";
      const config={
        //method, headers, body
        method:'post',
        headers:{
            'Content-Type':'application/json; charset=utf-8'
        },
        body:JSON.stringify(cmtData)

      };
      
      const resp=await fetch(url,config);
      const result=await resp.text(); //isOk값을 리턴 
      return result;

    } catch (error) {
        console.log(error);
    }
}