package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger log= LoggerFactory.getLogger(BoardDAOImpl.class);
	
	//db연결
	private SqlSession sql; 
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql= DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info(">>insert check 3");
		int isOk=sql.insert("BoardMapper.add",bvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		log.info(">>list check 3");
		return sql.selectList("BoardMapper.list");
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>detail check 3");
		return sql.selectOne("BoardMapper.detail",bno);
	}

	@Override
	public int readcountUpdate(int bno) {
		log.info(">>detail ReadCount update check");
		int isOk=sql.update("BoardMapper.read",bno);
		if(isOk>0) {
			sql.commit();
		}
		return 0;
	}

}
