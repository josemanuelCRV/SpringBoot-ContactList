package com.jmclabs.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCryptPasswordEncoder
 * 
 * Cifrar el password. Creamos un usuario de prueba en la db y en campo password
 * le añadimos la clave gerenada
 * 
 * @author josem
 *
 */
public class TestCrypt {

	public static void main(String[] args) {

		BCryptPasswordEncoder pswEncode = new BCryptPasswordEncoder();

		System.out.println(pswEncode.encode("user"));

	}

}
