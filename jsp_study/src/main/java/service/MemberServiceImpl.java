package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	
	private static final Logger log= 
			LoggerFactory.getLogger( MemberServiceImpl.class);
	
	private MemberDAO mdao; //REPOSITORY=>INTERFACE로 생성 
	
	public MemberServiceImpl() {
		mdao= new MemberDAOImpl();
	}

	
	//메서드 처리
	@Override
	public int register(MemberVO mvo) {
		log.info(">>join check 2");
		return mdao.insert(mvo);
	}


	@Override
	public MemberVO login(MemberVO mvo) {
		log.info(">>login check 2 ");
		return mdao.login(mvo);
	}


	@Override
	public int lastLogin(String id) {
		log.info(">>lastLogin check 2");
		return mdao.lastLogin(id);
	}

}
