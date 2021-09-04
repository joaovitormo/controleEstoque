package com.produtos.apirest.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.produtos.apirest.model.usuarios;
import com.produtos.apirest.model.usuariosLogin;
import com.produtos.apirest.repository.usuariosRepository;

@Service
public class usuariosService {
	
	@Autowired
	private usuariosRepository usuarioRepository;
	
	public usuarios cadastrarUsuario(usuarios usuario) {
		
		if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Email já cadastrado!", null);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return usuarioRepository.save(usuario);
	}
	
	public Optional<usuarios> atualizarUsuario(usuarios usuario) {
		
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			
			return Optional.of(usuarioRepository.save(usuario));
			
		} else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
			
		}
	}
	
	public Optional<usuariosLogin> logarUsuario(Optional<usuariosLogin> usuarioLogin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<usuarios> usuario = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());
		
		if(usuario.isPresent()) {
			if (encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = usuarioLogin.get().getEmail() + ":" + usuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				
				usuarioLogin.get().setToken(authHeader);
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setEmail(usuario.get().getEmail());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setId(usuario.get().getId());
				
				return usuarioLogin;
				
			}
		}
		
		return null;
	}

}
