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

import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.entity.ClientZjlTJ;
import com.ruicai.basis.entity.Tbl_userinfo;
import com.ruicai.basis.tj.dao.ClientZjlTJDAO;

public class ClientZjlTJDAOImpl extends BaseDAO implements ClientZjlTJDAO{

	private static final Log log = LogFactory.getLog(ClientZjlTJDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public List<ClientZjlTJ> findZjlByChannels(List<Integer> channels,
			String beginTime, String endTime) throws Exception {

		Connection conn = getSession().connection();

		@SuppressWarnings("rawtypes")
		List list_ = new ArrayList();
		
		List<ClientZjlTJ> list = new ArrayList<ClientZjlTJ>();
		
		ClientZjlTJ payTJ_ = null;

		RowSetDynaClass result = null;
		PreparedStatement stat;
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT  p.statdate ,p.coopid , p.new_fix  ");	
				sql.append("from client.tbl_stat_coop p where ");
				if(beginTime!=null){
					sql.append(" p.statdate >= STR_TO_DATE('"+beginTime+"','%Y-%m-%d') and");
				}
				if(endTime!=null){
					sql.append(" p.statdate <= STR_TO_DATE('"+endTime+"','%Y-%m-%d') and ");
				}
				sql.append(" p.coopid in ( ");
                for(Integer channel:channels){
                	sql.append(channel+",");
                }	
                sql.deleteCharAt(sql.length()-1);
                sql.append(") ");
                sql.append("order by p.statdate desc,p.coopid desc, p.new_fix desc ");
				
                log.info("sql:"+sql.toString());
                
				stat = conn.prepareStatement(sql.toString());

				ResultSet res = stat.executeQuery();

				result = new RowSetDynaClass(res);

				list_ = result.getRows();
				
				for(int i=0;i<list_.size();i++){

					payTJ_ = new ClientZjlTJ();
					
					BasicDynaBean basicdynaBean = (BasicDynaBean)list_.get(i);
					if(basicdynaBean.get("new_fix")==null || (basicdynaBean.get("coopid")==null) || (basicdynaBean.get("statdate")==null)) {
						continue;
					}

					payTJ_.setStatdate(basicdynaBean.get("statdate").toString());
					payTJ_.setCoopid(basicdynaBean.get("coopid").toString());
					String newFix = basicdynaBean.get("new_fix").toString();
					payTJ_.setZjl(Integer.parseInt(newFix));
					list.add(payTJ_);
				}
			} catch (Exception e) {
				log.info("findZjlByChannels Error", e);
			}

		return list;
	}
	
	public Tbl_userinfo findClientUserByImei(String imei) {
		List<Tbl_userinfo> list =  this.getHibernateTemplate().find("from Tbl_userinfo p where p.imei = ?", imei);
		return list.size() == 0 ? null:list.get(0);
	}
}
