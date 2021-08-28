package com.produtos.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produtos.apirest.model.produtos;

@Repository
public interface produtosRepository extends JpaRepository<produtos, Long>{

	public List<produtos> findAllByNomeContainingIgnoreCase(String nome);
	
	//a jpa repository, já trás todos os métodos padrão para usar na controller
}
