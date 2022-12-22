import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        StringBuilder numToN = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t * m; i++) {
            numToN.append(Integer.toString(i, t));
        }
        for (int i = p - 1; answer.length() < t; i+=m) {
            answer.append(numToN.charAt(i));
        }
        System.out.println(answer.toString().toUpperCase());
    }
}