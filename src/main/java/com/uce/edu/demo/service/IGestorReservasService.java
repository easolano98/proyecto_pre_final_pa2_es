package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.dto.VehiculoVIP;

public interface IGestorReservasService {

	public String reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public List<LocalDateTime> verificarDisponibilidad(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public VehiculoVIP retirarVehiculo(String numero);
	
}
