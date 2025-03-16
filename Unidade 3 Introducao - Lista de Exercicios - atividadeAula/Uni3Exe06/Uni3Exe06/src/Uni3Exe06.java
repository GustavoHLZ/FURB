import java.util.Scanner;

public class Uni3Exe06 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        double quilo, peso = 0.75;

        System.out.println("Digite o valor do prato: ");
        quilo = l.nextDouble();

        double calculo = quilo - peso;

         double valorprato = calculo * 25;

        System.out.println("O valor do prato do cliente é R$" + valorprato);

    }
}
