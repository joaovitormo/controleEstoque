package com.produtos.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.model.clientes;
import com.produtos.apirest.repository.clientesRepository;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class clientesController {

	@Autowired
	private clientesRepository clientesRepository;

	@GetMapping
	public ResponseEntity<List<clientes>> GetAll() {
		return ResponseEntity.ok(clientesRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<clientes> getById(@PathVariable long id){
		return clientesRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	// Listar por nome

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<clientes>> getByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(clientesRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	// Criar cliente

	@PostMapping
	public ResponseEntity<clientes> postClientes(@RequestBody clientes clientes) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clientesRepository.save(clientes));
	}

	@PutMapping("/alterar")
	public ResponseEntity<clientes> putClientes(@RequestBody clientes clientes) {
		return ResponseEntity.status(HttpStatus.OK).body(clientesRepository.save(clientes));
	}

	@DeleteMapping("/{id}")
	public void deleteClientes(@PathVariable long id) {
		clientesRepository.deleteById(id);
	}

}
