package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.dto.VehiculoVIP;
import com.uce.edu.demo.repository.modelo.dto.controller.ReservaDto;
import com.uce.edu.demo.service.IGestorReservasService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired
	private IGestorReservasService gestorReservasService;
	@Autowired
	private IReservaService reservaService;

	@GetMapping("/disponibles")
	public String consultaDisponibles(Vehiculo vehiculo) {
		return "vistaConsultaDisponibles";
	}

	@GetMapping("/buscarDisponibles")
	public String vehiculosDisponibles(Vehiculo vehiculo, Model model) {
		List<Vehiculo> disponibles = this.vehiculoService.buscarMarcaModeloDisponible(vehiculo.getMarca(),
				vehiculo.getModelo());
		model.addAttribute("disponibles", disponibles);
		return "vistaBusquedaDisponibles";
	}

	@GetMapping("/crearReserva")
	public String paginaNuevoProducto(ReservaDto reservaDto) {
		return "vistaGenerarReserva";
	}

	@PostMapping("/reservar")
	public String reservarVehiculo(ReservaDto reserva, Model modelo) {

		String numeroReserva = this.gestorReservasService.reservarVehiculo(reserva.getPlaca(), reserva.getCedula(),
				LocalDateTime.parse(reserva.getFechaInicio().concat("T00:00:00")),
				LocalDateTime.parse(reserva.getFechaFin().concat("T00:00:00")));
		System.out.println(numeroReserva);
		modelo.addAttribute("ticket", numeroReserva);
		return "vistaConfirmacion";
	}

	@GetMapping("/confirmacionReserva/{numero}")
	public String confirmacionReserva(@PathVariable("numero") String numero, Model modelo) {
		System.out.println(numero);
		Reserva verificacion = this.reservaService.buscarNumero(numero);
		modelo.addAttribute("verificacion", verificacion);
		return "redirect:/vehiculos/crearReserva";
	}

	@GetMapping("/vehiculoReservado")
	public String verificarReserva(Model modelo, VehiculoVIP vehiculoVIP) {

		modelo.addAttribute("verificacion", vehiculoVIP);

		return "vistaBuscarVehiculoReservado";

	}

	@GetMapping("/reservados/{numero}")
	public String buscarReservados(@PathVariable("numero") String numero, Reserva reserva, Model modelo) {

		VehiculoVIP vehiculoVIP = this.gestorReservasService.retirarVehiculo(numero);
		modelo.addAttribute("retirar", vehiculoVIP);

		return "vistaRetirarVehiculo";
	}

}
