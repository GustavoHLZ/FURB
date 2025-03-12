import java.util.Scanner;

public class Exerc8{

    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        double dolar, cotacao = 5.65;

        System.out.println("Digite o valor em dólares");
        dolar = l.nextDouble();

        cotacao = dolar * cotacao;

        System.out.println("O atendente deve devolver R$" + cotacao + " para o cliente.");
      

    }
}