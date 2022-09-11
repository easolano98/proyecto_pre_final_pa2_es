package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	private IVehiculoService vehiculoService;
	
	
	@GetMapping("/buscarDisponibles/{marca}{modelo}")
	public String vehiculosDisponibles(@PathVariable("marca")String marca,@PathVariable("modelo")String modelo, Model model) {
		System.out.println(marca+"---------- "+modelo);
		List<Vehiculo>disponibles=this.vehiculoService.buscarMarcaModeloDisponible(marca, modelo);
		model.addAttribute("disponibles", disponibles);
		return "vistaBusquedaDisponibles";
	}
	
	@GetMapping("/disponibles")
	public String consultaDisponibles(Vehiculo vehiculo) {
	
		System.out.println("-------"+vehiculo.getMarca()+" "+vehiculo.getModelo());
		return "vistaConsultaDisponibles";
	}
	
	
}
