import java.util.Scanner;

public class Uni3Exe15 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        int centena,dezena, unidade, numero;

        System.out.println("Digite um número: ");
        numero = l.nextInt();

        int calculoCentena = numero/100;
        int calculoDezena = (numero-(calculoCentena * 100))/10;
       int calculoUnidade = numero % 10;

        System.out.println(calculoCentena + " centena(s) " + calculoDezena + " dezena(s) " + calculoUnidade + " unidade(s)" );

        l.close();


    }
}
