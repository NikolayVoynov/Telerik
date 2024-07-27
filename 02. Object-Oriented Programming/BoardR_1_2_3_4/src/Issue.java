import java.time.LocalDate;

public class Issue extends BoardItem {

    public static final String NO_DESCRIPTION = "No description";
    private final String description;

    public Issue(String title, String description, LocalDate dueDate) {
        super(title, dueDate);

        if (description.isEmpty()) {
            this.description = NO_DESCRIPTION;
        } else {
            this.description = description;
        }
    }

    public String getDescription(){
        return description;
    }


    @Override
    public String viewInfo(){
        String baseInfo = super.viewInfo();

        return String.format("Issue: %s, Description: %s", baseInfo, this.getDescription());
    }


}
