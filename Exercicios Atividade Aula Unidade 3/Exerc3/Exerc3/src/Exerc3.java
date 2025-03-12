import java.text.DecimalFormat;
import java.util.Scanner;

public class Exerc3 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        double valorgasolina,valor;

        System.out.println("Digite o preço o valor da gasolina: ");
        valorgasolina=l.nextDouble();
        System.out.println("Digite o valor do pagamento: ");
        valor=l.nextDouble();

        DecimalFormat format = new DecimalFormat("#.##");
    
        double calculogasolina = valor/ valorgasolina;

        System.out.println("O motorista conseguiu colocar:" + format.format(calculogasolina) + " litros");

    }
}
