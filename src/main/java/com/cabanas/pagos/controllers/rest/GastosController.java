package com.cabanas.pagos.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cabanas.pagos.models.Gasto;
import com.cabanas.pagos.models.User;
import com.cabanas.pagos.services.GastoService;
import com.cabanas.pagos.services.UserService;

@Controller
@RequestMapping("/gastos")
public class GastosController {

	//Capa de servicio a gastos
	@Autowired
	private GastoService gastoService;
	
	//Capa de servicio a usuarios
	@Autowired
	private UserService userService;
		
	
	
	//Pantalla principal de gastos
	@GetMapping("/index")
	public ModelAndView getIndex(){
		
		//Obtiene todos los gastos
		final List<Gasto> gastos = gastoService.getAll();
				
		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
	
		//Obtiene el rol
		final Optional<User> user_ = userService.findByUsername(username);
		final User user = user_.get();
						
		final ModelAndView model = new ModelAndView("gastos");
	    model.addObject("gastos",gastos);
	    model.addObject("user",user);
	    
		return model;
	}
	
	//Pantalla para dar de alta un nuevo gasto
	@GetMapping("/nueva")
	public ModelAndView getNew(){
	
		final Gasto gasto = new Gasto();
		gasto.setId(-1);
		
		final ModelAndView model = new ModelAndView("gastos_nuevo");
		model.addObject("editable",true);
	    model.addObject("title","Nuevo Gasto");
	    model.addObject("actualizar",false);
	    model.addObject("gasto",gasto);
	    
		return model;
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
	    model.addObject("editable",false);
	    model.addObject("title","Ver Gasto");
	    
		return model;
	}
	
	//Pantalla para actualizar un gasto
	@GetMapping("/actualizar/{id}")
	public ModelAndView getUpdate(@PathVariable("id") int id){
		
		final Optional<Gasto> gasto_ = gastoService.findById(id);
		
		Gasto gasto;
		
		if(!gasto_.isPresent()) {
			gasto = new Gasto();
		} else {
			gasto = gasto_.get();
		}
		
		final ModelAndView model = new ModelAndView("gastos_nuevo");
	    model.addObject("gasto",gasto);
	    model.addObject("editable",true);
	    model.addObject("title","Editar Gasto");
	    model.addObject("actualizar",true);
	    
		return model;
	}
	
	//Pantalla principal de gastos
	@GetMapping("/reportes")
	public String getReportes(){
		return "reportes";
	}
}
