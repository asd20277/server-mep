package framework.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

/**
 * Runtime Request object.
 * 
 * @author FengShuo Yu
 */
public class RuntimeRequest {
	private ServletContext servletContext = null;
	/**
	 * Http request.
	 */
	private HttpServletRequest req = null;
	/**
	 * Http response.
	 */
	private HttpServletResponse response = null;

	/**
	 * @return
	 */
	public HttpServletRequest getReq() {
		return req;
	}

	/**
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param request
	 */
	public void setReq(HttpServletRequest request) {
		req = request;
	}

	/**
	 * @param response
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @return
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @param context
	 */
	public void setServletContext(ServletContext context) {
		servletContext = context;
	}

	public String getValue(String name) {
		String str = "";
		try {
			String a = req.getParameter(name);
			// if(a != null){
			// //str = new String(a.getBytes("ISO8859_1"));
			// str = new String(a.getBytes("ISO8859_1"), "utf-8");
			// }
			str = a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
		// String name1=new
		// String(request.getReq().getParameter("keyword").getBytes("ISO8859_1"));
	}

}
