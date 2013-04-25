package com.ruicai.basis.channel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.context.ApplicationContext;

import com.ruicai.basis.channel.dao.UserCfgDAO;
import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.UserCfg; 

public class UserCfgDAOImpl extends BaseDAO implements UserCfgDAO {

	private static final Log log = LogFactory.getLog(UserCfgDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(UserCfg transientInstance) {
		log.debug("saving UserCfg instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void update(UserCfg transientInstance) {
		log.debug("updating UserCfg instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(UserCfg persistentInstance) {
		log.debug("deleting UserCfg instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(String ids) throws Exception {
		log.debug("deleting UserCfg ids");
		
		Session session = getSession();
		Query query = session.createQuery("delete from UserCfg where id in ("+ids+") ");
		query.executeUpdate();		
	}

	public UserCfg findById(java.lang.Integer id) {
		log.debug("getting UserCfg instance with id: " + id);
		try {
			UserCfg instance = (UserCfg) getHibernateTemplate().get(
					"com.ruicai.basis.entity.UserCfg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}	

	public List findByExample(UserCfg instance) {
		log.debug("finding UserCfg instance by example");
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
		log.debug("finding UserCfg instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserCfg as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all UserCfg instances");
		try {
			String queryString = "from UserCfg Order by id desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserCfg merge(UserCfg detachedInstance) {
		log.debug("merging Channel instance");
		try {
			UserCfg result = (UserCfg) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserCfg instance) {
		log.debug("attaching dirty UserCfg instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserCfg instance) {
		log.debug("attaching clean UserCfg instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserCfgDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (UserCfgDAOImpl) ctx.getBean("ChannelDAOImpl");
	}

	/**
	 * 返回查询信息
	 * 
	 * @param Channel
	 * @param rollPage
	 * @param resCount
	 * @return
	 * @throws Exception
	 */
	public List<UserCfg> findList(UserCfg instance, RollPage rollPage,
			Integer resCount) throws Exception {

		Criteria crt = getSession().createCriteria(UserCfg.class);
		List<UserCfg> list = new ArrayList<UserCfg>();

		if (instance != null) {

			settingRollPage(rollPage, crt, resCount);
			crt.addOrder(Order.desc("id"));
			if(instance.getUser().getId() != 0){
				crt.add(Expression.eq("user",instance.getUser()));			
			}
			if(instance.getChannel().getId() != 0){
				crt.add(Expression.eq("channel",instance.getChannel()));
			}
			list = crt.list();
		}

		return list;
	}

	/**
	 * 返回查询总数
	 * 
	 * @param Channel
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(UserCfg instance) throws Exception {

		Criteria crt = getSession().createCriteria(UserCfg.class);
		Integer resCount = 0;
		if (instance != null) {
			if(instance.getUser().getId() != 0){
				crt.add(Expression.eq("user",instance.getUser()));			
			}
			if(instance.getChannel().getId() != 0){
				crt.add(Expression.eq("channel",instance.getChannel()));
			}
			crt.setProjection(Projections.rowCount());
			resCount = Integer.parseInt( crt.uniqueResult().toString());
		}

		return resCount;
	}

}