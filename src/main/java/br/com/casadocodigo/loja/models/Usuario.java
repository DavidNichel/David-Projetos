package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Usuario(){	
	}

	@Id
	private String email;
	private String senha;
	private String nome; 
	
	@Transient
	private String senhaRepetida;
	/*@JoinTable pode ser usado para mapear as seguintes associações para a tabela de banco de dados: 
	 * associações bidirecionais muitos-para-um /
	 *  um-para-muitos, unidirecionais muitos-para-um e um-para-um (bidirecionais e unidirecionais).*/
	@JoinTable(name = "Usuario_Role",
	joinColumns = @JoinColumn(name = "email"), 
	inverseJoinColumns = @JoinColumn(name = "role_nome"))
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>()	;

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.roles;
	}
	@Override
	public String getPassword() {
		
		return this.senha;
	}
	@Override
	public String getUsername() {
		// aqui o email está como nome do usuario
		return this.email;
	}
	
	public String getSenhaRepetida() {
		return senhaRepetida;
	}

	public void setSenhaRepetida(String senhaRepetida) {
		this.senhaRepetida = senhaRepetida;
	}
	@Override
	public boolean isAccountNonExpired() {
		// detalhes sobre a conta
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// detalhes sobre a conta
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// detalhes sobre a conta
		return true;
	}
	@Override
	public boolean isEnabled() {
		// detalhes sobre a conta
		return true;
	}
	
}
