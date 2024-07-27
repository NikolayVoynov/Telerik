public enum Status {
    OPEN, TODO, IN_PROGRESS, DONE, VERIFIED;

    @Override
    public String toString() {
        switch (this) {
            case OPEN:
                return "Open";
            case TODO:
                return "To Do";
            case IN_PROGRESS:
                return "In Progress";
            case DONE:
                return "Done";
            case VERIFIED:
                return "Verified";
            default:
                throw new IllegalArgumentException("No such status");
        }

    }
}
