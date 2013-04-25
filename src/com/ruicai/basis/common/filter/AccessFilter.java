package com.ruicai.basis.common.filter;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ruicai.basis.common.BaseHttpServlet;
import com.ruicai.basis.common.ProUtil;
import com.ruicai.basis.entity.RoleMenu;
import com.ruicai.basis.entity.User;
import com.ruicai.basis.sys.business.RoleManage;

/**
 * @author Administrator
 * @see 过滤所有URL地址判断用户是否已经登陆
 */
public class AccessFilter extends BaseHttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(AccessFilter.class);
	private ProUtil proutil = new ProUtil();
	private FilterConfig filterConfig;

	public void destroy() {

	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = req.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        
		User user = (User) req.getSession().getAttribute("user");
		if (user != null) {

			Hashtable ht = (Hashtable) req.getSession().getAttribute("ht");

			if (ht == null) {

				ht = new Hashtable();

				RoleManage roleManage = (RoleManage) getBean("RoleManage");
				List<RoleMenu> rolemenus = null;
				try {
					rolemenus = roleManage.findRoleMenu(user.getRoleid());
				} catch (Exception e) {
					log.error("findRoleMenu failed", e);
				}

				for (RoleMenu rolmenu : rolemenus) {
					if (rolmenu.getBool().equals("false")&&rolmenu.getFid()!=0) {
						ht.put(path+rolmenu.getUrl().trim(), rolmenu.getName());
						log.debug("用户受限地址 :" + path+rolmenu.getUrl().trim() + " " + rolmenu.getName());
						//System.out.println("用户受限地址 :" + path+rolmenu.getUrl().trim() + " " + rolmenu.getName());
					}
				}

				req.getSession().setAttribute("ht", ht);

			} else {

				if (ht.containsKey(req.getRequestURI())) {
					log.debug("用户受限地址 :" + req.getRequestURI());
					res.sendRedirect(path + "/noaccess.html");	
					//res.sendRedirect(basePath+"noaccess.html");	
					//RequestDispatcher dispatcher = req.getRequestDispatcher("/noaccess.html");
					//dispatcher.forward(req, res);
					//return;
				}

			}
		}
        
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	/**
	 * @interpret 获取BEAN
	 * @param name
	 * @return Object
	 */
	public Object getBean(String name) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(
				filterConfig.getServletContext()).getBean(name);
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

}
