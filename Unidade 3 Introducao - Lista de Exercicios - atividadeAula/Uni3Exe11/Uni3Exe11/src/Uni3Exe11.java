import java.util.Scanner;

public class Uni3Exe11 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);

        System.out.println("Digite a temperatura em °C (Celsius): ");
        double C = l.nextDouble();

        double F = (C * 9/5) + 32;

        System.out.println(F);
}
}
