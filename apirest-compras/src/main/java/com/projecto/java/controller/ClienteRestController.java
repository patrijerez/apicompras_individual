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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projecto.java.entity.Cliente;
import com.projecto.java.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private ClienteService servicio;

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return servicio.findAll();
	}

	@GetMapping("/cliente/{codCliente}") // Paso el id en la dirección
	public ResponseEntity<?> findClienteById(@PathVariable Long codCliente) {

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {

			cliente = servicio.findById(codCliente);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (cliente == null) {
			response.put("mensaje",
					"El cliente ID: ".concat(codCliente.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@GetMapping("/cliente/buscarPorNombre/{nombre}") // Paso el nombre en la dirección
	public ResponseEntity<?> findClienteByNombre(@PathVariable String nombre) {

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {

			cliente = servicio.findByNombre(nombre);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (cliente == null) {
			response.put("mensaje",
					"El cliente con nombre: ".concat(nombre.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping("/cliente/saveCliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		Map<String, Object> response = new HashMap<>();

		try {
			servicio.save(cliente);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "¡El cliente ha sido creado con exito!");
		response.put("cliente", cliente);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/cliente/updateCliente/{codCliente}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> upDateCliente(@RequestBody Cliente cliente, @PathVariable Long codCliente) {

		Cliente clienteActual = servicio.findById(codCliente);

		Map<String, Object> response = new HashMap<>();

		if (clienteActual == null) {
			response.put("mensaje",
					"El cliente ID: ".concat(codCliente.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			clienteActual.setCodCliente(codCliente);
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellidos(cliente.getApellidos());
			clienteActual.setEmpresa(cliente.getEmpresa());
			clienteActual.setPuesto(cliente.getPuesto());
			clienteActual.setCp(cliente.getCp());
			clienteActual.setProvincia(cliente.getProvincia());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setFechaNacimiento(cliente.getFechaNacimiento());

			servicio.save(clienteActual);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la actualización a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "¡El cliente ha sido actualizado con exito!");
		response.put("cliente", clienteActual);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@DeleteMapping("/cliente/deleteCliente/{codCliente}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long codCliente) {

		Cliente clienteABorrar = servicio.findById(codCliente);

		Map<String, Object> response = new HashMap<>();

		if (clienteABorrar == null) {

			response.put("mensaje", "El cliente ID: ".concat(codCliente.toString().concat(" no se pudo eliminar")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		try {
			servicio.delete(codCliente);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la eliminación en la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "¡El cliente ha sido borrado con exito!");
		response.put("cliente", clienteABorrar);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
