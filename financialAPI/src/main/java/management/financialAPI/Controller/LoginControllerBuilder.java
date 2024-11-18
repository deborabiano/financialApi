package management.financialAPI.Controller;

import management.financialAPI.DAO.UsuarioRepository;

public class LoginControllerBuilder {
    private UsuarioRepository ignoredUsuarioRepository;

    public LoginControllerBuilder setIgnoredUsuarioRepository(UsuarioRepository ignoredUsuarioRepository) {
        this.ignoredUsuarioRepository = ignoredUsuarioRepository;
        return this;
    }

    public LoginController createLoginController() {
        return LoginController.createLoginController(ignoredUsuarioRepository);
    }
}