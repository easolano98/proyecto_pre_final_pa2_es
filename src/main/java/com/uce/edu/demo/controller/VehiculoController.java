package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.dto.ReporteVIP;
import com.uce.edu.demo.repository.modelo.dto.VehiculoVIP;
import com.uce.edu.demo.repository.modelo.dto.controller.ReservaDto;
import com.uce.edu.demo.service.IClienteService;
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
	@Autowired
	private IClienteService clienteService;

	@GetMapping("/disponibles")
	public String consultaDisponibles(Vehiculo vehiculo) {
		return "vistaConsultaDisponibles";
	}

	@GetMapping("/buscarDisponibles")
	public String vehiculosDisponibles(Vehiculo vehiculo, Model model) {
		try {
			List<Vehiculo> disponibles = this.vehiculoService.buscarMarcaModeloDisponible(vehiculo.getMarca(),
					vehiculo.getModelo());
			if (disponibles.isEmpty()) {
				return "redirect:/vehiculos/disponibles";
			}

			model.addAttribute("disponibles", disponibles);
			return "vistaBusquedaDisponibles";
		} catch (Exception e) {
			return "redirect:/vehiculos/disponibles";
		}
	}

	@GetMapping("/crearReserva")
	public String paginaNuevoProducto(ReservaDto reservaDto) {
		return "vistaGenerarReserva";
	}

	@PostMapping("/reservar")
	public String reservarVehiculo(ReservaDto reserva, Model modelo) {
		try {
			List<LocalDateTime> rangoFecha = this.gestorReservasService.rangoFecha(
					LocalDateTime.parse(reserva.getFechaInicio()), LocalDateTime.parse(reserva.getFechaFin()));
			List<LocalDateTime> fechasDisponibles = this.gestorReservasService.verificarDisponibilidad(
					reserva.getPlaca(), LocalDateTime.parse(reserva.getFechaInicio()),
					LocalDateTime.parse(reserva.getFechaFin()));
			if (rangoFecha.size() == fechasDisponibles.size()) {
				Cliente c = this.clienteService.buscarCedula(reserva.getCedula());

				c.setNumeroTarjeta(reserva.getTarjetaCredito());
				this.clienteService.actualizar(c);
				String numeroReserva = this.gestorReservasService.reservarVehiculo(reserva.getPlaca(),
						reserva.getCedula(), LocalDateTime.parse(reserva.getFechaInicio()),
						LocalDateTime.parse(reserva.getFechaFin()));
				System.out.println(numeroReserva);
				modelo.addAttribute("ticket", numeroReserva);
				return "vistaConfirmacion";
			} else {
				List<VehiculoVIP> lista = fechasDisponibles.stream().map(f -> {
					VehiculoVIP v = new VehiculoVIP();
					v.setFechaInicio(f);

					return v;

				}).collect(Collectors.toList());
				modelo.addAttribute("fechas", lista);

				return "vistaFechasDisponibles";
			}
		} catch (Exception e) {
			return "redirect:/vehiculos/crearReserva";
		}
	}

	@GetMapping("/confirmacionReserva/{numero}")
	public String confirmacionReserva(@PathVariable("numero") String numero, Model modelo) {
		try {
			Reserva verificacion = this.reservaService.buscarNumero(numero);
			modelo.addAttribute("verificacion", verificacion);
			return "redirect:/vehiculos/crearReserva";
		} catch (Exception e) {
			return "redirect:/vehiculos/reservar";
		}
	}

	@GetMapping("/vehiculoReservado")
	public String presentarReserva(Model modelo, VehiculoVIP vip) {
		modelo.addAttribute("verificacion", vip);

		return "vistaBuscarVehiculoReservado";
	}

	@GetMapping("/reservados")
	public String retirarVehiculo(VehiculoVIP vip, Model modelo) {
		try {
			VehiculoVIP vehiculoVIP = this.gestorReservasService.retirarVehiculo(vip.getNumeroReserva());

			modelo.addAttribute("retirar", vehiculoVIP);

			return "vistaRetirarVehiculo";
		} catch (Exception e) {
			return "redirect:/vehiculos/vehiculoReservado";
		}
	}

	@PutMapping("/noReservados")
	public String sinReservarVehiculo(ReporteVIP reporte) {

		List<Vehiculo> vehiculosD = this.vehiculoService.buscarMarcaModeloDisponible(reporte.getMarca(),
				reporte.getModelo());
		Vehiculo v = this.vehiculoService.buscarPlaca(reporte.getPlaca());
		if (vehiculosD.contains(v)) {
			String numero = this.gestorReservasService.reservarVehiculo(reporte.getPlaca(), reporte.getCedula(),
					LocalDateTime.now(), LocalDateTime.parse(reporte.getFechaFin()));
			this.gestorReservasService.retirarVehiculo(numero);
		}
		return "redirect:/empl/vehiculos";
	}

	@GetMapping("/vistaNoReservados")
	public String vistaNoReservados(ReporteVIP reporte, Model modelo) {
		modelo.addAttribute("reporte", reporte);
		return "vistaNoReservados";
	}

}