package framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 檔案上傳參數
 *  */
public class FileUploadParamer {
String path="0";//完整資料夾路徑
String fullPath;
String directory;//存取資料夾
String fileName;//檔案名稱
String originalFileName;
Map parameter=new HashMap();


public Map getParameter() {
	return parameter;
}
public void setParameter(Map parameter) {
	this.parameter = parameter;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getFullPath() {
	return fullPath;
}
public void setFullPath(String fullPath) {
	this.fullPath = fullPath;
}
public String getDirectory() {
	return directory;
}
public void setDirectory(String directory) {
	this.directory = directory;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getOriginalFileName() {
	return originalFileName;
}
public void setOriginalFileName(String originalFileName) {
	this.originalFileName = originalFileName;
}


}
