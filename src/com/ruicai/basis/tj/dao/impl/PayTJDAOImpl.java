package com.ruicai.basis.tj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.entity.PayTJ;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.tj.dao.PayTJDAO;

public class PayTJDAOImpl extends BaseDAO implements PayTJDAO{

	private static final Log log = LogFactory.getLog(PayTJDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(PayTJ transientInstance) {
		log.debug("saving PayTJ instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PayTJ persistentInstance) {
		log.debug("deleting PayTJ instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PayTJ findById(Integer id) {
		log.debug("getting PayTJ instance with id: " + id);
		try {
			PayTJ instance = (PayTJ) getHibernateTemplate().get(
					"com.ruicai.basis.entity.PvTJ", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PayTJ instance) {
		log.debug("finding PayTJ instance by example");
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
		log.debug("finding PayTJ instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PayTJ as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all PayTJ instances");
		try {
			String queryString = "from PayTJ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PayTJ merge(PayTJ detachedInstance) {
		log.debug("merging PayTJ instance");
		try {
			PayTJ result = (PayTJ) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty PayTJ instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PayTJ instance) {
		log.debug("attaching clean PayTJ instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PayTJDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (PayTJDAOImpl) ctx.getBean("PvTJDAOImpl");
	}
	
	public List<PayTJ> findPayTJList_all(PayTJ payTJ,String firsttime,String lasttime) {
		Connection conn = getSession().connection();

		List list_ = new ArrayList();
		
		List<PayTJ> list = new ArrayList<PayTJ>();
		
		PayTJ payTJ_ = null;

		RowSetDynaClass result = null;
		PreparedStatement stat;
		if (payTJ != null) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT a.ywname,a.cname,p.paytype,p.usernum ,p.paymoney,to_char(p.tjdate,'YYYY-MM-DD HH24:MI:SS') as tjdate ");				
				sql.append("from paytj p ");
				sql.append("left join ( select c.id ccode, yw.id ywcode, c.name cname, yw.name ywname from channel c left join yw yw on c.ywid = yw.id ) a ");
				sql.append("on (p.ywid = a.ywcode and p.channelid = a.ccode) ");
				sql.append("where tjdate >= to_date('"+firsttime+"','yyyy-mm-dd') and tjdate < to_date('"+lasttime+"','yyyy-mm-dd')  ");
				if(!payTJ.getYwid().equals("")){
					sql.append(" and p.ywid = '"+ payTJ.getYwid()+"' ");
				}
                if(!payTJ.getChannelid().equals("")){
                	sql.append(" and p.channelid = '"+ payTJ.getChannelid()+"' ");
				}
                sql.append("order by tjdate desc,ywname desc,cname desc ");

                
                log.info("sql:"+sql.toString());
				stat = conn.prepareStatement(sql.toString());

				ResultSet res = stat.executeQuery();

				result = new RowSetDynaClass(res);

				list_ = result.getRows();
				
				for(int i=0;i<list_.size();i++){

					payTJ_ = new PayTJ();
					
					BasicDynaBean basicdynaBean = (BasicDynaBean)list_.get(i);
					if(basicdynaBean.get("usernum")==null || (basicdynaBean.get("paymoney")==null)) {
						continue;
					}
					if(new Integer(basicdynaBean.get("usernum").toString().trim()).intValue()==0 && (new Float(basicdynaBean.get("paymoney").toString().trim()).floatValue()==0.0f)) {
						continue;
					}

					payTJ_.setYwid(basicdynaBean.get("ywname").toString());
					payTJ_.setChannelid(basicdynaBean.get("cname").toString());
					payTJ_.setPaytype(basicdynaBean.get("paytype").toString());
					
					payTJ_.setUsernum(new Integer(basicdynaBean.get("usernum").toString().trim()));
					payTJ_.setPaymoney(new Float(basicdynaBean.get("paymoney").toString().trim()));
					payTJ_.setTjdate(basicdynaBean.get("tjdate").toString());
					
					list.add(payTJ_);
				}

			} catch (Exception e) {
				log.error("findPayTJList_all Error", e);
			}
		}

		return list;
	}

	public List<PayTJ> findPayTJList_allGroupByChannel(PayTJ payTJ,
			String firsttime, String lasttime) throws Exception {
		Connection conn = getSession().connection();

		List list_ = new ArrayList();
		
		List<PayTJ> list = new ArrayList<PayTJ>();
		
		PayTJ payTJ_ = null;

		RowSetDynaClass result = null;
		PreparedStatement stat;
		if (payTJ != null) {
			try {
				StringBuffer sql = new StringBuffer();
				
				sql.append("SELECT a.ywname ywname,a.cname cname,sum(p.usernum) usernum,sum(p.paymoney) paymoney ");				
				sql.append("from paytj p ");	
				sql.append("left join ( select c.id ccode, yw.id ywcode, c.name cname, yw.name ywname from channel c left join yw yw on c.ywid = yw.id ) a ");
				sql.append("on (p.ywid = a.ywcode and p.channelid = a.ccode) ");
				sql.append("where p.tjdate >= to_date('"+firsttime+"','yyyy-mm-dd') and p.tjdate < to_date('"+lasttime+"','yyyy-mm-dd')  ");
				if(!payTJ.getYwid().equals("")){
					sql.append(" and p.ywid = '"+ payTJ.getYwid()+"' ");
				}
                if(!payTJ.getChannelid().equals("")){
                	sql.append(" and p.channelid = '"+ payTJ.getChannelid()+"' ");
				}
                sql.append("group by ywname,cname  order by ywname desc,cname desc ");
				
                log.info("sql:"+sql.toString());
                
				stat = conn.prepareStatement(sql.toString());

				ResultSet res = stat.executeQuery();

				result = new RowSetDynaClass(res);

				list_ = result.getRows();
				
				for(int i=0;i<list_.size();i++){

					payTJ_ = new PayTJ();
					
					BasicDynaBean basicdynaBean = (BasicDynaBean)list_.get(i);
					if(basicdynaBean.get("usernum")==null || (basicdynaBean.get("paymoney")==null)) {
						continue;
					}
					if(new Integer(basicdynaBean.get("usernum").toString().trim()).intValue()==0 && (new Float(basicdynaBean.get("paymoney").toString().trim()).floatValue()==0.0f)) {
						continue;
					}

					payTJ_.setYwid((String)basicdynaBean.get("ywname"));
					payTJ_.setChannelid((String)basicdynaBean.get("cname"));
					
					payTJ_.setUsernum(new Integer(basicdynaBean.get("usernum").toString().trim()));
					payTJ_.setPaymoney(new Float(basicdynaBean.get("paymoney").toString().trim()));
					
					list.add(payTJ_);
				}
			} catch (Exception e) {
				log.error("findPayTJList_allGroupByChannel Error", e);
			}
		}

		return list;
	}
	
	@Override
	public List<PayTJ> findPayTJList_allGroupByChannels(List<Integer> channels,
			String beginTime, String endTime) throws Exception {
		Connection conn = getSession().connection();

		List list_ = new ArrayList();
		
		List<PayTJ> list = new ArrayList<PayTJ>();
		
		PayTJ payTJ_ = null;

		RowSetDynaClass result = null;
		PreparedStatement stat;
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT a.ywname,a.cname,p.usernum ,paymoney,p.paytype,to_char(p.tjdate,'YYYY-MM-DD') as tjdate ");		
				sql.append("from paytj p ");	
				sql.append("left join ( select c.id ccode, yw.id ywcode, c.name cname, yw.name ywname from channel c left join yw yw on c.ywid = yw.id ) a ");
				sql.append("on (p.ywid = a.ywcode and p.channelid = a.ccode) where");
				if(beginTime!=null){
					sql.append(" p.tjdate >= to_date('"+beginTime+"','yyyy-mm-dd') and");
				}
				if(endTime!=null){
					sql.append(" p.tjdate < to_date('"+endTime+"','yyyy-mm-dd') and ");
				}
				sql.append(" p.channelid in ( ");
                for(Integer channel:channels){
                	sql.append(channel+",");
                }	
                sql.deleteCharAt(sql.length()-1);
                sql.append(") ");
                sql.append("order by tjdate desc,ywname desc,cname desc ");
				
                log.info("sql:"+sql.toString());
                
				stat = conn.prepareStatement(sql.toString());

				ResultSet res = stat.executeQuery();

				result = new RowSetDynaClass(res);

				list_ = result.getRows();
				
				for(int i=0;i<list_.size();i++){

					payTJ_ = new PayTJ();
					
					BasicDynaBean basicdynaBean = (BasicDynaBean)list_.get(i);
					if(basicdynaBean.get("usernum")==null || (basicdynaBean.get("paymoney")==null)) {
						continue;
					}
					if(new Integer(basicdynaBean.get("usernum").toString().trim()).intValue()==0 && (new Float(basicdynaBean.get("paymoney").toString().trim()).floatValue()==0.0f)) {
						continue;
					}

					payTJ_.setYwid((String)basicdynaBean.get("ywname"));
					payTJ_.setChannelid((String)basicdynaBean.get("cname"));
					
					payTJ_.setUsernum(new Integer(basicdynaBean.get("usernum").toString().trim()));
					payTJ_.setPaymoney(new Float(basicdynaBean.get("paymoney").toString().trim()));

					payTJ_.setPaytype(basicdynaBean.get("paytype").toString());
					payTJ_.setTjdate(basicdynaBean.get("tjdate").toString());
					list.add(payTJ_);
				}
			} catch (Exception e) {
				log.error("findPayTJList_allGroupByChannel Error", e);
			}

		return list;
	}

	@Override
	public List findFailedPayTJList_all(String firsttime, String lasttime)
			throws Exception {
		
		Connection conn = getSession().connection();
		
		List list = new ArrayList();
		
		RowSetDynaClass result = null;
		PreparedStatement stat;
		try {
		/*			
  			sql:查询充值方式为银行卡充值、平台卡充值和点卡充值的失败交易记录的手机号、充值金额、交易时间和充值方式   去掉 时间段内同一用户充值成功的记录
			select * from (
			select t.id id,u.mobileid mobileid,u.userno userno,t.amt / 100 amt,to_char(t.plattime,'yyyy-MM-dd hh24:mi:ss') plattime,f.platformname platformname 
			         from jrtsch.ttransaction t,jrtsch.tplatform f,jrtsch.tuserinfo u,jrtstatis.channel c 
			         where to_char(t.plattime,'yyyy-MM-dd')<'2011-02-17' and to_char(t.plattime,'yyyy-MM-dd')>='2011-01-15' 
			         and t.bankid=f.platformid(+) and t.userno=u.userno and t.type in (2,3,10) and t.state=2 and u.channel=to_char(c.id)
			) a where exists (
			  select * from (
			    select tt.userno from jrtsch.ttransaction tt,jrtsch.tuserinfo tu where tt.state = 1 and tt.type in (2,3,10) 
			    and to_char(tt.plattime,'yyyy-MM-dd')<'2011-02-17' and to_char(tt.plattime,'yyyy-MM-dd')>='2011-01-15'and  tt.userno = tu.userno 
			  ) b where a.userno = b.userno
		    )

		*/
			StringBuffer sql = new StringBuffer();
			sql.append("select * from ( ");
			sql.append("select t.id id,u.mobileid mobileid,u.userno userno,t.amt / 100 amt,to_char(t.plattime,'yyyy-MM-dd hh24:mi:ss') plattime,f.platformname platformname ");
			sql.append("from jrtsch.ttransaction t,jrtsch.tplatform f,jrtsch.tuserinfo u,jrtstatis.channel c ");
			sql.append("where to_char(t.plattime,'yyyy-MM-dd')<'"+lasttime+"' and to_char(t.plattime,'yyyy-MM-dd')>='"+firsttime+"' ");
			sql.append("and t.bankid=f.platformid(+) and t.userno=u.userno and t.type in (2,3,10) and t.state=2 and u.channel=to_char(c.id) ");
			sql.append(") a where not exists ( ");
			sql.append("select * from ( ");
			sql.append("select tt.userno from jrtsch.ttransaction tt,jrtsch.tuserinfo tu where tt.state = 1 and tt.type in (2,3,10) ");
			sql.append("and to_char(tt.plattime,'yyyy-MM-dd')<'"+lasttime+"' and to_char(tt.plattime,'yyyy-MM-dd')>='"+firsttime+"'and  tt.userno = tu.userno ");
			sql.append(") b where a.userno = b.userno ");
			sql.append(" )");
           
            stat = conn.prepareStatement(sql.toString());
			ResultSet res = stat.executeQuery();

			result = new RowSetDynaClass(res);

			list = result.getRows();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("findFailedPayTJList_all Error", e);
		}

		return list;
	}

}
