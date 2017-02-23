package com.jmclabs.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmclabs.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{
	// metodo que llamaremos desde el servicio
	// buscaremos un usuario por el username
	public abstract User findByUsername(String username);
	
	//obtendremos una entidad usuario
}
