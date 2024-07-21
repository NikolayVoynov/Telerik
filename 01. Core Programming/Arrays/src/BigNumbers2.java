import java.util.Scanner;

public class BigNumbers2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String numbers = scan.nextLine();

        //String numbers = "12 1 890 65";
        String[] tokens = numbers.split(" ");
        int[] ary = new int[tokens.length];

        int i = 0;
        for (String token : tokens) {
            ary[i++] = Integer.parseInt(token);
        }

        if (ary[0] < ary[1]) {
            int temp = ary[1];
            ary[1] = ary[0];
            ary[0] = temp;
        }
        //number 1
        String number1 = scan.nextLine();
        String number2 = scan.nextLine();

        //String numbers = "12 1 890 65";
        String[] tokens1 = number1.split(" ");
        String[] tokens2 = number2.split(" ");
        int length = Math.max(tokens1.length, tokens2.length);
        int[] ary1 = new int[length];
        int[] ary2 = new int[length];

        int i1 = 0;
        for (String token : tokens1) {
            ary1[i1++] = Integer.parseInt(token);
        }
        //number 2
        int i2 = 0;
        for (String token : tokens2) {
            ary2[i2++] = Integer.parseInt(token);
        }

        int naum = 0;
        for (int isum = 0; isum < length; isum++) {

            if (ary1[isum] + ary2[isum] + naum <= 9) {
                System.out.print((ary1[isum] + ary2[isum] + naum) + " ");
                naum = 0;
            } else {
                System.out.print((ary1[isum] + ary2[isum] + naum - 10) + " ");
                naum = 1;
            }
        }
    }
}

