package com.ruicai.basis.entity;

/**
 * 用户配置表
 * @author liukw
 * date 2010-5-24
 */
public class UserCfg implements java.io.Serializable{	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private User user;
	private Channel channel;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}		
}
