package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BoardController;
import domain.BoardVO;
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

		@Override
		public int register(BoardVO bvo) {
			log.info(">>>>>insert check 2 ");
			return bdao.insert(bvo);
		}

		@Override
		public List<BoardVO> getList() {
			log.info(">>>list check 2 ");
			return bdao.selectList();
		}

		@Override
		public BoardVO getDatail(int bno) {
			log.info(">>>>detail check 2 ");
			//detail 체크 시 readcount +1 
			int isOk= bdao.readcountUpdate(bno);
			return bdao.getDetail(bno);
		}

		@Override
		public int modify(BoardVO bvo) {
			log.info(">>>modify check 2");
			return bdao.update(bvo);
		}

}
