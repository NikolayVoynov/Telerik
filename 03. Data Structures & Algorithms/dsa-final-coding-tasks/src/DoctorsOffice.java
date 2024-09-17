import java.util.*;

public class DoctorsOffice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> peopleQueue = new ArrayList<>();

        Map<String, Integer> nameNumberOfAppearance = new HashMap<>();

        String input = scanner.nextLine();

        while (!input.equals("End")) {

            String[] commandArgs = input.split(" ");
            String operation = commandArgs[0];

            switch (operation) {
                case "Append":
                    String appendName = commandArgs[1];
                    peopleQueue.add(appendName);
                    nameNumberOfAppearance.put(appendName, nameNumberOfAppearance.getOrDefault(appendName, 0) + 1);
                    System.out.println("OK");
                    break;

                case "Insert":
                    int position = Integer.parseInt(commandArgs[1]);
                    String insertName = commandArgs[2];
                    if (position >= 0 && position <= peopleQueue.size()) {
                        peopleQueue.add(position, insertName);
                        nameNumberOfAppearance.put(insertName, nameNumberOfAppearance.getOrDefault(insertName, 0) + 1);
                        System.out.println("OK");
                    } else {
                        System.out.println("Error");
                    }
                    break;

                case "Find":
                    String findName = commandArgs[1];
                    int count = nameNumberOfAppearance.getOrDefault(findName, 0);
                    System.out.println(count);
                    break;

                case "Examine":
                    int examineCount = Integer.parseInt(commandArgs[1]);
                    if (examineCount > peopleQueue.size()) {
                        System.out.println("Error");
                    } else {
                        List<String> examinedPeople = new ArrayList<>();
                        for (int i = 0; i < examineCount; i++) {
                            String person = peopleQueue.remove(0);
                            examinedPeople.add(person);

                            nameNumberOfAppearance.put(person, nameNumberOfAppearance.get(person) - 1);
                        }
                        System.out.println(String.join(" ", examinedPeople));
                    }
                    break;

                default:
                    System.out.println("Error");
                    break;
            }

            input = scanner.nextLine();
        }
    }
}
