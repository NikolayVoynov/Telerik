import java.util.Scanner;

public class LeastMajorityMultiple {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[5];

        for (int i = 0; i < 5; i++) {
            nums[i] = Integer.parseInt(scanner.nextLine());
        }

        int result = 0;
        int counter = 0;

        while (counter < 3) {
            result++;
            if (result % nums[0] == 0){
                counter++;
            }
            if (result % nums[1] == 0){
                counter++;
            }
            if (result % nums[2] == 0){
                counter++;
            }
            if (result % nums[3] == 0){
                counter++;
            }
            if (result % nums[4] == 0){
                counter++;
            }

            if (counter < 3){
                counter = 0;
            }
        }

        System.out.println(result);
    }
}
