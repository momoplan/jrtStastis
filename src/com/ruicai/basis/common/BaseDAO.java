package com.ruicai.basis.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAO extends HibernateDaoSupport{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * 
	 * 用Criteria实现翻页(Hql)
	 * 
	 * @param rollPage
	 *            翻页类
	 * @param Criteria
	 * 
	 */
	protected void settingRollPage(RollPage rollPage, Criteria crt,Integer resCount) {
		// 翻页设置
		if (rollPage != null) {
			//List list = crt.list();
			// 设置记录总数
			//rollPage.setRowCount(list.size());
			rollPage.setRowCount(resCount);
			// 初始化
			rollPage.init();
			// 设置查找记录的起始位置
			crt.setFirstResult(rollPage.getPageFirst());
			// 设置查找记录的最大条数
			crt.setMaxResults(rollPage.getPagePer());
		}
	}
	
	/**
	 * 
	 * 用Query实现翻页(Hql)
	 * 
	 * @param rollPage
	 *            翻页类
	 * @param Query
	 * 
	 */
	protected void settingRollPage(RollPage rollPage, Query crt,Integer resCount) {
		// 翻页设置
		if (rollPage != null) {
			//List list = crt.list();
			// 设置记录总数
			//rollPage.setRowCount(list.size());
			rollPage.setRowCount(resCount);
			// 初始化
			rollPage.init();
			// 设置查找记录的起始位置
			crt.setFirstResult(rollPage.getPageFirst());
			// 设置查找记录的最大条数
			crt.setMaxResults(rollPage.getPagePer());
		}
	}
	
	/**
	 * 
	 * 用SQLQuery实现翻页(Hql)
	 * 
	 * @param rollPage
	 *            翻页类
	 * @param SQLQuery
	 * 
	 */
	protected void settingRollPage(RollPage rollPage, SQLQuery crt,Integer resCount) {
		// 翻页设置
		if (rollPage != null) {
			//List list = crt.list();
			// 设置记录总数
			//rollPage.setRowCount(list.size());
			rollPage.setRowCount(resCount);
			// 初始化
			rollPage.init();
			// 设置查找记录的起始位置
			crt.setFirstResult(rollPage.getPageFirst());
			// 设置查找记录的最大条数
			crt.setMaxResults(rollPage.getPagePer());
		}
	}

	
	/**
	 * 
	 * 用java.sql实现翻页(sql)
	 * 
	 * @param rollPage
	 *            翻页类
	 * @param sql
	 *            查询语句
	 * 
	 */
	protected ResultSet settingRollPage(RollPage rollPage, String sql,Integer resCount)
			throws BaseException {

		try {
			// 获取连接
			Connection conn = getSession().connection();

			Statement stat = conn
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			//ResultSet res = stat.executeQuery(sql);

			//int i = 0;
			//while (res.next()) {
			//	i++;
			//}
			// 设置总页数
			rollPage.setRowCount(resCount);
			// 初始化
			rollPage.init();
			// 设置翻页
			String hsql = getRollPageString(rollPage, sql);

			System.out.println(hsql);

			ResultSet res = stat.executeQuery(hsql);

			return res;
		} catch (Exception e) {
			BaseException ex = new BaseException();
			ex.setMessageKey("settingRollPage分页查询错误！");
			throw ex;
		}
	}
	
	// 进行分页设置
	private String getRollPageString(RollPage rollPage, String sql) {
		StringBuffer pagingSelect = new StringBuffer();

		pagingSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ where rownum <= ");
		pagingSelect.append(rollPage.getPageFirst());
		pagingSelect.append(") where rownum_ > ");
		pagingSelect.append(rollPage.getPagePer());

		return pagingSelect.toString();
	}
	
	public static Object getBean(String name){
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext-*.xml");

		Object obj=ctx.getBean(name);

		return obj;
	}
}
