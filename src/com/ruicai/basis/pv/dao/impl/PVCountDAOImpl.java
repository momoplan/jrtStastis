package com.ruicai.basis.pv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Order;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.entity.PVCount;
import com.ruicai.basis.pv.dao.PVCountDAO;

public class PVCountDAOImpl extends BaseDAO implements PVCountDAO {

	private static final Log log = LogFactory.getLog(PVCountDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(PVCount transientInstance) {
		log.debug("saving PVCount instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(PVCount transientInstance) {
		log.debug("updating PVCount instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(PVCount persistentInstance) {
		log.debug("deleting PVCount instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}	
	

	public PVCount findById(java.lang.Integer id) {
		log.debug("getting PVCount instance with id: " + id);
		try {
			PVCount instance = (PVCount) getHibernateTemplate().get(
					"com.ruicai.basis.entity.PVCount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PVCount instance) {
		log.debug("finding PVCount instance by example");
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
		log.debug("finding PVCount instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PVCount as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<PVCount> findAll() {
		log.debug("finding all Thd instances");
		try {
			String queryString = "from PVCount "; 
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PVCount merge(PVCount detachedInstance) {
		log.debug("merging PVCount instance");
		try {
			PVCount result = (PVCount) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PVCount instance) {
		log.debug("attaching dirty PVCount instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PVCount instance) {
		log.debug("attaching clean PVCount instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 返回最近200条记录
	 * @return
	 * @throws Exception
	 */
	public List<PVCount> findList() throws Exception {

		Criteria crt = getSession().createCriteria(PVCount.class);
		List<PVCount> list = new ArrayList<PVCount>();

		crt.addOrder(Order.desc("id"));
		crt.setMaxResults(200);
		list = crt.list();
		
		return list;
	}
	

}