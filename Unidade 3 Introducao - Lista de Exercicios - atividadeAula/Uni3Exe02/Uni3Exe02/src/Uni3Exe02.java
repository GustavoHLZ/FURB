import java.util.Scanner;

public class Uni3Exe02
 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        double valor;

        System.out.println("Digite o valor do para de sapato");
        valor = l.nextDouble();

        double valorcomdesconto = valor * 0.12; 
        valor = valor - valorcomdesconto;

        System.out.println("O valor do desconto é de R$" + valorcomdesconto);
        System.out.println("O preço do par de sapatos com desconto é R$"+ valor);

    }
}
