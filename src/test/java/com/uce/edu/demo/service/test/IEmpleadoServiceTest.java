package com.uce.edu.demo.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.dto.VehiculoVIP;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IEmpleadoService;
import com.uce.edu.demo.service.IGestorReservasService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@SpringBootTest
@Transactional
@Rollback(true)
class IEmpleadoServiceTest {

	@Autowired
	private IEmpleadoService empleadoService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IGestorReservasService gestorReservasService;

	@Autowired
	private IReservaService iReservaService;

	@Autowired
	private IGestorReservasService iGestorReservasService;

	@Test
	void testRegistrarCliente() {
		Cliente clie = new Cliente();

		clie.setCedula("1720757103");
		clie.setApellido("Perez");
		clie.setNombre("Juan");
		clie.setGenero("M");
		clie.setRegistro("E");
		clie.setFechaNacimiento(LocalDateTime.of(1999, 9, 28, 17, 30));

		this.clienteService.insertar(clie);

		assertThat(this.clienteService.buscarCedula(clie.getCedula()).getId()).isNotNull();
	}

	@Test
	void testIngresarVehiculo() {
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

		this.empleadoService.ingresarVehiculo(v);

		assertThat(this.iVehiculoService.buscarPlaca(v.getPlaca()).getPlaca()).isNotNull();
	}

	@Test
	void testVehiculosVIP() {
		Vehiculo vehiculo1 = new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje("18CV");
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PAWiTeeeerre-1298");
		vehiculo1.setValorPorDia(new BigDecimal(40));
		this.iVehiculoService.insertar(vehiculo1);

		Cliente cliente = new Cliente();
		cliente.setApellido("Velez");
		cliente.setCedula("789900rr0ee");
		cliente.setFechaNacimiento(LocalDateTime.of(1999, 5, 7, 17, 30));
		cliente.setGenero("F");
		cliente.setNombre("Alexandra");
		this.clienteService.insertar(cliente);

		this.iGestorReservasService.reservarVehiculo("CXX-333", "1720230315", LocalDateTime.of(2022, 9, 21, 0, 0),
				LocalDateTime.of(2022, 9, 23, 0, 0));

		List<VehiculoVIP> listaVIP = this.empleadoService.vehiculosVIP("2022", "11");
		assertThat(listaVIP).isNotEmpty();
	}

}
