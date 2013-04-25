package com.ruicai.manage.tj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.tj.business.PictureRingcountManage;

@Controller
public class PictureringcountAction{

	private static Log log = LogFactory.getLog(PictureringcountAction.class);
	
	private String viewPage;
	
	@Autowired
	PictureRingcountManage prManage;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	public PictureringcountAction(){
	}
	
	@RequestMapping("/tj/pictureringcount.do")
	public ModelAndView find(HttpServletRequest request,HttpServletResponse response ,BuyTJ command) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String beginTime = "";	
		String endTime = "";
		String type="";
		
		Calendar cal = Calendar.getInstance();
		beginTime = ConvertLang.convertDateTimeYMD(cal.getTime());
		cal.add(Calendar.DATE, +0);
		endTime = ConvertLang.convertDateTimeYMD(cal.getTime());
		if(request.getParameter("beginTime")!= null){
			beginTime = request.getParameter("beginTime");
		}
		if(request.getParameter("endTime")!= null){
			endTime = request.getParameter("endTime");
		}
		Date d=new Date();
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		String date=s.format(d);
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		try {
			c1.setTime(s.parse(endTime));
			c2.setTime(s.parse(date));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		int result=c1.compareTo(c2);
		if(result>0){
			out.println("<script>window.alert('时间超出范围');window.history.go(-1);</script>");
			return null;
		}
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		String diag="1";
		if(request.getParameter("diag")!=null){
			diag=request.getParameter("diag");
		}
		request.setAttribute("diag", diag);
		HashMap hs=new HashMap();
		hs.put("beginTime", beginTime);
		hs.put("endTime", endTime);
		
		if(diag.equals("1")){
		if(request.getParameter("type")!=null&&!request.getParameter("type").equals("")){
		type=request.getParameter("type");
		hs.put("type","and d.type="+ type);
		request.setAttribute("type",type);
		}else{
			hs.put("type", " ");
			
			request.setAttribute("type", "");
		}
		int count=0;
		try {
			List l=prManage.findPictureRingcountByAll(hs);
			request.setAttribute("list",l);
			for(int i=0;i<l.size();i++){
				HashMap hh=(HashMap)l.get(i);
				count=count+Integer.parseInt(hh.get("DOWNCOUNT").toString());
			}
			request.setAttribute("count",count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else{
			int indexcount=0;
			int downcount=0;
			try {
				List l=prManage.findPvCountList(hs);
				request.setAttribute("list",l);
				for(int i=0;i<l.size();i++){
					HashMap hh=(HashMap)l.get(i);
					downcount=downcount+Integer.parseInt(hh.get("DOWNCOUNT").toString());
					indexcount=indexcount+Integer.parseInt(hh.get("INDEXCOUNT").toString());
				}
				request.setAttribute("downcount",downcount);
				request.setAttribute("indexcount",indexcount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return new ModelAndView(this.getViewPage());
	}
	
	@RequestMapping("/pv/pictureringcountexport.do")
	public void export(HttpServletRequest request,HttpServletResponse response ,BuyTJ command) throws Exception {
		response.setCharacterEncoding("UTF-8");
		JSONObject jsonObject = null;
		try {
		
			jsonObject = JSONObject.fromObject(request.getParameter("jsonObject"));
			
		}catch(Exception e){
			
				e.printStackTrace();
				
		}
		String id = "";
		String mobileid ="";
		JSONObject json = new JSONObject();
		HashMap hs=new HashMap();
		hs.put("ID", id);
		List l=new ArrayList();
		try{
		if(jsonObject.containsKey("id")){
		 id = jsonObject.get("id").toString();
		 hs.put("ID", id);
		 l=prManage.findTPictureRingByID(hs);
		 if(!jsonObject.containsKey("mobileid")){
			 jsonObject.put("mobileid", " ");
		 }
		 int i=prManage.findTuserinfoByMobileid(jsonObject.get("mobileid").toString());
		 if(jsonObject.containsKey("mobileid")&&jsonObject.get("mobileid")!=null&&!jsonObject.get("mobileid").toString().trim().equals("")&&i>0){
		 mobileid = jsonObject.get("mobileid").toString();
		 }else{
			 			hs=(HashMap)l.get(0);
						try {
							if (!"".equals(hs.get("PATH").toString())) {
								//String size=new FileInputStream(new File(hs.get("PATH").toString())).available()/1024+"K";
								String path=hs.get("PATH").toString();
								String[] paths=path.split("upload");
								String  str="upload"+paths[1];
								URL url = new URL("http://"+request.getServerName()+":"+request.getServerPort()+"/"+str) ;
								URLConnection urlCon = url.openConnection() ;	// 建立连接
								JSONObject jsons=new JSONObject();
								jsons.put("id", hs.get("ID"));
								jsons.put("content", hs.get("CONTENT"));
								jsons.put("path", str);
								if(urlCon.getContentLength()>1){
									jsons.put("size", 0+"K");
									}else{
									jsons.put("size", urlCon.getContentLength()/1024+"K");
									}
								json.put("code", "2");
								json.put("value", jsons);
								response.getWriter().write(json.toString());
								response.getWriter().flush();
								response.getWriter().close();
								return ;
							}
						} catch (Exception e) {
							json.put("code", "3");
							response.getWriter().write(json.toString());
							response.getWriter().flush();
							response.getWriter().close();
										e.printStackTrace();
								 
										log.error("获取路径发生异常" + e.toString());
							return;
						}
		 }
		}else{
			json.put("code", "1");
			response.getWriter().write(json.toString());
			response.getWriter().flush();
			response.getWriter().close();
			log.error("获取图铃编码发生异常");
			return;
		}
		}catch(Exception e){
			 json.put("code", "1");
				response.getWriter().write(json.toString());
				response.getWriter().flush();
				response.getWriter().close();
				log.error("获取图铃编码发生异常、"+e.toString());
			 return;
		 }
//		 HashMap hh=new HashMap();
//		 hh.put("mobileid", mobileid);
//		 int diag=prManage.findTranstion(hh);
//		 if(diag==0||diag<1){
//			 try {
//				 	json.put("code", "2");		//未充值用户
//					response.getWriter().write(json.toString());
//					response.getWriter().flush();
//					response.getWriter().close();
//					return;
//				} catch (Exception e) {
//					logger.error("异常:"+e.toString());
//					e.printStackTrace();
//				}
//			 
//		 }
	
		
		List list=prManage.findPictureRingcountByID(hs);
		if(list.size()==0||list==null){
			HashMap hm=(HashMap)l.get(0);
			prManage.doCreatePictureRingcount(hm);
		}else{
			HashMap hm=(HashMap)list.get(0);
			prManage.doUpdatePictureRingcount(hm);
		}
		hs=(HashMap)l.get(0);
//		String[] pathname=hs.get("PATH").toString().split("\\.");
//		String filename=URLEncoder.encode(hs.get("CONTENT").toString())+"."+pathname[pathname.length-1];
//		response.setContentType("application/octet-stream");
//		response.addHeader("Content-Disposition",
//				"attachment;filename="+filename+"");
				try {
					if (!"".equals(hs.get("PATH").toString())) {
//						FileInputStream fis = new FileInputStream(hs.get("PATH").toString());
//						ServletOutputStream sos = response.getOutputStream();
//						int i = 0;
//						while ((i = fis.read()) != -1) {
//							sos.write(i);
//						}
//						fis.close();
//						sos.close();
						String path=hs.get("PATH").toString();
						String[] paths=path.split("upload");
						String  str="upload"+paths[1];
						URL url = new URL("http://"+request.getServerName()+":"+request.getServerPort()+"/"+str) ;
						URLConnection urlCon = url.openConnection() ;	// 建立连接
						JSONObject jsons=new JSONObject();
						jsons.put("id", hs.get("ID"));
						jsons.put("content", hs.get("CONTENT"));
						jsons.put("path", str);
						if(urlCon.getContentLength()>1){
						jsons.put("size", 0+"K");
						}else{
						jsons.put("size", urlCon.getContentLength()/1024+"K");}
						json.put("code", "0");
						json.put("value", jsons);
						response.getWriter().write(json.toString());
						response.getWriter().flush();
						response.getWriter().close();
					}
				} catch (Exception e) {
					json.put("code", "3");
					response.getWriter().write(json.toString());
					response.getWriter().flush();
					response.getWriter().close();
					e.printStackTrace();
						 
					log.error("获取路径发生异常" + e.toString());
					return;
				}
	}
	
	@RequestMapping("/pv/findTPictureRingByAll.do")
	public ModelAndView findTPictureRingByAll(HttpServletRequest request,HttpServletResponse response ,BuyTJ command) throws IOException {
		response.setCharacterEncoding("UTF-8");
		JSONArray jsonArray = new JSONArray();
		HashMap hs=new HashMap();
		JSONObject jsonObject = null;
		int startPage = 1;													//分页开始数据
		int endPage = 10;													//分页结束数据
		try {
			jsonObject = JSONObject.fromObject(request.getParameter("jsonObject"));
			if (jsonObject.containsKey("startPage")) {
				startPage = Integer.parseInt(jsonObject.getString("startPage"));//分页开始数据
			}
			if (jsonObject.containsKey("endPage")) {
				endPage = Integer.parseInt(jsonObject.getString("endPage"));	//分页结束数据
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}if (jsonObject.containsKey("type")) {
		hs.put("TYPE"," where type='"+jsonObject.getString("type")+"'");
		} else{
			hs.put("TYPE"," ");
		}
		hs.put("PAGE", "  ");
		List list=prManage.findTPictureRingByAll(hs);
		int countpage=list.size();
		hs.put("PAGE"," where RN<="+endPage+"  AND RN>="+startPage);
		List l=prManage.findTPictureRingByAll(hs);
		for(int i=0;i<l.size();i++){
			JSONObject json = new JSONObject();
			HashMap hm=(HashMap) l.get(i);
			json.put("id",hm.get("ID"));
			json.put("content",hm.get("CONTENT"));
			String  str="upload"+hm.get("PATH").toString().split("upload",hm.get("PATH").toString().length()-1)[1];
			json.put("path",str);
			json.put("type",hm.get("TYPE"));
			json.put("datetime",hm.get("DATETIME"));
			json.put("countpage",list.size());
			jsonArray.add(json);
		}
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("jsonobject", jsonArray);
		try {
			response.getWriter().write(jsonobject.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			log.error("异常:"+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/pv/findTPictureRingByRandom.do")
	public void findTPictureRingByRandom(HttpServletRequest request,HttpServletResponse response ,BuyTJ command) throws IOException {
		HashMap hh=new HashMap();
		JSONObject jsonObject = null;
		try {
		
			jsonObject = JSONObject.fromObject(request.getParameter("jsonObject"));
			
		}catch(Exception e){
			
				e.printStackTrace();
				
		}
		if(jsonObject.containsKey("type")){
			hh.put("TYPE"," where type='"+jsonObject.get("type").toString()+"'");
			hh.put("PAGE"," ");
		}
		List l=prManage.findTPictureRingByRandom(hh);
			JSONObject json = new JSONObject();
			HashMap hm=(HashMap) l.get(0);
			json.put("id",hm.get("ID"));
			json.put("content",hm.get("CONTENT"));
			String  str="upload"+hm.get("PATH").toString().split("upload",hm.get("PATH").toString().length()-1)[1];
			json.put("path",str);
			json.put("type",hm.get("TYPE"));
			json.put("datetime",hm.get("DATETIME"));
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			log.error("异常:"+e.toString());
			e.printStackTrace();
		}
		//return null;
	}

}
