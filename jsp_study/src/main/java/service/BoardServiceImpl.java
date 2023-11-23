package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BoardController;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	
	//로그 기록 객체 생성 
		private static final Logger log=
				LoggerFactory.getLogger(BoardServiceImpl.class);
		
		
		private BoardDAO bdao; //interface로 생성
		
		public BoardServiceImpl() {
			bdao= new BoardDAOImpl(); //class로 생성 bdao 구현 객체 생성 
		}

}
