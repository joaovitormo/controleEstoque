package com.produtos.apirest.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name="tb_pedidos")
public class pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String formPag;
	
	private Float valorTotal;
	
	private Long numVezes;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCompra = new java.sql.Date(System.currentTimeMillis());
	
	
	@OneToMany(mappedBy = "pedidos", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pedidos")
    private List<produtos> produto;
    
    
	@ManyToOne
	@JsonIgnoreProperties("pedidos")
	private usuarios usuarios;
	
	@ManyToOne
	@JsonIgnoreProperties("pedidos")
	private clientes clientes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFormPag() {
		return formPag;
	}

	public void setFormPag(String formPag) {
		this.formPag = formPag;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getNumVezes() {
		return numVezes;
	}

	public void setNumVezes(Long numVezes) {
		this.numVezes = numVezes;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	
	public List<produtos> getProduto() {
		return produto;
	}

	public void setProduto(List<produtos> produto) {
		this.produto = produto;
	}

	public usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public clientes getClientes() {
		return clientes;
	}

	public void setClientes(clientes clientes) {
		this.clientes = clientes;
	}
	
	
}
