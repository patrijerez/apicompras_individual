package com.projecto.java.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.entity.Articulo;

@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, Long> {
	
	@Query("select a from Articulo a where a.nombre=?1")
	public Articulo findByNombre(String nombre);
}
