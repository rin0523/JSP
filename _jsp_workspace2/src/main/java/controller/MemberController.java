package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/memb/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	private RequestDispatcher rdp;

	private String destPage;

	private int isOk;

	private MemberService msv;

	public MemberController() {
		msv = new MemberServiceImpl();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info(">>path>>" + path);

		switch (path) {
		case "join":
			destPage ="/member/join.jsp";
			break;

		case "register":
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));

				MemberVO mvo = new MemberVO(id, pwd, email, age);
				log.info(">>join check 1>>{}" + mvo);
				isOk = msv.register(mvo);
				log.info("join>>", (isOk > 0) ? "OK" : "Fail");
				destPage = "/index.jsp";

			} catch (Exception e) {
				e.printStackTrace();
				log.info(">>join error");
			}
			break;

		case "login":
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				MemberVO mvo = new MemberVO(id, pwd);

				log.info(">>login check 1");

				MemberVO loginMvo = msv.login(mvo);
				log.info("login mvo>>{}" + loginMvo);

				if (loginMvo != null) {
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginMvo);
					ses.setMaxInactiveInterval(10 * 60);
				} else {
					request.setAttribute("msg_login", -1);
				}
				destPage = "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				log.info(">>login error");
			}
			break;

		case "logout":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO) ses.getAttribute("ses");
				log.info("ses에서 추출한 mvo>>{}", mvo);

				isOk = msv.lastLogin(mvo.getId());
				log.info("lastLogin>>", isOk > 0 ? "OK" : "Fail");
				ses.invalidate();
				destPage = "/index.jsp";

			} catch (Exception e) {
				e.printStackTrace();
				log.info(">>logout error");
			}
			break;

		case "detail":
			try {
				destPage = "/member/detail.jsp";

			} catch (Exception e) {
				e.printStackTrace();
				log.info(">>detail error");
			}
			break;

		case "list":
			try {
				log.info("list check 1");
				List<MemberVO> list = msv.getList();

				log.info("list>>{}" + list);
				request.setAttribute("list", list);
				destPage = "/member/list.jsp";

			} catch (Exception e) {
				log.info(">>list error");
				e.printStackTrace();
			}

			break;
			
		case"modify":
			try {
				String id=request.getParameter("id");
				String pwd=request.getParameter("pwd");
				String email=request.getParameter("email");
				MemberVO mvo= new MemberVO(id,pwd,email);
				log.info(">>>>"+mvo);
				isOk=msv.modify(mvo);
				log.info("modify>>{}"+(isOk>0? "OK":"Fail"));
				if(isOk>0) {
					request.setAttribute("msg_modify", "ok");
				}
				HttpSession ses=request.getSession();
				ses.invalidate();
				
				destPage="/index.jsp";
			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;
			
		case "edit":
			try {
				String pwd=request.getParameter("pwd");
				String email=request.getParameter("email");
				String age=request.getParameter("age");
				MemberVO mvo=new MemberVO(pwd,email,age,isOk);
				log.info("edit check 1");
				log.info("edit>>{}"+mvo);
				
				isOk=msv.modify(mvo);
				log.info("edit>>{}", isOk>0? "OK":"Fail");
				destPage="logout";
			} catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
			}
			break;
			
		case "remove":
			try {
				HttpSession ses=request.getSession();
				MemberVO mvo=(MemberVO)ses.getAttribute("ses");
				String id=mvo.getId();
				
				isOk=msv.remove(id);
				ses.invalidate();
				
				if(isOk>0) {
					request.setAttribute("msg_remove", "ok");
				}
				
				destPage="/index.jsp";
				
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
