import java.util.Scanner;

public class Exerc1 {
    public static void main(String[] args) {
       Scanner l = new Scanner(System.in);
        int altura,comprimento;

        System.out.println("Digite a altura: ");
        altura = l.nextInt();
        System.out.println("Digite o comprimento");
        comprimento = l.nextInt();

        int area = altura * comprimento;

        System.out.println(area);
    }
}
