package com.projecto.java.service;

import java.util.List;

import com.projecto.java.entity.Articulo;

public interface ArticuloService {

	public List<Articulo> findAll();

	public Articulo findById(Long codArticulo);

	public Articulo findByNombre(String nombre);

	public Articulo save(Articulo codArticulo);

	public void delete(Long codArticulo);

}