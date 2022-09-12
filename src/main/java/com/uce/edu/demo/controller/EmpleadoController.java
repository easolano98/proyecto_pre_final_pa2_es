package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
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
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.dto.controller.ClienteDTO;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IEmpleadoService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/empl")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService iEmpleadoService;

	@Autowired
	private IClienteService iClienteService;
	
	@Autowired
	private IVehiculoService iVehiculoService;

	@GetMapping("/clientes")
	public String verClientes(Model modelo) {
		List<Cliente> clientes = this.iEmpleadoService.verClientes();
		modelo.addAttribute("clientes", clientes);
		return "vistaEmplClientes";
	}

	@PostMapping("/registrar")
	public String registrarCliente(ClienteDTO cliente) {
		System.out.println(" - " + cliente.getCedula());
		Cliente c = new Cliente();
		c.setCedula(cliente.getCedula());
		c.setNombre(cliente.getNombre());
		c.setApellido(cliente.getApellido());
		c.setFechaNacimiento(LocalDateTime.parse(cliente.getFecha()));
		c.setGenero(cliente.getGenero());
		this.iEmpleadoService.registrarCliente(c);
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
		Cliente c = this.iClienteService.buscar(id);
		cliente.setCedula(c.getCedula());
		cliente.setFechaNacimiento(c.getFechaNacimiento());
		cliente.setRegistro(c.getRegistro());
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
	public String registo(ClienteDTO c, Model modelo) {
		modelo.addAttribute("cliente", c);
		return "vistaEmplInsertarCliente";
	}

	@GetMapping("/actualizarC/{id}")
	public String actualizarC(@PathVariable("id") Integer id, Model modelo) {
		Cliente cliente = this.iClienteService.buscar(id);
		modelo.addAttribute("cliente", cliente);
		return "vistaEmplActualizarCliente";
	}

	// Vehiculos

	@GetMapping("/vehiculos")
	public String verVehiculos(Model modelo) {
		List<Vehiculo> vehiculos = this.iEmpleadoService.verVehiculos();
		modelo.addAttribute("vehiculos", vehiculos);
		return "vistaEmplVehiculos";
	}

	@PostMapping("/ingresarVehiculo")
	public String registrarVehiculos(Vehiculo vehiculo) {
		System.out.println(" - " + vehiculo.getPlaca());

		this.iEmpleadoService.ingresarVehiculo(vehiculo);
		return "redirect:/empl/vehiculos";
	}

	@GetMapping("/vehiculoBusq/{placa}")
	public String buscarVehiculos(@PathVariable("placa") String placa, Model modelo) {
		Vehiculo v = this.iVehiculoService.buscarPlaca(placa);
		modelo.addAttribute("vehiculo", v);
		return "vistaEmplVehiculoInd";
	}

	@PutMapping("/vehiculoActualizar/{placa}")
	public String actualizarVehiculos(@PathVariable("placa") String placa, Vehiculo vehiculo) {
		vehiculo.setPlaca(placa);
		System.out.println(" Nombre: " + vehiculo.getPlaca());
		Vehiculo v = this.iVehiculoService.buscarPlaca(placa);
		vehiculo.setId(v.getId());
		vehiculo.setEstado(v.getEstado());
		this.iVehiculoService.actualizar(vehiculo);
		return "redirect:/empl/vehiculos";
	}

	@DeleteMapping("/vehiculoEliminar/{placa}")
	public String eliminarVehiculos(@PathVariable("placa") String placa) {
		this.iVehiculoService.eliminar(placa);
		return "redirect:/empl/vehiculos";
	}

	// Metodos de redireccionamiento a paginas
	@GetMapping("/ingresoVehiculo")
	public String registoVehiculos( Vehiculo v, Model modelo) {
		modelo.addAttribute("vehiculo", v);
		return "vistaEmplInsertarVehiculo";
	}

	@GetMapping("/actualizarV/{placa}")
	public String actualizarV(@PathVariable("placa") String placa, Model modelo) {
		Vehiculo vehiculo = this.iVehiculoService.buscarPlaca(placa);
		modelo.addAttribute("vehiculo", vehiculo);
		return "vistaEmplActualizarVehiculo";
	}

}
