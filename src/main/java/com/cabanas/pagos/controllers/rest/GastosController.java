package com.cabanas.pagos.controllers.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gastos")
public class GastosController {

	//Pantalla principal de gastos
	@GetMapping("/index")
	public String getIndex(){
		return "gastos";
	}
	
	//Pantalla para dar de alta un nuevo gasto
	@GetMapping("/nueva")
	public String getNew(){
		return "gastos_nuevo";
	}
	
	//Pantalla para actualizar un gasto
	@GetMapping("/actualizar")
	public String getUpdate(){
		return "gastos_nuevo";
	}
}
