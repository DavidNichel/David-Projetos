package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidation implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		System.out.println("Target: " +target);
		
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senhaRepetida", "field.required");
		
		Usuario usuario = (Usuario) target;
		
		System.out.println(usuario.getNome());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getSenha());
		System.out.println(usuario.getRoles());
		
		if(usuario.getSenha().length() < 5) {
			errors.rejectValue("senha", "field.min");
		}
		
		if(!usuario.getSenha().equals(usuario.getSenhaRepetida())) {
			errors.rejectValue("senha", "field.password.match");
			errors.rejectValue("senhaRepetida", "field.password.match");
		}
	
	}
}
