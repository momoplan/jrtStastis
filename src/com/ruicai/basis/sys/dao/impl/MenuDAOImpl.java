package com.ruicai.basis.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.Menu;
import com.ruicai.basis.entity.RoleCfg;
import com.ruicai.basis.sys.dao.MenuDAO;

public class MenuDAOImpl extends BaseDAO implements MenuDAO {

	private static final Log log = LogFactory.getLog(MenuDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Menu transientInstance) {
		log.debug("saving Menu instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Menu transientInstance) {
		log.debug("updating Menu instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(Menu persistentInstance) {
		log.debug("deleting Menu instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void delete(String ids) throws Exception {
		log.debug("deleting Menu ids");
		
		Session session = getSession();
		Query query = session.createQuery("delete from Menu where id in ("+ids+") ");
		query.executeUpdate();		
	}

	public Menu findById(java.lang.Integer id) {
		log.debug("getting Menu instance with id: " + id);
		try {
			Menu instance = (Menu) getHibernateTemplate().get(
					"com.ruicai.basis.entity.Menu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Menu instance) {
		log.debug("finding Menu instance by example");
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
		log.debug("finding Menu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Menu as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Menu> findAll() {
		log.debug("finding all Thd instances");
		try {
			String queryString = "from Menu order by FID asc ,ASCID asc"; 
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Menu merge(Menu detachedInstance) {
		log.debug("merging User instance");
		try {
			Menu result = (Menu) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Menu instance) {
		log.debug("attaching dirty Menu instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Menu instance) {
		log.debug("attaching clean Menu instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MenuDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (MenuDAOImpl) ctx.getBean("UserDAOImpl");
	}

	/**
	 * 返回菜单查询信息
	 * 
	 * @param menu
	 * @param rollPage
	 * @param resCount
	 * @return
	 * @throws Exception
	 */
	public List<Menu> findMenuList(Menu menu, RollPage rollPage,
			Integer resCount) throws Exception {

		Criteria crt = getSession().createCriteria(Menu.class);
		List<Menu> list = new ArrayList<Menu>();

		if (menu != null) {

			settingRollPage(rollPage, crt, resCount);
			crt.addOrder(Order.asc("id"));
			list = crt.list();
		}

		return list;
	}

	/**
	 * 返回菜单查询总数
	 * 
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	public Integer findMenuListCount(Menu menu) throws Exception {

		Criteria crt = getSession().createCriteria(Menu.class);
		Integer resCount = 0;
		if (menu != null) {
			crt.setProjection(Projections.rowCount());
			resCount = Integer.parseInt(crt.uniqueResult().toString() ) ;
		}

		return resCount;
	}

	/**
	 * 返回MaxID
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer getMaxID() throws Exception {
		Criteria crt = getSession().createCriteria(Menu.class);
		Integer resCount = 0;

		resCount = (Integer) crt.setProjection(
				Projections.projectionList().add(Projections.max("id")))
				.uniqueResult();

		return resCount + 1;
	}	

}