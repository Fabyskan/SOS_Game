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

    public char getDisplay() {
        return display;
    }

    public boolean isScored() {
        return scored;
    }

    public static Entry fromDisplay(char display) {
      return switch (display) {
        case 'S' -> S_UNSCORED;
        case 'O' -> O_UNSCORED;
        case '$' -> S_SCORED;
        case '0' -> O_SCORED;
        default -> null;
        };
    }

    public Entry toUnscored() {
        return switch (this) {
            case S_SCORED -> S_UNSCORED;
            case O_SCORED -> O_UNSCORED;
            case S_UNSCORED -> S_UNSCORED;
            case O_UNSCORED -> O_UNSCORED;
        };
    }

    public Entry toScored() {
        return switch (this) {
            case S_UNSCORED -> S_SCORED;
            case O_UNSCORED -> O_SCORED;
            case S_SCORED -> S_SCORED;
            case O_SCORED -> O_SCORED;
        };
    }

}
