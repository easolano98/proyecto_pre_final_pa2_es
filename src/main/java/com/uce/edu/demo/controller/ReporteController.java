package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.dto.ClienteVIP;
import com.uce.edu.demo.repository.modelo.dto.ReporteVIP;
import com.uce.edu.demo.repository.modelo.dto.VehiculoVIP;
import com.uce.edu.demo.service.IEmpleadoService;


@Controller
@RequestMapping("/reportes")
public class ReporteController {
	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping("/buscarReporteVip")
	public String buscarReporteVip(ReporteVIP reporte, Model modelo) {
		List<ReporteVIP> vip = this.empleadoService.reporteVIP(LocalDateTime.parse(reporte.getFechaInicio()), LocalDateTime.parse(reporte.getFechaFin()) );
		modelo.addAttribute("vips", vip);
		return "vistaReporteVip";
	}
	
	@GetMapping("/generarReporteVip")
	public String generaReporteVip(ReporteVIP reporteVip,  Model modelo) {
		modelo.addAttribute("reporteVip", reporteVip);
		return "vistaGenerarReporte";
	}
	
	
	@GetMapping("/buscarClientesVip")
	public String buscarClienteVip(Model modelo) {
		List<ClienteVIP>clienteVip=this.empleadoService.clientesVIP();
		modelo.addAttribute("clientes", clienteVip);
		return "vistaClientesVip";
	}
	
	
	@GetMapping("/generarVehiculoVip")
	public String generarBusquedaVehiculos (VehiculoVIP vehiculoVip,  Model modelo) {
		modelo.addAttribute("vehiculoVIP", vehiculoVip);
		return "vistaGenerarVehiculo";
	}
	
	@GetMapping("/buscarVehiculoVip")
	public String buscarVehiculos(VehiculoVIP vehiculo,  Model modelo) {
		List<VehiculoVIP>vip=this.empleadoService.vehiculosVIP(vehiculo.getAnio(), vehiculo.getMes());
		modelo.addAttribute("vips", vip);
		return "vistaVehiculoVip";
	}
	
	
	
	
	
	
	
	
	
	
}
