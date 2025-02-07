package ph.com.fss.bootstrap;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Database configuration. Note the 'Import' annotation in RootConfig that activates this. PropertySource and
 * Environment pair allows externalized settings.
 * 
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfigMySQL{
 
   @Bean
   public LocalContainerEntityManagerFactoryBean pmsEntityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(pmsDataSource());
      em.setPackagesToScan(new String[] {"ph.com.fss.entity.pms"}); 
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalProperties()); 
      return em;
   }
   
   @Bean
   public LocalContainerEntityManagerFactoryBean fssEntityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(fssDataSource());
      em.setPackagesToScan(new String[] {"ph.com.fss.entity.fss"}); 
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalProperties()); 
      return em;
   }
 
   @Bean
   public DataSource pmsDataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://pms2015.fss.com.ph/pms2015");
      dataSource.setUsername("root");
      dataSource.setPassword("fss@12345");
      return dataSource;
   }
   
   @Bean
   public DataSource fssDataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/fss");
      dataSource.setUsername("root");
      dataSource.setPassword("root");
      return dataSource;
   }
 
   @Bean
   public JpaTransactionManager pmsTransactionManager(EntityManagerFactory pmsEntityManagerFactory){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(pmsEntityManagerFactory); 
      return transactionManager;
   }
   
   @Bean
   public JpaTransactionManager fssTransactionManager(EntityManagerFactory fssEntityManagerFactory){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(fssEntityManagerFactory); 
      return transactionManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }
   
   Properties additionalProperties() {
      Properties properties = new Properties();
      properties.setProperty("hibernate.hbm2ddl.auto", "update");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
      return properties;
   }
}
