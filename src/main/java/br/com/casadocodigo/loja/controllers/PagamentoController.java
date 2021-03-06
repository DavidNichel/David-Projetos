package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
	@Autowired		
	private CarrinhoCompras carrinho;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MailSender sender;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	//comentado para testes.
	//	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model){
//		return () -> {
			
		public ModelAndView finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model){	
			String uri = "http://book-payment.herokuapp.com/payment";
			
			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				System.out.println(response);
				enviaEmailCompraProduto(usuario);
				model.addFlashAttribute("message", response);
				//método para tirar todos os livros do carrinho
				this.carrinho.limpa();
				return new ModelAndView("redirect:/");
				
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				System.out.println();
				model.addFlashAttribute("falha", "Valor maior que o permitido!");
				//comentado para testes return new ModelAndView("redirect:/produtos");
				return new ModelAndView("redirect:/");
			}
	//};
	}

	private void enviaEmailCompraProduto(Usuario usuario) {
//metodo para enviar email		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada om sucesso!");
		email.setTo(usuario.getEmail());
		//para testar o email realmente chegando no usuario certo
		email.setTo("email valido e com acesso");
		email.setText("Compra aprovada com sucesso no valor de " + carrinho.getTotal());
		email.setFrom("compras@casadocodigo.com.br");
		
		System.out.println("Envio desabilitado para e-mail.");
		//sender.send(email);
	}

}
