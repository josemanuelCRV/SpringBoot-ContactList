package com.jmclabs.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// TODO: Auto-generated Javadoc
// @EnableGlobalMethodSecurity(prePostEnabled = true) 
// permite incluir anotaciones para securizar los métodos

/**
 * The Class SecurityConfiguration.
 * 
 * Securizando los métodos o clases de la aplicación
 * 
 * @EnableGlobalMethodSecurity permite incluir anotaciones para securizar los
 *                             métodos
 * 
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/** The user service. */
	@Autowired
	@Qualifier("userService")
	private UserDetailsService userService;

	//

	/**
	 * Configure global.
	 * 
	 * Añade el userDetailService y cifra el password
	 * 
	 * Sobreescribimos la configuración
	 * 
	 * @param auth
	 *            the auth
	 * 
	 * @throws Exception
	 *             the exception
	 * 
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.HttpSecurity)
	 * 
	 * sobreescribimos la configuración
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/*", "/imgs/*").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").loginProcessingUrl("/logincheck").usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/loginsuccess").permitAll().and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();

	}

}
