package br.com.casadocodigo.loja.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	// email, login, etc.
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario find(String email) {
		return manager.find(Usuario.class, email);
	}
	
	public List<Usuario> listar() {
		return manager.createQuery("select u from Usuario u ", Usuario.class)
				.getResultList();
	}
	
	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.email = :email",Usuario.class)
		.setParameter("email", email)
		.getResultList();
		
		if (usuarios.isEmpty()){
			throw new UsernameNotFoundException("Usuario" + email + "não foi encontrado");
		}
		return usuarios.get(0);
	}
	public void gravar(Usuario usuario) {
		System.out.println("Gravando usuário");
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		manager.persist(usuario);
	}

	
	public void gravarRoles(String email, List<Role> roles) {
		System.out.println("Gravando roles");
		
		Usuario usuario = manager.find(Usuario.class, email);
		usuario.setRoles(roles);
		
}
	
	public boolean usuarioRepetido(Usuario usuario) {
		if (manager.find(Usuario.class, usuario.getEmail()) != null) {
			return true;
		}
		return false;
	}
	
}
