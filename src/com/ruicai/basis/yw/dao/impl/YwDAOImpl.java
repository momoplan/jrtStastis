package com.ruicai.basis.yw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.ruicai.basis.entity.Yw;
import com.ruicai.basis.yw.dao.YwDAO;

public class YwDAOImpl extends BaseDAO implements YwDAO {

	private static final Log log = LogFactory.getLog(YwDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Yw transientInstance) {
		log.debug("saving Yw instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}
	
	public void update(Yw transientInstance) {
		log.debug("updating Yw instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public void deleteJL(String ids) throws Exception {
		log.debug("deleting yw channel 级联删除");
		
		Session session = getSession();
		Query query1 = session.createQuery("delete from Channel where ywid in ("+ids+")");	
		query1.executeUpdate();
		Query query2 = session.createQuery("delete from Yw where id in ("+ids+")");		
		query2.executeUpdate();
		
		/*
		Session session = getSession();
		//Transaction tx = session.beginTransaction();		
		Connection con= session.connection();
		PreparedStatement stmt1;
		stmt1 = con.prepareStatement("delete from Channel where ywid in ("+ids+")");		
		stmt1.executeQuery();
		PreparedStatement stmt2;
		stmt2 = con.prepareStatement("delete from Yw where id in ("+ids+")");		
		stmt2.executeQuery();
		//tx.commit(); 
		*/
	}

	public void delete(Yw persistentInstance) {
		log.debug("deleting Yw instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void delete(String ids) throws Exception {
		log.debug("deleting Yw ids");
		
		Session session = getSession();
		//Transaction tx = session.beginTransaction();		
		Connection con= getSession().connection();
		PreparedStatement stmt;
		stmt = con.prepareStatement("delete from Yw where id in ("+ids+")");
		stmt.executeQuery();		
		//tx.commit(); 
	}

	public Yw findById(java.lang.Integer id) {
		log.debug("getting Yw instance with id: " + id);
		try {
			Yw instance = (Yw) getHibernateTemplate().get(
					"com.ruicai.basis.entity.Yw", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Yw instance) {
		log.debug("finding Yw instance by example");
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
		log.debug("finding Yw instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Yw as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Yw> findAll() {
		log.debug("finding all Yw instances");
		try {
			String queryString = "from Yw Order by id desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Yw merge(Yw detachedInstance) {
		log.debug("merging Yw instance");
		try {
			Yw result = (Yw) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Yw instance) {
		log.debug("attaching dirty Yw instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Yw instance) {
		log.debug("attaching clean Yw instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static YwDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (YwDAOImpl) ctx.getBean("YwDAOImpl");
	}

	/**
	 * 返回查询信息
	 * 
	 * @param yw
	 * @param rollPage
	 * @param resCount
	 * @return
	 * @throws Exception
	 */
	public List<Yw> findList(Yw yw, RollPage rollPage,
			Integer resCount) throws Exception {

		Criteria crt = getSession().createCriteria(Yw.class);
		List<Yw> list = new ArrayList<Yw>();

		if (yw != null) {

			settingRollPage(rollPage, crt, resCount);
			crt.addOrder(Order.desc("id"));
			list = crt.list();
		}

		return list;
	}

	/**
	 * 返回查询总数
	 * 
	 * @param yw
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(Yw yw) throws Exception {

		Criteria crt = getSession().createCriteria(Yw.class);
		Integer resCount = 0;
		if (yw != null) {
			crt.setProjection(Projections.rowCount());
			resCount = Integer.parseInt(  crt.uniqueResult().toString());
		}

		return resCount;
	}	

}