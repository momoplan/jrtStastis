package com.ruicai.basis.tj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.transform.Transformers;
import com.ruicai.basis.common.BaseDAO;
import com.ruicai.basis.tj.dao.PictureRingcountDAO;

public class PictureRingcountDAOImpl extends BaseDAO implements PictureRingcountDAO {
	private static final Log log = LogFactory.getLog(PictureRingcountDAOImpl.class);
	@Override
	public List findPictureRingcountByAll(HashMap hm) throws Exception {
		  log.debug("findPictureRingcountByAll ");
		String sql="select d.DATES,d.ID,d.CONTENT,decode(d.TYPE,1,'图片',2,'铃声','未知') TYPE,nvl(s.DOWNCOUNT,0) DOWNCOUNT,s.DATETIME from ( "+
"select d.dates,t.id,t.content,t.type from ("+
"SELECT to_char(TO_DATE('"+hm.get("beginTime")+"', 'yyyy-mm-dd') + ROWNUM - 1,'yyyy-mm-dd') AS dates "+
"FROM JRTSTATIS.PVCOUNT "+
"WHERE "+
"TO_DATE('"+hm.get("beginTime")+"', 'yyyy-mm-dd') + ROWNUM - 1 < = "+
"TO_DATE('"+hm.get("endTime")+"', 'yyyy-mm-dd') order by dates desc )  "+
"d,jrtsch.tpicturering t order by d.dates desc,t.type desc "+
") d left join ( "+
"SELECT ID,PID,TYPE,DOWNCOUNT,DATETIME FROM JRTSTATIS.pictureringcount p "+
"WHERE "+
" p.datetime>= to_timestamp('"+hm.get("beginTime")+"','yyyy-mm-dd')  "+
"and  "+
" p.datetime<= to_timestamp('"+hm.get("endTime")+"','yyyy-mm-dd')+1 "+
") s on d.id=s.pid and d.dates=to_char(s.DATETIME,'yyyy-mm-dd') where 1=1 "+hm.get("type");		
			//sql.append("group by ywid,channelid,buytype ");		
			
			List<HashMap> result = null;
			try {
		        result = getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			} catch (RuntimeException re) {
				log.error("findPictureRingcountByAll failed", re);
				throw re;
			}
		    
			return result;	
	}
	@Override
	public List findPictureRingcountByID(HashMap hm) throws Exception {
		String sql="select ID from jrtstatis.PictureRingcount where 1=1 and pid='"+hm.get("ID")+"' and to_char(datetime,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')";
		List<HashMap> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	        
		} catch (RuntimeException re) {
			log.error("findPictureRingcountByID failed", re);
			throw re;
		}
		return result;
	}
	@Override
	public int doCreatePictureRingcount(HashMap hm) throws Exception {

		String sql="insert into pictureringcount(ID,PID,TYPE,DOWNCOUNT,DATETIME,PNAME) values(pictureringcount_seq.nextval,?,?,?,sysdate,?)";
			Connection conn=getSession().connection();
			PreparedStatement pstmt = null;
			try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, hm.get("ID").toString());
			pstmt.setString(2, hm.get("TYPE").toString());
			pstmt.setString(3,"1");
			pstmt.setString(4, hm.get("CONTENT").toString());
			//pstmt.setString(1,code);
			  pstmt.executeUpdate();
			 return 1;
			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}finally{
				pstmt.close();
				conn.close();
			}
	
	}
	@Override
	public int doUpdatePictureRingcount(HashMap hm) throws Exception {
		String sql="update JRTSTATIS.pictureringcount set downcount=(select downcount+1 downcount from JRTSTATIS.pictureringcount where ID=? ) WHERE ID=?";
		Connection conn=getSession().connection();
		PreparedStatement pstmt = null;
		try{
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(hm.get("ID").toString()));
		pstmt.setInt(2, Integer.parseInt(hm.get("ID").toString()));
		  pstmt.executeUpdate();
		 return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			pstmt.close();
			conn.close();
		}
	}
	@Override
	public List findTPictureRingByID(HashMap hm) {
		String sql="select ID,CONTENT,PATH,TYPE,to_char(DATETIME,'yyyy-mm-dd') DATETIME from jrtsch.tpicturering WHERE ID="+hm.get("ID");
		List<HashMap> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	        
		} catch (RuntimeException re) {
			log.error("findTPictureRingByID failed", re);
			throw re;
		}
		return result;
	}
	@Override
	public List findTPictureRingByAll(HashMap hm) {
		if(hm==null||hm.size()==0){
			hm=new HashMap();
			hm.put("TYPE", " ");
			hm.put("PAGE", " ");
		}
		String sql="select ID,CONTENT,PATH,TYPE,DATETIME, RN from(" +
				"select ID,CONTENT,PATH,TYPE,to_char(DATETIME,'yyyy-mm-dd') DATETIME,rownum RN " +
				"from jrtsch.tpicturering "+hm.get("TYPE")+") "+hm.get("PAGE")+"";
		List<HashMap> result = null;
		try {
	        result = getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	        
		} catch (RuntimeException re) {
			log.error("findTPictureRingByAll failed", re);
			throw re;
		}
		return result;
	}
	@Override
	public int findTranstion(HashMap hm) {
		String sql="select t.id from jrtsch.ttransaction t where t.userno=(select u.userno from jrtsch.tuserinfo u where u.mobileid='"+hm.get("mobileid").toString()+"'  )  and t.type in(2,3,10)";
		List<HashMap> result = null;
		try {
			
	        result = getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	        
		} catch (RuntimeException re) {
			log.error("findTranstion failed", re);
			throw re;
		}
		return result.size();
	}
	@Override
	public List findTPictureRingByRandom(HashMap hh) {
		List l=this.findTPictureRingByAll(hh);
		List list=new ArrayList();
		
		for(int i = 0;i<l.size();i++){
			HashMap hs=(HashMap) l.get(i);
			list.add(hs.get("ID").toString());
		}
			Random random=new Random();
			int count=random.nextInt(list.size());
			HashMap hm=new HashMap();
			hm.put("ID",list.get(count));
			list=this.findTPictureRingByID(hm);
		return list;
	}
	@Override
	public List findPvCountList(HashMap hm) {
String sql=" SELECT d.dates,s.tsj,nvl(s.indexcount,0) indexcount,nvl(s.downcount,0) downcount from ( " +
"   SELECT to_char(TO_DATE('"+hm.get("beginTime")+"', 'yyyy-mm-dd') + ROWNUM - 1,'yyyy-mm-dd') AS dates     " +
" FROM   PVCOUNT WHERE  " +
" TO_DATE('"+hm.get("beginTime")+"', 'yyyy-mm-dd') + ROWNUM - 1 < = TO_DATE('"+hm.get("endTime")+"', 'yyyy-mm-dd') " +
" ) d left join ( " +
"  select to_char(tsj,'YYYY-MM-DD') TSJ,sum(decode(type,11,1,0)) indexcount,sum(decode(type,12,1,0)) downcount " +
"  from jrtstatis.pvcount where type in (11,12) and  tsj>= to_timestamp('"+hm.get("beginTime")+"','YYYY-MM-DD') and  " +
" tsj<= to_timestamp('"+hm.get("endTime")+"','YYYY-MM-DD')+1 group by to_char(tsj,'YYYY-MM-DD') ORDER BY TSJ DESC) s " +
" on d.dates=s.tsj";
List<HashMap> result = null;
try {
    result = getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    
} catch (RuntimeException re) {
	log.error("findPvCountList failed", re);
	throw re;
}
	return result;
	}
	@Override
	public int findTuserinfoByMobileid(String mobileid) {
		
		String sql="select u.userno from jrtsch.tuserinfo u where u.mobileid='"+mobileid+"'";
		List<HashMap> result = null;
		try {
			
	        result = getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	        
		} catch (RuntimeException re) {
			log.error("findTranstion failed", re);
			throw re;
		}
		return result.size();
	}


}
