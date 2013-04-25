package com.ruicai.basis.tj.dao;

import java.util.List;

import com.ruicai.basis.entity.ClientZjlTJ;
import com.ruicai.basis.entity.Tbl_userinfo;

public interface ClientZjlTJDAO {
	List<ClientZjlTJ> findZjlByChannels(List<Integer> channels, String beginTime, String endTime) throws Exception;
	public Tbl_userinfo findClientUserByImei(String imei);
}
