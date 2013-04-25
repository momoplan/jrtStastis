package com.ruicai.basis.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.entity.Role;
import com.ruicai.basis.sys.dao.RoleDAO;

public class RoleDAOImpl extends BaseDAO implements RoleDAO {

	private static final Log log = LogFactory.getLog(RoleDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}
	
	public Role findById(java.lang.Integer id) {
		log.debug("getting Role instance with id: " + id);
		try {
			Role instance = (Role) getHibernateTemplate().get(
					"com.ruicai.basis.entity.Role", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	

	public void save(Role transientInstance) {
		log.debug("saving Role instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}
	
	public void update(Role transientInstance) {
		log.debug("updating Role instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public List<Role> findAll() {
		log.debug("finding all Role instances");
		try {
			String queryString = "from Role Order by id desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
	public void deleteJL(String ids) throws Exception {
		log.debug("deleting ids");
		
		Session session = getSession();
		Query query = session.createQuery("delete from Role where id in ("+ids+") ");
		//query.setString("ids", ids);
		query.executeUpdate();	
		
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
	public List<Role> findList(Role instance, RollPage rollPage,Integer resCount) throws Exception {

		Criteria crt = getSession().createCriteria(Role.class);
		List<Role> list = new ArrayList<Role>();

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
	public Integer findListCount(Role instance) throws Exception {

		Criteria crt = getSession().createCriteria(Role.class);
		Integer resCount = 0;
		if (instance != null) {
			crt.setProjection(Projections.rowCount());
			resCount = Integer.parseInt( crt.uniqueResult().toString()  );
		}

		return resCount;
	}

}