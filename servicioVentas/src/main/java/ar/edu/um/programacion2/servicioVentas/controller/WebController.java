package ar.edu.um.programacion2.servicioVentas.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.um.programacion2.servicioVentas.model.Venta;
import ar.edu.um.programacion2.servicioVentas.service.VentaService;

@Controller
@RequestMapping("/")
public class WebController {
	
	@Autowired
	private VentaService service;
	
	@RequestMapping
	public String getAllVentas(Model model) {
		List<Venta> list = service.getAllVentas();

		model.addAttribute("ventas", list);
		return "list-ventas";
	}

}
