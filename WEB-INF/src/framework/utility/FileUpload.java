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
				if (part.isFile()) { // �B�z�W�Ǫ��ɮ� 
					FilePart filePart = (FilePart) part;
					String fileName = filePart.getFileName();
					if (fileName != null) {
						// the part actually contained a file 
						// �ϥ� ��
						long ct = System.currentTimeMillis();
						// ���X���ɦW
						int a = fileName.indexOf(".");
						String extName = fileName.substring(a + 1);
						// �g��						
						String newFileName = ct + "." + extName; // �Q�ά�ƨӷ�@�ɮת��W��
						File dir = new File(dirName + newFileName); // �إ��ɮת���
						long size = filePart.writeTo(dir);  // �g�J�ɮ�
						result = newFileName; // �p�G�^���ɮצW�١A����x�s���\
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
