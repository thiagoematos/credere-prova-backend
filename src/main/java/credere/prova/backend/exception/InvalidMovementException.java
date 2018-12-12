package credere.prova.backend.exception;

public class InvalidMovementException extends Throwable {

    public InvalidMovementException(int x, int y) {
        super("Um movimento inválido foi detectado, infelizmente a sonda ainda não possui a habilidade de ir até (" + x + ", " + y + ")");
    }

}
