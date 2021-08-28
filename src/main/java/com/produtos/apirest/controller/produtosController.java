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

import com.produtos.apirest.model.produtos;
import com.produtos.apirest.repository.produtosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class produtosController {
	
	@Autowired
	produtosRepository produtosRepository;
	
	
	@GetMapping
	public ResponseEntity<List<produtos>> GetAll() {
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	//Listar por nome
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<produtos>> getByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	//Listar por id
	@GetMapping("/{id}")
	public ResponseEntity<produtos> getById(@PathVariable long id){
		return produtosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Criar produto
	
	@PostMapping
	public ResponseEntity<produtos> postProdutos (@RequestBody produtos produtos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));
	}
	
	@PutMapping("/{id]")
	public ResponseEntity<produtos> putProdutos (@RequestBody produtos produtos) {
		return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produtos));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProdutos(@PathVariable long id) {
		produtosRepository.deleteById(id);
	}
	

}
