package com.produtos.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produtos.apirest.model.fornecedores;

@Repository
public interface fornecedoresRepository extends JpaRepository <fornecedores, Long>{
	public Optional<fornecedores> findByEmail(String email);
	
	public List<fornecedores> findAllByNomeContainingIgnoreCase(String nome);

}
