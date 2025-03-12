import java.text.DecimalFormat;
import java.util.Scanner;

public class Exerc4 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        double nota1,nota2,nota3;

        System.out.println("Digite a nota 1: ");
        nota1 = l.nextDouble();
        System.out.println("Digite a nota 2: ");
        nota2 = l.nextDouble();
        System.out.println("Digite a nota 3: ");
        nota3 = l.nextDouble();

        double calculo = (nota1 * 5) + (nota2 * 3) + (nota3 * 2);
        calculo = calculo/10;

        DecimalFormat format = new DecimalFormat("#.00");

        System.out.println(format.format(calculo));

    }
    
}
