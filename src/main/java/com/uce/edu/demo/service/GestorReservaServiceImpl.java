package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.IVehiculoRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;

@Service
public class GestorReservaServiceImpl implements IGestorReservasService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private IReservaRepository reservaRepository;

	private static final Logger LOG = Logger.getLogger(GestorReservaServiceImpl.class);

	@Override
	public void reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		Vehiculo vehiculo = this.vehiculoRepository.buscarPlaca(placa);

		Cliente cliente = this.clienteRepository.buscarCedula(cedula);

		int longitud = this.rangoFecha(fechaInicio, fechaFin).size();
		List<LocalDateTime> disponibles = this.verificarDisponibilidad(placa, fechaInicio, fechaFin);

		if (longitud == disponibles.size()) {
			Reserva reserva = new Reserva();

			reserva.setEstado("G");
			reserva.setFechaInicio(fechaInicio);
			reserva.setFechaFin(fechaFin);
			reserva.setCliente(cliente);
			reserva.setVehiculo(vehiculo);

			BigDecimal subtotal = vehiculo.getValorPorDia().multiply(new BigDecimal(longitud + 1));
			reserva.setSubTotal(subtotal);
			reserva.setIva(subtotal.multiply(new BigDecimal(12)).divide(new BigDecimal(100)));
			reserva.setTotalPagar(subtotal.add(reserva.getIva()));

			this.reservaRepository.insertar(reserva);
			reserva.setNumero(reserva.getId() + "");
			this.reservaRepository.actualizar(reserva);

			LOG.info("Número de reserva: " + reserva.getNumero());

		} else {
			if (!disponibles.isEmpty()) {
				LOG.info(" *** Fechas Disponibles ***");
				disponibles.forEach(f -> LOG.info(f.getDayOfMonth() + "/" + f.getMonthValue() + "/" + f.getYear()));
			} else
				LOG.info("No hay fechas disponibles en el intervalo de fechas indicado.");
		}

	}

	@Override
	public List<LocalDateTime> verificarDisponibilidad(String placa, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		List<Reserva> listaReservas = this.reservaRepository
				.buscarPorVehiculo(this.vehiculoRepository.buscarPlaca(placa));

		List<LocalDateTime> disponibles = this.rangoFecha(fechaInicio, fechaFin);
		List<LocalDateTime> fechasReservadas = new ArrayList<>();

		if (!listaReservas.isEmpty()) {
			for (Reserva r : listaReservas) {
				List<LocalDateTime> reservadas = this.rangoFecha(r.getFechaInicio(), r.getFechaFin());
				fechasReservadas.addAll(reservadas);
			}
			List<Integer> fechasReservadasTransformadas = fechasReservadas.stream().map(f -> f.getDayOfYear())
					.collect(Collectors.toList());
			disponibles = disponibles.stream().filter(i -> !fechasReservadasTransformadas.contains(i.getDayOfYear()))
					.collect(Collectors.toList());
		}

		return disponibles;
	}

	private List<LocalDateTime> rangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		List<LocalDateTime> rangoFecha = new ArrayList<>();
		rangoFecha.add(fechaInicio);
		int tiempoReserva = fechaFin.getDayOfYear() - fechaInicio.getDayOfYear();
		for (int i = 1; i <= tiempoReserva; i++) {
			LocalDateTime fecha = fechaInicio.plusDays(i);
			rangoFecha.add(fecha);
		}
		return rangoFecha;
	}

	@Override
	public void retirarVehiculo(String numero) {
		// TODO Auto-generated method stub
		Reserva r = this.reservaRepository.buscarNumero(numero);
		// Dia reserva <= dia actual
		if (r.getFechaInicio().getDayOfYear() <= LocalDateTime.now().getDayOfYear() && r.getEstado().equals("G")) {
			r.getVehiculo().setEstado("ND");
			r.setEstado("E");
			this.vehiculoRepository.actualizar(r.getVehiculo());
			this.reservaRepository.actualizar(r);
		} else {
			System.out.println("Aun no ocurre el dia de reserva");
		}
	}
	
	

}
