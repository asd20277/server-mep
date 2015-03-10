package edu.ksu.mep.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import edu.ksu.mep.bean.Record;
import edu.ksu.mep.service.RecordService;
import edu.ksu.mep.utility.AppConstants;
import framework.action.AbstractAction;
import framework.bean.RuntimeRequest;
import framework.service.ServiceLocator;

public class AddRecordActionMobile extends AbstractAction {

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
		
		request.getResponse().setCharacterEncoding("utf-8");
		Record record = (Record)this.getAttributeFromSession(request, "record");
		RecordService service = (RecordService)ServiceLocator.getService("RecordService");
		
		boolean result = service.addRecord(record);
		
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

	@Override
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub
		request.getReq().setCharacterEncoding("utf-8");
		Record record = new Record();
		long tid = System.currentTimeMillis();
		record.setRecordname(tid + "");
		
		String dirname = AppConstants.images_ROOT;
		
		try {
			MultipartParser mp = new MultipartParser(request.getReq(),1024 * 1024);
			Part part;
			while ((part = mp.readNextPart()) != null) {
				String data = part.getName();

				if (part.isParam()) {
					ParamPart paramPart = (ParamPart) part;
					String value = paramPart.getStringValue("utf-8");
					System.out.println(value);
					setParameter(record, data, value);
				} else if (part.isFile()) {
					FilePart filePart = (FilePart) part;
					String fileName = filePart.getFileName();
					if(fileName != null){
						long ct = System.currentTimeMillis();
						int a = fileName.indexOf(".");
						String extName = fileName.substring(a+1);
						String newFileName = ct + "." + extName;
						File dir = new File(dirname+ "\\Record\\" +newFileName);
						
						record.setRecordname(newFileName);
						
						long size = filePart.writeTo(dir);
					}else {
						System.out.println("file; name=" + data + "; EMPTY");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		this.addAttributeToSession(request, "record", record);
	}
	
	
	private void setParameter(Record bean, String name, String value) {
		// TODO Auto-generated method stub
		if (name.equals("userid")) {
			bean.setUserid(value);
		}
		if (name.equals("title")) {
			bean.setTitle(value);
		}
		if (name.equals("species")) {
			bean.setSpecies(value);
		}
		if (name.equals("latitude")) {
			bean.setLatitude(value);
		}
		if (name.equals("longitude")) {
			bean.setLongitude(value);
		}
		if (name.equals("datetime")) {
			bean.setDatetime(value);
		}
		int v = 0;
		bean.setViewers(v);
	}

	@Override
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

}
