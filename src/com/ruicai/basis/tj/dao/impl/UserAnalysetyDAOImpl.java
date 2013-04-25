package com.ruicai.basis.tj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.context.ApplicationContext;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.entity.UserActionTJ;
import com.ruicai.basis.entity.UserAnalysety;
import com.ruicai.basis.tj.dao.UserAnalysetyDAO;

public class UserAnalysetyDAOImpl extends BaseDAO implements UserAnalysetyDAO{

	private static final Log log = LogFactory.getLog(UserAnalysetyDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(UserAnalysety transientInstance) {
		log.debug("saving UserAnalysety instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserAnalysety persistentInstance) {
		log.debug("deleting UserAnalysety instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserAnalysety findById(Integer id) {
		log.debug("getting UserAnalysety instance with id: " + id);
		try {
			UserAnalysety instance = (UserAnalysety) getHibernateTemplate().get(
					"com.ruicai.basis.entity.UserAnalysety", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserAnalysety instance) {
		log.debug("finding UserAnalysety instance by example");
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
		log.debug("finding UserAnalysety instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserAnalysety as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all UserAnalysety instances");
		try {
			String queryString = "from UserAnalysety";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserAnalysety merge(UserAnalysety detachedInstance) {
		log.debug("merging UserAnalysety instance");
		try {
			UserAnalysety result = (UserAnalysety) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserAnalysety instance) {
		log.debug("attaching dirty UserAnalysety instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserAnalysety instance) {
		log.debug("attaching clean UserAnalysety instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserAnalysetyDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (UserAnalysetyDAOImpl) ctx.getBean("UserAnalysetyDAOImpl");
	}
	
	/**
	 * 返回用户分析统计结果
	 * @param userAnalysety
	 * @param firsttime
	 * @param lasttime
	 * @return
	 * @throws Exception
	 */
	public List<UserAnalysety> findUserAnalysetyList_all(UserAnalysety userAnalysety,String firsttime,String lasttime) throws Exception{
		
		Criteria crt = getSession().createCriteria(UserAnalysety.class);		
		List<UserAnalysety> list = new ArrayList<UserAnalysety>();
		
		crt.add(Expression.ge("tjdate", ConvertLang.convertDateTimeStr_ymd(firsttime)));
		crt.add(Expression.lt("tjdate", ConvertLang.convertDateTimeStr_ymd(lasttime)));
		
		crt.addOrder(Order.asc("id"));
		
		list = crt.list();	

		return list;
	}
}
