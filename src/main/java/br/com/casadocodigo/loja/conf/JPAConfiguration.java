package br.com.casadocodigo.loja.conf;

import java.util.Properties;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			Properties additionalProperties) {
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendoAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendoAdapter);
		
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaProperties(additionalProperties);
				
		return factoryBean;
		
	}
	
	@Bean
	@Profile("dev")
	public Properties additionalProperties() {
		Properties props = new Properties();
		//dialeto usado pelo hibernate para conversar com o banco de dados mysql
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		//props.setProperty("hibernate.hbm2ddl.auto", "update");
		props.setProperty("hibernate.hbm2ddl.auto", "create");
		//propriedade para mostra o DDL gerado como log
		props.setProperty("javax.persistence.schema-generation.scripts.create-target", "db-schema.jpa.ddl");
		return props;
	}
	@Bean
	@Profile("dev")
	private DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("");
		//                 banco mysql...3306 porta padrão do mysql...nome do banco...
		dataSource.setUrl("jdbc:mysql://localhost/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
}
