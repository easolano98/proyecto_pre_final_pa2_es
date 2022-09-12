package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IEmpleadoService;

@Controller
@RequestMapping("/empl")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService iEmpleadoService;

	@Autowired
	private IClienteService iClienteService;

	@GetMapping("/clientes")
	public String verClientes(Model modelo) {
		List<Cliente> clientes = this.iEmpleadoService.verClientes();
		modelo.addAttribute("clientes", clientes);
		return "vistaEmplClientes";
	}

	@PostMapping("/registrar")
	public String registrarCliente(Cliente cliente) {
		System.out.println(" - " + cliente.getCedula());
		this.iEmpleadoService.registrarCliente(cliente);
		return "redirect:/empl/clientes";
	}

	@GetMapping("/clienteBusq/{id}")
	public String buscarCliente(@PathVariable("id") Integer id, Model modelo) {
		Cliente c = this.iClienteService.buscar(id);
		modelo.addAttribute("cliente", c);
		return "vistaEmplClienteInd";
	}

	@PutMapping("/clienteAct/{idCliente}")
	public String actualizarCliente(@PathVariable("idCliente") Integer id, Cliente cliente) {
		cliente.setId(id);
		System.out.println(" Nombre: " + cliente.getNombre());
		this.iClienteService.actualizar(cliente);
		return "redirect:/empl/clientes";
	}

	@DeleteMapping("/clienteDel/{id}")
	public String eliminarCliente(@PathVariable("id") Integer id) {
		this.iClienteService.eliminar(id);
		return "redirect:/empl/clientes";
	}

	// Metodos de redireccionamiento a paginas
	@GetMapping("/registroCliente")
	public String registo(Cliente c, Model modelo) {
		modelo.addAttribute("cliente", c);
		return "vistaEmplInsertarCliente";
	}

	@GetMapping("/actualizarC/{id}")
	public String actualizarC(@PathVariable("id") Integer id, Model modelo) {
		Cliente c = this.iClienteService.buscar(id);
		modelo.addAttribute("cliente", c);
		return "vistaEmplActualizarCliente";
	}

}
