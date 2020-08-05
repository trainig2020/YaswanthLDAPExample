package com.yaswanth.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { DbConfig.class };
		//return null;
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { };
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
 
}