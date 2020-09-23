package br.com.casadocodigo.loja.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*@JsonInclude é usado em propriedades de exclusão com valores nulos / vazios ou padrão.
 * 
 * @JsonPropertyOrder a anotação é usada para especificar a ordem das propriedades serializadas.
*/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "valor","tipo"
})
public class PrecoJSON {
	/* * @JsonProperty é usado para marcar o método getter / setter 
	 * não padrão a ser usado com relação à propriedade json.
	 * */
    @JsonProperty("valor")
    private Double valor;
    @JsonProperty("tipo")
    private String tipo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
    @JsonProperty("valor")
    public Double getValor() {
		return valor;
	}
	@JsonProperty("valor")
	public void setValor(Double valor) {
		this.valor = valor;
	}
	@JsonProperty("tipo")
	public String getTipo() {
		return tipo;
	}
	@JsonProperty("tipo")
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	@JsonAnySetter
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
    
}
