package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.dao.UsuarioDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
//	    .antMatchers("/resources/**").permitAll()
	    .antMatchers("/usuarios").hasRole("ADMIN")
	    .antMatchers("/usuarios/**").hasRole("ADMIN")	
	    .antMatchers("/pedidos").hasRole("ADMIN")
	    .antMatchers("/pedidos/**").hasRole("ADMIN")
	    .antMatchers("/produtos/").hasRole("ADMIN")
	    .antMatchers("/produtos").hasRole("ADMIN") 
	    .antMatchers("/produtos/form").hasRole("ADMIN")
	    .antMatchers("/produtos/**").permitAll()
	    .antMatchers("/carrinho/**").permitAll()
	    .antMatchers("/pagamento/**").permitAll()
	    .antMatchers("/relatorio-produtos/**").permitAll()	
	    .antMatchers("/").permitAll()
	    .antMatchers("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn").permitAll()
	    .anyRequest().authenticated().and()
		.formLogin().loginPage("/login")
		.defaultSuccessUrl("/produtos")
		.permitAll().and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll() 
        .logoutSuccessUrl("/login");
	    //testar http.csrf().disable();
	    http.csrf().disable();
}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioDAO)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	// Forma recomendada de ignorar no filtro de segurança as requisições para recursos estáticos
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
