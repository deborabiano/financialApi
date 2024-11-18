package management.financialAPI.services;

import jakarta.validation.Valid;
import management.financialAPI.cadastro.dadosAutenticacao;
import management.financialAPI.repository.usuarioRepository;
import management.financialAPI.security.dadosTokenJWT;
import management.financialAPI.user.usuarioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/login")
public class autenticacaoService {

    @Autowired
    private usuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid dadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.allocateToken(String.valueOf((usuarioLogin) authentication.getPrincipal()));

        return ResponseEntity.ok(new dadosTokenJWT(tokenJWT));
    }
}