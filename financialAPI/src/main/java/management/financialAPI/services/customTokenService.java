package management.financialAPI.services;

import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

@Service
public class customTokenService implements TokenService {

    @Override
    public Token allocateToken(String extendedInformation) {
        // Implementação personalizada para gerar o token
        return null;
    }

    @Override
    public Token verifyToken(String key) {
        // Implementação personalizada para verificar o token
        return null;
    }
}