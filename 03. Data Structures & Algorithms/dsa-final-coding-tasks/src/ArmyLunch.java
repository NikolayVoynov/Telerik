import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArmyLunch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String[] inputSoldiers = Arrays.stream(scanner.nextLine().split(" ")).toArray(String[]::new);

        List<String> sergeants = new ArrayList<>();
        List<String> corporals = new ArrayList<>();
        List<String> privates = new ArrayList<>();

        for (String soldier : inputSoldiers) {
            if (soldier.startsWith("S")) {
                sergeants.add(soldier);
            } else if (soldier.startsWith("C")) {
                corporals.add(soldier);
            } else if (soldier.startsWith("P")) {
                privates.add(soldier);
            }
        }

        StringBuilder result = new StringBuilder();


        for (String sergeant : sergeants) {
            result.append(sergeant).append(" ");
        }

        for (String corporal : corporals) {
            result.append(corporal).append(" ");
        }

        for (String privat : privates) {
            result.append(privat).append(" ");
        }


        System.out.println(result.toString().trim());
    }
}
