package com.cabanas.pagos.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cabanas.pagos.models.Gasto;
import com.cabanas.pagos.services.GastoService;

@Controller
@RequestMapping("/gastos")
public class GastosController {

	//Capa de servicio a usuarios
	@Autowired
	private GastoService gastoService;
	
		
	//Pantalla principal de gastos
	@GetMapping("/index")
	public ModelAndView getIndex(){
		
		//Obtiene todos los gastos
		final List<Gasto> gastos = gastoService.getAll();
		
		final ModelAndView model = new ModelAndView("gastos");
	    model.addObject("gastos",gastos);
	    
		return model;
	}
	
	//Pantalla para dar de alta un nuevo gasto
	@GetMapping("/nueva")
	public String getNew(){
		return "gastos_nuevo";
	}
	
	//Pantalla para ver un gasto
	@GetMapping("/ver/{id}")
	public ModelAndView getView(@PathVariable("id") int id){
		
		final Optional<Gasto> gasto_ = gastoService.findById(id);
		
		Gasto gasto;
		
		if(!gasto_.isPresent()) {
			gasto = new Gasto();
		} else {
			gasto = gasto_.get();
		}
			
		final ModelAndView model = new ModelAndView("gastos_nuevo");
	    model.addObject("gasto",gasto);
	    
		return model;
	}
	
	//Pantalla para actualizar un gasto
	@GetMapping("/actualizar")
	public String getUpdate(){
		return "gastos_nuevo";
	}
}
