package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.CommentVO;
import domain.PagingVO;
import handler.FileRemoveHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;
	private String savePath;

	private BoardService bsv;

	public BoardController() {

		bsv = new BoardServiceImpl();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.info("필요한 로그 띄우기 가능");
	
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=UTF-8");

		String uri = request.getRequestURI();
		System.out.println("sysout을 통한 로그 >> " + uri);
		log.info("log객체를 통한 로그>> " + uri); 
		String path = uri.substring(uri.lastIndexOf("/") + 1);

		log.info("실 요청 경로>>" + path);

		switch (path) {
		case "register":
			destPage = "/board/register.jsp";
			break;

		case "insert":
			try {
				
				savePath= getServletContext().getRealPath("/_fileUpload");
				File fileDir= new File(savePath);
				log.info("저장위치 >>{}"+ savePath);
				
				
				DiskFileItemFactory fileItemFactory= new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); 
				fileItemFactory.setSizeThreshold(1024*1024*3); 
				
				
				BoardVO bvo= new BoardVO();
				
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem>itemList= fileUpload.parseRequest(request);
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "title" :
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "writer":
						bvo.setWriter(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "image_file":
						
						if(item.getSize()>0) {
							String fileName=item.getName()
									.substring(item.getName().lastIndexOf(File.separator)+1);  
							
							fileName=System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath= new File(fileDir+File.separator+fileName); 
							log.info("uploadFilePath>>{}"+uploadFilePath.toString());
							
							
							try {
								item.write(uploadFilePath); 
								bvo.setImageFile(fileName); 
								
								
								Thumbnails.of(uploadFilePath)
								.size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
								
								
							} catch (Exception e) {
								log.info(">>>file writer on disk error");
								e.printStackTrace();
							}
							
						}
						break;
					}
					
				}
				
				isOk=bsv.register(bvo);
				log.info("board register>>{}", isOk>0? "OK" :"Fail");
				
				destPage = "/index.jsp";
				
				
				

			} catch (Exception e) {
				log.info("insert Error");
				e.printStackTrace();
			}

			break;

		case "list" :
			try {
				
				log.info("list check 1");
				PagingVO pgvo = new PagingVO();	
				
				if(request.getParameter("pageNo") != null) {
					int pageNo = Integer.parseInt(request.getParameter("pageNo"));
					int qty = Integer.parseInt(request.getParameter("qty"));
					String type = request.getParameter("type");
					String keyword = request.getParameter("keyword");
					log.info(">>> pageNo / qty " + pageNo + " / " +qty + " / " + type + " / " + keyword);
					pgvo = new PagingVO(pageNo, qty, type, keyword);
				}
				
				
				List<BoardVO> list = bsv.getList(pgvo);
				log.info("list >>>> {} " + list);
				
				int totalCount = bsv.getTotCnt(pgvo);	
				log.info("totalCount >>>> {} " + totalCount);
				PagingHandler ph = new PagingHandler(pgvo, totalCount);
				
				request.setAttribute("list", list);
				request.setAttribute("ph", ph);
				destPage = "/board/list.jsp";
				
			} catch (Exception e) {
				log.info("list error");
				e.printStackTrace();
			}
			break;

		case "detail":
			try {
				
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("detail check 1");

				BoardVO bvo = bsv.getDetail(bno);
				log.info("detail bvo>>> {}", bvo);
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";

			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}

			break;

		case "modify":
			try {
				
				int bno = Integer.parseInt(request.getParameter("bno"));

				BoardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
				destPage = "/board/modify.jsp";

			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;

		case "edit":
		try {
			
			savePath=getServletContext().getRealPath("/_fileUpload");
			File fileDir = new File(savePath);
			
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(fileDir);
			fileItemFactory.setSizeThreshold(3*1024*1024);
			
			BoardVO bvo= new BoardVO();
			
			ServletFileUpload fileUpload= new ServletFileUpload(fileItemFactory);
			
			List<FileItem>itemList= fileUpload.parseRequest(request);
			String old_file=null;
			
			for(FileItem item: itemList) {
				switch(item.getFieldName()) {
				case "bno":
					bvo.setBno(Integer.parseInt(item.getString("utf-8")));
					break;
				case "title":
					bvo.setTitle(item.getString("utf-8"));
					break;
				case "content":
					bvo.setContent(item.getString("utf-8"));
					break;
				case "image_file":
					
					old_file=item.getString("utf-8");
					break;
				case "new_file":
					
					if(item.getSize()>0) {
						
					
						if(old_file!=null) {
						
							
							FileRemoveHandler fileHandler= new FileRemoveHandler();
							isOk= fileHandler.deleteFile(old_file, savePath);
							
						}
						
						String fileName= item.getName()
								.substring(item.getName().lastIndexOf(File.separator)+1);
						log.info("new File Name>>{}" +fileName);
						
						fileName= System.currentTimeMillis()+"_"+fileName;
						File uploadFilePath= new File(fileDir+File.separator+fileName);
						
						try {
							item.write(uploadFilePath);
							bvo.setImageFile(fileName);
							
							Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
							
							
							
						} catch (Exception e) {
							log.info("Fail Update Error");
							e.printStackTrace();
							
						}
						}else {
							 
							bvo.setImageFile(old_file);
							
						}
					break;
				}
			}
			
			isOk=bsv.modify(bvo);
			log.info("edit >> {} ", isOk > 0 ? "OK" : "Fail");
         	destPage = "list"; 
			
			
	
			

			} catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
			}

			break;
			
			
		case "remove" :
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				savePath = getServletContext().getRealPath("/_fileUpload");
				String imageFileName = bsv.getFileName(bno);
				int isDel = 0;
				if(imageFileName != null) {
					FileRemoveHandler fh = new FileRemoveHandler();
				
					isDel = fh.deleteFile(imageFileName, savePath);
				}
				
				
				
				isOk = bsv.remove(bno);
				log.info("remove check 1");
				log.info("remove >>> {} " + (isOk > 0 ? "OK" : "Fail"));
				
				destPage = "list";
			} catch (Exception e) {
				log.info("remove error");
				e.printStackTrace();
			}
			
			break;
		}

		

		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response); 

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		service(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		service(request, response);
	}

}