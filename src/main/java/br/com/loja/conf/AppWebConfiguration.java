package br.com.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.loja.controller.HomeController;
import br.com.loja.daos.ProdutoDAO;
import br.com.loja.infra.FileSaver;
import br.com.loja.model.CarrinhoCompras;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDAO.class, FileSaver.class, CarrinhoCompras.class})
public class AppWebConfiguration  extends  WebMvcConfigurerAdapter{
	 
	@Bean
	public InternalResourceViewResolver internalResourceViewResolve() {
        InternalResourceViewResolver resolve = new InternalResourceViewResolver();
        resolve.setPrefix("/WEB-INF/views/");
        resolve.setSuffix(".jsp");
      //resolver.setExposeContextBeansAsAttributes(true);
        resolve.setExposedContextBeanNames("carrinhoCompras");
        return resolve;
    }
	
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource  messageSource = new ReloadableResourceBundleMessageSource();
	     messageSource.setBasename("/WEB-INF/messages");
         messageSource.setDefaultEncoding("UTF-8");
         messageSource.setCacheSeconds(1);

         return messageSource;
	}
	
	
	@Bean
	public FormattingConversionService mvcConversionService(){
	    DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
	    DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
	    formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
	    formatterRegistrar.registerFormatters(conversionService);

	    return conversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
			
	}

  
   @Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
           configurer.enable();
       }
   
	
}
