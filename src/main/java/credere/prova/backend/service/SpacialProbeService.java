package credere.prova.backend.service;

import credere.prova.backend.exception.InvalidMovementException;
import credere.prova.backend.model.Face;
import credere.prova.backend.model.Movement;
import credere.prova.backend.model.Movements;
import credere.prova.backend.model.Status;
import credere.prova.backend.util.TracerUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class SpacialProbeService {

    private static final Map<Face, Consumer<Status>> FORWARD_OPERATION =
            Map.of(
                    Face.E, (status) -> status.setX(status.getX() - 1),
                    Face.D, (status) -> status.setX(status.getX() + 1),
                    Face.B, (status) -> status.setY(status.getY() - 1),
                    Face.C, (status) -> status.setY(status.getY() + 1)
            );
    private static final Map<Face, Consumer<Status>> TURN_RIGHT_OPERATION =
            Map.of(
                    Face.E, (status) -> status.setFace(Face.C),
                    Face.C, (status) -> status.setFace(Face.D),
                    Face.D, (status) -> status.setFace(Face.B),
                    Face.B, (status) -> status.setFace(Face.E)
            );
    private static final Map<Face, Consumer<Status>> TURN_LEFT_OPERATION =
            Map.of(
                    Face.E, (status) -> status.setFace(Face.B),
                    Face.B, (status) -> status.setFace(Face.D),
                    Face.D, (status) -> status.setFace(Face.C),
                    Face.C, (status) -> status.setFace(Face.E)
            );
    private static final Map<Movement, Map<Face, Consumer<Status>>> OPERATIONS =
            Map.of(
                    Movement.GE, TURN_LEFT_OPERATION,
                    Movement.GD, TURN_RIGHT_OPERATION,
                    Movement.M, FORWARD_OPERATION
            );
    private static final Map<Movement, Function<Status, String>> TRACER_MESSAGE =
            Map.of(
                    Movement.GE, (status) -> "girou para a esquerda",
                    Movement.GD, (status) -> "girou para a direita",
                    Movement.M, (status) -> "se moveu no eixo " + status.getFace().getAxis()
            );

    private static Status CURRENT;

    public SpacialProbeService() {
        sendToTheBeginning();
    }

    public void move(Movements movements) throws InvalidMovementException {
        var result = CURRENT.copy();

        movements
                .movements
                .forEach(movement -> apply(movement, result));

        if (result.getX() < 0 || result.getX() >= 5 || result.getY() >= 5 || result.getY() < 0) {
            throw new InvalidMovementException(result.getX(), result.getY());
        }

        TracerUtil
                .with(result.getTrace())
                .log();

        CURRENT = result;
    }

    private void apply(Movement movement, Status status) {
        var operationDependsOnMovement = OPERATIONS.get(movement);
        var operationDependsOnFace = operationDependsOnMovement.get(status.getFace());

        operationDependsOnFace.accept(status);
        status.getTrace().add(TRACER_MESSAGE.get(movement).apply(status));
    }

    public Status getCurrent() {
        return CURRENT;
    }

    public void resetPosition() {
        sendToTheBeginning();
    }

    private void sendToTheBeginning() {
        CURRENT = Status.INITIAL;
    }

}
