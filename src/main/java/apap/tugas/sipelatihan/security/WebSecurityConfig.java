package apap.tugas.sipelatihan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Add otorisasi 
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/user/add").permitAll()
                .antMatchers("/api/v1/**").permitAll()
<<<<<<< Updated upstream
                .antMatchers("/trainer/add").hasAnyAuthority("Staff Training")
                .antMatchers("/trainer/update/**").hasAnyAuthority("Staff Training")
                .antMatchers("/pelatihan").hasAnyAuthority("Staff Training", "Kepala Bagian", "Kepala Departemen HR")
                .antMatchers("/pelatihan/add").hasAnyAuthority("Staff Training", "Kepala Bagian")
                .antMatchers("/pelatihan/change/**").hasAnyAuthority("Staff Training", "Kepala Bagian")
                .antMatchers("/pelatihan/view/**").hasAnyAuthority("Staff Training", "Kepala Bagian", "Kepala Departemen HR")
                .antMatchers("/pelatihan/delete/**").hasAnyAuthority("Staff Training", "Kepala Bagian", "Kepala Departemen HR")
                .antMatchers("/peserta/add").hasAnyAuthority("Staff Training", "Kepala Bagian")
                .antMatchers("/pelatihan/*/add/peserta").hasAnyAuthority("Staff Training", "Kepala Bagian")
=======
                .antMatchers("/peserta/tambah").hasAnyAuthority("Karyawan")
                .antMatchers("/pelatihan/view/**").hasAnyAuthority("Kepala Bagian", "Kepala Departemen HR","Staff Training")
>>>>>>> Stashed changes
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and().cors().and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder())
//                .withUser("admin").password(encoder().encode("admin"))
//                .roles("Staff Training");
//    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

}
