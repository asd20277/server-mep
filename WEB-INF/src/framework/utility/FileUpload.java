package framework.utility;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

public class FileUpload {
	
	public String saveFile(HttpServletRequest request){
		String result = null;
		String dirName = "c:\\eclipse-3.2.2-mis\\workspace\\ksumis\\temp\\";
		try {
			MultipartParser mp = new MultipartParser(request, 1 * 1024 * 1024);
			
			// 10MB
			Part part;
			while ((part = mp.readNextPart()) != null) {
				String name = part.getName();
				if (part.isFile()) { // 處理上傳的檔案 
					FilePart filePart = (FilePart) part;
					String fileName = filePart.getFileName();
					if (fileName != null) {
						// the part actually contained a file 
						// 使用 秒
						long ct = System.currentTimeMillis();
						// 取出副檔名
						int a = fileName.indexOf(".");
						String extName = fileName.substring(a + 1);
						// 寫檔						
						String newFileName = ct + "." + extName; // 利用秒數來當作檔案的名稱
						File dir = new File(dirName + newFileName); // 建立檔案物件
						long size = filePart.writeTo(dir);  // 寫入檔案
						result = newFileName; // 如果回傳檔案名稱，表示儲存成功
					} else { // the field did not contain a file 
						System.out.println("file; name=" + name + "; EMPTY");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
