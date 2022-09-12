package com.uce.edu.demo.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.service.IClienteService;
@SpringBootTest
@Transactional
@Rollback(true)
public class ClienteServiceTest {
	@Autowired
	private IClienteService iClienteService;

	@Test
	void testInsertar() {
		Cliente clie = new Cliente();

		clie.setCedula("1720757101");
		clie.setApellido("Cen");
		clie.setNombre("Michael");
		clie.setGenero("M");
		clie.setFechaNacimiento(LocalDateTime.of(1999, 9, 28, 17, 30));

		this.iClienteService.insertar(clie);
		assertThat(this.iClienteService.buscarCedula(clie.getCedula()).getId()).isNotNull();
	}

	@Test
	void testBuscarCedula() {
		Cliente clie = new Cliente();
		clie.setCedula("1720075456");
		clie.setApellido("Piruch");
		clie.setNombre("Edwin");
		clie.setGenero("M");
		clie.setFechaNacimiento(LocalDateTime.of(1999, 5, 7, 17, 30));
		this.iClienteService.insertar(clie);
		Cliente clienteBuscado = this.iClienteService.buscarCedula(clie.getCedula());
		assertEquals(clie, clienteBuscado);
	}

	@Test
	void testBuscarApellido() {
		Cliente clie = new Cliente();
		clie.setCedula("1720757101");
		clie.setApellido("Solano");
		clie.setNombre("Erick");
		clie.setGenero("M");
		clie.setFechaNacimiento(LocalDateTime.of(1998, 5, 8, 17, 30));
		this.iClienteService.insertar(clie);

		List<Cliente> clienteBuscado = this.iClienteService.buscarApellido(clie.getApellido());
		assertThat(clienteBuscado).isNotEmpty();
	}

	@Test
	void testActualizar() {
		Cliente clie = new Cliente();
		clie.setCedula("1720075456");
		clie.setApellido("Piruch");
		clie.setNombre("Edwin");
		clie.setGenero("M");
		clie.setFechaNacimiento(LocalDateTime.of(1999, 5, 7, 17, 30));
		this.iClienteService.insertar(clie);

		clie.setCedula("1720075455");
		this.iClienteService.actualizar(clie);

		assertEquals(this.iClienteService.buscarCedula(clie.getCedula()).getCedula(), clie.getCedula());
	}

	@Test
	void testEliminar() {
		Cliente c = new Cliente();
		c.setCedula("1313151621");
		this.iClienteService.eliminarPorCedula(c.getCedula());
		try {
			Cliente cbusqueda = this.iClienteService.buscarCedula(c.getCedula());
			assertThat(cbusqueda).isNotNull();
		} catch (RuntimeException e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
}
