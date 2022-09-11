package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

	@Autowired
	private IVehiculoService vehiculoService;

	@GetMapping("/buscarDisponibles")
	public String vehiculosDisponibles(Vehiculo vehiculo, Model model) {
		System.out.println(vehiculo.getMarca() + " ---------- " + vehiculo.getModelo());
		List<Vehiculo> disponibles = this.vehiculoService.buscarMarcaModeloDisponible(vehiculo.getMarca(),
				vehiculo.getModelo());
		model.addAttribute("disponibles", disponibles);
		return "vistaBusquedaDisponibles";
	}

	// Metodos de redireccionamiento a paginas
	@GetMapping("/disponibles")
	public String consultaDisponibles(Vehiculo vehiculo) {
		System.out.println("------- " + vehiculo.getMarca() + " " + vehiculo.getModelo());
		return "vistaConsultaDisponibles";
	}

}
