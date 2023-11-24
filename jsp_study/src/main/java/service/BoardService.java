package service;

import java.util.List;

import domain.BoardVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDatail(int bno);

	int modify(BoardVO bvo);

}
