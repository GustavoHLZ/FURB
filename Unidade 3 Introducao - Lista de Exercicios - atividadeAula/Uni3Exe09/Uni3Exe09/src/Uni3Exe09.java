import java.text.DecimalFormat;
import java.util.Scanner;

public class Uni3Exe09 {



    public static void main(String[] args) {

        Scanner l = new Scanner(System.in);

        double raio,altura, pi = 3.14159265358979;

        System.out.println("Digite o raio: ");
        raio = l.nextDouble();
        System.out.println("Digite a altura: ");
        altura = l.nextDouble();

        double volume = pi * Math.pow(raio, 2) * altura;

        DecimalFormat format = new DecimalFormat("#.##");

        System.out.println("O volume da lata de óleo é " + format.format(volume));

        l.close();

    }
}


