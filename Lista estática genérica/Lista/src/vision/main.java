package vision;

import control.ListaEstatica;

public class main {

    public static void main(String[] args) {

        System.out.println("=== Casos de Teste PL01 – Validar funcionamento da implementação estática de lista ===\n");

        // Casos anteriores (mantidos para referência)

        // Caso 1 - Lista vazia
        System.out.println("--- Caso 1: Lista vazia ---");
        ListaEstatica<Integer> lista1 = new ListaEstatica<>();
        System.out.println("estaVazia: " + lista1.estaVazia()); // true
        System.out.println("toString: \"" + lista1.toString() + "\"");
        System.out.println();

        // Caso 2 - Inserir um elemento
        System.out.println("--- Caso 2: Inserir 5 ---");
        ListaEstatica<Integer> lista2 = new ListaEstatica<>();
        lista2.inserir(5);
        System.out.println("toString: \"" + lista2.toString() + "\""); // "5"
        System.out.println();

        // Caso 3 - Inserir múltiplos elementos
        System.out.println("--- Caso 3: Inserir 5, 10, 15 ---");
        ListaEstatica<Integer> lista3 = new ListaEstatica<>();
        lista3.inserir(5);
        lista3.inserir(10);
        lista3.inserir(15);
        System.out.println("toString: \"" + lista3.toString() + "\""); // "5,10,15"
        System.out.println();

        // Caso 4 - Buscar elemento presente
        System.out.println("--- Caso 4: Buscar 10 ---");
        ListaEstatica<Integer> lista4 = new ListaEstatica<>();
        lista4.inserir(5);
        lista4.inserir(10);
        lista4.inserir(15);
        System.out.println("buscar(10): " + lista4.buscar(10)); // 1
        System.out.println();

        // Caso 5 - Buscar elemento ausente
        System.out.println("--- Caso 5: Buscar 20 (ausente) ---");
        ListaEstatica<Integer> lista5 = new ListaEstatica<>();
        lista5.inserir(5);
        lista5.inserir(10);
        lista5.inserir(15);
        System.out.println("buscar(20): " + lista5.buscar(20)); // -1
        System.out.println();

        // Caso 6 - Retirar elemento
        System.out.println("--- Caso 6: Retirar 10 ---");
        ListaEstatica<Integer> lista6 = new ListaEstatica<>();
        lista6.inserir(5);
        lista6.inserir(10);
        lista6.inserir(15);
        lista6.retirar(10);
        System.out.println("toString: \"" + lista6.toString() + "\""); // "5,15"
        System.out.println();

        // Caso 7 - Liberar lista
        System.out.println("--- Caso 7: Liberar lista ---");
        ListaEstatica<Integer> lista7 = new ListaEstatica<>();
        lista7.inserir(5);
        lista7.inserir(10);
        lista7.liberar();
        System.out.println("estaVazia: " + lista7.estaVazia()); // true
        System.out.println("toString: \"" + lista7.toString() + "\"");
        System.out.println();

        // Caso 8 - obterElemento
        System.out.println("--- Caso 8: obterElemento(1) ---");
        ListaEstatica<Integer> lista8 = new ListaEstatica<>();
        lista8.inserir(5);
        lista8.inserir(10);
        lista8.inserir(15);
        System.out.println("obterElemento(1): " + lista8.obterElemento(1)); // 10
        System.out.println();

        // Caso 9 - IndexOutOfBoundsException
        System.out.println("--- Caso 9: obterElemento(5) com lista de 3 elementos ---");
        ListaEstatica<Integer> lista9 = new ListaEstatica<>();
        lista9.inserir(5);
        lista9.inserir(10);
        lista9.inserir(15);
        try {
            lista9.obterElemento(5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
        System.out.println();

        // Caso 10 - inverter() com quantidade par de dados
        System.out.println("--- Caso 10: inverter() com 4 elementos (quantidade par) ---");
        ListaEstatica<Integer> lista10 = new ListaEstatica<>();
        lista10.inserir(5);
        lista10.inserir(10);
        lista10.inserir(15);
        lista10.inserir(20);
        lista10.inverter();
        String resultado10 = lista10.toString();
        System.out.println("toString após inverter: \"" + resultado10 + "\"");
        System.out.println("Esperado:              \"20,15,10,5\"");
        System.out.println("✓ Caso 10 " + (resultado10.equals("20,15,10,5") ? "PASSOU" : "FALHOU"));
        System.out.println();

        // Caso 11 - inverter() com quantidade ímpar de dados
        System.out.println("--- Caso 11: inverter() com 5 elementos (quantidade ímpar) ---");
        ListaEstatica<Integer> lista11 = new ListaEstatica<>();
        lista11.inserir(5);
        lista11.inserir(10);
        lista11.inserir(15);
        lista11.inserir(20);
        lista11.inserir(25);
        lista11.inverter();
        String resultado11 = lista11.toString();
        System.out.println("toString após inverter: \"" + resultado11 + "\"");
        System.out.println("Esperado:              \"25,20,15,10,5\"");
        System.out.println("✓ Caso 11 " + (resultado11.equals("25,20,15,10,5") ? "PASSOU" : "FALHOU"));
        System.out.println();
    }

}