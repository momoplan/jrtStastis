package com.ruicai.basis.tj.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.entity.UserActionTJ;
import com.ruicai.basis.tj.dao.UserActionTJDAO;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
public class UserActionTJDAOImpl extends BaseDAO implements UserActionTJDAO{

	private static final Log log = LogFactory.getLog(UserActionTJDAOImpl.class);

	// property constants
private BasicDataSource datasource;
	public BasicDataSource getDatasource() {
	return datasource;
}

public void setDatasource(BasicDataSource datasource) {
	this.datasource = datasource;
}

	protected void initDao() {
		// do nothing
	}

	public void save(UserActionTJ transientInstance) {
		log.debug("saving UserActionTJ instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserActionTJ persistentInstance) {
		log.debug("deleting UserActionTJ instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserActionTJ findById(Integer id) {
		log.debug("getting UserActionTJ instance with id: " + id);
		try {
			UserActionTJ instance = (UserActionTJ) getHibernateTemplate().get(
					"com.ruicai.basis.entity.UserActionTJ", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserActionTJ instance) {
		log.debug("finding UserActionTJ instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserActionTJ instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserActionTJ as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all UserActionTJ instances");
		try {
			String queryString = "from BuyTJ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserActionTJ merge(UserActionTJ detachedInstance) {
		log.debug("merging PayTJ instance");
		try {
			UserActionTJ result = (UserActionTJ) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserActionTJ instance) {
		log.debug("attaching dirty UserActionTJ instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserActionTJ instance) {
		log.debug("attaching clean BuyTJ instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserActionTJDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (UserActionTJDAOImpl) ctx.getBean("BuyTJDAOImpl");
	}
	
	/**
	 * 返回用户行为统计结果
	 * @param userActionTJ
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<UserActionTJ> findUserActionTJList(UserActionTJ userActionTJ,String firsttime,String lasttime) throws Exception {
				
		Criteria crt = getSession().createCriteria(UserActionTJ.class);		
		List<UserActionTJ> list = new ArrayList<UserActionTJ>();
		
		crt.add(Expression.ge("tjdate", ConvertLang.convertDateTimeStr_ymd(firsttime)));
		crt.add(Expression.lt("tjdate", ConvertLang.convertDateTimeStr_ymd(lasttime)));
		
		crt.addOrder(Order.asc("id"));
		
		list = crt.list();	

		return list;
		
	}

	public List findUserActionTJ(String firsttime, String lasttime,String qudao)
			throws Exception {

		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(this.getDatasource()); 
		String channel="";
		String channelid="";
		if(qudao!=null){
			channel="and channel='"+qudao+"'";
			channelid="and channelid='"+qudao+"'";
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("select to_char(tjdate,'yyyy-MM-dd') dates, indexvn indexvn, popvn popvn, payvn payvn, paymoney paymoney ,   realpaymoney realpaymoney,");
		sql.append("psoftn psoftn,loginn loginn,getmoneyn getmoneyn,buyn buyn,payn payn ");
		sql.append("from jrtstatis.useractiontj ");
		sql.append("where to_char(tjdate,'yyyy-MM-dd')>='"+firsttime+"' and to_char(tjdate,'yyyy-MM-dd')<'"+lasttime+"' ");
		sql.append("order by tjdate desc");
		log.info("sql:" + sql.toString());
		return sjt.queryForList(sql.toString());
	}

	@Override
	public boolean findChannelByCode(String channel) throws Exception {
		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(this.getDatasource()); 
		String sql="select * from jrtstatis.channel where id=?";
		List l=sjt.queryForList(sql, channel);
		if(l.size()!=0&&l!=null){
			return true;
			
		}else{
		return false;}
	}	

}
