package com.ruicai.basis.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.context.ApplicationContext;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.RedTel;
import com.ruicai.basis.sys.dao.RedTelDAO;

public class RedTelDAOImpl extends BaseDAO implements RedTelDAO {

	private static final Log log = LogFactory.getLog(RedTelDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(RedTel transientInstance) {
		log.debug("saving RedTel instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	@Override
	public void delete(String ids) throws Exception {
		log.debug("deleting ids");
		
		Session session = getSession();
		Query query = session.createQuery("delete from RedTel where id in ("+ids+") ");
		query.executeUpdate();

	}

	public void delete(RedTel persistentInstance) {
		log.debug("deleting RedTel instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void update(RedTel transientInstance) {
		log.debug("updating RedTel instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public RedTel findById(java.lang.Integer id) {
		log.debug("getting RedTel instance with id: " + id);
		try {
			RedTel instance = (RedTel) getHibernateTemplate().get(
					"com.ruicai.basis.entity.RedTel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RedTel> findByExample(RedTel instance) {
		log.debug("finding RedTel instance by example");
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

	public List<RedTel> findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RedTel as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RedTel> findAll() {
		log.debug("finding all Thd instances");
		try {
			String queryString = "from User order by id desc ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RedTel merge(RedTel detachedInstance) {
		log.debug("merging User instance");
		try {
			RedTel result = (RedTel) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RedTel instance) {
		log.debug("attaching dirty RedTel instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RedTel instance) {
		log.debug("attaching clean RedTel instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RedTelDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (RedTelDAOImpl) ctx.getBean("RedTelDAOImpl");
	}
	

	/**
	 * 返回查询信息
	 * 
	 * @param Role
	 * @param rollPage
	 * @param resCount
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RedTel> findList(RedTel instance, RollPage rollPage,Integer resCount) throws Exception {

		Criteria crt = getSession().createCriteria(RedTel.class);
		List<RedTel> list = new ArrayList<RedTel>();

		if (instance != null) {
			settingRollPage(rollPage, crt, resCount);
			crt.addOrder(Order.desc("id"));
			list = crt.list();
		}

		return list;
	}

	/**
	 * 返回查询总数
	 * 
	 * @param instance
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer findListCount(RedTel instance) throws Exception {

		Criteria crt = getSession().createCriteria(RedTel.class);
		Integer resCount = 0;
		if (instance != null) {
			crt.setProjection(Projections.rowCount());
			resCount = Integer.parseInt(  crt.uniqueResult().toString());
		}

		return resCount;
	}

}