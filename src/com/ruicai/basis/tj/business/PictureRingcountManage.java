package com.ruicai.basis.tj.business;

import java.util.HashMap;
import java.util.List;

import com.ruicai.basis.entity.BuyTJ;
import com.ruicai.basis.entity.User;

public interface PictureRingcountManage {
	public List findPictureRingcountByAll(HashMap hs) throws Exception;
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
