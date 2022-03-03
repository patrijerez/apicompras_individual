package com.projecto.java.service;

import java.util.Date;
import java.util.List;

import com.projecto.java.entity.Compra;

public interface CompraService {

	public List<Compra> findAll();

	public Compra findById(Long id);
	
	public List<Compra> findByFechaBetween(Date fechaI, Date fechaF);

	public Compra save(Compra compra);

	public void delete(Long id);
	
	public void deleteCompra(Compra c);
}