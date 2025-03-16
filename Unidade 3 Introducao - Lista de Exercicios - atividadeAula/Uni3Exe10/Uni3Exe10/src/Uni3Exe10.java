import java.util.Scanner;

public class Uni3Exe10 {

    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        double catetoOposto, catetoAdjacente;

        System.out.println("Digite o cateto oposto: ");
        catetoOposto = l.nextDouble();

        System.out.println("Digite o cateto adjacente");
        catetoAdjacente = l.nextDouble();

        double calculo = Math.pow(catetoOposto, 2) + Math.pow(catetoAdjacente, 2);

        double hipotenusa = Math.sqrt(calculo);

        System.out.println("A hipotenusa é: " + hipotenusa);

    }
}
