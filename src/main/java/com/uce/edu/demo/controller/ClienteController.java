package com.uce.edu.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.dto.controller.ClienteDTO;
import com.uce.edu.demo.service.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@PostMapping("/registrar")
	public String registrarCliente(ClienteDTO cliente) {
		try {
			System.out.println(" - " + cliente.getCedula());
			Cliente c = new Cliente();
			c.setCedula(cliente.getCedula());
			c.setNombre(cliente.getNombre());
			c.setApellido(cliente.getApellido());
			c.setFechaNacimiento(LocalDateTime.parse(cliente.getFecha()));
			c.setGenero(cliente.getGenero());
			this.clienteService.insertar(c);
			return "redirect:/menus/cliente";
		} catch (Exception e) {
			return "redirect:/clientes/registroCliente";
		}
	}

	@GetMapping("/registroCliente")
	public String registo(ClienteDTO c, Model modelo) {
		modelo.addAttribute("cliente", c);
		return "vistaCliInsertarCliente";
	}

	@GetMapping("/actualizarCliente")
	public String actualizarC(ClienteDTO cliente, Model modelo) {
		modelo.addAttribute("cliente", cliente);
		return "vistaClieActualizarCliente";
	}

	@PutMapping("/actualizar")
	public String actualizarDatos(ClienteDTO cliente) {
		try {
			Cliente c = this.clienteService.buscarCedula(cliente.getCedula());
			if (cliente.getNombre().equals("") && cliente.getApellido().equals("") && cliente.getGenero().equals("")) {
				return "redirect:/clientes/actualizarCliente";
			}
			c.setNombre(cliente.getNombre());
			c.setApellido(cliente.getApellido());
			c.setGenero(cliente.getGenero());

			this.clienteService.actualizar(c);
			return "redirect:/menus/cliente";
		} catch (Exception e) {
			return "redirect:/clientes/actualizarCliente";
		}
	}

	@GetMapping("/buscarDisponibles")
	public String buscarDisponibles() {
		return "redirect:/vehiculos/disponibles";
	}

	@GetMapping("/reservarCliente")
	public String reservar() {
		return "redirect:/vehiculos/crearReserva";
	}

}