package com.ruicai.basis.tj.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.transform.Transformers;

import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.User;

public interface PictureRingcountDAO {

	public List findPictureRingcountByAll(HashMap hm) throws Exception; 
	public List findPictureRingcountByID(HashMap hm) throws Exception; 
	public int doCreatePictureRingcount(HashMap hm)throws Exception;
	public int doUpdatePictureRingcount(HashMap hm)throws Exception;
	public List findTPictureRingByID(HashMap hm);
	public List findTPictureRingByAll(HashMap hm);
	public List findTPictureRingByRandom(HashMap hm);
	public int findTranstion(HashMap hm);
	public List findPvCountList(HashMap hm);
	public int findTuserinfoByMobileid(String mobileid);
	
}
