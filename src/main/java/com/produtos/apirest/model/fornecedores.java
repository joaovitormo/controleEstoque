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
@Table(name="tb_fornecedores")
public class fornecedores {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	private String email;
	
	private Long celular;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());

	@OneToMany(mappedBy = "fornecedores", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("fornecedores")
    private List<produtos> produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<produtos> getProduto() {
		return produto;
	}

	public void setProduto(List<produtos> produto) {
		this.produto = produto;
	}
    
    
    
    
}
