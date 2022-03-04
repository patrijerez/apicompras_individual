package com.projecto.java.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projecto.java.entity.Compra;
import com.projecto.java.service.CompraService;

@RestController
@RequestMapping("/api")
public class CompraRestController {

	@Autowired
	private CompraService servicio;

	@GetMapping("/compras")
	public List<Compra> index() {
		return servicio.findAll();
	}

	@GetMapping("/compra/{cod_compra}")
	public ResponseEntity<?> buscarCompraporId(@PathVariable Long cod_compra) {

		Compra compra = null;

		Map<String, Object> response = new HashMap<>();

		try {
			compra = servicio.findById(cod_compra);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (compra == null) {
			response.put("mensaje", "La compra con id:".concat(cod_compra.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Compra>(compra, HttpStatus.OK);
	}

	@GetMapping("/compras/fechas/{fecha1}/{fecha2}")

	public ResponseEntity<?> findByFechaBetween(@PathVariable String fecha1, @PathVariable String fecha2) throws ParseException {

		Map<String, Object> response = new HashMap<>();

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Date fechaI = formato.parse(fecha1);
		Date fechaF = formato.parse(fecha2);

		List<Compra> resultado = new ArrayList<Compra>();
		
		try {
			
			resultado = servicio.findByFechaBetween(fechaI, fechaF);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar compras.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (resultado == null) {
			response.put("mensaje", "No existen compras entre esas fechas.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Búsqueda con éxito.");
		response.put("compra", resultado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PostMapping("/compra/saveCompra")
	public ResponseEntity<?> guardarCompra(@RequestBody Compra compra) {

		Compra compraNuevo = null;
		Map<String, Object> response = new HashMap<>();

		try {
			compraNuevo = servicio.save(compra);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar inserción.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La compra fue creada con éxito.");
		response.put("compra", compraNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/compra/updateCompra/{cod_compra}")
	public ResponseEntity<?> actualizarCompra(@RequestBody Compra compra, @PathVariable Long cod_compra) {

		Compra compraActualizar = servicio.findById(cod_compra);

		Map<String, Object> response = new HashMap<>();

		if (compraActualizar == null) {
			response.put("mensaje", "La compra id:".concat(cod_compra.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			compraActualizar.setCod_compra(cod_compra);
			compraActualizar.setUnidades(compra.getUnidades());
			compraActualizar.setFecha(compra.getFecha());

			servicio.save(compraActualizar);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar actualización.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La compra fue actualizada con éxito.");
		response.put("compra", compraActualizar);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/compra/deleteCompra/{cod_compra}")
	public ResponseEntity<?> eliminarCompra(@PathVariable Long cod_compra) {

		Compra compraActualizar = servicio.findById(cod_compra);
		Map<String, Object> response = new HashMap<>();

		try {
			servicio.delete(cod_compra);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (compraActualizar == null) {
			response.put("mensaje", "La compra id:".concat(cod_compra.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Eliminado con éxito.");
		response.put("compra", compraActualizar);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
