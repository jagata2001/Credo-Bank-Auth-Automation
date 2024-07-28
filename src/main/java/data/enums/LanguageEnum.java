package data.enums;

public enum LanguageEnum {
    GEO("ქართული"),
    MEG("მეგრული"),
    SVA("სვანური");


    private final String text;

    LanguageEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
