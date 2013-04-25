package com.ruicai.basis.tj.dao.impl;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.entity.PvTJ;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.tj.dao.PvTJDAO;

public class PvTJDAOImpl extends BaseDAO implements PvTJDAO {

	private static final Log log = LogFactory.getLog(PvTJDAOImpl.class);

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

	public static PvTJDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (PvTJDAOImpl) ctx.getBean("PvTJDAOImpl");
	}

	public List<PvTJ> findPvTJList_all(PvTJ pvTJ, String firsttime,	String lasttime, String orderLine, String orderRule) throws Exception {
		log.debug("findPvTJList_all ");
		
		/*
		Criteria crt = getSession().createCriteria(PvTJ.class);		
		List<PvTJ> list = new ArrayList<PvTJ>();
		if(!pvTJ.getYwid().equals("")){
		   crt.add(Expression.eq("ywid", pvTJ.getYwid()));
		}
		if(!pvTJ.getProvince().equals("")){
		   crt.add(Expression.eq("channelid", pvTJ.getChannelid()));
		}
		if(!pvTJ.getProvince().equals("")){
		   crt.add(Expression.eq("province", pvTJ.getProvince()));
		}	
		crt.add(Expression.ge("tjdate", ConvertLang.convertDateTimeStr_ymd(firsttime)));
		crt.add(Expression.lt("tjdate", ConvertLang.convertDateTimeStr_ymd(lasttime)));
		
		crt.addOrder(Order.asc("id"));
		
		list = crt.list();	

		return list;
		*/
		

		StringBuffer sql = new StringBuffer();				
		
//		sql.append("SELECT ywid,channelid,province,visitnum ,regnum,paynum,to_char(tjdate,'YYYY-MM-DD HH24:MI:SS'),uvnum ");
//		sql.append("from pvtj "); 
//		sql.append("where tjdate >= to_date('"+firsttime+"','yyyy-mm-dd') and tjdate < to_date('"+lasttime+"','yyyy-mm-dd') ");
//		if(!pvTJ.getYwid().equals("")){
//			sql.append(" and ywid = '"+ pvTJ.getYwid()+"' ");
//		}
//        if(!pvTJ.getChannelid().equals("")){
//        	sql.append(" and channelid = '"+ pvTJ.getChannelid()+"' ");
//		}
//        if(!pvTJ.getProvince().equals("")){
//        	sql.append(" and province = '"+ pvTJ.getProvince()+"' ");
//		}
        
		sql.append("select p.province,p.visitnum ,p.regnum,p.paynum,to_char(p.tjdate,'YYYY-MM-DD HH24:MI:SS'),p.uvnum,a.ccode,a.ywcode,a.cname,a.ywname from pvtj p left join ( select c.id ccode, yw.id ywcode, c.name cname, yw.name ywname from channel c left join yw yw on c.ywid = yw.id ) a on (p.ywid = a.ywcode and p.channelid = a.ccode) ");
		sql.append("where to_char(p.tjdate,'YYYY-MM-DD') >= '"+firsttime+"' and p.tjdate<to_timestamp('"+lasttime+"','yyyy-mm-dd') ");
		if(!pvTJ.getYwid().equals("")){
			sql.append(" and p.ywid = '"+ pvTJ.getYwid()+"' ");
		}
        if(!pvTJ.getChannelid().equals("")){
        	sql.append(" and p.channelid = '"+ pvTJ.getChannelid()+"' ");
		}
        if(!pvTJ.getProvince().equals("")){
        	sql.append(" and p.province = '"+ pvTJ.getProvince()+"' ");
		}
		
        sql.append("order by p.tjdate desc,p."+orderLine+" "+orderRule+" ");
		//sql.append("group by ywid,channelid,province ");
		String pvuvSql = sql.toString();
		List<Object[]> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).list();
		} catch (RuntimeException re) {
			log.error("findPvTJList_all failed", re);
			throw re;
		}
	    
	    List<PvTJ> list = new Vector<PvTJ>();
        for(Object[] datas :result){
        	if(datas[9]==null || datas[8]==null || datas[0]==null || datas[1]==null || datas[2]==null || datas[3]==null || datas[4]==null || datas[5]==null) {
        		continue;
        	}
        	if(new Integer(datas[1].toString().trim()).intValue() == 0 && (new Integer(datas[2].toString().trim()).intValue() == 0) && (new Integer(datas[3].toString().trim()).intValue() == 0) && (new Integer(datas[5].toString().trim()).intValue() == 0)) {
        		continue;
        	}
        	PvTJ model = new PvTJ();        	
        	model.setYwid(datas[9]==null?"":datas[9].toString());
        	model.setChannelid(datas[8]==null?"":datas[8].toString());
        	model.setProvince(datas[0]==null?"":datas[0].toString());
        	model.setVisitnum(new Integer(datas[1]==null?"":datas[1].toString().trim()));        	
        	model.setRegnum(new Integer(datas[2]==null?"":datas[2].toString().trim()));
        	model.setPaynum(new Integer(datas[3]==null?"":datas[3].toString().trim()));
        	model.setPercent((float) (model.getRegnum() * 100) / (model.getVisitnum()));
        	model.setTjdate(datas[4]==null?"":datas[4].toString());
        	model.setUvnum(new Integer(datas[5]==null?"":datas[5].toString().trim()));
        	list.add(model);
        }		
		return list;
			
	}
	
	public List<PvTJ> findPvTJList_hezuo(User user, String firsttime, String lasttime) throws Exception {
        
		StringBuffer sql = new StringBuffer();				
		
		sql.append("select c.ywid,c.channelid,c.province,c.visitnum ,c.regnum,c.paynum,to_char(c.tjdate,'YYYY-MM-DD HH24:MI:SS') ");
		sql.append("from pvtj c , ");
		sql.append("  (select a.id as id ,b.id as ywcode, a.id as channelcode ");
		sql.append(" from ");
		sql.append("  (select * from channel where id in (select e.channelid from tusercfg e where e.userid = "+user.getId()+" )) a ");
		sql.append("left join ");
		sql.append("  yw b  ");
		sql.append("on a.ywid = b.id) d ");
		sql.append("where ");
		sql.append("  c.ywid = d.ywcode and c.channelid = d.channelcode ");
		sql.append("  and c.tjdate >= to_date('"+firsttime+"','yyyy-mm-dd') and c.tjdate < to_date('"+lasttime+"','yyyy-mm-dd') ");
		sql.append("  order by c.tjdate desc,c.ywid desc,c.channelid desc ");
		
		//sql.append("  group by c.ywid,c.channelid,c.province ");
		
		List<Object[]> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).list();
		} catch (RuntimeException re) {
			log.error("findPvTJList_all failed", re);
			throw re;
		}
	    
	    List<PvTJ> list = new Vector<PvTJ>();
        for(Object[] datas :result){
        	
        	PvTJ model = new PvTJ();    
        	model.setYwid(datas[0].toString());
        	model.setChannelid(datas[1].toString());
        	model.setProvince(datas[2].toString());
        	model.setVisitnum(new Integer(datas[3].toString().trim()));        	
        	model.setRegnum(new Integer(datas[4].toString().trim()));
        	model.setPaynum(new Integer(datas[5].toString().trim()));
        	model.setPercent((float) (model.getRegnum() * 100) / (model.getVisitnum()));
        	model.setTjdate(datas[6].toString().trim());
        	        	
        	list.add(model);
        }		
		
		return list;
	}
	
	
}
