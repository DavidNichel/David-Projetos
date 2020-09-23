package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "valor",
    "data",
    "produtos"
})
public class Pedidos {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("valor")
    private Double valor;
    
    @DateTimeFormat
    @JsonProperty("data")
    private Calendar data;
    
    @JsonProperty("ProdutoJSON")
    private List<ProdutoJSON> produtos = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("valor")
    public Double getValor() {
        return valor;
    }

    @JsonProperty("valor")
    public void setValor(Double valor) {
        this.valor = valor;
    }

    @JsonProperty("data")
    public Calendar getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Calendar data) {
        this.data = data;
    }

    @JsonProperty("produtos")
    public List<ProdutoJSON> getProdutos() {
        return produtos;
    }

    @JsonProperty("produtos")
    public void setProdutos(List<ProdutoJSON> produtos) {
        this.produtos = produtos;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		
		return "Pedidos [produtos=" + produtos.toString() + "]";
	}
    
    

}