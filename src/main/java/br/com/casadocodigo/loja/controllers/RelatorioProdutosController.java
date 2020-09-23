package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class RelatorioProdutosController {
	@Autowired
	private ProdutoDAO produtoDao;
	  
	    @RequestMapping( method = RequestMethod.GET, value="/relatorio-produtos" )
		@ResponseBody
		public List<Produto> produzirJSON_(@RequestParam(value="data", required=false,  defaultValue="") String data){
			 List<Produto> produto = null;
	    	if(!data.isEmpty()) {
	    		System.out.println("Valor do objeto data: " + data);
			    produto = produtoDao.findByDate(data);
			  }
	    	else produto = produtoDao.listar();
			return produto;
		}
}

