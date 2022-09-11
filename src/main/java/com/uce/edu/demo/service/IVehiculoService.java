package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;

public interface IVehiculoService {

	public void insertar(Vehiculo vehiculo);

	// public List<Vehiculo> buscarDisponibles(String marca, String modelo );

	public Vehiculo buscarPlaca(String placa);

	public List<Vehiculo> buscarMarca(String marca);

	public List<Vehiculo> buscarMarcaModeloDisponible(String marca, String modelo);

	public void actualizar(Vehiculo vehiculo);

	public void eliminar(String placa);
}
