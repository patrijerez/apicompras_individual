package com.projecto.java.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.entity.Usuario;



@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Long> {
	public Usuario findByUsername(String username);

	// Tb se podría hacer:
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);
}
