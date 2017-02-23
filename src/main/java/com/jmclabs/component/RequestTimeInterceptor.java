package com.jmclabs.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jmclabs.repository.LogRepository;

// TODO: Auto-generated Javadoc
/*
 * Interceptor de Spring MVC- 
 * Extendemos de una clase de Spring HandlerInterceptorAdapter.
 * Hará que todas las peticiones entren por este método antes 
 * de entrar por los métodos de los Controladores.
 * 
 * Sobreescribimos métodos de esa clase para manejar su comportamiento
 * al entrar al Interceptor y al salir del Controlador hacia la vista.
 */

/**
 * The Class RequestTimeInterceptor.
 */
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	/** The log repository. */
	
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

	/** The total time. */
	public long totalTime = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
	 * preHandle(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// se ejecuta antes de entrar en el Controlador
		LOG.info("preHandle() - [IN] -- Hello form Interceptor!!");
		// añadimos como atributo a la request la hora actual en milisegundos
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
	 * afterCompletion(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Se ejecuta antes de salir hacia la vista desde el controlador, justo
		// antes del método de return del Controlador
		// recuperamos el atributo guardado en la request por medio de su nombre
		long startTime = (long) request.getAttribute("startTime");
		// this.totalTime = System.currentTimeMillis() - startTime;
		setTotalTime(System.currentTimeMillis() - startTime);

		String url = request.getRequestURL().toString();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if (null != auth && auth.isAuthenticated()) {
			username = auth.getName();
		}

		// com.jmclabs.entity.Log log = new com.jmclabs.entity.Log(new Date(),
		// auth.getDetails().toString(), username, url);
		// llamamos al repo y pasamos la entity Log
		
		logRepository.save(new com.jmclabs.entity.Log(new Date(), auth.getDetails().toString(), username, url));

		LOG.info("afterCompletion() - [OUT] " + "\nRequest URL: " + url + "\nTOTAL TIME: "
				+ (System.currentTimeMillis() - startTime) + "ms " + "\nServer Name: " + request.getServerName()
				+ "\nRemote Addres:" + request.getRemoteAddr() + "\nServer Port: " + request.getServerPort());

		// super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * Gets the total time.
	 *
	 * @return the total time
	 */
	// getters & setters aux para utilizar en ejercicio
	public long getTotalTime() {
		return totalTime;
	}

	/**
	 * Sets the total time.
	 *
	 * @param totalTime
	 *            the new total time
	 */
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

}