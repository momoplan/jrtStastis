package com.ruicai.basis.sys.dao.impl;

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

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.sys.dao.UserDAO;

public class UserDAOImpl extends BaseDAO implements UserDAO {

	private static final Log log = LogFactory.getLog(UserDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	@Override
	public void deleteJL(String ids) throws Exception {
		log.debug("deleting ids");
		
		Session session = getSession();
		Query query = session.createQuery("delete from User where id in ("+ids+") ");
		//Query query = session.createQuery("delete from User where id in (:ids) ");
		//query.setString("ids", ids);
		query.executeUpdate();
		query = session.createQuery("delete from UserCfg where userid in ("+ids+")");
		query.executeUpdate();		
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void update(User transientInstance) {
		log.debug("updating User instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"com.ruicai.basis.entity.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
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

	public List<User> findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<User> findAll() {
		log.debug("finding all Thd instances");
		try {
			String queryString = "from User order by id desc ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAOImpl) ctx.getBean("UserDAOImpl");
	}

	/**
	 * 返回用户信息
	 * 
	 * @interpret ������������
	 * @param operator
	 * @return List<Operator>
	 * @throws Exception
	 */
	public List<User> findLoginInfo(User user) throws Exception {

		Criteria crt = getSession().createCriteria(User.class);

		List<User> list = new ArrayList<User>();
       
		if (user != null) {
			crt.add(Expression.eq("name", user.getName()));
			crt.add(Expression.eq("pass", user.getPass()));
			crt.addOrder(Order.desc("id"));
			list = crt.list();
		}
		
		return list;
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
	public List<User> findList(User instance, RollPage rollPage,Integer resCount) throws Exception {

		Criteria crt = getSession().createCriteria(User.class);
		List<User> list = new ArrayList<User>();

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
	public Integer findListCount(User instance) throws Exception {

		Criteria crt = getSession().createCriteria(User.class);
		Integer resCount = 0;
		if (instance != null) {
			crt.setProjection(Projections.rowCount());
			resCount =  Integer.parseInt(   crt.uniqueResult().toString());
		}

		return resCount;
	}

}