package management.financialAPI.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import management.financialAPI.repository.cadastroUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/cadastro")
public class cadastroController {

    @Autowired
    private cadastroUserRepository repository;

    @Autowired
    private management.financialAPI.services.excelService excelService;

}
