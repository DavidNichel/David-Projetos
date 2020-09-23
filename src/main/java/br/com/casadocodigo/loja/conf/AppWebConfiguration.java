package br.com.casadocodigo.loja.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.CarrinhoCompras;


@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, ProdutoDAO.class, UsuarioDAO.class, FileSaver.class,CarrinhoCompras.class})
@EnableCaching
@EnableTransactionManagement(proxyTargetClass = true) 
public class AppWebConfiguration extends WebMvcConfigurerAdapter  {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		// resolver.setExposeContextBeansAsAttributes(true);
		resolver.setExposedContextBeanNames("carrinhoCompras");
		
		return resolver; 
	}
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("/WEB-INF/messages");
	    messageSource.setDefaultEncoding("ISO-8859-1");
	    messageSource.setCacheSeconds(1);
	    return messageSource;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	@Bean	
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	  registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
				.maximumSize(100)
				.expireAfterAccess(5,TimeUnit.MINUTES);
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(builder);
		return manager; //new ConcurrentMapCacheManager();
	}
	
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager){
	    List<ViewResolver> viewResolvers = new ArrayList<>();
	    viewResolvers.add(internalResourceViewResolver());
	    viewResolvers.add(new JsonViewResolver());

	    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
	    resolver.setViewResolvers(viewResolvers);
	    resolver.setContentNegotiationManager(manager);
	    return resolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	@Bean
	public LocaleResolver localeResolver(){
	    return new CookieLocaleResolver();
	}
//utilizando email...metodo no PagamentoController comentado tbm.
	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("servidor smtp"); //Ex:smtp.gmail.com
		mailSender.setUsername("seu email");
		mailSender.setPassword("sua senha");
	    mailSender.setPort(587);

	    Properties mailProperties = new Properties();
	    mailProperties.put("mail.smtp.auth", true);
	    mailProperties.put("mail.smtp.starttls.enable", true);

	    mailSender.setJavaMailProperties(mailProperties);
		
		return mailSender;
	}
}