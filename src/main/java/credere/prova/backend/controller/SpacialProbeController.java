package credere.prova.backend.controller;

import credere.prova.backend.exception.InvalidMovementException;
import credere.prova.backend.model.Movements;
import credere.prova.backend.model.SpacialProbe;
import credere.prova.backend.service.SpacialProbeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "Posição", description = "Controlar a posição da sonda, através de movimentos pré-definidos.")
@RestController
@RequestMapping("/posicao")
public class SpacialProbeController {

    @Autowired
    private SpacialProbeService service;

    @ApiOperation(value = "Obtém a posição atual da sonda.")
    @GetMapping
    public SpacialProbe current() {
        return service.getCurrent();
    }

    @ApiOperation(value = "Faz a sonda movimentar-se.")
    @PutMapping
    public SpacialProbe move(@RequestBody Movements movements, HttpServletResponse response) throws IOException {
        try {
            return service.move(movements);
        } catch (InvalidMovementException e) {
            response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getMessage());
        }
        return null;
    }

    @ApiOperation(value = "Volta a sonda para a posição inicial.")
    @DeleteMapping
    public void resetPosition() {
        service.resetPosition();
    }

}
