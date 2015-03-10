package edu.ksu.mep.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.XmlReader;
import javax.sql.rowset.spi.XmlWriter;

import org.json.JSONObject;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;
import com.sun.corba.se.spi.servicecontext.ServiceContext;
import com.sun.org.apache.xerces.internal.jaxp.JAXPConstants;

import edu.ksu.mep.bean.Account;
import edu.ksu.mep.bean.Article;
import edu.ksu.mep.bean.ArticleItem;
import edu.ksu.mep.service.ArticleService;
import edu.ksu.mep.utility.AppConstants;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class AddArticleActionMobile extends AbstractAction {

	@Override
	protected void doDelete(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsert(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doUpdate(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doView(RuntimeRequest request) throws SQLException,
			IOException {
		// TODO Auto-generated method stub
		
		
		

	}

	@Override
	protected Collection getModel(RuntimeRequest request) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(RuntimeRequest request) throws SQLException,
			IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getReq().getSession();
		request.getResponse().setContentType("text/html; charset=utf-8");
		PrintWriter out = request.getResponse().getWriter();
//		
		request.getResponse().setCharacterEncoding("utf-8");
		ArticleItem add = (ArticleItem) this.getAttributeFromSession(request, "add");
		ArticleService service = (ArticleService) ServiceLocator.getService("ArticleService");
		boolean result = service.addArticleWithPhoto(add);
		
		JsonObjectResponse jsonResponse = new JsonObjectResponse();
		jsonResponse.setTotalRows(0);
		
		if (result == true) {
			jsonResponse.setSuccess(true);
		} else {
			jsonResponse.setSuccess(false);
		}
		
		JSONObject json = new JSONObject(jsonResponse);
		String returnValue = json.toString();
		request.getResponse().getWriter().println(returnValue);
	}

	private void init(ServletConfig config) {
		// TODO Auto-generated method stub
		String a = config.getInitParameter("fileuploaddirectory");
	}
	
	
	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		request.getReq().setCharacterEncoding("utf-8");
		ArticleItem add = new ArticleItem();
		long tid= System.currentTimeMillis();//上傳物品時產生Item_id
		add.setPhotoname(tid + ""); //轉毫秒
    
		
		String dirName = AppConstants.images_ROOT;
			
		
		try {
			MultipartParser mp = new MultipartParser(request.getReq(), 1024 * 1024); // 10MB
			Part part;
			while((part = mp.readNextPart()) != null){
				String data = part.getName();
					
				if(part.isParam()){
					ParamPart paramPart = (ParamPart) part;
					String value = paramPart.getStringValue("UTF-8");
					setParameter(add, data, value);
				}else if(part.isFile()){
					FilePart filePart = (FilePart)part;
					String fileName = filePart.getFileName();
					if(fileName != null){
						long ct = System.currentTimeMillis();
						int a = fileName.indexOf(".");
						String extName = fileName.substring(a+1);
						String newFileName = ct + "." + extName;
						File dir = new File(dirName+newFileName);
						
						add.setPhotoname(newFileName);
						
						long size = filePart.writeTo(dir);
					}else{
						System.out.println("file; name=" + data + "; EMPTY");
					}
				}					
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		this.addAttributeToSession(request, "add", add);
	}
	
	private void setParameter(ArticleItem bean, String name, String value){

		if(name.equals("title")){
			bean.setTitle(value);			
		}
		
		if(name.equals("content")){
			bean.setContent(value);			
		}
		
		if(name.equals("userid")){
			bean.setUserid(value);			
		}
		if(name.equals("longitude")){
			bean.setLongitude(value);			
		}
		if(name.equals("latitude")){
			bean.setLatitude(value);			
		}
		if(name.equals("datetime")){
			bean.setDatetime(value);			
		}
		if(name.equals("species")){
			bean.setSpecies(value);			
		}
		int v = 0;
		bean.setViewers(v);
		System.out.println( name + ":" + value);

	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
