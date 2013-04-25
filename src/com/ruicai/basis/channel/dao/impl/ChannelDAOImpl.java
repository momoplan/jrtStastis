package com.ruicai.basis.channel.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.context.ApplicationContext;

import com.ruicai.basis.channel.dao.ChannelDAO;
import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.common.RollPage;
import com.ruicai.basis.common.util.CommonUtil;
import com.ruicai.basis.common.util.FinalVar;
import com.ruicai.basis.entity.Channel;
import com.ruicai.basis.entity.TCooperat;

public class ChannelDAOImpl extends BaseDAO implements ChannelDAO {

	private static final Log log = LogFactory.getLog(ChannelDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Channel transientInstance) {
		log.debug("saving Channel instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void update(Channel transientInstance) {
		log.debug("updating Channel instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(Channel persistentInstance) {
		log.debug("deleting Channel instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(String ids) throws Exception {
		log.debug("deleting Channel ids");
		
		Session session = getSession();
		Query query = session.createQuery("delete from Channel where id in ("+ids+") ");
		query.executeUpdate();
		query = session.createQuery("delete from UserCfg where channelid in ("+ids+")  ");
		query.executeUpdate();
	}

	public Channel findById(java.lang.Integer id) {
		log.debug("getting Channel instance with id: " + id);
		try {
			Channel instance = (Channel) getHibernateTemplate().get(
					"com.ruicai.basis.entity.Channel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Channel> findByCode(Channel instance) {
		log.debug("finding all Channel instances");

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT channel.* from Channel channel where channel.code = '"
			+ instance.getCode()+"'");

		try {
			List<Channel> result = getSession().createSQLQuery(sql.toString()).addEntity(Channel.class).list();
			return result;
		} catch (RuntimeException re) {
			log.error("get findByCode failed", re);
			throw re;
		}
	}

	public List findByExample(Channel instance) {
		log.debug("finding Channel instance by example");
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
		log.debug("finding Channel instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Channel as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Channel instances");
		try {
			String queryString = "from Channel Order by id desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Channel merge(Channel detachedInstance) {
		log.debug("merging Channel instance");
		try {
			Channel result = (Channel) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Channel instance) {
		log.debug("attaching dirty Channel instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Channel instance) {
		log.debug("attaching clean Channel instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ChannelDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (ChannelDAOImpl) ctx.getBean("ChannelDAOImpl");
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
	public List<Channel> findList(Channel instance, RollPage rollPage,
			Integer resCount) throws Exception {
		List<Channel> channelList = new ArrayList<Channel>();
		Criteria crt = getSession().createCriteria(Channel.class);
		List<Channel> list = new ArrayList<Channel>();
        
		if (instance != null) {
			if(instance.getYw().getId()!=0){
			   crt.add(Expression.eq("yw",instance.getYw()));
			}			
			crt.addOrder(Order.desc("id"));
			list = crt.list();
			
			
			for(int i=0;i<list.size();i++){
				Channel channel = list.get(i);
				channel.getYw().setStartTime(instance.getYw().getStartTime());
				channel.getYw().setEndTime(instance.getYw().getEndTime());
				channel.setDays(instance.getDays());
				channel.setMonths(instance.getMonths());
				channel.setRegnum(getRegnum(channel));//获取注册人数            getRegPaynum  getPaynum
				channel.setRegPaynum(getRegPaynum(channel));//获取注册当天的充值用户数
				channel.setPaymoney(getPaymoney(channel));//获取充值金额
				channel.setBuymoney(getBuymoney(channel));//获取购彩金额
				channel.setVisitnum(getVisitnum(channel));//获取用户访问数
				channel.setBalance(getBalance(channel));//结算金额

				/*
				if(new Long(channel.getRegnum().toString().trim()).intValue() == 0 && (new Double(channel.getPaymoney()).doubleValue() == 0.0) && (new Double(channel.getBuymoney()).doubleValue() == 0.0) && (new Double(channel.getBalance()).doubleValue() == 0.0)
						&& (new Long(channel.getRegPaynum().toString().trim()).intValue() == 0)
						&& (new Long(channel.getPaynum().toString().trim()).intValue() == 0)
						) {
					continue;
				}*/
				channelList.add(channel);
			}
			settingRollPage(rollPage, crt, channelList.size());
		}

		return channelList;
	}

	/**
	 * 返回查询总数
	 * 
	 * @param Channel
	 * @return
	 * @throws Exception
	 */
	public Integer findListCount(Channel instance) throws Exception {

		Criteria crt = getSession().createCriteria(Channel.class);
		Integer resCount = 0;
		if (instance != null) {
			if(instance.getYw().getId()!=0){
			   crt.add(Expression.eq("yw",instance.getYw()));
		    }
			crt.setProjection(Projections.rowCount());
			resCount =  Integer.parseInt( crt.uniqueResult().toString());
		}

		return resCount;
	}

	/**
	 * 添加合作方式
	 * @param instance
	 * @return
	 */
	public int createCooperat(HashMap hs) throws Exception {
		Connection conn=getSession().connection();
		PreparedStatement pstmt = null;
		int count;
		try{
		String sql="insert into tcooperat(ID,CHANNL_ID,COOPERAT_TYPE,COUNT_TYPE,COUNT) VALUES(tcooperat_seq.nextval,?,?,?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,hs.get("CHANNEL_ID").toString());
		pstmt.setString(2,hs.get("COOPERAT_TYPE").toString());
		pstmt.setString(3,hs.get("COUNT_TYPE").toString());
		pstmt.setDouble(4, Double.parseDouble(hs.get("COUNT").toString()));
		
		 count = pstmt.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			pstmt.close();
			conn.close();
		}
		return count;
	}

	/**
	 * 查询合作方式
	 * @param instance
	 * @return
	 */
	public List findCooperatByChannelId(HashMap hs) {
//	    List iter = getSession().createQuery("SELECT * FROM TCOOPERAT WHERE CHANNL_ID='"+hs.get("CHANNL_ID").toString()+"'").setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP).list();
//	    return iter;

//		String sql="SELECT * FROM TCOOPERAT WHERE CHANNL_ID='"+hs.get("CHANNL_ID").toString()+"'";
//		Query cooperateQuery = getSession().createSQLQuery(sql).addScalar("ID").addScalar("CHANNL_ID").addScalar("COOPERAT_TYPE").addScalar("COUNT_TYPE").addScalar("COUNT").setResultTransformer(Transformers.aliasToBean(TCOOPERAT2.class));
//		List<TCOOPERAT2> l = cooperateQuery.list();
//		//List<HashMap> l=getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
//		return l; 

		log.debug("findCooperatByChannelId");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tCooperat.* from TCooperat tCooperat where tCooperat.CHANNL_ID = '"
			+ hs.get("CHANNL_ID").toString()+"'");
		try {
			List<TCooperat> result = getSession().createSQLQuery(sql.toString()).addEntity(TCooperat.class).list();
			return result;
		} catch (RuntimeException re) {
			log.error("findCooperatByChannelId failed", re);
			throw re;
		}
	}

	/**
	 * 删除合作方式
	 * @param instance
	 * @return
	 */
	public int delCooperat(String code)throws Exception{
		HashMap hs=new HashMap();
		hs.put("CHANNL_ID", code);
		List l=this.findCooperatByChannelId(hs);
		if(l.size()>0){
			Connection conn=getSession().connection();
			PreparedStatement pstmt = null;
			int count;
			try{
			String sql="delete from tcooperat where CHANNL_ID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,code);
			 count = pstmt.executeUpdate();
			}catch(Exception e){
				throw e;
			}finally{
				pstmt.close();
				conn.close();
			}
			return 1;
		}else{
		return 0;
		}
	}
	/**
	 * 获取注册用户数
	 * 渠道编号
	 * 开始日期
	 * 截止日期
	 * @param instance
	 * @return
	 * @throws Exception
	 */
	public Long getRegnum(Channel instance)throws Exception{
		Long regnum = new Long(0);
		
		log.info("获取注册用户数方法开始!!!!!");

		StringBuffer sql = new StringBuffer();
		sql.append("select sum(p.regnum) as regnum from   pvtj p  ");
		sql.append(" where 1=1 ");
		/**
		 * 根据渠道编号查询
		 */
		sql.append(" and p.channelid ='").append(instance.getId()).append("'");
		if(instance.getYw() != null && !"".equals(instance.getYw().getId())){
			sql.append(" and p.ywid='").append(instance.getYw().getId()).append("'");
		}
		if(instance.getYw().getStartTime() != null && !"".equals(instance.getYw().getStartTime().trim()))
			sql.append("and p.tjdate>=to_date('").append(instance.getYw().getStartTime().trim()).append("','yyyy-MM-dd')");
		if(instance.getYw().getEndTime() != null && !"".equals(instance.getYw().getEndTime().trim()))
			sql.append("and p.tjdate<to_date('").append(instance.getYw().getEndTime().trim()).append("','yyyy-MM-dd')");
		log.info("=======sql==="+sql);
		try {
			List<BigDecimal> result = getSession().createSQLQuery(sql.toString()).list();
			if(result != null && result.size() > 0 && result.get(0) != null)
			    regnum = result.get(0).longValue();
			return regnum;
		} catch (RuntimeException re) {
			log.error("get getRegnum failed", re);
			throw re;
		}
	}

	/**
	 * @param  渠道编号 开始日期 截止日期
	 * @return 注册当天充值的用户数
	 * @throws Exception
	 */
	public Long getRegPaynum(Channel instance)throws Exception{
		Long regnum = new Long(0);
		log.info("regPayNum begin!!!!!");
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(p.regpaynum) as regpaynum from   pvtj p  ");
		sql.append(" where 1=1 ");
		/**
		 * 根据渠道编号查询
		 */
		sql.append(" and p.channelid ='").append(instance.getId()).append("'");
		if(instance.getYw() != null && !"".equals(instance.getYw().getId())){
			sql.append(" and p.ywid='").append(instance.getYw().getId()).append("'");
		}
		if(instance.getYw().getStartTime() != null && !"".equals(instance.getYw().getStartTime().trim()))
			sql.append("and p.tjdate>=to_date('").append(instance.getYw().getStartTime().trim()).append("','yyyy-MM-dd')");
		if(instance.getYw().getEndTime() != null && !"".equals(instance.getYw().getEndTime().trim()))
			sql.append("and p.tjdate<to_date('").append(instance.getYw().getEndTime().trim()).append("','yyyy-MM-dd')");
		try {
			List<BigDecimal> result = getSession().createSQLQuery(sql.toString()).list();
			if(result != null && result.size() > 0 && result.get(0) != null)
			    regnum = result.get(0).longValue();
			return regnum;
		} catch (RuntimeException re) {
			log.error("get getRegpaynum failed", re);
			throw re;
		}
	}

	/**
	 * @param  渠道编号 开始日期 截止日期
	 * @return 当天所有充了值的用户数
	 * @throws Exception
	 */
	public Long getPaynum(Channel instance)throws Exception{
		Long regnum = new Long(0);
		log.info("totalPayNum begin!!!!!");
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(p.paynum) as paynum from   pvtj p  ");
		sql.append(" where 1=1 ");
		/**
		 * 根据渠道编号查询
		 */
		sql.append(" and p.channelid ='").append(instance.getId()).append("'");
		if(instance.getYw() != null && !"".equals(instance.getYw().getId())){
			sql.append(" and p.ywid='").append(instance.getYw().getId()).append("'");
		}
		if(instance.getYw().getStartTime() != null && !"".equals(instance.getYw().getStartTime().trim()))
			sql.append("and p.tjdate>=to_date('").append(instance.getYw().getStartTime().trim()).append("','yyyy-MM-dd')");
		if(instance.getYw().getEndTime() != null && !"".equals(instance.getYw().getEndTime().trim()))
			sql.append("and p.tjdate<to_date('").append(instance.getYw().getEndTime().trim()).append("','yyyy-MM-dd')");
		try {
			List<BigDecimal> result = getSession().createSQLQuery(sql.toString()).list();
			if(result != null && result.size() > 0 && result.get(0) != null)
			    regnum = result.get(0).longValue();
			return regnum;
		} catch (RuntimeException re) {
			log.error("get getpaynum failed", re);
			throw re;
		}
	}
	
	public Long getVisitnum(Channel instance)throws Exception{
		Long visitnum = new Long(0);
		
		log.info("获取用户访问数方法开始!!!!!");

		StringBuffer sql = new StringBuffer();
		sql.append("select sum(p.VISITNUM) as VISITNUM from pvtj p");
		sql.append(" where 1=1 ");
		/**
		 * 根据渠道编号查询
		 */
		sql.append(" and p.channelid ='").append(instance.getId()).append("'");
		if(instance.getYw() != null && !"".equals(instance.getYw().getId())){
			sql.append(" and p.ywid='").append(instance.getYw().getId()).append("'");
		}
		if(instance.getYw().getStartTime() != null && !"".equals(instance.getYw().getStartTime().trim()))
			sql.append("and p.tjdate>=to_date('").append(instance.getYw().getStartTime().trim()).append("','yyyy-MM-dd')");
		if(instance.getYw().getEndTime() != null && !"".equals(instance.getYw().getEndTime().trim()))
			sql.append("and p.tjdate<to_date('").append(instance.getYw().getEndTime().trim()).append("','yyyy-MM-dd')");
		log.info("=======sql==="+sql);
		try {
			List<BigDecimal> result = getSession().createSQLQuery(sql.toString()).list();
			log.info("===============result==========="+result.get(0));
			if(result != null && result.size() > 0 && result.get(0) != null)
				visitnum = result.get(0).longValue();
			log.info("=========================用户访问数:::"+visitnum);
			log.info("获取用户访问数方法结束!!!!");
			return visitnum;
			
		} catch (RuntimeException re) {
			log.error("get getRegnum failed", re);
			throw re;
		}
	}
	/**
	 * 获取充值总金额
	 * 渠道编号
	 * 开始日期
	 * 截止日期
	 * @param instance
	 * @return
	 * @throws Exception
	 */
	public Double getPaymoney(Channel instance)throws Exception{
		Double paymoney = new Double(0.00);

		StringBuffer sql = new StringBuffer();
		sql.append("select sum(p.paymoney) as paymoney from   " +
				"paytj p  ");
		sql.append(" where 1=1 ");
		/**
		 * 根据渠道编号查询
		 */
		sql.append(" and p.channelid ='").append(instance.getId())
				.append("'").append(" and p.ywid='").append(instance.getYw().getId()).append("'");
		if(instance.getYw() != null && !"".equals(instance.getYw().getId())){
			sql.append(" and p.ywid='").append(instance.getYw().getId()).append("'");
		}
		if (instance.getYw().getStartTime() != null
				&& !"".equals(instance.getYw().getStartTime().trim()))
			sql.append("and p.tjdate>=to_date('").append(
					instance.getYw().getStartTime().trim()).append(
					"','yyyy-MM-dd')");
		if (instance.getYw().getEndTime() != null
				&& !"".equals(instance.getYw().getEndTime().trim()))
			sql.append("and p.tjdate<to_date('").append(
					instance.getYw().getEndTime().trim()).append(
					"','yyyy-MM-dd')");
		log.info("=======sql===" + sql);
		try {
			List<BigDecimal> result = getSession().createSQLQuery(
					sql.toString()).list();
			if (result != null && result.size() > 0 && result.get(0) != null)
				paymoney = result.get(0).doubleValue();
			log.info("==================paymoney==========" + paymoney);
			return paymoney;
		} catch (RuntimeException re) {
			log.error("get getPaymoney failed", re);
			throw re;
		}
		
	}
	
	/**
	 * 获取购彩总金额
	 * @param instance
	 * @return
	 * @throws Exception
	 */
	public Double getBuymoney(Channel instance)throws Exception{
		Double buymoney = new Double(0.00);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(p.buymoney) as buymoney from  "
				+ " buytj p  ");
		sql.append(" where 1=1 ");
		/**
		 * 根据渠道编号查询
		 */
		sql.append(" and p.channelid ='").append(instance.getId())
				.append("'").append(" and p.ywid='").append(instance.getYw().getId()).append("'");
		if(instance.getYw() != null && !"".equals(instance.getYw().getId())){
			sql.append(" and p.ywid='").append(instance.getYw().getId()).append("'");
		}
		if (instance.getYw().getStartTime() != null
				&& !"".equals(instance.getYw().getStartTime().trim()))
			sql.append("and p.tjdate>=to_date('").append(
					instance.getYw().getStartTime().trim()).append(
					"','yyyy-MM-dd')");
		if (instance.getYw().getEndTime() != null
				&& !"".equals(instance.getYw().getEndTime().trim()))
			sql.append("and p.tjdate<to_date('").append(
					instance.getYw().getEndTime().trim()).append(
					"','yyyy-MM-dd')");
		log.info("=======sql===" + sql);
		
		try {
			List<BigDecimal> result = getSession().createSQLQuery(
					sql.toString()).list();
			if (result != null && result.size() > 0 && result.get(0) != null)
				buymoney = result.get(0).doubleValue();
			log.info("==================buymoney==========" + buymoney);
			return buymoney;
		} catch (RuntimeException re) {
			log.error("get getBuymoney failed", re);
			throw re;
		}
		
	}
	
	/**
	 * 获取结算总金额
	 * @param instance
	 * @return
	 * @throws Exception
	 */
	public Double getBalance(Channel instance)throws Exception{
		Double balance = new Double(0.00);
		Double cpaBlance = new Double(0.00);//CPA的结算金额
		Double cpcBlance = new Double(0.00);//CPc的结算金额
		Double cpsBlance = new Double(0.00);//CPS的结算金额
		Double rivetBlance = new Double(0.00);//固定金额的结算金额
		
        log.info("======================结算金额开始");
		StringBuffer sql = new StringBuffer();
		sql.append("select p.* from TCOOPERAT p ");
		sql.append(" where 1=1 ");
		log.info("=====================渠道编号======"+instance.getCode());
		/**
		 * 根据渠道编号查询
		 */
		sql.append(" and p.CHANNL_ID ='").append(instance.getCode())
				.append("'");

		log.info("=======sql===" + sql);
		try {
			List<TCooperat> resultList = getSession().createSQLQuery(
					sql.toString()).addEntity(TCooperat.class).list();

			if (resultList != null && resultList.size() > 0) {
				for (TCooperat cooperat : resultList) {
					if (cooperat.getCooperatType().equals(
							FinalVar.COOPERAT_TYPE_CPA)) {
						// 如果是CPA regnum*count 注册用户数*单个金额
						cpaBlance = CommonUtil.formatDouble(instance.getRegnum() * cooperat.getCount(), FinalVar.FORMAT_DOUBLE_STR);
					
					}
					if (cooperat.getCooperatType().equals(
							FinalVar.COOPERAT_TYPE_CPC)) {
						// 如果是cpc visitnum* count 访问量*金额
						cpcBlance = CommonUtil.formatDouble(instance.getVisitnum()* cooperat.getCount(), FinalVar.FORMAT_DOUBLE_STR);
						
					}
					if (cooperat.getCooperatType().equals(
							FinalVar.COOPERAT_TYPE_CPS)) {
						// 如果是cps buymoney*count/100 购彩金额*金额 除以100
						cpsBlance = CommonUtil.formatDouble(instance.getBuymoney()* cooperat.getCount() / 100, FinalVar.FORMAT_DOUBLE_STR);
					}
					if (cooperat.getCooperatType().equals(
							FinalVar.COOPERAT_TYPE_RIVET)) {
//						// 如果是固定金额(月) count/30*days 金额除以30 乘以天数
//						rivetBlance = CommonUtil.formatDouble((cooperat.getCount()/FinalVar.DAYS)*instance.getDays(), FinalVar.FORMAT_DOUBLE_STR);
					    //固定金额的规则 月份数
						rivetBlance = CommonUtil.formatDouble((cooperat.getCount()*(instance.getMonths()==0?1:instance.getMonths())), FinalVar.FORMAT_DOUBLE_STR);
						log.info("========instance.getMonths()====="+instance.getMonths());
					}

				}
			}
			/**
			 * 结算金额 = CPA的结算金额+CPc的结算金额+CPS的结算金额+固定金额的结算金额
			 */
			balance = cpaBlance + cpcBlance + cpsBlance + rivetBlance;
			log.info("======================CPA结算金额为========="+cpaBlance);
			log.info("======================CPC结算金额为========="+cpcBlance);
			log.info("======================CPS结算金额为========="+cpsBlance);
			log.info("======================固定结算金额为========="+rivetBlance);
			log.info("======================结算金额为========="+balance);
			log.info("========instance.getDays()======="+instance.getDays());
			log.info("======================结算金额结束");
		} catch (RuntimeException re) {
			log.error("get getBalance failed", re);
			throw re;
		}
		
		return balance;
	}
	
       public List<TCooperat> findAllTCooperat(Channel channel) throws Exception {

		List<TCooperat> list = new ArrayList<TCooperat>();
		StringBuffer sql = new StringBuffer();
		sql.append("select p.* from TCOOPERAT p ");
		sql.append(" where 1=1 ");

		/**
		 * 根据渠道编号查询
		 */
		sql.append(" and p.CHANNL_ID ='").append(channel.getCode())
				.append("'");
		log.info("=========sql=========="+sql);
		try {
			list = getSession().createSQLQuery(
					sql.toString()).addEntity(TCooperat.class).list();

		} catch (Exception e) {
			log.error("========findAllTCooperat faile " + e);
		} finally {
			log.info("===========findAllTCooperat  执行完毕！！！！！！");
		}
		return list;
	}
       
       public long get3gall(String userno,String begintime,String endtime,HttpServletRequest request){
    	ApplicationContext ac = (ApplicationContext)request.getSession().getServletContext().getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
    	begintime="to_date('"+begintime+"',"+"'yyyy-mm-dd')";
    	endtime="to_date('"+endtime+"',"+"'yyyy-mm-dd')";

    	
    	String sql="select sum(amt) as amt from jrtsch.tlot where userno='"+userno+"'"+" and ordertime between "+begintime+"  and "+endtime;
       SessionFactory sf = (SessionFactory)ac.getBean("sessionFactory");
       	List list=sf.openSession().createSQLQuery(
   				sql.toString()).list();
       	BigDecimal obs = (BigDecimal)list.get(0);
       	return obs == null ? 0 : obs.longValue();
       }

	public Map<String, Channel> findByYwid(String ywid) {
		log.debug("finding Channels with ywid");
		Map<String, Channel> channelMap = new HashMap<String, Channel>();
		try {
			String queryString = "from Channel as model where model.yw.id=?";
			List<Channel> channelList = getHibernateTemplate().find(queryString, Integer.parseInt(ywid));
			for(Channel ch : channelList) {
				channelMap.put(ch.getId().toString(), ch);
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		return channelMap;
	}
}