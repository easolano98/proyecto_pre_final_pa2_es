package com.uce.edu.demo.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IVehiculoService;
@SpringBootTest
@Transactional

public class VehiculoServiceTest {
	
		@Autowired
		private IVehiculoService iVehiculoService;

		@Test
		@Rollback(true)
		void testInsertar() {
			Vehiculo v = new Vehiculo();
			v.setPlaca("GXS-2221");
			v.setMarca("Porshe");
			v.setModelo("Ala");
			v.setAvaluo(new BigDecimal(20000));
			v.setAnioFabricacion("2019");
			v.setCilindraje("17CV");
			v.setPaisFabricacion("China");
			v.setValorPorDia(new BigDecimal(100));
			this.iVehiculoService.insertar(v);
			assertThat(this.iVehiculoService.buscarPlaca(v.getPlaca()).getPlaca()).isNotNull();

		}

		@Test
		@Rollback(true)
		void testBuscarPlaca() {
			Vehiculo v = new Vehiculo();
			v.setPlaca("CXX-333");
			assertThat(this.iVehiculoService.buscarPlaca(v.getPlaca()).getPlaca()).isNotNull();
		}

		@Test
		@Rollback(true)
		void testBuscarMarca() {
			Vehiculo v = new Vehiculo();
			v.setMarca("Mazda");
			assertThat(this.iVehiculoService.buscarMarca(v.getMarca())).isNotNull();
		}

		@Test
		@Rollback(true)
		void testBuscarMarcaModeloDisponible() {
			Vehiculo v = new Vehiculo();
			v.setMarca("Mazda");
			v.setModelo("Alegro");
			assertThat(this.iVehiculoService.buscarMarcaModeloDisponible(v.getMarca(), v.getModelo())).isNotNull();
		}

		@Test
		@Rollback(true)
		void testActualizar() {
			Vehiculo v = new Vehiculo();
			v.setPlaca("APM-1921");
			v.setMarca("Lamborghini");
			v.setModelo("ZZ");
			v.setAvaluo(new BigDecimal(19000));
			v.setAnioFabricacion("2018");
			v.setCilindraje("27CV");
			v.setPaisFabricacion("Francia");
			v.setValorPorDia(new BigDecimal(30));
			this.iVehiculoService.insertar(v);

			Vehiculo vbusqueda = this.iVehiculoService.buscarPlaca(v.getPlaca());
			vbusqueda.setModelo("Spark");
			vbusqueda.setPlaca("CXS-412");
			vbusqueda.setModelo("Toyota");
			this.iVehiculoService.actualizar(vbusqueda);
			assertThat(this.iVehiculoService.buscarPlaca(vbusqueda.getPlaca())).isNotNull();
		}

		@Test
		@Rollback(true)
		void testEliminar() {
			Vehiculo v = new Vehiculo();
			v.setPlaca("CXX-333");
			this.iVehiculoService.eliminar(v.getPlaca());
			try {
				Vehiculo vbusqueda = this.iVehiculoService.buscarPlaca(v.getPlaca());
				assertThat(vbusqueda).isNotNull();
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}

		}

		@Test
		@Rollback(true)
		void testRealizarCalculoVehiculo() {
			Vehiculo v = new Vehiculo();
			v.setValorPorDia(new BigDecimal(20));
			v.setPlaca("CXX-333");
			BigDecimal valor = new BigDecimal(0);
			if (valor.compareTo(new BigDecimal(1)) < 0) {
				valor = valor.add(new BigDecimal(20));
			}
			this.iVehiculoService.actualizar(v);
			try {
				assertEquals(this.iVehiculoService.buscarPlaca("CXX-333"), v.getPlaca());
			} catch (RuntimeException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
		

	}
}
