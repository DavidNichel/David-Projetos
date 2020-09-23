package br.com.casadocodigo.loja.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.Pedidos;


@Controller
public class PedidosServicoController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value="/pedidos")
	public ModelAndView buscaPedidos(RedirectAttributes model){

		try {
			String uri = "https://book-payment.herokuapp.com/orders";
			Pedidos[] pedidos =  restTemplate.getForObject(uri, Pedidos[].class);
			ModelAndView modelAndView = new ModelAndView("pedidos");
			modelAndView.addObject("pedidos", pedidos); 
			return modelAndView; 
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			model.addFlashAttribute("falha", "Valor maior que o permitido");
			return new ModelAndView("pedidos");
		}
	}

}
