package com.projecto.java;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projecto.java.entity.Compra;
import com.projecto.java.service.CompraService;

@SpringBootTest
class ApirestComprasApplicationTests {
	@Autowired
	CompraService servicio;
	
	@Test
	void buscarFechas() throws Exception {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date fecha1 = formato.parse("01/03/2022");
		Date fecha2 = formato.parse("03/03/2022");
		List<Compra> resultado = servicio.findByFechaBetween(fecha1,fecha2);
		 assertEquals(3,resultado.size());
	}

}
