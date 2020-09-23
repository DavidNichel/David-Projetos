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

/*@JsonInclude é usado em propriedades de exclusão com valores nulos / vazios ou padrão.
 * 
 * @JsonPropertyOrder a anotação é usada para especificar a ordem das propriedades serializadas.
*/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "titulo", "descricao", "paginas", "precos", "dataLancamento" })
public class ProdutoJSON {
	/* * @JsonProperty é usado para marcar o método getter / setter 
	 * não padrão a ser usado com relação à propriedade json.
	 * */
	@JsonProperty("titulo")
	private String titulo;
	@JsonProperty("descricao")
	private String descricao;
	@JsonProperty("paginas")
	private Integer paginas;
	@JsonProperty("precos")
	private List<PrecoJSON> precos = null;

	@DateTimeFormat
	@JsonProperty("dataLancamento")
	private Calendar dataLancamento;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("titulo")
	public String getTitulo() {
		return titulo;
	}

	@JsonProperty("titulo")
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@JsonProperty("descricao")
	public String getDescricao() {
		return descricao;
	}
	@JsonProperty("descricao")
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@JsonProperty("paginas")
	public Integer getPaginas() {
		return paginas;
	}
	@JsonProperty("paginas")
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	@JsonProperty("precos")
	public List<PrecoJSON> getPrecos() {
		return precos;
	}
	@JsonProperty("precos")
	public void setPrecos(List<PrecoJSON> precos) {
		this.precos = precos;
	}
	@JsonProperty("dataLancamento")
	public Calendar getDataLancamento() {
		return dataLancamento;
	}
	@JsonProperty("dataLancamento")
	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	@JsonAnySetter
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return titulo + " ";
	}    

}
