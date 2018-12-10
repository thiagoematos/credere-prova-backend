package credere.prova.backend.model;

public class StatusResponse {

    private String error;
    private Status value;

    public StatusResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Status getValue() {
        return value;
    }

    public void setValue(Status value) {
        this.value = value;
    }

    public boolean hasError() {
        return this.error != null;
    }

}
