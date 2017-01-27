package sec.project.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    //private PasswordEncoder encoder;
    
    //Testing accounts are created
    @PostConstruct
    public void init() {
        this.userDetailsService.addUser("ted", "pass");//encoder.encode("pass"));
        this.userDetailsService.addUser("ben", "word");//encoder.encode("word"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/book"/*,/search*/).authenticated();
        http.formLogin()
                .permitAll();
        http.logout()
                .permitAll();
        http.sessionManagement().sessionFixation().none();//enables sessionhijack, to prevent .migrateSession()
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        this.encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }
    
}
