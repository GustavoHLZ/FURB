import java.util.Scanner;

public class Uni3Exe13 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
       double altura,comprimento;

       System.out.println("Digite a altura:");
       altura  = l.nextDouble();
       System.out.println("Digite o comprimento:");
       comprimento = l.nextDouble();

       double area = altura * comprimento;

       area = area * 9;

       area = area * 12.50;

       System.out.println("O valor final é R$"+ area);

    }
}
