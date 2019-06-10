package ar.com.mercadolibre.solarsystem.model;

public enum Weather {

    DROUGHT("Sequia"),
    RAIN("Lluvia"),
    STABLE("Presion y temperatura optima"),
    UNDEFINED("Indefinido");

    private final String description;

    Weather(String description) {
        this.description = description;
    }

    public boolean isRain() {
        return this == Weather.RAIN;
    }

    public String getDescription() {
        return description;
    }
}
