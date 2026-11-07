package py.edu.uc.lp3.rest.controller.dto;

/** Body del PUT: solo los dos campos que Arma.reasignar(...) acepta cambiar. */
public class ReasignacionRequest {

    private String equipo;
    private float precio;

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
