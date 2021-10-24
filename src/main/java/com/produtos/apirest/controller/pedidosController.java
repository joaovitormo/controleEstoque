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


import com.produtos.apirest.model.pedidos;
import com.produtos.apirest.repository.pedidosRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class pedidosController {
	
	@Autowired
	private pedidosRepository pedidosRepository;
	
	@GetMapping
	public ResponseEntity<List<pedidos>> GetAll() {
		return ResponseEntity.ok(pedidosRepository.findAll());
	}
	
	//Listar por id 
	
	@GetMapping("/{id}")
	public ResponseEntity<pedidos> GetById(@PathVariable long id){
		return pedidosRepository.findById(id)
		.map(resp -> ResponseEntity.ok(resp))
		.orElse(ResponseEntity.notFound().build());	
	}
	
	@PostMapping
	public ResponseEntity<pedidos> postPedidos(@RequestBody pedidos pedidos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidosRepository.save(pedidos));
	}

	@PutMapping("/{id]")
	public ResponseEntity<pedidos> putPedidos(@RequestBody pedidos pedidos) {
		return ResponseEntity.status(HttpStatus.OK).body(pedidosRepository.save(pedidos));
	}
	
	@DeleteMapping("/{id}")
	public void deletePedidos(@PathVariable long id) {
		pedidosRepository.deleteById(id);
	}

}
