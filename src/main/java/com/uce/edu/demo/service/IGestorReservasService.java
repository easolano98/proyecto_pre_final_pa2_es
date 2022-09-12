package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

public interface IGestorReservasService {

	public String reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public List<LocalDateTime> verificarDisponibilidad(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public void retirarVehiculo(String numero);
	
}
