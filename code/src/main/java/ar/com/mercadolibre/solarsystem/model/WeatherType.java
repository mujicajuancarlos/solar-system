package ar.com.mercadolibre.solarsystem.model;

public enum WeatherType {

    DROUGHT("Sequia"),
    RAIN("Lluvia"),
    STABLE("Presion y temperatura optima"),
    UNDEFINED("Indefinido");

    private final String description;

    WeatherType(String description) {
        this.description = description;
    }

    public boolean isRain() {
        return this == WeatherType.RAIN;
    }

    public String getDescription() {
        return description;
    }
}
