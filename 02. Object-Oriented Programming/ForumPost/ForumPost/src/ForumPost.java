import java.util.ArrayList;

public class ForumPost {
    String author;
    String text;
    int upVotes;
    ArrayList<String> replies;


    public ForumPost(String author, String text) {
        this(author, text, 0);
    }

    public ForumPost(String author, String text, int upVotes) {
        this.author = author;
        this.text = text;
        this.upVotes = upVotes;
        this.replies = new ArrayList<>();
    }

    String format() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s / by %s, %d votes. %n", this.text, this.author, this.upVotes));

        if (!replies.isEmpty()) {
            for (String reply : replies) {
                result.append(reply)
                        .append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}