package com.ruicai.basis.tj.business.impl;

import java.util.HashMap;
import java.util.List;

import com.ruicai.basis.tj.business.PictureRingcountManage;
import com.ruicai.basis.tj.dao.PictureRingcountDAO;

public class PictureRingcountManageImpl implements PictureRingcountManage {
private PictureRingcountDAO pictureringcountDAO;

public void setPictureringcountDAO(PictureRingcountDAO pictureringcountDAO) {
	this.pictureringcountDAO = pictureringcountDAO;
}
	@Override
	public List findPictureRingcountByAll(HashMap hs) throws Exception {
		return pictureringcountDAO.findPictureRingcountByAll(hs);
	}

	public int doCreatePictureRingcount(HashMap hm) throws Exception {
		return pictureringcountDAO.doCreatePictureRingcount(hm);
		
	}
	@Override
	public int doUpdatePictureRingcount(HashMap hm) throws Exception {
		// TODO Auto-generated method stub
		return pictureringcountDAO.doUpdatePictureRingcount(hm);
	}
	@Override
	public List findPictureRingcountByID(HashMap hm) throws Exception {
		// TODO Auto-generated method stub
		return pictureringcountDAO.findPictureRingcountByID(hm);
	}
	@Override
	public List findTPictureRingByID(HashMap hm) {
		// TODO Auto-generated method stub
		return pictureringcountDAO.findTPictureRingByID(hm);
	}
	@Override
	public List findTPictureRingByAll(HashMap hm) {
		// TODO Auto-generated method stub
		return pictureringcountDAO.findTPictureRingByAll(hm);
	}
	@Override
	public List findTPictureRingByRandom(HashMap hm) {
		// TODO Auto-generated method stub
		return pictureringcountDAO.findTPictureRingByRandom(hm);
	}
	@Override
	public int findTranstion(HashMap hm) {
		// TODO Auto-generated method stub
		return pictureringcountDAO.findTranstion(hm);
	}
	@Override
	public List findPvCountList(HashMap hm) {
		// TODO Auto-generated method stub
		return pictureringcountDAO.findPvCountList(hm);
	}
	@Override
	public int findTuserinfoByMobileid(String mobileid) {
		// TODO Auto-generated method stub
		return pictureringcountDAO.findTuserinfoByMobileid(mobileid);
	}
}
