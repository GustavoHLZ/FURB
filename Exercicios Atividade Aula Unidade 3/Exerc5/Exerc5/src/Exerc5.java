import java.util.Scanner;

public class Exerc5 {
    public static void main(String[] args) {

        Scanner l = new Scanner(System.in);
        double anelalimento = 7, anelchip = 4.00, valor;

        System.out.println("Digite o gasto total da granja para marcar todos os frangos: ");
        double valortotal = l.nextDouble();
        valor = valortotal;  
        valortotal = (valortotal * anelalimento) + (valortotal * anelchip);
        
        System.out.println("O gasto total para marcar " + valor + " é R$" + valortotal);

    }
}
