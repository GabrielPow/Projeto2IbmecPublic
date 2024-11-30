package ibmec.meninasabores.config;

import ibmec.meninasabores.security.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;

    public SecurityConfig(CustomAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers(new AntPathRequestMatcher("/index/**")).permitAll() // Permitir acesso a todas as rotas em /public/** sem autentica��o
                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN") // Requer login e papel de ADMIN para /admin/**
                .anyRequest().permitAll() // Liberar tudo o que estiver fora dos diret�rios /user/ e /admin/
                )
                .formLogin((form) -> form
                .loginPage("/home/login") // Definir p�gina de login personalizada
                .successHandler(successHandler) // Usar o handler customizado para redirecionamento ap�s login
                .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/home/logout") // URL for logout
                        .logoutSuccessUrl("/home") // Redirect to /home after logout
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/home/logout", "POST")) // Logout via POST);
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("Menin@Sab0r3s2024"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
