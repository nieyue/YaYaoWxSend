package com.dlg.wxsend.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

@Component
@ServletComponentScan
@WebFilter(filterName="CORSFilter",urlPatterns={"/*"})
public class CORSFilter implements Filter {
public void destroy() {}
@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) req;
	 HttpServletResponse response = (HttpServletResponse) res;
	 	response.setHeader("Access-Control-Allow-Credentials", "true");
	 	response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));  //"表明允许"http://foo.org"发起跨域请求
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  //表明它允许GET、PUT、DELETE的外域请求
	    response.setHeader("Access-Control-Max-Age", "3600");   //表明在3628800秒内，不需要再发送预检验请求，可以缓存该结果
	    response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");  //表明它允许跨域请求包含content-type头等
	    chain.doFilter(request, response);
	
}
@Override
public void init(FilterConfig arg0) throws ServletException {
	// TODO Auto-generated method stub
	
}
}
