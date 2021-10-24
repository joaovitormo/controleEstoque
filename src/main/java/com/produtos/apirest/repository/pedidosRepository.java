package com.produtos.apirest.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produtos.apirest.model.pedidos;

@Repository
public interface pedidosRepository extends JpaRepository <pedidos, Long>{

}
