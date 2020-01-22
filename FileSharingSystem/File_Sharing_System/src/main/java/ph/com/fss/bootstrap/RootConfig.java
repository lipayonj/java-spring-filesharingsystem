package ph.com.fss.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Bootstrap for service layer.
 */
@Configuration
@Import(DatabaseConfigMySQL.class)
@ComponentScan(basePackages = { "ph.com.fss.service", "ph.com.fss.dao", "ph.com.fss.util" })
@EnableScheduling
public class RootConfig {
	
	@Bean
	public ThreadPoolTaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		return executor;
	}
	
	@Bean
	public LdapContextSource contextSource(){
		LdapContextSource source = new LdapContextSource();
		source.setUrl("ldap://192.168.101.7:389");
		source.setBase("dc=alliance,dc=com,dc=ph");
		source.setUserDn("cn=admin,dc=alliance,dc=com,dc=ph");
		source.setPassword("1qaz2wsx");
		return source;
	}
	
	@Bean
	public LdapTemplate ldapTemplate(){
		return new LdapTemplate(contextSource());
	}	
	
   @Bean
   public CommonsMultipartResolver multipartResolver(){
	   CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	   commonsMultipartResolver.setDefaultEncoding("utf-8");
	   commonsMultipartResolver.setMaxUploadSize(500000000);
	   return commonsMultipartResolver;
   }
   /*
   @Bean
   public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping(){
	   BeanNameUrlHandlerMapping handlerMapping = new BeanNameUrlHandlerMapping();
	   
	   Object[] interceptor = new Object[1];
	   
	   interceptor[0] = new RequestInterceptors();
	   
	   handlerMapping.setInterceptors( interceptor);
	   
	   return handlerMapping;
   }*/
}
