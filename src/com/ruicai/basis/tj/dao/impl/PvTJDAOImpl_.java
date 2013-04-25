package com.ruicai.basis.tj.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.context.ApplicationContext;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.common.ConvertLang;
import com.ruicai.basis.entity.PvTJ;
import com.ruicai.basis.entity.User;


public class PvTJDAOImpl_ extends BaseDAO {

	private static final Log log = LogFactory.getLog(PvTJDAOImpl_.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(PvTJ transientInstance) {
		log.debug("saving PvTJ instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PvTJ persistentInstance) {
		log.debug("deleting PvTJ instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PvTJ findById(Integer id) {
		log.debug("getting PvTJ instance with id: " + id);
		try {
			PvTJ instance = (PvTJ) getHibernateTemplate().get(
					"com.ruicai.basis.entity.PvTJ", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PvTJ instance) {
		log.debug("finding PvTJ instance by example");
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
		log.debug("finding PvTJ instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PvTJ as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all PvTJ instances");
		try {
			String queryString = "from PvTJ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PvTJ merge(PvTJ detachedInstance) {
		log.debug("merging PvTJ instance");
		try {
			PvTJ result = (PvTJ) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty PvTJ instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PvTJ instance) {
		log.debug("attaching clean PvTJ instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PvTJDAOImpl_ getFromApplicationContext(ApplicationContext ctx) {
		return (PvTJDAOImpl_) ctx.getBean("PvTJDAOImpl");
	}

	// t
	public List<PvTJ> pvTJList(PvTJ pvTJ, String firsttime, String lasttime){
		
		log.debug("finding all PvTJ instances");
		try {
			String queryString = "from PvTJ";
			List list1 = getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		
		List<PvTJ> list = new ArrayList<PvTJ>();
		return list;
	}
	public List<PvTJ> findPvTJList_all(PvTJ pvTJ, String firsttime,	String lasttime) throws Exception{
		
		Criteria crt = getSession().createCriteria(PvTJ.class);		
		List<PvTJ> list = new ArrayList<PvTJ>();
		
		//crt.add(Expression.eq("ywid", pvTJ.getYwid()));
		//crt.add(Expression.eq("channelid", pvTJ.getChannelid()));
		//crt.add(Expression.eq("province", pvTJ.getProvince()));
		crt.add(Expression.ge("tjdate", ConvertLang.convertDateTimeStr_ymd(firsttime)));
		crt.add(Expression.lt("tjdate", ConvertLang.convertDateTimeStr_ymd(lasttime)));
		
		crt.addOrder(Order.asc("id"));
		
		list = crt.list();	

		return list;
		
		/*
		Connection conn = getSession().connection();

		List list_ = new ArrayList();

		List<PvTJ> list = new ArrayList<PvTJ>();

		PvTJ pvTJ_ = null;

		RowSetDynaClass result = null;
		PreparedStatement stat;
		if (pvTJ != null) {
			try {
				StringBuffer sql = new StringBuffer();
				
				
				//sql.append("SELECT ywid,channelid,province,sum(visitnum) as	visitnum ,sum(regnum) as regnum,sum(paynum) as paynum ");
				sql.append("SELECT ywid,channelid,province,visitnum ,regnum,paynum ");
				sql.append("from pvtj "); 
				sql.append("where tjdate >= to_date('"+firsttime+"','yyyy-mm-dd') and tjdate < to_date('"+lasttime+"','yyyy-mm-dd') ");
				if(!pvTJ.getYwid().equals("")){
					sql.append(" and ywid = '"+ pvTJ.getYwid()+"' ");
				}
                if(!pvTJ.getChannelid().equals("")){
                	sql.append(" and channelid = '"+ pvTJ.getChannelid()+"' ");
				}
                if(!pvTJ.getProvince().equals("")){
                	sql.append(" and province = '"+ pvTJ.getProvince()+"' ");
				}
				//sql.append("group by ywid,channelid,province ");
				//System.out.println(sql.toString());
				stat = conn.prepareStatement(sql.toString());

				ResultSet res = stat.executeQuery();

				result = new RowSetDynaClass(res);

				list_ = result.getRows();

				for (int i = 0; i < list_.size(); i++) {

					pvTJ_ = new PvTJ();

					BasicDynaBean basicdynaBean = (BasicDynaBean) list_.get(i);

					pvTJ_.setYwid(basicdynaBean.get("ywid").toString());
					pvTJ_.setChannelid(basicdynaBean.get("channelid")
							.toString());
					pvTJ_.setProvince(basicdynaBean.get("province").toString());

					pvTJ_.setVisitnum(new Integer(basicdynaBean.get("visitnum")
							.toString().trim()));
					pvTJ_.setRegnum(new Integer(basicdynaBean.get("regnum")
							.toString().trim()));
					pvTJ_.setPaynum(new Integer(basicdynaBean.get("paynum")
							.toString().trim()));
					pvTJ_.setPercent((float) (pvTJ_.getRegnum() * 100)
							/ (pvTJ_.getVisitnum()));

					list.add(pvTJ_);
				}

			} catch (Exception e) {
				log.error("findPvTJList Error", e);
			}
		}

		return list;
		
		*/
	}
	
}
