import java.util.Scanner;

public class Uni3Exe01 {


    public static void main(String[] args) {
       Scanner l = new Scanner(System.in);
        int largura,comprimento;

        System.out.println("Digite a largura: ");
        largura = l.nextInt();
        System.out.println("Digite o comprimento");
        comprimento = l.nextInt();

        int area = largura * comprimento;

        System.out.println(area);
    }
}
