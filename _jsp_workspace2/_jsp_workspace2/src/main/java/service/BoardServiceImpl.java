package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BoardController;
import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {

	
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);

	private BoardDAO bdao;
	private CommentServiceImpl csv= new CommentServiceImpl();

	public BoardServiceImpl() {
		bdao = new BoardDAOImpl(); 
	}

	@Override
	public int register(BoardVO bvo) {
		log.info(">>>>>insert check 2 ");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info(">>>list check 2 ");
		return bdao.selectList(pgvo);
	}



	@Override
	public int modify(BoardVO bvo) {
		log.info(">>>modify check 2");
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info(">>>>remove check 2 ");
	
		int isOk= csv.removeAll(bno);
		return bdao.delete(bno);
	}

	@Override
	public BoardVO getDetail(int bno) {
		// TODO Auto-generated method stub
		int isOk = bdao.readcountUpdate(bno);
		return bdao.getDetail(bno);
	}
	
	@Override
	public int getTotCnt(PagingVO pgvo) {
		log.info(">>>> totalCount check 2");
		return bdao.getTotCnt(pgvo);
	}

	@Override
	public String getFileName(int bno) {
		log.info(">>>> getfilename check 2");
		return bdao.getFileName(bno);
	}


}
