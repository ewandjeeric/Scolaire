package org.etudiant;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource datasource) throws Exception {
		/*
		 * auth.inMemoryAuthentication().withUser("ADMIN").password(passwordEncoder().
		 * encode("admin")).roles("ADMIN");
		 * auth.inMemoryAuthentication().withUser("PROF").password(passwordEncoder().
		 * encode("prof")).roles("PROF");
		 * auth.inMemoryAuthentication().withUser("ETUD").password(passwordEncoder().
		 * encode("etud")).roles("ETUDIANT");
		 */
		auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(datasource)
				.usersByUsernameQuery("select username, password, 'true' from users where username = ?")
				.authoritiesByUsernameQuery("select user_username, roles_role from users_roles where user_username = ?")
				.rolePrefix("ROLE_");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/index.html");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
