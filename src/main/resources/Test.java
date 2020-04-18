import java.util.*;

public class Test {
	public static void main(String... args) {
		Scanner scanner = new Scanner(System.in);
		if (scanner.nextLine().equals("1")) {
			throw new RuntimeException();
		}
	}
}