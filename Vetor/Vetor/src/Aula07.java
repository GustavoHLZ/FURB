public class Aula07 {

    public static void main(String[] args) {

        Vetor vetor = new Vetor(10);

        vetor.adiciona("B");
        vetor.adiciona("C");
        vetor.adiciona("E");
        vetor.adiciona("F");
        vetor.adiciona("G");

        System.out.println("Vetor original: " + vetor);

        // Testando a inserção no início
        vetor.adiciona(0, "A");
        System.out.println("Após adicionar A na pos 0: " + vetor);

        // Testando a inserção no meio
        vetor.adiciona(3, "D");
        System.out.println("Após adicionar D na pos 3: " + vetor);
    }
}