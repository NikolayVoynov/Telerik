import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class NoahArk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        TreeMap<String, Integer> noahArk = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String animal = scanner.nextLine();

            if (!noahArk.containsKey(animal)) {
                noahArk.put(animal, 1);
            } else {
                noahArk.put(animal, noahArk.get(animal) + 1);
            }
        }

        noahArk.entrySet().forEach(entry->{
            String even = entry.getValue() % 2 == 0 ? "Yes" : "No";

            System.out.printf("%s %d %s%n", entry.getKey(), entry.getValue(), even);
        });

//        StringBuilder result = new StringBuilder();
//
//        for (Map.Entry<String, Integer> entry : noahArk.entrySet()) {
//            String even = entry.getValue() % 2 == 0 ? "Yes" : "No";
//
//            result
//                    .append(entry.getKey())
//                    .append(" ")
//                    .append(entry.getValue())
//                    .append(" ")
//                    .append(even)
//                    .append(System.lineSeparator());
//        }
//
//        System.out.println(result.toString().trim());
    }
}
