package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log= LoggerFactory.getLogger(CommentController.class);
	private CommentService csv;
	private int isOk;
       
   
    public CommentController() {
        csv=new CommentServiceImpl();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri=request.getRequestURI();
		log.info("경로>>{}"+uri);
		String pathUri=uri.substring("/cmt".length());
		String path=pathUri;
		String pathVar="";
		if(pathUri.contains("/")) {
			path=pathUri.substring(0,pathUri.lastIndexOf("/"));
			pathVar=pathUri.substring(pathUri.lastIndexOf("/")+1);
		}
		
		log.info("path>>{}"+path);
		log.info("pathVar>>{}"+pathVar);
		
		switch(path) {
		case "post":
			
			try {
				
				StringBuffer sb= new StringBuffer();
				String line="";
				BufferedReader br= request.getReader();
				while((line=br.readLine())!=null) {
					sb.append(line);
				}
				log.info(">>>>sb>>}"+sb.toString());
				JSONParser parser= new JSONParser();
				JSONObject jsonObj=(JSONObject)parser.parse(sb.toString());
				
				int bno=Integer.parseInt(jsonObj.get("bno").toString());
				String writer=jsonObj.get("writer").toString();
				String content=jsonObj.get("content").toString();
				
				CommentVO cvo= new CommentVO(bno,writer,content);
				log.info("commentVO>>{}"+cvo);
				isOk=csv.post(cvo);
				log.info("isOk>>"+((isOk>0)? "OK":"Fail"));
				
				PrintWriter out =response.getWriter();
				out.print(isOk);
				
			} catch (Exception e) {
				
				log.info(">>comment post error");
				e.printStackTrace();
			}
		
			
			break;
		}
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
