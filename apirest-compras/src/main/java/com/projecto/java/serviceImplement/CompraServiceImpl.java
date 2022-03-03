package com.projecto.java.serviceImplement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.Dao.CompraRepository;
import com.projecto.java.entity.Compra;
import com.projecto.java.service.CompraService;

@Service
public class CompraServiceImpl implements CompraService {

	@Autowired
	private CompraRepository compraDao;

	@Override
	@Transactional(readOnly = true)
	public List<Compra> findAll() {

		return (List<Compra>) compraDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Compra findById(Long id) {

		return compraDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Compra> findByFechaBetween(Date fechaI, Date fechaF) {

		return compraDao.findByFechaBetween(fechaI, fechaF);
	}

	@Override
	@Transactional
	public Compra save(Compra compra) {

		return compraDao.save(compra);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		compraDao.deleteById(id);

	}

	@Override
	@Transactional
	public void deleteCompra(Compra c) {
		compraDao.delete(c);

	}

}
