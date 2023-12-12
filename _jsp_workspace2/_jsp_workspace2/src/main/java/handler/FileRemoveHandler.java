package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRemoveHandler {
	
	private static final Logger log= LoggerFactory.getLogger(FileRemoveHandler.class);
	
	
	public int deleteFile(String imageFileName,String savePath) {
		
		boolean isDel = false;
		log.info(">>>deleteFile method 접근 완료 ~!! " +imageFileName);
		
		File fileDir = new File(savePath);
		File removeFile= new File(fileDir+fileDir.separator+imageFileName);
		File removeThumbFile= new File(fileDir+fileDir.separator+"th_"+imageFileName);
		
		if(removeFile.exists()|| removeThumbFile.exists()) {
			isDel=removeFile.delete();
		    log.info(">>>fileRemove : " + (isDel? "OK":"Fail"));
		    if(isDel) {
		    	isDel=removeThumbFile.delete();
		    	 log.info(">>>fileThumbRemove : " + (isDel? "OK":"Fail"));
		    }
		    
		}
		
		log.info(">>>>remove ok");
		
		return isDel? 1 :0;
	}

}