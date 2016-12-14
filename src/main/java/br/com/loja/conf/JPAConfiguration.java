package br.com.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		JpaVendorAdapter jpaVendorAdapter= new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter );
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        
        factoryBean.setDataSource(dataSource);
        
        Properties props= new Properties();
        props.setProperty("hibernate.dialect" , "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        
        factoryBean.setJpaProperties(props);
        
        factoryBean.setPackagesToScan("br.com.loja.model");
        
        return factoryBean;

		
	}/*
	Por segurança, o Hibernate pede que toda operação seja feita através de uma transação, mas gerenciar todas essas transações é bem trabalhoso, 
	para isso vamos pedir para o Spring gerenciar isso para nós.
	Para habilitar esse módulo adicione a anotação @EnableTransactionManagement 
	na nossa classe JPAConfiguration. 
	Em seguida, precisamos adicionar um bean que será o gerenciador das transações, isto é, a partir desse bean o Spring fornecerá as transações 
	para o EntityManager. 
	Para isso basta adicionar o códido abaixo na classe JPAConfiguration:*/
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
	    return new JpaTransactionManager(emf);
	}
}
