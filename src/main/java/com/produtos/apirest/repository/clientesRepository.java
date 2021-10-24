package com.produtos.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produtos.apirest.model.clientes;
import com.produtos.apirest.model.produtos;



@Repository
public interface clientesRepository extends JpaRepository <clientes, Long> {
	public Optional<clientes> findByEmail(String email);
	
	public List<clientes> findAllByNomeContainingIgnoreCase(String nome);

}
