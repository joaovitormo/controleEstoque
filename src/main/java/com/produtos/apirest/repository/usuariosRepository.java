package com.produtos.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produtos.apirest.model.usuarios;

@Repository
public interface usuariosRepository extends JpaRepository <usuarios, Long> {
	public Optional<usuarios> findByLEmail(String email);
	
	public usuarios findByNome(String nome);
}
