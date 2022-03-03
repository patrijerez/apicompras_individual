package com.projecto.java.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.Dao.ClienteRepository;
import com.projecto.java.entity.Cliente;
import com.projecto.java.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repositorio;

	@Override
	@Transactional(readOnly = true)
	public void delete(Long codCliente) {
		repositorio.deleteById(codCliente);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) repositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long codCliente) {
		return repositorio.findById(codCliente).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return repositorio.save(cliente);
	}

	@Override
	@Transactional
	public Cliente deleteConRetorno(Long codCliente) {
		Cliente c = repositorio.findById(codCliente).get();
		repositorio.deleteById(codCliente);
		return c;
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findByNombre(String nombre) {
		return repositorio.findByNombre(nombre);
	}

}
