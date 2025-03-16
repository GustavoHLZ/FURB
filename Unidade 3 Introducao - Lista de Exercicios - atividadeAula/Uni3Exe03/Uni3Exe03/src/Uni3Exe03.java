import java.text.DecimalFormat;
import java.util.Scanner;

public class Uni3Exe03 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        double precogasolina,valor;

        System.out.println("Digite o preço o valor da gasolina: ");
        precogasolina=l.nextDouble();
        System.out.println("Digite o valor do pagamento: ");
        valor=l.nextDouble();

        DecimalFormat format = new DecimalFormat("#.##");
    
        double calculogasolina = valor/ precogasolina;

        System.out.println("O motorista conseguiu colocar:" + format.format(calculogasolina) + " litros");

    }
}
