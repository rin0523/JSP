package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.PagingHandler;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;

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
		System.out.println("sysout을 통한 로그>>" + uri);
		log.info("log객체를 통한 로그>>" + uri);
		String path = uri.substring(uri.lastIndexOf("/") + 1);

		log.info("실 요청 경로>>" + path);

		switch (path) {
		case "register":
			destPage = "/board/register.jsp";
			break;

		case "insert":
			try {
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				log.info(">>insert check 1");

				BoardVO bvo = new BoardVO(title, writer, content);
				log.info("insert bvo>>{}" + bvo);

				isOk = bsv.register(bvo);
				log.info("board register>>{}", isOk > 0 ? "OK" : "Fail");

				destPage = "/index.jsp";

			} catch (Exception e) {
				log.info("insert error");
				e.printStackTrace();
			}
			break;

		case "list":
			try {
				log.info("list check 1");
				PagingVO pgvo= new PagingVO();
				
				if(request.getParameter("pageNo")!=null) {
					int pageNo=Integer.parseInt(request.getParameter("pageNo"));
					int qty=Integer.parseInt(request.getParameter("qty"));
					String type=request.getParameter("type");
					String keyword=request.getParameter("keyword");
					log.info(">>>>pageNo/qty"+pageNo+"/"+qty+"/"+type+"/"+keyword);
					pgvo=new PagingVO(pageNo,qty,type,keyword);
				}
				
				
				
				
				List<BoardVO> list = bsv.getList();
				log.info("list>>{}" + list);
				
				int totalCount=bsv.getToCnt(pgvo);
				log.info("totalCount>>{}"+totalCount);
				PagingHandler pg= new PagingHandler(pgvo,totalCount);
				
				
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
				log.info("detail bvo>>{}", bvo);
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
				log.info("detail check 1");

				BoardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail/jsp";

			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;

		case "edit":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				BoardVO bvo = new BoardVO(bno, title, content);
				log.info("edit check 1");
				log.info("edit>>{}" + bvo);

				isOk = bsv.modify(bvo);
				log.info("edit>>{}", isOk > 0 ? "OK" : "Fail");
				destPage = "list";
			} catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
			}
			break;

		case "remove":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("remove check 1 ");
				isOk = bsv.remove(bno);
				log.info("remove>>{}", isOk > 0 ? "OK" : "Fail");

				destPage = "list";

			} catch (Exception e) {
				log.info("remove error");
				e.printStackTrace();
			}
			break;

		}

		// 목적지 주소로 데이터를 전달(RequestDispatcher)

		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response); // 요청에 필요한 객체를 가지고 경로로 전송

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get으로 오는 요청 처리
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// post로 오는 요청을 처리
		service(request, response);
	}

}
