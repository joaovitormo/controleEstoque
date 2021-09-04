package com.produtos.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.model.usuarios;
import com.produtos.apirest.model.usuariosLogin;
import com.produtos.apirest.repository.usuariosRepository;
import com.produtos.apirest.service.usuariosService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class usuariosController {
	
	@Autowired
	private usuariosRepository usuariosRepository;
	
	@Autowired
	private usuariosService usuariosService;
	
	
	@GetMapping
	public ResponseEntity<List<usuarios>> getAll(){
		return ResponseEntity.ok(usuariosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<usuarios> getById(@PathVariable long id){
		return usuariosRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/login")
	public ResponseEntity<usuariosLogin> autenticationUsuario(@RequestBody Optional<usuariosLogin> usuariologin) {
		return usuariosService.logarUsuario(usuariologin)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());	
	}	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<usuarios> postUsuarios(@RequestBody usuarios usuarios){
		usuarios newUser = usuariosService.cadastrarUsuario(usuarios);
		
		try {
			return ResponseEntity.ok(newUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<usuarios> putUsuarios(@RequestBody usuarios usuarios) {
		
		Optional<usuarios> updateUser = usuariosService.atualizarUsuario(usuarios);
		try {
			return ResponseEntity.ok(updateUser.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
