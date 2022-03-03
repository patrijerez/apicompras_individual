package com.projecto.java.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@Query("select c from Cliente c where c.nombre=?1")
	public Cliente findByNombre(String nombre);

}
