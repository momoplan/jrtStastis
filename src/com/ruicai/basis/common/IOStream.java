package com.ruicai.basis.common;

import java.io.File;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IOStream {

	private static Log log = LogFactory.getLog(IOStream.class);
	/**
	 * @interpret 根据URI,文件名称删除指定目录下指定的文件
	 * @param     filepath
	 * @throws    Exception
	 */
	public void del(String filepath,String filename,String[] suffix) throws Exception {
		
		File f = new File(filepath);// 定义文件路径
		
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				//f.delete();
			} else {
				// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				
				int i = f.listFiles().length;
				
				for (int j = 0; j < i; j++) {
					
					if (delFile[j].isDirectory()) {
						del(delFile[j].getAbsolutePath(),filename,suffix);// 递归调用del方法并取得子目录路径
					}
					//删除指定后缀名称的文件
					for(int m=0;m<suffix.length;m++){
						
						boolean flag = delFile[j].getName().endsWith(suffix[m]);
						//如果为TURE表示找到
						if(flag){
							if(delFile[j].getName().equals(filename)){
								log.info(" 页面名称: "+delFile[j].getName()+" , 最后修改时间: "+ConvertLang.getDateTimeStr(delFile[j].lastModified()));
								delFile[j].delete();// 删除文件
							}
						}
					}
				}
			}
		}
	}
	/**
	 * @interpret 根据URI地址删除指定目录下所有文件
	 * @param     filepath
	 * @throws    Exception
	 */
	public void del(String filepath) throws Exception {
		
		File f = new File(filepath);// 定义文件路径
		
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				f.delete();
			} else {
				// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				
				int i = f.listFiles().length;
				
				for (int j = 0; j < i; j++) {
					
					if (delFile[j].isDirectory()) {
						del(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
					}
					delFile[j].delete();// 删除文件
				}
			}
		}
	}
	/**
	 * @interpret 根据URI地址删除指定目录下所有文件
	 * @param     filepath
	 * @throws    Exception
	 */
	public void del(String filepath,String[] suffix) throws Exception {
		
		File f = new File(filepath);// 定义文件路径
		
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				//f.delete();
			} else {
				// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				
				int i = f.listFiles().length;
				
				for (int j = 0; j < i; j++) {
					
					if (delFile[j].isDirectory()) {
						del(delFile[j].getAbsolutePath(),suffix);// 递归调用del方法并取得子目录路径
					}
					//删除指定后缀名称的文件
					for(int m=0;m<suffix.length;m++){
						
						boolean flag = delFile[j].getName().endsWith(suffix[m]);
						//如果为TURE表示找到
						if(flag){
							log.info(" 页面名称: "+delFile[j].getName()+" , 最后修改时间: "+ConvertLang.getDateTimeStr(delFile[j].lastModified()));
							delFile[j].delete();// 删除文件
						}
					}
				}
			}
		}
	}
	
	/**
	 * @interpret 根据URI地址查询指定目录下所有文件
	 * @param     filepath
	 * @throws    Exception
	 */
	public void find(String filepath,String[] suffix) throws Exception {
		
		File f = new File(filepath);// 定义文件路径
		
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				//f.delete();
			} else {
				// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				
				int i = f.listFiles().length;
				
				for (int j = 0; j < i; j++) {
					
					if (delFile[j].isDirectory()) {
						find(delFile[j].getAbsolutePath(),suffix);// 递归调用del方法并取得子目录路径
					}
					//删除指定后缀名称的文件
					for(int m=0;m<suffix.length;m++){
						
						boolean flag = delFile[j].getName().endsWith(suffix[m]);
						//如果为TURE表示找到
						if(flag){
							log.info(" 页面名称: "+delFile[j].getName()+" , 最后修改时间: "+ConvertLang.getDateTimeStr(delFile[j].lastModified()));		
						}
					}
				}
			}
		}
	}
	/**
	 * @interpret 根据URI地址查询指定目录下所有文件.并返回LIST对象
	 * @param     filepath
	 * @throws    Exception
	 */
	public List<StaticFile> findFile(String filepath,String[] suffix,List<StaticFile> list,Long fileid) throws Exception {
		
		//List<StaticFile> list = new ArrayList<StaticFile>();
		
		StaticFile staticfile;
		
		//Long fileid = new Long(1);
		
		File f = new File(filepath);// 定义文件路径
		
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				//f.delete();
			} else {
				// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				
				int i = f.listFiles().length;
				
				for (int j = 0; j < i; j++) {
					
					if (delFile[j].isDirectory()) {
						findFile(delFile[j].getAbsolutePath(),suffix,list, fileid);// 递归调用del方法并取得子目录路径
					}
					//删除指定后缀名称的文件
					for(int m=0;m<suffix.length;m++){
						
						boolean flag = delFile[j].getName().endsWith(suffix[m]);
						//如果为TURE表示找到
						if(flag){
							log.info(" 页面名称: "+delFile[j].getName()+" ,最后修改时间: "+ConvertLang.getDateTimeStr(delFile[j].lastModified())+" ,路径: "+delFile[j].getPath()+" ,页面字符长度:"+delFile[j].length());		
							staticfile = new StaticFile();
							
							staticfile.setFileid(fileid);
							staticfile.setFilename(delFile[j].getName());
							staticfile.setFilelastmodified(ConvertLang.getDateTimeStr(delFile[j].lastModified()));
							staticfile.setFileuil(delFile[j].getPath());
							staticfile.setFilecontentlength(delFile[j].length());
							
							fileid++;
							
							list.add(staticfile);
						}
					}
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		
		IOStream iostream = new IOStream();
		
		//String;
		
		String[] buff = {"class"};
		
		try {
			iostream.find("D:/workspace3.4/DiagramDLT/WebRoot/WEB-INF/classes/", buff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
