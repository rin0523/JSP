package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	
	private SqlSession sql;
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}


	@Override
	public int insert(MemberVO mvo) {
		log.info(">>> join check 3");
		// 
		int isOk = sql.insert("MemberMapper.reg", mvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info(">>login check 3");
		return sql.selectOne("MemberMapper.login" , mvo);
		
	}

	@Override
	public int lastLogin(String id) {
		log.info(">>lastLogin check 3");
		int isOk = sql.update("MemberMapper.last", id); 
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public List<MemberVO> selectList() {
		log.info(">>>list check3");
		return sql.selectList("MemberMapper.list");
	}

	@Override
	public int update(MemberVO mvo) {
		log.info(">>modify check 3");
		int isOk=sql.update("MemberMapper.up",mvo);
		//update,insert,delete 는 commit 필요
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int delete(String id) {
		log.info(">>remove check 3");
		int isOk=sql.delete("MemberMapper.del",id);
		//update,insert,delete 는 commit 필요
		if(isOk>0)sql.commit();
		return isOk;
	}
	
	
	
}