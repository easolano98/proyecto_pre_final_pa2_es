package com.uce.edu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menus")
public class MenuController {

	@GetMapping("")
	public String menuPrincipal() {
		return "vistaMenuPrincipal";
	}

	@GetMapping("/empleado")
	public String menuEmpleado() {
		return "vistaMenuEmpleado";
	}

	@GetMapping("/cliente")
	public String menuCliente() {
		return "vistaMenuCliente";
	}

	@GetMapping("/reportes")
	public String menuReportes() {
		return "vistaMenuReportes";
	}

}
