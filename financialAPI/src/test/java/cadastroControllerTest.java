import management.financialAPI.user.cadastroUsuario;
import management.financialAPI.cadastro.dadosCadastro;
import management.financialAPI.user.enderecoUsuario;
import management.financialAPI.repository.cadastroUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.springframework.security.config.http.MatcherType.mvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CadastroControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<cadastroUsuario> dadosCadastroJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoCadastro> dadosDetalhamentoCadastroJson;

    @MockBean
    private cadastroUserRepository repository;

}

@Test
@DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
@WithMockUser
public void cadastrar_cenario1() throws Exception {
    var response = mvc
            .perform(post("/cadastro"))
            .andReturn().getResponse();

    assertThat(response.getStatus())
            .isEqualTo(HttpStatus.BAD_REQUEST.value());
}

@Test
@DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
@WithMockUser
public void cadastrar_cenario2() throws Exception {
    var dadosCadastro = new dadosCadastro(
            "Teste 01",
            "123.456.789-00",
            "teste@financialapi.com",
            "06/10/1994",
            "61999999999",
            "123456",
            dadosEndereco());

    when(repository.save(any())).thenReturn(new cadastroUsuario(dadosCadastro));

    var response = mvc
            .perform(post("/cadastro")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(dadosCadastroJson.write(dadosCadastro).getJson()))
            .andReturn().getResponse();

    var dadosDetalhamento = new DadosDetalhamentoCadastro(
            null,
            dadosCadastro.nome(),
            dadosCadastro.email(),
            dadosCadastro.cpf(),
            dadosCadastro.telefone(),
            dadosCadastro.dataNascimento(),
            new enderecoUsuario(dadosCadastro.endereco())
    );
    var jsonEsperado = dadosDetalhamentoMedicoJson.write(dadosDetalhamento).getJson();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
}

private void DadosEndereco dadosEndereco() {
    return new DadosEndereco(
            "rua xpto",
            "bairro",
            "00000000",
            "Brasilia",
            "DF",
            null,
            null
    );
}