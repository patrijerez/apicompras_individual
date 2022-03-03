package com.projecto.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "compras")
public class Compra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_compra;

	private int unidades;

	@Temporal(TemporalType.DATE)
	private Date fecha;

//	Method
	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

//	Getters and Setters
	public Long getCod_compra() {
		return cod_compra;
	}

	public void setCod_compra(Long cod_compra) {
		this.cod_compra = cod_compra;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
