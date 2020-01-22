package ph.com.fss.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("Configuring web security");
		http
		.authorizeRequests()
			//.antMatchers("/").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            //.failureUrl("/login?error")
            //.defaultSuccessUrl("/welcome")
            .passwordParameter("password")
        	.usernameParameter("username")
         .permitAll();
            
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {	
			auth
			.ldapAuthentication()
            .userDnPatterns("cn={0},dc=alliance,dc=com,dc=ph")
            //.userDetailsContextMapper(userDetailsContectMapper)
            .userSearchFilter("(&(mail={0})(objectClass=inetOrgPerson))")
			.contextSource()
				.url("ldap://192.168.101.7:389/dc=alliance,dc=com,dc=ph")
	            .managerDn("cn=admin,dc=alliance,dc=com,dc=ph")
	            .managerPassword("1qaz2wsx");
    }
	/*
	@Configuration
	protected static class AuthenticationConfiguration extends
			GlobalAuthenticationConfigurerAdapter {
		
		@Autowired
		UserDetailsContextMapper userDetailsCtxMapper;
		
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			/*
			DefaultSpringSecurityContextSource 
			source = new DefaultSpringSecurityContextSource("ldap://192.168.101.7:389");
			//source.setUrl("ldap://192.168.101.7:389");
			source.setBase("dc=alliance,dc=com,dc=ph");
			source.setUserDn("cn=admin,dc=alliance,dc=com,dc=ph");
			source.setPassword("1qaz2wsx");
		
			auth
				.ldapAuthentication()
					.userDnPatterns("mail={0},ou=person")
					.contextSource(source);
		auth
		.ldapAuthentication()
		.userDetailsContextMapper(userDetailsCtxMapper)
	    .userDnPatterns("cn={0},dc=alliance,dc=com,dc=ph")
	    .userSearchFilter("(&(mail={0})(objectClass=inetOrgPerson))")
		.contextSource()
			.url("ldap://192.168.101.7:389/dc=alliance,dc=com,dc=ph")
	        .managerDn("cn=admin,dc=alliance,dc=com,dc=ph")
	        .managerPassword("1qaz2wsx");
	    
			}
	}*/
}
