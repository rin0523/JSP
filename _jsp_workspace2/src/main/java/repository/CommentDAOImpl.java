package repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	
	private static Logger log= LoggerFactory.getLogger(CommentDAOImpl.class);
    private SqlSession sql;
    private int isOk;
    
    public CommentDAOImpl() {
    	new DatabaseBuilder();
    	sql=DatabaseBuilder.getFactory().openSession();
    }
	
	@Override
	public int insert(CommentVO cvo) {
		log.info("comment post check 3 ");
		isOk=sql.insert("CommentMappeer.post",cvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

}
