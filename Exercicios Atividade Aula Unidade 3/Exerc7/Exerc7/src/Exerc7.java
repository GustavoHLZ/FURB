import java.util.Scanner;

public class Exerc7 {
    public static void main(String[] args) {

        Scanner l = new Scanner(System.in);
        
        double qnt350, qnt600, qnt2l;

        System.out.println("Digite a quantidade lata de 350ml");
        qnt350 = l.nextDouble();
        System.out.println("Digite a quantidade lata de 600ml");
        qnt600 = l.nextDouble();
        System.out.println("Digite a quantidade lata de 2 litros");
        qnt2l = l.nextDouble();

        double calculo = ((qnt350 * 350) /1000)  + ((qnt600 * 600) /1000) + (qnt2l *2);

        System.out.println("O cliente comprou ao total " + calculo + " litros");

    }
}
