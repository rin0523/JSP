package service;

import java.util.List;

import domain.BoardVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList();

	int modify(BoardVO bvo);

	int remove(int bno);

	BoardVO getDetail(int bno);

}


