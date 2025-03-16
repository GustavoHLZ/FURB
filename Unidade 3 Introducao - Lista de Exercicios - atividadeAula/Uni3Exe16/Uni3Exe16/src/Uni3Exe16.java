import java.util.Scanner;

public class Uni3Exe16 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        int valortotaldacompra,valortotalcliente;
        
        System.out.println("Valor total da compra:");
        valortotaldacompra = l.nextInt();
        System.out.println("Valor total dado pelo cliente:");
        valortotalcliente = l.nextInt();
        
        int Troco = valortotalcliente - valortotaldacompra;
        int calculonota100 = Troco/100;
        int restodovalor = Troco % 100; // Sobra das notas de 100
        int calculonotas10 = restodovalor/10;
        restodovalor = restodovalor % 10;
        int calculonota1 = restodovalor;

        int totalNotas = calculonota100 + calculonotas10 + calculonota1;

        System.out.println("Notas de 100: " + calculonota100);
        System.out.println("Notas de 10: " + calculonotas10);
        System.out.println("Notas de 1: " + calculonota1);
        System.out.println("O número mínimo de notas de troco é: " + totalNotas);
       


        l.close();

    }
}
