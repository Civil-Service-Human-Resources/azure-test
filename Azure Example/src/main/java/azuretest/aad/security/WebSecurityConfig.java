/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */
package azuretest.aad.security;

//import com.microsoft.azure.spring.autoconfigure.aad.AADAuthenticationFilter;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableGlobalMethodSecurity(securedEnabled = true,
//        prePostEnabled = true)
public class WebSecurityConfig 
//        extends WebSecurityConfigurerAdapter 
{

//    @Autowired
//    private AADAuthenticationFilter aadAuthFilter;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests().antMatchers("/home").permitAll();
//        http.authorizeRequests().antMatchers("/secure/**").authenticated();
//
//        http.logout().logoutSuccessUrl("/").permitAll();
//
//        http.authorizeRequests().anyRequest().permitAll();
//
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//
//        http.addFilterBefore(aadAuthFilter, UsernamePasswordAuthenticationFilter.class);
//    }
}
