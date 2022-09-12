package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.dto.ClienteVIP;
import com.uce.edu.demo.repository.modelo.dto.ReporteVIP;
import com.uce.edu.demo.repository.modelo.dto.VehiculoVIP;

public interface IEmpleadoService {
	public void registrarCliente(Cliente cliente);

	public void ingresarVehiculo(Vehiculo vehiculo);

	public List<ReporteVIP> reporteVIP(LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public List<ClienteVIP> clientesVIP();

	public List<VehiculoVIP> vehiculosVIP(String anio, String mes);

	public List<Cliente> verClientes();
	
	public List<Vehiculo>verVehiculos();

}
