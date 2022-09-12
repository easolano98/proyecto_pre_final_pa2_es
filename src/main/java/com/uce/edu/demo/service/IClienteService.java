package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;

public interface IClienteService {

	public void insertar(Cliente cliente);

	public Cliente buscar(Integer id);

	public Cliente buscarCedula(String cedula);

	public List<Cliente> buscarApellido(String apellido);

	public int actualizar(Cliente cliente);

	public void eliminar(Integer id);

	public void eliminarPorCedula(String cedula);

}
