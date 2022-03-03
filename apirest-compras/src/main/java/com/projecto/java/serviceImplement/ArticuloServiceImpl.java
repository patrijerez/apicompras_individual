package com.projecto.java.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.Dao.ArticuloRepository;
import com.projecto.java.entity.Articulo;
import com.projecto.java.service.ArticuloService;

@Service
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	private ArticuloRepository articuloRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findAll() {
		return (List<Articulo>) articuloRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findById(Long codArticulo) {
		return articuloRepository.findById(codArticulo).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findByNombre(String nombre) {
		return articuloRepository.findByNombre(nombre);
	}

	@Override
	@Transactional
	public Articulo save(Articulo articulo) {
		return articuloRepository.save(articulo);
	}

	@Override
	@Transactional
	public void delete(Long codArticulo) {
		articuloRepository.deleteById(codArticulo);

	}

}
