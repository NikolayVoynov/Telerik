import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class StudentsOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputNK = scanner.nextLine().split(" ");

        int n = Integer.parseInt(inputNK[0]);
        long k = Long.parseLong(inputNK[1]);

        String[] inputStudents = scanner.nextLine().split(" ");

        LinkedList<String> listOfStudents = new LinkedList<>(Arrays.asList(inputStudents));

        for (long i = 0; i < k; i++) {
            String[] change = scanner.nextLine().split(" ");
            String firstName = change[0];
            String secondName = change[1];

            listOfStudents.remove(firstName);
            int index = listOfStudents.indexOf(secondName);
            listOfStudents.add(index, firstName);

        }

        System.out.println(String.join(" ", listOfStudents));
    }
}
