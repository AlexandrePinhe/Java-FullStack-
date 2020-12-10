package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/

/*@Configuration
@EnableWebSecurity*/
@SuppressWarnings("deprecation")
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/{
	 private static final String[] AUTH_LIST = {
		        "/login",
		        "/cadastro",
		        "/usuarios"
		    };

	 		/*
		  
		    @Override
		    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		        auth
		        	.inMemoryAuthentication()
		                .withUser("luiz").password("123").roles("ADMIN");
		    }
		    
		    @Bean
		    public PasswordEncoder passwordEncoder() {
				return NoOpPasswordEncoder.getInstance();
		    	
		    }
		    
		    @Override
		    protected void configure(HttpSecurity http) throws Exception{
		        http
		        	.authorizeRequests()
		                .antMatchers(AUTH_LIST).permitAll()
		                .anyRequest().authenticated()
		            .and()
		            .httpBasic();
		    }*/
		    
}
