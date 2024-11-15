package management.financialAPI.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Seguranca {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Desabilitar CSRF para simplificar a implementação de APIs, habilitar em produção se necessário.
                .authorizeRequests()
                .requestMatchers("/login", "cadastro/upload-excel").permitAll() // Permitir acesso sem autenticação ao login e upload.
                .anyRequest().authenticated()          // Todas as outras requisições devem ser autenticadas.
                .and()
                .formLogin().disable()  // Desabilitar a página de login padrão do Spring Security.
                .httpBasic();           // Habilitar autenticação básica HTTP.

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Criptografia para senhas.
    }
}
