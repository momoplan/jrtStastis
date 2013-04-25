package com.ruicai.basis.tj.dao.impl;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.tj.dao.BuyTJDAO;

public class BuyTJDAOImpl extends BaseDAO implements BuyTJDAO{

	private static final Log log = LogFactory.getLog(BuyTJDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(BuyTJ transientInstance) {
		log.debug("saving BuyTJ instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BuyTJ persistentInstance) {
		log.debug("deleting BuyTJ instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BuyTJ findById(Integer id) {
		log.debug("getting BuyTJ instance with id: " + id);
		try {
			BuyTJ instance = (BuyTJ) getHibernateTemplate().get(
					"com.ruicai.basis.entity.BuyTJ", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BuyTJ instance) {
		log.debug("finding BuyTJ instance by example");
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
		log.debug("finding BuyTJ instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BuyTJ as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all BuyTJ instances");
		try {
			String queryString = "from BuyTJ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BuyTJ merge(BuyTJ detachedInstance) {
		log.debug("merging PayTJ instance");
		try {
			BuyTJ result = (BuyTJ) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BuyTJ instance) {
		log.debug("attaching dirty BuyTJ instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BuyTJ instance) {
		log.debug("attaching clean BuyTJ instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BuyTJDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (BuyTJDAOImpl) ctx.getBean("BuyTJDAOImpl");
	}
	
	public List<BuyTJ> findBuyTJList_all(BuyTJ buyTJ,String firsttime,String lasttime) {
        log.debug("findPvTJList_all ");
		
		StringBuffer sql = new StringBuffer();				
		
		sql.append("SELECT ywid,channelid,buytype,usernum ,buymoney,to_char(tjdate,'YYYY-MM-DD HH24:MI:SS') ");				
		sql.append("from buytj ");				
		sql.append("where tjdate >= to_date('"+firsttime+"','yyyy-mm-dd') and tjdate < to_timestamp('"+lasttime+"','yyyy-mm-dd')  ");
		if(!buyTJ.getYwid().equals("")){
			sql.append(" and ywid = '"+ buyTJ.getYwid()+"' ");
		}
        if(!buyTJ.getChannelid().equals("")){
        	sql.append(" and channelid = '"+ buyTJ.getChannelid()+"' ");
		}
        sql.append("order by tjdate desc,ywid desc,channelid desc ");
		//sql.append("group by ywid,channelid,buytype ");		
		
		List<Object[]> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).list();
		} catch (RuntimeException re) {
			log.error("findBuyTJList_all failed", re);
			throw re;
		}
	    
	    List<BuyTJ> list = new Vector<BuyTJ>();
        for(Object[] datas :result){
        	if(new Float(datas[4].toString().trim()).floatValue()==0.0f && (new Integer(datas[3].toString().trim()).intValue()==0)) {
        		continue;
        	}
        	BuyTJ model = new BuyTJ();         	
        	model.setYwid(datas[0].toString());
        	model.setChannelid(datas[1].toString());
        	model.setBuytype(datas[2].toString());			
        	model.setUsernum(new Integer(datas[3].toString().trim()));
        	model.setBuymoney(new Float(datas[4].toString().trim())); 
        	model.setTjdate(datas[5].toString().trim());
        	
        	list.add(model);
        }		
		
		return list;		
	}
	
	public List<BuyTJ> findBuyTJList_hezuo(User user, String firsttime, String lasttime) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select c.ywid,c.channelid,c.buytype ,c.usernum,c.buymoney,to_char(c.tjdate,'YYYY-MM-DD HH24:MI:SS') ");
		sql.append("from buytj c , ");
		sql.append("  (select a.id as id ,b.id as ywcode, a.id as channelcode ");
		sql.append(" from ");
		sql.append("  (select * from channel where id in (select e.channelid from tusercfg e where e.userid = "+user.getId()+" )) a ");
		sql.append("left join ");
		sql.append("  yw b  ");
		sql.append("on a.ywid = b.id) d ");
		sql.append("where ");
		sql.append("  to_char(c.ywid) = to_char(d.ywcode) and to_char(c.channelid) = to_char(d.channelcode) ");
		sql.append("  and c.tjdate >= to_date('"+firsttime+"','yyyy-mm-dd') and c.tjdate < to_date('"+lasttime+"','yyyy-mm-dd') ");
		sql.append("  order by c.tjdate desc,c.ywid desc,c.channelid desc ");
		
		List<Object[]> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).list();
		} catch (RuntimeException re) {
			log.error("findBuyTJList_hezuo failed", re);
			throw re;
		}
	    
	    List<BuyTJ> list = new Vector<BuyTJ>();
        for(Object[] datas :result){
        	
        	BuyTJ model = new BuyTJ();         	
        	model.setYwid(datas[0].toString());
        	model.setChannelid(datas[1].toString());
        	model.setBuytype(datas[2].toString());			
        	model.setUsernum(new Integer(datas[3].toString().trim()));
        	model.setBuymoney(new Float(datas[4].toString().trim())); 
        	model.setTjdate(datas[5].toString().trim());
        	
        	list.add(model);
        }		
		
		return list;		
	}
}
