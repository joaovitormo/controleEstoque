package com.produtos.apirest.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name="tb_clientes")
public class clientes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String nome;
	
	private String email;
	
	private Long celular;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());

	@OneToMany(mappedBy = "clientes", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("clientes")
    private List<pedidos> pedido;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<pedidos> getPedido() {
		return pedido;
	}

	public void setPedido(List<pedidos> pedido) {
		this.pedido = pedido;
	}
	
	
	
	
}
