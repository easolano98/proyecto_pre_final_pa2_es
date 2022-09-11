package com.uce.edu.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IVehiculoRepository;
import com.uce.edu.demo.repository.modelo.Vehiculo;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository iVehiculoRepository;

	@Override
	public void insertar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		vehiculo.setEstado("D");
		this.iVehiculoRepository.insertar(vehiculo);
	}

	@Override
	public Vehiculo buscarPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarPlaca(placa);
	}

	@Override
	public List<Vehiculo> buscarMarca(String marca) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarMarca(marca);
	}

	@Override
	public List<Vehiculo> buscarMarcaModeloDisponible(String marca, String modelo) {
		// TODO Auto-generated method stub
		List<Vehiculo> listaMarca = this.iVehiculoRepository.buscarMarca(marca);
		List<Vehiculo> listaMarcaModelo = listaMarca.stream()
				.filter(v -> v.getModelo().equals(modelo) && v.getEstado().equals("D")).collect(Collectors.toList());

		return listaMarcaModelo;
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.iVehiculoRepository.actualizar(vehiculo);
	}

	@Override
	public void eliminar(String placa) {
		// TODO Auto-generated method stub
		this.iVehiculoRepository.eliminar(placa);
	}

}
