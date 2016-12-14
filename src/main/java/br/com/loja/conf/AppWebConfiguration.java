package br.com.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.loja.controller.HomeController;
import br.com.loja.daos.ProdutoDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDAO.class})
public class AppWebConfiguration {
	 
	@Bean
	public InternalResourceViewResolver internalResourceViewResolve() {
        InternalResourceViewResolver resolve = new InternalResourceViewResolver();
        resolve.setPrefix("/WEB-INF/views/");
        resolve.setSuffix(".jsp");
        return resolve;
    }
}
