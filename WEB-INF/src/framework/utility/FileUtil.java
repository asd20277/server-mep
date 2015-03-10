package framework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.oreilly.servlet.MultipartRequest;

import framework.bean.FileUploadParamer;
import framework.bean.RuntimeRequest;
import framework.service.Service;

/** 
 *  有關檔案處理的服務
 *  @author Shin-Ti Wu
 * */
public class FileUtil extends Service{
	
	/** 單一檔案上傳流程,回傳檔案上傳相關資料及參數*/
	public FileUploadParamer saveFile(RuntimeRequest request) {
		FileUploadParamer fup = null;
		try {
			fup = new FileUploadParamer();
			//設定上傳路徑
			//fup.setDirectory(String.valueOf(System.currentTimeMillis()));
			fup.setDirectory("2");
			System.out.println("fup.getDirectory()="+fup.getDirectory());
			
			//建立上傳路徑資料夾
			File webPath =new File(FileUploadConstants.FILE_UPLOAD_DIRECTORY);
			System.out.println("webPath="+webPath.getPath());
			if(!webPath.exists())
				webPath.mkdir();

			// 計算上傳路徑
			fup.setPath(FileUploadConstants.FILE_UPLOAD_DIRECTORY + fup.getDirectory());
			
			boolean result;
			if (!new File(fup.getPath()).exists())//資料夾是否存在
				result = (new File(fup.getPath())).mkdir();//建立資料夾
			else
				result=true;
			
			//boolean result = true;
			
			if (result) {
				// 檔案上傳
				MultipartRequest multi = new MultipartRequest(request.getReq(),
						fup.getPath(),
						FileUploadConstants.FILE_UPLOAD_SIZE * 1024 * 1024, "UTF-8");
				// 取得檔案名稱  Filedata
				fup.setOriginalFileName(multi.getOriginalFileName("file"));//前端的file參數name
				//multi.getParameter("id"));//一般getParameter
				// 取得檔案上傳參數
				Enumeration<String> em = multi.getParameterNames();
				Map<String, String> model = new HashMap<String, String>();
				while(em.hasMoreElements())
				{
					String key=(String) em.nextElement();
					model.put(key, multi.getParameter(key));
				}
				fup.setParameter(model);
				System.out.println("fup.getParameter()="+fup.getParameter());
				System.out.println("fup.getOriginalFileName()="+fup.getOriginalFileName());
				// 取得副檔名
				String[] temp = fup.getOriginalFileName().split("[.]");
				if (temp.length > 1) {
					fup.setFileName(fup.getOriginalFileName());
					System.out.println(fup.getFileName());
					fup.setFullPath(fup.getPath() + "/" + fup.getOriginalFileName());
					System.out.println(fup.getFullPath());
				} else {
					fup.setFileName(fup.getDirectory());
					fup.setFullPath(fup.getPath() + "/" + fup.getDirectory());
				}

				// 更改檔案名稱
				File originalFile = new File(fup.getPath() + "/"+ fup.getOriginalFileName());
				File newFile = new File(fup.getFullPath());
				originalFile.renameTo(newFile);
			} else {
				// IO error
				fup = null;
			}
		} catch (Exception e) {
			fup=null;
			e.printStackTrace();
		} finally {
			return fup;
		}
	}
	
	
	/** 刪除指定資料夾*/
	public static boolean setDeleteDirectory(String  directory) {
		boolean flag = false;

		try {
			File file = new File(directory);
			if (file.exists()) {
				String[] list = file.list();
				for (int i = 0; i < list.length; i++) {
					File temp = new File(directory + "/" + list[i]);
					temp.delete();
				}
				flag = file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return flag;
		}
	}
	
	/** 複製檔案到指定目錄中 */
	public static boolean setCopyFileToDirectory(String SourcePath, String TargetPath) {

		try {
			FileInputStream fIn = new FileInputStream(SourcePath);

			FileOutputStream fOut = new FileOutputStream(TargetPath);

			while (fIn.available() > 0) {
				byte[] b = new byte[512];
				int nResult = fIn.read(b);
				if (nResult == -1)
					break;
				fOut.write(b, 0, nResult);
			}
			fIn.close();
			fOut.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
}
