package com.ruicai.basis.pv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.entity.UserLog;
import com.ruicai.basis.pv.dao.UserLogDAO;

public class UserLogDAOImpl extends BaseDAO implements UserLogDAO {

	private static final Log log = LogFactory.getLog(UserLogDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(UserLog transientInstance) {
		log.debug("saving UserLog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(UserLog transientInstance) {
		log.debug("updating UserLog instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(UserLog persistentInstance) {
		log.debug("deleting UserLog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}	
	

	public UserLog findById(java.lang.Integer id) {
		log.debug("getting UserLog instance with id: " + id);
		try {
			UserLog instance = (UserLog) getHibernateTemplate().get(
					"com.ruicai.basis.entity.UserLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserLog instance) {
		log.debug("finding UserLog instance by example");
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
		log.debug("finding UserLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserLog as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<UserLog> findAll() {
		log.debug("finding all Thd instances");
		try {
			String queryString = "from UserLog "; 
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserLog merge(UserLog detachedInstance) {
		log.debug("merging PVCount instance");
		try {
			UserLog result = (UserLog) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserLog instance) {
		log.debug("attaching dirty UserLog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserLog instance) {
		log.debug("attaching clean UserLog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 返回用户日志信息
	 * @param tel
	 * @return
	 * @throws Exception
	 */
	public List<UserLog> findLogInfo(String tel) throws Exception {

		Criteria crt = getSession().createCriteria(UserLog.class);

		List<UserLog> list = new ArrayList<UserLog>();

		crt.add(Expression.eq("tel_num", tel));
		crt.addOrder(Order.desc("log_id"));
		crt.setMaxResults(1);
		list = crt.list();
		
		return list;
	}
	
	/**
	 * 返回最近200条记录
	 * @return
	 * @throws Exception
	 */
	public List<UserLog> findLast200List() throws Exception {

		Criteria crt = getSession().createCriteria(UserLog.class);
		List<UserLog> list = new ArrayList<UserLog>();

		crt.addOrder(Order.desc("log_id"));
		crt.setMaxResults(200);
		list = crt.list();
		
		return list;
	}
	

}