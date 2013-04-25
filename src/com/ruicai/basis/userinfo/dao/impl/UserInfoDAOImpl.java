package com.ruicai.basis.userinfo.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.entity.Ttransaction;
import com.ruicai.basis.entity.UserInfo;
import com.ruicai.basis.entity.UserTransAction;
import com.ruicai.basis.userinfo.dao.UserInfoDAO;

public class UserInfoDAOImpl extends BaseDAO implements UserInfoDAO{

	private static final Log log = LogFactory.getLog(UserInfoDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}
	
	/**
	 * 获取用户信息
	 * @param instance
	 * @return
	 */
	public UserInfo findUserInfo(String tel) throws RuntimeException{
        log.debug("find findUserInfo ");
        
        UserInfo userinfo = null;
		
		StringBuffer sql = new StringBuffer();	
		sql.append("select t.mobileid as tel,t.certid as certid,t.channel as channel,to_char(t.regtime,'yyyy-mm-dd hh24:mi:ss') as regtime, ");	
		sql.append("to_char(t.modtime,'yyyy-mm-dd hh24:mi:ss') as modtime, ");
		sql.append("m.totalbetamt as totalbetamt ,m.totaldepositamt as totaldepositamt,m.balance as balance, t.userno as userno ");
		sql.append("from jrtsch.tuserinfo t left join jrtsch.taccount m on t.userno = m.userno ");				
		sql.append("where t.mobileid = '"+tel+"' and rownum = 1 ");			
		
		List<Object[]> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).list();
		} catch (RuntimeException re) {
			log.error("findUserInfo failed", re);
			//throw re;
		}
	    
		if(result.size()==1){
			Object[] ob = result.get(0);
			userinfo = new UserInfo();
			userinfo.setTel(ob[0].toString());	
			userinfo.setCertid(ob[1].toString());
			userinfo.setChannel(ob[2].toString());
			userinfo.setRegtime(ob[3].toString());
			userinfo.setModtime(ob[4].toString());
			
			userinfo.setTotalbetamt(new Float(ob[5].toString().trim())/100);  
			userinfo.setTotalbepositamt(new Float(ob[6].toString().trim())/100);
			userinfo.setBalance(new Float(ob[7].toString().trim())/100);
			userinfo.setUserno(ob[8].toString());		
		}	
		
		return userinfo;		
	}
	
	/**
	 * 返回用户交易信息
	 * @param userno
	 * @return
	 */
	public List<UserTransAction> findUserTransAction(String userno) {
        log.debug("UserTransAction ");
		
		StringBuffer sql = new StringBuffer();				
		
		sql.append("select b.userno as userno,b.acceptno as acceptno, ");
		sql.append("a.lotno as lotno,a.betnum as betnum,a.amt as amt,to_char(b.plattime,'yyyy-mm-dd hh24:mi:ss') as plattime ");
		sql.append("from jrtsch.ttransaction b, jrtsch.tlot a ");
		sql.append("where a.flowno = b.flowno and b.type = 1 and b.userno = '"+userno+"' and rownum <= 100 ");				
		sql.append("order by b.plattime desc  ");		

		List<Object[]> result = null;
		try {
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			/*
			query.addScalar("userno", Hibernate.STRING);
			query.addScalar("acceptno", Hibernate.STRING);
			query.addScalar("lotno", Hibernate.STRING);
			query.addScalar("betnum", Hibernate.STRING);
			query.addScalar("amt", Hibernate.STRING);
			query.addScalar("plattime", Hibernate.STRING);
			query.addEntity(UserTransAction.class);
			*/

	        result = query.list();
		} catch (RuntimeException re) {
			log.error("findBuyTJList_all failed", re);
			//throw re;
		}	
		
		List<UserTransAction> list = new Vector<UserTransAction>();
        for(Object[] datas :result){
        	
        	UserTransAction model = new UserTransAction();         	
        	model.setUserno(datas[0].toString());
        	model.setAcceptno(datas[1].toString()==""?"赠送":"投注");
        	model.setLotno(getLOTNOStr(datas[2].toString()));
        	model.setBetnum(datas[3].toString());
        	float a = (new Integer(datas[4].toString().trim()))/100;
        	model.setAmt(String.valueOf(a));
        	model.setPlattime(datas[5].toString()); 
        	
        	list.add(model);
        }	
		
		return list;		
	}
	
	public String getLOTNOStr(String str){
		if("F47103".equals(str)) str="3D";
		if("F47104".equals(str)) str="双色球";
		//caoyongxing
		if("F47102".equals(str)) str="七乐彩";
		return str;
	}
	
	public List findTtrlist(final String hql, final List<Object> values,
			 final int offset, final int pageSize){
		return this.getHibernateTemplate().executeFind(
			new HibernateCallback<List<Ttransaction>>() {
				public List<Ttransaction> doInHibernate(Session session) throws HibernateException, SQLException{
					Query query = session.createQuery(hql);
					for (int i = 0 ; i < values.size() ; i++){
						query.setParameter( i, values.get(i));
					}
					return query.setFirstResult(offset).setMaxResults(pageSize).list();
				}
			}
		);
	} 

	/**
	 * 返回查询总数
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer findListCount(String hql, List<Object> values) throws Exception {
		return this.getHibernateTemplate().find(hql, values.toArray()).size();
	}
	
	/**
	 * 返回查询总钱数
	 * @return
	 * @throws Exception
	 */
	public Long findSumAmt(String hql, List<Object> values) throws Exception {
		return (Long)this.getHibernateTemplate().find(hql, values.toArray()).get(0);
	}
}
