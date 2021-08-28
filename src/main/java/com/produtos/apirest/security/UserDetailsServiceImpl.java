package com.produtos.apirest.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.produtos.apirest.model.usuarios;
import com.produtos.apirest.repository.usuariosRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private usuariosRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<usuarios> usuario = userRepository.findByLEmail(username);
		usuario.orElseThrow(()-> new UsernameNotFoundException(username + " not found."));
		
		return usuario.map(UserDetailsImpl::new).get();
	}

}
