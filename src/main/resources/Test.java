import java.util.Scanner;

public class Test {
    public static void main(String... var0) {
        Scanner var1 = new Scanner(System.in);
        if (var1.nextLine().equals("1")) {
            System.out.println(1);
            int i = 1/0;
        }
    }
}