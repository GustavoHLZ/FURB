import java.util.Scanner;

public class Uni3Exe05 {
    public static void main(String[] args) {

        Scanner l = new Scanner(System.in);
        double anelalimento = 7, anelchip = 4.00, valor;

        System.out.println("Digite o gasto total da granja para marcar todos os frangos: ");
        double quantidade = l.nextDouble();
        valor = quantidade;  
        quantidade = (quantidade * anelalimento) + (quantidade * anelchip);
        
        System.out.println("O gasto total para marcar " + valor + " é R$" + quantidade);

    }
}
