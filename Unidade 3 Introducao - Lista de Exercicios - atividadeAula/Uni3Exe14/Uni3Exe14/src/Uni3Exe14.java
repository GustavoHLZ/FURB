import java.util.Scanner;

public class Uni3Exe14
 {
    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);
        double distancia, tempo;

        System.out.println("Digite a distância  percorrida: ");
        distancia = l.nextDouble();
        System.out.println("Digite o tempo: ");
        tempo = l.nextDouble();

        double velocidademedia = distancia/tempo;

        double combustivel = distancia/12;

        System.out.println("A velocidade média foi de " + velocidademedia + "km/h e a quantidade de combustível usado foi " + combustivel + " litros");

    }
}
