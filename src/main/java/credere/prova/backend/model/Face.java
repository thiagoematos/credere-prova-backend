package credere.prova.backend.model;

public enum Face {

    E,
    D,
    C,
    B;

    public String getAxis() {
        return this == E || this == B ? "y" : "x";
    }

}
