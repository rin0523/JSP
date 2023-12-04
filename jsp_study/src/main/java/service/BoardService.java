package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	int modify(BoardVO bvo);

	int remove(int bno);

	BoardVO getDetail(int bno);

	int getTotCnt(PagingVO pgvo);

}
