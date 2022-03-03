package com.projecto.java.controller;

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

import com.projecto.java.entity.Articulo;
import com.projecto.java.service.ArticuloService;

@RestController
@RequestMapping("/api")
public class ArticuloRestController {

	@Autowired
	private ArticuloService servicio;

	@GetMapping("/articulos")
	public List<Articulo> index() {
		return servicio.findAll();
	}

	@GetMapping("articulo/{codArticulo}")
	public ResponseEntity<?> findProductoById(@PathVariable Long codArticulo) {
		Articulo articulo = null;
		Map<String, Object> response = new HashMap<>();

		try {
			articulo = servicio.findById(codArticulo);
		} catch (DataAccessException e) {// MUY ESPECÍFICO, EXCEPCIONES SOBRE EL DAO
			response.put("mensaje", "Error al rellenar la consulta a base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (articulo == null) {
			response.put("mensaje",
					"El Código de articulo: ".concat(codArticulo.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
	}

	@GetMapping("/articulo/buscarPorNombre/{nombre}") // Paso el nombre en la dirección
	public ResponseEntity<?> findArticuloByNombre(@PathVariable String nombre) {

		Articulo articulo = null;
		Map<String, Object> response = new HashMap<>();

		try {

			articulo = servicio.findByNombre(nombre);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (articulo == null) {
			response.put("mensaje",
					"El artículo con nombre: ".concat(nombre.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
	}

	@PostMapping("/articulo/saveProducto")
	public ResponseEntity<?> saveProducto(@RequestBody Articulo articulo) {
		Articulo articuloNew = null;

		Map<String, Object> response = new HashMap<>();

		try {
			articuloNew = servicio.save(articulo);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al rellenar la consulta a base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El articulo ha sido creado con éxito!");
		response.put("producto", articuloNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/articulo/updateArticulo/{codArticulo}")
	public ResponseEntity<?> updateCliente(@RequestBody Articulo articulo, @PathVariable Long codArticulo) {

		Articulo articuloActual = servicio.findById(codArticulo);

		Map<String, Object> response = new HashMap<>();

		if (articuloActual == null) {
			response.put("mensaje", "Error: no se pudo editar. El producto con Codigo: " + codArticulo.toString()
					+ " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			articuloActual.setCodArticulo(codArticulo);
			articuloActual.setUnidades_stock(articulo.getUnidades_stock());
			articuloActual.setStock_seguridad(articulo.getStock_seguridad());
			articuloActual.setImagen(articulo.getImagen());
			articuloActual.setNombre(articulo.getNombre());
			articuloActual.setPrecio_unidad(articulo.getPrecio_unidad());
			articuloActual.setDescripcion(articulo.getDescripcion());

			servicio.save(articuloActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al rellenar la consulta a base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El articulo ha sido actualizado con éxito!");
		response.put("cliente", articuloActual);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/articulo/deleteArticulo/{codArticulo}")
	public ResponseEntity<?> deleteArticulo(@PathVariable Long codArticulo) {

		Articulo articuloBorrar = servicio.findById(codArticulo);

		Map<String, Object> response = new HashMap<>();

		if (articuloBorrar == null) {

			response.put("mensaje", "El articulo ID: ".concat(codArticulo.toString().concat(" no se pudo eliminar")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		try {
			servicio.delete(codArticulo);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la eliminación en la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "¡El articulo ha sido borrado con exito!");
		response.put("articulo", articuloBorrar);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
