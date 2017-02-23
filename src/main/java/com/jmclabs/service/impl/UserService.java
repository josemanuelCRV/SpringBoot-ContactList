package com.jmclabs.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jmclabs.entity.User;
import com.jmclabs.entity.UserRole;
import com.jmclabs.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service("userService")
public class UserService implements UserDetailsService {

	// UserDetailsService para identicar usuarios

	/** The user repository. */
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 * 
	 * llamara a nuestro repository findByUsername() y obtendra una entidad
	 * usuario habrá que converirla en un userDetail crearemos para ello 2
	 * metodos privados
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthority(user.getUserRole());
		return buildUser(user, authorities);
	}

	/**
	 * Builds the user.
	 * 
	 * <GrantedAuthority> representa a la entidad role
	 *
	 * @param user
	 *            the user
	 * @param authorities
	 *            the authorities
	 * @return the org.springframework.security.core.userdetails. user
	 */
	private org.springframework.security.core.userdetails.User buildUser(User user,
			List<GrantedAuthority> authorities) {

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnable(), true, true, true, authorities);
	}

	/**
	 * Builds the authority.
	 *
	 * Convierta los roles a una lista del objeto GrantedAuthority Set de roles
	 * del usuario a un listado de GrantedAuthority que es lo que SPring
	 * Security necesita para saber los roles del usuario autenticado
	 *
	 * @param userRoles
	 *            the user roles
	 * @return the list
	 */
	private List<GrantedAuthority> buildAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		// recorremos los userrole que nos están llegando y por cada vuelta del
		// for va a ir guardando en auths el role que tiene cada uno
		for (UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		// devolvemos la lista de roles
		return new ArrayList<GrantedAuthority>(auths);
	}

}
