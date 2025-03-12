import java.util.Scanner;

public class Exerc12 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        String nome;
        int horastrabalhadas, numerodependentes;

        System.out.println("Digite o nome:");
        nome = l.nextLine();
        System.out.println("Digite as horas trabalhadas: ");
        horastrabalhadas = l.nextInt();
        System.out.println("Número de dependentes: ");
        numerodependentes = l.nextInt();

        double salariobruto = (horastrabalhadas * 10 ) + ( numerodependentes * 60 );
   
        double salarioliquido = (horastrabalhadas * 10);
       
        salarioliquido= (salarioliquido * 0.085) + (salarioliquido * 0.05) ;

        double calc = salariobruto - salarioliquido;

        
        System.out.println("O funcionário" + nome + "possui um salário bruto de R$" + salariobruto + "e um salário líquido de R$" + calc );



}
}