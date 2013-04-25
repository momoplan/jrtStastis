package com.ruicai.basis.common;

/**
 * @author redsun
 * <静态文件对象,用于显示文件各种属性>
 */
public class StaticFile {
	/**
	 * 文件编号
	 */
	private Long fileid;
	/**
	 * 文件名称
	 */
	private String filename;
	/**
	 * 文件最后修改时间
	 */
	private String filelastmodified;
	/**
	 * 文件URI地址
	 */
	private String fileuil;
	/**
	 * 文件内容长度
	 */
	private Long filecontentlength;
	
	public Long getFileid() {
		return fileid;
	}
	public void setFileid(Long fileid) {
		this.fileid = fileid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilelastmodified() {
		return filelastmodified;
	}
	public void setFilelastmodified(String filelastmodified) {
		this.filelastmodified = filelastmodified;
	}
	public Long getFilecontentlength() {
		return filecontentlength;
	}
	public void setFilecontentlength(Long filecontentlength) {
		this.filecontentlength = filecontentlength;
	}
	public String getFileuil() {
		return fileuil;
	}
	public void setFileuil(String fileuil) {
		this.fileuil = fileuil;
	} 
}
