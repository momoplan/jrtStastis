package com.ruicai.manage.pv.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ruicai.basis.common.BaseHttpServlet;
import com.ruicai.basis.common.TypeChange;
import com.ruicai.basis.entity.UserLog;
import com.ruicai.basis.pv.business.UserLogManage;

public class UserLogServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private static Log log = LogFactory.getLog(UserLogServlet.class);
	
	/**
	 * Constructor of the object.
	 */
	public UserLogServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tel = TypeChange.objectToString(request.getParameter("tel"));	
		
		UserLogManage userLogManage = (UserLogManage) getBean("UserLogManage");
		UserLog userLog = new UserLog();

		try {
			List<UserLog> list = userLogManage.findLogInfo(tel);
			if(list.size()>=1){
				userLog = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("findLogInfo 查询错误 ：" + e.getMessage());
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("cn="+userLog.getCn_num());
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
