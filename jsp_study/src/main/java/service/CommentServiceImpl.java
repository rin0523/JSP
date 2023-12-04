package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import repository.CommentDAO;
import repository.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {
	
	private static Logger log= 
			LoggerFactory.getLogger(CommentServiceImpl.class);
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao= new CommentDAOImpl();
	}
	
	

}
