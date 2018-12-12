package credere.prova.backend.controller;

import credere.prova.backend.exception.InvalidMovementException;
import credere.prova.backend.model.Movements;
import credere.prova.backend.model.Status;
import credere.prova.backend.service.SpacialProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/posicao")
public class SpacialProbeController {

    @Autowired
    private SpacialProbeService service;

    @GetMapping
    public Status current() {
        return service.getCurrent();
    }

    @PutMapping
    public void move(@RequestBody Movements movements, HttpServletResponse response) throws IOException {
        try {
            service.move(movements);
        } catch (InvalidMovementException e) {
            response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getMessage());
        }
    }

    @DeleteMapping
    public void resetPosition() {
        service.resetPosition();
    }

}
