package com.ruicai.manage.sys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruicai.basis.channel.dao.impl.ChannelDAOImpl;
import com.ruicai.basis.entity.Tlot;



/**
 * Servlet implementation class T3gServlet
 */
public class T3gServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public T3gServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String agencyno=request.getParameter("agencyno");
		String lotno=request.getParameter("lotno");
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		ChannelDAOImpl chandao=new ChannelDAOImpl();
		long count=chandao.get3gall(agencyno, begintime, endtime, request);
		Tlot tlot=new Tlot();
		tlot.setLotno("七乐彩");
		tlot.setAmt(count/100);
		List list=new ArrayList();
		list.add(tlot);
		request.getSession().setAttribute("list", list);
		request.getRequestDispatcher("/3g/buylist.jsp").forward(request, response);
		
	}

}
