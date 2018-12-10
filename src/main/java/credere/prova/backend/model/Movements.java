package credere.prova.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Movements {

    @JsonProperty("movimentos")
    public List<Movement> movements = new ArrayList<>();

    public Movements() {
    }

    public Movements(List<Movement> movements) {
        this.movements = movements;
    }

}
