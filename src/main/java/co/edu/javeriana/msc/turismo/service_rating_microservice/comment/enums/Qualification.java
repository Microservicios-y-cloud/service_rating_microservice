package co.edu.javeriana.msc.turismo.service_rating_microservice.comment.enums;

import lombok.Getter;

@Getter
public enum Qualification {
    DEFICIENTE(1),
    REGULAR(2),
    SATISFACTORIO(3),
    BUENO(4),
    SOBRESALIENTE(5);


    private final int estrellas;

    Qualification(int estrellas) {
        this.estrellas = estrellas;
    }

    public static Qualification fromEstrellas(int estrellas) {
        for (Qualification q : Qualification.values()) {
            if (q.estrellas == estrellas) {
                return q;
            }
        }
        throw new IllegalArgumentException("Calificación no válida: " + estrellas);
    }

}