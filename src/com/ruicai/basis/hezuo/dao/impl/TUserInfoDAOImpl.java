package com.ruicai.basis.hezuo.dao.impl;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.entity.TUserInfo;
import com.ruicai.basis.hezuo.dao.TUserInfoDAO;

public class TUserInfoDAOImpl extends BaseDAO implements TUserInfoDAO {

	private static final Log log = LogFactory.getLog(TUserInfoDAOImpl.class);


	protected void initDao() {
		// do nothing
	}	


	/**
	 * 查询用户
	 * @param model
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<TUserInfo> findTUserInfoList(String channel,String time) throws Exception{
		
        StringBuffer sql = new StringBuffer();				
		
		sql.append("select a.userno,a.mobileid,to_char(a.regtime,'YYYY-MM-DD HH24:MI:SS'),a.channel from tuserinfo a ");				
		sql.append("where a.channel = '"+channel+"' ");				
		sql.append("and a.regtime >= to_timestamp('"+time+" 00:00:00','YYYY-MM-DD HH24:MI:SS')  ");
		sql.append("and a.regtime < to_timestamp('"+time+" 23:59:59','YYYY-MM-DD HH24:MI:SS')  ");

		List<Object[]> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).list();
		} catch (RuntimeException re) {
			log.error("findBuyTJList_all failed", re);
			throw re;
		}
	    
	    List<TUserInfo> list = new Vector<TUserInfo>();
        for(Object[] datas :result){
        	
        	TUserInfo model = new TUserInfo(); 
        	if(datas[0]!=null){
        	   model.setUserno(datas[0].toString());
        	}
        	if(datas[1]!=null){
        	   // 屏蔽部分手机号
        	   String s = datas[1].toString().substring(0,3)+"****"+datas[1].toString().substring(7);
        	   model.setMobileid(s);
        	}
        	if(datas[2]!=null){
         	   model.setRegtime(datas[2].toString());
         	}
        	if(datas[3]!=null){
         	   model.setChannel(datas[3].toString());  
        	}
        	
        	list.add(model);
        }		
		
		return list;
		
	}
	
	/**
	 * 查询用户
	 * @param model
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<TUserInfo> findTUserInfoList(Integer beginnum ,Integer endnum) throws Exception{
		
        StringBuffer sql = new StringBuffer();	
        
        sql.append("select * from ");
        sql.append(" (  select rownum as r,t.* from ");
        sql.append("      (  select b.userno,b.mobileid,to_char(b.regtime,'YYYY-MM-DD HH24:MI:SS'),b.channel  from tuserinfo b ");
        sql.append("         where EXISTS ");
        sql.append("         ( ");
        sql.append("            select a.* ");
        sql.append("            from ttransaction a ");
        sql.append("            where type in (2,3,10) and a.userno = b.userno ");
        sql.append("         ) ");
        sql.append("         order by b.userno ASC ");
        sql.append("     ) t where rownum<= " + endnum);
        sql.append(" ) ");
        sql.append("where r> " + beginnum);

		List<Object[]> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).list();
		} catch (RuntimeException re) {
			log.error("findBuyTJList_all failed", re);
			throw re;
		}
	    
	    List<TUserInfo> list = new Vector<TUserInfo>();
        for(Object[] datas :result){
        	
        	TUserInfo model = new TUserInfo(); 
        	if(datas[0]!=null){
        	   model.setUserno(datas[0].toString());
        	}
        	if(datas[1]!=null){
        	   // 屏蔽部分手机号
        	   String s = "";
        	   if(datas[1].toString().length()>=11){
        		   s = datas[1].toString().substring(0,3)+"****"+datas[1].toString().substring(7);
        	   }else{
        		   s = datas[1].toString();
        	   }
        	   model.setMobileid(s);
        	}
        	if(datas[2]!=null){
         	   model.setRegtime(datas[2].toString());
         	}
        	if(datas[3]!=null){
         	   model.setChannel(datas[3].toString());  
        	}
        	
        	list.add(model);
        }		
		
		return list;
		
	}
}