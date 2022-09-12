package com.uce.edu.demo.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;
@SpringBootTest
@Transactional
@Rollback(true)
	class ReservaServiceTest {

	@Autowired
	private IReservaService iReservaService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Test
	void testInsertar() {
		Vehiculo v = new Vehiculo();
		v.setPlaca("ASD-333");
		v.setMarca("Audi");
		v.setModelo("Blackc");
		v.setAvaluo(new BigDecimal(20000));
		v.setAnioFabricacion("2021");
		v.setCilindraje("18CV");
		v.setPaisFabricacion("Francia");
		v.setValorPorDia(new BigDecimal(200));
		v.setEstado("D");

		this.iVehiculoService.insertar(v);

		Reserva reserva = new Reserva();

		reserva.setNumero("0001");
		reserva.setEstado("D");
		reserva.setFechaInicio(LocalDateTime.of(2022, 9, 11, 12, 30));
		reserva.setFechaFin(LocalDateTime.of(2022, 9, 13, 12, 30));
		reserva.setIva(new BigDecimal(12));
		reserva.setSubTotal(new BigDecimal(150));
		reserva.setTotalPagar(new BigDecimal(1500));
		reserva.setVehiculo(v);

		this.iReservaService.insertar(reserva);
		assertThat(this.iReservaService.buscarNumero(reserva.getNumero()).getNumero()).isNotNull();

	}

	@Test
	void testBuscarNumero() {
		Vehiculo v = new Vehiculo();
		v.setPlaca("ASD-333");
		v.setMarca("Audi");
		v.setModelo("Blackc");
		v.setAvaluo(new BigDecimal(20000));
		v.setAnioFabricacion("2021");
		v.setCilindraje("18CV");
		v.setPaisFabricacion("Francia");
		v.setValorPorDia(new BigDecimal(200));
		v.setEstado("D");

		this.iVehiculoService.insertar(v);

		Reserva reserva = new Reserva();
		reserva.setNumero("0001");
		reserva.setEstado("D");
		reserva.setFechaInicio(LocalDateTime.of(2022, 9, 11, 12, 30));
		reserva.setFechaFin(LocalDateTime.of(2022, 9, 13, 12, 30));
		reserva.setIva(new BigDecimal(12));
		reserva.setSubTotal(new BigDecimal(150));
		reserva.setTotalPagar(new BigDecimal(1500));
		reserva.setVehiculo(v);

		this.iReservaService.insertar(reserva);

		Reserva reservaBuscado = this.iReservaService.buscarNumero(reserva.getNumero());
		assertEquals(reserva, reservaBuscado);
	}

	@Test
	void testActualizar() {
		Vehiculo v = new Vehiculo();
		v.setPlaca("ASD-333");
		v.setMarca("Audi");
		v.setModelo("Blackc");
		v.setAvaluo(new BigDecimal(20000));
		v.setAnioFabricacion("2021");
		v.setCilindraje("18CV");
		v.setPaisFabricacion("Francia");
		v.setValorPorDia(new BigDecimal(200));
		v.setEstado("D");

		this.iVehiculoService.insertar(v);

		Reserva reserva = new Reserva();
		reserva.setNumero("0001");
		reserva.setEstado("N");
		reserva.setFechaInicio(LocalDateTime.of(2022, 9, 11, 12, 30));
		reserva.setFechaFin(LocalDateTime.of(2022, 9, 13, 12, 30));
		reserva.setIva(new BigDecimal(12));
		reserva.setSubTotal(new BigDecimal(150));
		reserva.setTotalPagar(new BigDecimal(1500));
		reserva.setVehiculo(v);

		this.iReservaService.insertar(reserva);

		reserva.setNumero("0002");

		this.iReservaService.actualizar(reserva);
		assertEquals(this.iReservaService.buscarNumero(reserva.getNumero()).getNumero(), reserva.getNumero());
	}
	
	}

