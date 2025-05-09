public enum Entry {
    S_UNSCORED('S', false),
    O_UNSCORED('O', false),
    S_SCORED('$', true),
    O_SCORED('0',true);

    public char display;
    public boolean scored;

    Entry(char display, boolean scored) {
        this.display = display;
        this.scored = scored;
    }

    /**
     * Getter für den Anzeige Char
     * @return Entsprechender Spielstein als Char
     */
    public char getDisplay() {
        return display;
    }

    /**
     * Getter für den Wertungszustand
     * @return True: Gewertet, False: Ungewertet
     */
    public boolean isScored() {
        return scored;
    }

    /**
     * Methode um Chars in Entrys umzuwandeln
     * @param display Spielsteine als Char
     * @return Enum Spielsteine
     */
    public static Entry fromDisplay(char display) {
      return switch (display) {
        case 'S' -> S_UNSCORED;
        case 'O' -> O_UNSCORED;
        case '$' -> S_SCORED;
        case '0' -> O_SCORED;
        default -> null;
        };
    }

    /**
     * Methode um gewertete Spielsteine wieder zu entwerten
     * @return Entwertete Spielsteine
     */
    public Entry toUnscored() {
        return switch (this) {
            case S_SCORED -> S_UNSCORED;
            case O_SCORED -> O_UNSCORED;
            case S_UNSCORED -> S_UNSCORED;
            case O_UNSCORED -> O_UNSCORED;
        };
    }

    /**
     * Methode um Spielsteine von ungewertet zu gewertet zu wandeln
     * @return Umgewandelter Spielstein
     */
    public Entry toScored() {
        return switch (this) {
            case S_UNSCORED -> S_SCORED;
            case O_UNSCORED -> O_SCORED;
            case S_SCORED -> S_SCORED;
            case O_SCORED -> O_SCORED;
        };
    }

}
