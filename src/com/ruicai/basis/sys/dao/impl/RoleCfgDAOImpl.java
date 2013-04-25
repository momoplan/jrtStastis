package com.ruicai.basis.sys.dao.impl;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.entity.Menu;
import com.ruicai.basis.entity.RoleCfg;
import com.ruicai.basis.entity.RoleMenu;
import com.ruicai.basis.sys.dao.RoleCfgDAO;


public class RoleCfgDAOImpl extends BaseDAO implements RoleCfgDAO {

	private static final Log log = LogFactory.getLog(RoleCfgDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}	
	
	public void save(RoleCfg transientInstance) {
		log.debug("saving Role_Cfg instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}
	
	public void deleteJL(String roleids) throws Exception {
		log.debug("deleting ids");
		
		Session session = getSession();
		Query query = session.createQuery("delete from RoleCfg where roleid in ("+roleids+") ");
		//query.setString("ids", ids);
		query.executeUpdate();	
		
	}
	
	public List<RoleMenu> findRoleMenu(Integer roleid) {
        log.debug("findRoleMenu ");		
		StringBuffer sql = new StringBuffer();				
		
		sql.append("select a.id,a.name,a.url,a.fid,a.ascid,a.status,a.bz,b.menuid from menu a left join ");				
		sql.append("(select c.* from rolecfg c where c.roleid = "+roleid+") b on a.id = b.menuid ");			
		sql.append("order by a.ascid asc ");		
		
		List<Object[]> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).list();
		} catch (RuntimeException re) {
			log.error("findBuyTJList_all failed", re);
			throw re;
		}
	    
	    List<RoleMenu> list = new Vector<RoleMenu>();
        for(Object[] datas :result){
        	
        	RoleMenu model = new RoleMenu();         	
        	
        	model.setId(new Integer(datas[0].toString().trim()));
        	if(datas[1]!=null){
        	    model.setName(datas[1].toString());
        	}else{
        		model.setName("");
        	}
        	
        	if(datas[2]!=null){
        		model.setUrl(datas[2].toString());
        	}else{
        		model.setUrl("");
        	}
        	
        	
        	model.setFid(new Integer(datas[3].toString().trim()));
        	model.setAscid(new Integer(datas[4].toString().trim()));
        	model.setStatus(new Integer(datas[5].toString().trim()));
        	
        	if(datas[6]!=null){
        	    model.setBz(datas[6].toString().trim());
        	}else{
        		model.setBz("");
        	}      	
        	
        	if(datas[7]==null){
        		model.setBool("false");
        	}else{
        		model.setBool("true");
        	}      	
        	
        	list.add(model);
        	
        }		
		
		return list;		
	}
	
	/**
	 * 用户所属菜单查询
	 */
	public List<Menu> findUserMenu(Integer roleid) {
		log.debug("finding UserMenu instances");
		try {
			
			StringBuffer sql = new StringBuffer();				
			sql.append("select a.* from menu a ,(select c.* from rolecfg c where c.roleid = "+roleid+" ) b ");			
			sql.append("where a.id = b.menuid order by a.fid asc ,a.ascid asc ");
			
			return getSession().createSQLQuery(sql.toString()).addEntity(Menu.class).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 用户所属菜单查询
	 * @param roleid
	 * @param fid
	 * @return
	 */
	public List<Menu> findUserMenu(Integer roleid,Integer fid) {
		log.debug("finding UserMenu instances");
		try {
			
			StringBuffer sql = new StringBuffer();				
			sql.append("select a.* from menu a ,(select c.* from rolecfg c where c.roleid = "+roleid+" ) b ");			
			sql.append("where a.id = b.menuid and a.fid = "+fid+" order by a.fid asc ,a.ascid asc ");
			
			return getSession().createSQLQuery(sql.toString()).addEntity(Menu.class).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

}