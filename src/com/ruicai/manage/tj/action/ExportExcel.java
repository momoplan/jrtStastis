package com.ruicai.manage.tj.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.BasicDynaBean;

public class ExportExcel {

	public static void exportWithdrawReview(String fileName,
			List list, HttpServletResponse response) {
		// 创建工作流
		OutputStream os = null;
		// 初始化工作表
		WritableWorkbook wwb = null;
		try {

			// 设置弹出对话框
//			response.setContentType("application/vnd.ms-excel");
//			response.setContentType("application/dowload");
			response.setContentType("application/octet-stream");
//			 设置工作表的标题
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ new String((java.net.URLEncoder.encode(fileName,
							"UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
			os = response.getOutputStream();
//			os=new FileOutputStream("c:\\"+fileName);//输出的Excel文件URL 
			// 创建工作表
			wwb = Workbook.createWorkbook(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			Label label = null;
			// 创建一个可写入的工作表
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);
			for (int i = 0; i < 1000; i++) {
				ws.setColumnView(i, 20);
			}
			WritableFont wf = new jxl.write.WritableFont(WritableFont
					.createFont("宋体"), 11, WritableFont.NO_BOLD, false);
			
			WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf,NumberFormats.TEXT);

			// 准备设置excel工作表的标题
			String[] title1 = { "用户手机号", "充值金额（元）", "充值时间", "充值方式" };
			for (int i = 0; i < title1.length; i++) {
				// 在Excel中，第一个参数表示列，第二个表示行
				label = new Label(i, 0, title1[i], wcfF);
				addCell(ws, label);
			}
			
			int length = list.size();
			for(int i = 0;i<length;i++){
				BasicDynaBean basicdynaBean = (BasicDynaBean) list.get(i);
				label = new Label(0, i+1, basicdynaBean.get("mobileid")==null?"":basicdynaBean.get("mobileid").toString().trim(), wcfF);
				addCell(ws,label);
				label = new Label(1, i+1, basicdynaBean.get("amt")==null?"":basicdynaBean.get("amt").toString().trim(), wcfF);
				addCell(ws,label);
				label = new Label(2, i+1, basicdynaBean.get("plattime")==null?"":basicdynaBean.get("plattime").toString().trim(), wcfF);
				addCell(ws,label);
				label = new Label(3, i+1, basicdynaBean.get("platformname")==null?"":basicdynaBean.get("platformname").toString().trim(), wcfF);
				addCell(ws,label);
			}
		}
		try {
			// 从内存中写入文件中
			wwb.write();
			// 关闭资源，释放内存
			wwb.close();
			// 关闭流
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	// 向表格中添加数据
	public static void addCell(WritableSheet ws, Label label) {
		try {
			ws.addCell(label);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	// 得到当前时间
	public static String getDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return c.get(Calendar.YEAR) + "" + (month > 10 ? month : ("0" + month))
				+ "" + (day > 10 ? day : ("0" + day));
	}
}
