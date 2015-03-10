package edu.ksu.mep.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import edu.ksu.mep.bean.Photo;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;

public class UploadPictureActionMobile extends AbstractAction {

	@Override
	protected void doUpdate(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doDelete(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsert(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Collection getModel(RuntimeRequest request) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doView(RuntimeRequest request) throws SQLException,
			IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process(RuntimeRequest request) throws SQLException,
			IOException {
		HttpSession session = request.getReq().getSession();
		request.getResponse().setContentType("text/html; charset=utf-8");
		PrintWriter out = request.getResponse().getWriter();
		
		Photo bean = (Photo) getAttributeFromRequest(request, "photo"); // 這個要改!!!
		
		System.out.print("check file:" + bean.getName());
	}

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		Photo bean = new Photo();
		long tid= System.currentTimeMillis();//上傳物品時產生Item_id
		bean.setName(tid + ""); //轉毫秒

		
		//String dirName = AppConstants.images_ROOT;
		String dirName = "C:\\eclipse-android\\workspace\\mep\\upload\\"; // 這個要改!!!
		try {
			MultipartParser mp = new MultipartParser(request.getReq(), 64 * 1024 * 1024); // 10MB 
			Part part;
			while ((part = mp.readNextPart()) != null) {
				String name = part.getName();
				if (part.isParam()) { // 一般表單的情形 
					ParamPart paramPart = (ParamPart) part;
					String value = paramPart.getStringValue("UTF-8");
					setParameter(bean, name, value); // 這個方法一定要根據實際的情況重寫
					System.out.println("param; name=" + name + ", value=" + value);
				} else if (part.isFile()) { // 上傳檔案的情形
					FilePart filePart = (FilePart) part;
					String fileName = filePart.getFileName();
					if (fileName != null) {
						long ct = System.currentTimeMillis();
			
						int a = fileName.indexOf(".");
						String extName = fileName.substring(a+1);
					
						String newFileName=ct+"."+extName;
						File dir = new File(dirName+newFileName);
						System.out.println(dir.toString());
						long size = filePart.writeTo(dir);
						//System.out.println(	"file; name="+ name	+ "; filename="	+ fileName	+ ", filePath="	+ filePart.getFilePath()+ ", content type="
						//		+ filePart.getContentType()	+ ", size="	+ size);
					} else {  
						System.out.println("file; name=" + name + "; EMPTY");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		addAttributeToRequest(request, "photo", bean); // 這個要改!!!

	}
	
	/**
	 *  1. 一定要根據計畫中的JavaBean覆寫掉這一段程式，不然會有問題。
	 *  2. 要配合設計的JavaBean。
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 */
	private void setParameter(Photo bean, String name, String value){
		if(name.equals("photoCaption")){
			bean.setName(value);			
		} // 這個要改!!!
		System.out.println( name + ":" + value);

	}
}
