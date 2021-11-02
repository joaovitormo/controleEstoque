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

import com.produtos.apirest.model.fornecedores;
import com.produtos.apirest.repository.fornecedoresRepository;



@RestController
@RequestMapping("/fornecedores")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class fornecedoresController {
	
	@Autowired
	private fornecedoresRepository fornecedoresRepository;
	
	@GetMapping
	public ResponseEntity<List<fornecedores>> GetAll() {
		return ResponseEntity.ok(fornecedoresRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<fornecedores> getById(@PathVariable long id){
		return fornecedoresRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	// Listar por nome
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<fornecedores>> getByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(fornecedoresRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<fornecedores> postFornecedores(@RequestBody fornecedores fornecedores) {
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedoresRepository.save(fornecedores));
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<fornecedores> putFornecedores(@RequestBody fornecedores fornecedores) {
		return ResponseEntity.status(HttpStatus.OK).body(fornecedoresRepository.save(fornecedores));
	}
	
	@DeleteMapping("/{id}")
	public void deleteFornecedores(@PathVariable long id) {
		fornecedoresRepository.deleteById(id);
	}

}
