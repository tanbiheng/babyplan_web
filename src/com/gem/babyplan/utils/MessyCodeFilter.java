package com.gem.babyplan.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(urlPatterns={ "/MessyCodeFilter", "/*" },asyncSupported=true)
public class MessyCodeFilter implements Filter {
	public MessyCodeFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("中文乱码过滤器销毁");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//由于从请求中过来的方法，无非三种，即getParameter,getParameterMap,以及getparameters，三种方法，使用包装器类来处理他们，然后放在过滤器的前面即可
        //首先把返回去的设置好
		response.setContentType("text/html;charset=utf-8");
		//调用请求装饰类。改变传递请求参数
		HttpMessyCode req = new HttpMessyCode((HttpServletRequest) request);
		chain.doFilter(req, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	     System.out.println("中文乱码过滤器初始化完毕");
	}
}
