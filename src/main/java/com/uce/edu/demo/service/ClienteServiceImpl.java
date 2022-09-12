package com.uce.edu.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.modelo.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public void insertar(Cliente cliente) {
		// TODO Auto-generated method stub
		cliente.setRegistro("C");
		this.clienteRepository.insertar(cliente);
	}

	@Override
	public Cliente buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.clienteRepository.buscar(id);
	}

	@Override
	public Cliente buscarCedula(String cedula) {
		// TODO Auto-generated method stub
		return this.clienteRepository.buscarCedula(cedula);
	}

	@Override
	public List<Cliente> buscarApellido(String apellido) {
		// TODO Auto-generated method stub
		return this.clienteRepository.buscarApellido(apellido);
	}

	@Override
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.clienteRepository.actualizar(cliente);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.clienteRepository.eliminar(id);
	}

	@Override
	public void eliminarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		this.clienteRepository.eliminarPorCedula(cedula);
	}
	
	
}
