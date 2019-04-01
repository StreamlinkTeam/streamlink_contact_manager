package cl.streamlink.contact.utils.enums;


public enum ListeTempsEtat {
    EnAttenteDeValidation,
    Valide,
    Rejet;





    /*
     * @JsonCreator public static ListeTempsEtat fromString(final String value) {
     * return value != null ? Arrays.stream(values()).filter(val ->
     * MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
     * .findFirst().orElse(EnAttenteDeValidation) :EnAttenteDeValidation; }
     */


    /*
     * public static List<ListeTempsEtat> getAll() { return Arrays.asList(values());
     * }
     */


}
