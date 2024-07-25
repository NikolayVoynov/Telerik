public class Main {
    public static void main(String[] args) {

//        ForumPost post1 = new ForumPost();
//        post1.author = "Steven";
//        post1.text = "How to find use for every Microsoft product.";
//        post1.upVotes = 2;
//
//        ForumPost post2 = new ForumPost();
//        post2.author = "Edward";
//        post2.text = "Ford Focus for sale. First owner. Good mileage.";
//        post2.upVotes = 300;
//
//        System.out.printf("%s / by %s, %d votes. %n", post1.text, post1.author, post1.upVotes);
//        System.out.printf("%s / by %s, %d votes. %n", post2.text, post2.author, post2.upVotes);

// ----------------------------------------------------------------------------------------------

//        ForumPost post1 = new ForumPost("Steven", "How to find use for every Microsoft product.", 2);
//        ForumPost post2 = new ForumPost("Edward", "Ford Focus for sale. First owner. Good mileage.", 300);
//
//        System.out.printf("%s / by %s, %d votes. %n", post1.text, post1.author, post1.upVotes);
//        System.out.printf("%s / by %s, %d votes. %n", post2.text, post2.author, post2.upVotes);

// ----------------------------------------------------------------------------------------------

//        ForumPost post1 = new ForumPost("Steven", "How to find use for every Microsoft product.");
//        ForumPost post2 = new ForumPost("Edward", "Ford Focus for sale. First owner. Good mileage.", 300);
//
//        System.out.printf("%s / by %s, %d votes. %n", post1.text, post1.author, post1.upVotes);
//        System.out.printf("%s / by %s, %d votes. %n", post2.text, post2.author, post2.upVotes);

// ----------------------------------------------------------------------------------------------

//        ForumPost post1 = new ForumPost("Steven", "How to find use for every Microsoft product.");
//        ForumPost post2 = new ForumPost("Edward", "Ford Focus for sale. First owner. Good mileage.", 300);
//
//        System.out.print(post1.format());
//        System.out.print(post2.format());

// -------------------------------------------------------------------------------------------

        ForumPost post1 = new ForumPost("Steven", "How to find use for every Microsoft product.");
        post1.replies.add("I like Google!");
        post1.replies.add("Ugh, Microsoft... :(");

        ForumPost post2 = new ForumPost("Edward", "Ford Focus for sale. First owner. Good mileage.", 300);

        System.out.print(post1.format());

        System.out.print(post2.format());


    }
}
