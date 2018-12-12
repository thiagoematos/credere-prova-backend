package credere.prova.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import credere.prova.backend.model.Face;
import credere.prova.backend.model.Movement;
import credere.prova.backend.model.Movements;
import credere.prova.backend.model.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpacialProbeTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws Exception {
        this.mvc.perform(delete("/posicao"))
                .andExpect(status().isOk());
    }

    @Test
    public void comandoInvalido() throws Exception {
        // given
        var movements = "{\"movimentos\": [\"A\"]}";

        // when
        this.mvc.perform(
                put("/posicao")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(movements))

                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nenhumComando() throws Exception {
        // given
        var movements = new Movements();

        // when
        this.mvc.perform(
                put("/posicao")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(movements)))

                // then
                .andExpect(status().isOk());
    }

    @Test
    public void movimentoInvalido() throws Exception {
        // given
        var movements = new Movements(
                List.of(
                        Movement.M, Movement.M,
                        Movement.M, Movement.M,
                        Movement.M, Movement.M
                )
        );

        //when
        this.mvc.perform(
                put("/posicao")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(movements)))

                // then
                .andExpect(status().isExpectationFailed())
                .andExpect(status().reason(containsString("Um movimento inválido foi detectado, infelizmente a sonda ainda não possui a habilidade de ir até (6, 0)")));
    }

    @Test
    public void somenteProsseguirSemGirar() throws Exception {
        // given
        var movements = new Movements(
                List.of(
                        Movement.M
                )
        );

        // when
        this.mvc.perform(
                put("/posicao")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(movements)))
                .andExpect(status().isOk());

        // then
        this.mvc.perform(get("/posicao"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new Status(1, 0, Face.D))));
    }

    @Test
    public void variosComandos() throws Exception {
        // given
        var movements = new Movements(
                List.of(
                        Movement.GE,
                        Movement.M, Movement.M, Movement.M,
                        Movement.GD,
                        Movement.M, Movement.M
                )
        );

        // when
        this.mvc.perform(
                put("/posicao")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(movements)))
                .andExpect(status().isOk());

        // then
        this.mvc.perform(get("/posicao"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new Status(2, 3, Face.D))));
    }

    @Test
    public void resetar() throws Exception {
        // given
        var movements = new Movements(
                List.of(
                        Movement.GE,
                        Movement.M, Movement.M, Movement.M,
                        Movement.GD,
                        Movement.M, Movement.M
                )
        );

        this.mvc.perform(
                put("/posicao")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(movements)))
                .andExpect(status().isOk());

        // when
        this.mvc.perform(delete("/posicao"))
                .andExpect(status().isOk());

        // then
        this.mvc.perform(get("/posicao"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new Status(0, 0, Face.D))));
    }

}
