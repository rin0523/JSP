package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BoardController;
import domain.BoardVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {

	// 로그 기록 객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);

	private BoardDAO bdao; // interface로 생성

	public BoardServiceImpl() {
		bdao = new BoardDAOImpl(); // class로 생성 bdao 구현 객체 생성
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
	public int modify(BoardVO bvo) {
		log.info(">>>modify check 2");
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info(">>>>remove check 2 ");
		return bdao.delete(bno);
	}

	@Override
	public BoardVO getDetail(int bno) {
		// TODO Auto-generated method stub
		int isOk = bdao.readcountUpdate(bno);
		return bdao.getDetail(bno);
	}

}
