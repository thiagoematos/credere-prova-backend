package credere.prova.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Status {

    private int x;
    private int y;
    private Face face;

    @JsonIgnore
    private List<String> trace = new ArrayList<>();

    public static final Status INITIAL = new Status(0, 0, Face.D);

    public Status() {
    }

    public Status(int x, int y, Face face) {
        this.x = x;
        this.y = y;
        this.face = face;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public List<String> getTrace() {
        return trace;
    }

    public Status copy() {
        return new Status(this.x, this.y, this.face);
    }

}
