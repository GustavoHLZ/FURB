import java.util.Scanner;

/**
 * SISTEMA DE GERENCIAMENTO HOSPITALAR
 *
 * Usa as 3 estruturas genericas implementadas:
 * 1. ListaEncadeada<String> -> Fila de espera de pacientes
 * 2. ListaDupla<String> -> Historico de atendimentos
 * 3. ListaCircular<String> -> Rodizio de medicos de plantao
 *
 * Demonstra que a mesma estrutura generica funciona com qualquer tipo
 * (aqui usamos String, mas funcionaria com Integer, Pessoa, etc.).
 */
public class Sistema {

    static ListaEncadeada<String> filaEspera = new ListaEncadeada<>();
    static ListaDupla<String> historico = new ListaDupla<>();
    static ListaCircular<String> rodizio = new ListaCircular<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDadosIniciais();

        int opcao;
        do {
            mostrarMenu();
            opcao = lerInt("Escolha: ");
            switch (opcao) {
                case 1:
                    menuFilaEspera();
                    break;
                case 2:
                    menuHistorico();
                    break;
                case 3:
                    menuRodizio();
                    break;
                case 4:
                    mostrarTodasEstruturas();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    // ============== MENUS ==============

    static void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("  SISTEMA HOSPITALAR - MENU PRINCIPAL");
        System.out.println("========================================");
        System.out.println(" 1 - Fila de Espera (Lista Simples)");
        System.out.println(" 2 - Historico (Lista Duplamente Encadeada)");
        System.out.println(" 3 - Rodizio Medicos (Lista Circular)");
        System.out.println(" 4 - Visualizar TODAS as estruturas");
        System.out.println(" 0 - Sair");
        System.out.println("========================================");
    }

    static void menuFilaEspera() {
        System.out.println("\n--- FILA DE ESPERA (Lista Simples) ---");
        System.out.println("1-Inserir  2-Retirar  3-Buscar  4-Inverter  5-Visualizar  6-Comprimento");
        int op = lerInt("Opcao: ");
        switch (op) {
            case 1:
                String dado = lerString("Paciente (ex: Ana - 35 - ALTA): ");
                filaEspera.inserir(dado);
                System.out.println(">> Inserido na fila.");
                filaEspera.imprimirVisual();
                break;
            case 2:
                String alvo = lerString("Paciente a retirar: ");
                filaEspera.retirar(alvo);
                System.out.println(">> Operacao de retirada executada.");
                filaEspera.imprimirVisual();
                break;
            case 3:
                String busca = lerString("Paciente a buscar: ");
                NoLista<String> achado = filaEspera.buscar(busca);
                System.out.println(achado != null ? "Encontrado: " + achado.getInfo() : "Nao encontrado.");
                break;
            case 4:
                filaEspera.inverter();
                System.out.println(">> Fila invertida.");
                filaEspera.imprimirVisual();
                break;
            case 5:
                filaEspera.imprimirVisual();
                break;
            case 6:
                System.out.println("Comprimento: " + filaEspera.obterComprimento());
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    static void menuHistorico() {
        System.out.println("\n--- HISTORICO (Lista Duplamente Encadeada) ---");
        System.out.println("1-Inserir  2-Retirar  3-Buscar  4-Exibir ordem inversa  5-Visualizar  6-Liberar");
        int op = lerInt("Opcao: ");
        switch (op) {
            case 1:
                String dado = lerString("Atendimento (ex: Diego - consulta): ");
                historico.inserir(dado);
                System.out.println(">> Inserido no historico.");
                historico.imprimirVisual();
                break;
            case 2:
                String alvo = lerString("Atendimento a retirar: ");
                historico.retirar(alvo);
                System.out.println(">> Operacao de retirada executada.");
                historico.imprimirVisual();
                break;
            case 3:
                String busca = lerString("Atendimento a buscar: ");
                NoListaDupla<String> achado = historico.buscar(busca);
                System.out.println(achado != null ? "Encontrado: " + achado.getInfo() : "Nao encontrado.");
                break;
            case 4:
                System.out.print("Ordem inversa: ");
                historico.exibirOrdemInversa();
                break;
            case 5:
                historico.imprimirVisual();
                break;
            case 6:
                historico.liberar();
                System.out.println(">> Historico liberado (todos os encadeamentos removidos).");
                historico.imprimirVisual();
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    static void menuRodizio() {
        System.out.println("\n--- RODIZIO DE MEDICOS (Lista Circular) ---");
        System.out.println("1-Inserir  2-Retirar  3-Buscar  4-Proximo do rodizio  5-Visualizar  6-Comprimento");
        int op = lerInt("Opcao: ");
        switch (op) {
            case 1:
                String dado = lerString("Medico (ex: Dr. Silva - Clinico): ");
                rodizio.inserir(dado);
                System.out.println(">> Medico adicionado ao rodizio.");
                rodizio.imprimirVisual();
                break;
            case 2:
                String alvo = lerString("Medico a retirar: ");
                rodizio.retirar(alvo);
                System.out.println(">> Operacao de retirada executada.");
                rodizio.imprimirVisual();
                break;
            case 3:
                String busca = lerString("Medico a buscar: ");
                NoListaCircular<String> achado = rodizio.buscar(busca);
                System.out.println(achado != null ? "Encontrado: " + achado.getInfo() : "Nao encontrado.");
                break;
            case 4:
                String medico = rodizio.proximoDoRodizio();
                System.out.println(">> Medico da vez: " + medico);
                rodizio.imprimirVisual();
                break;
            case 5:
                rodizio.imprimirVisual();
                break;
            case 6:
                System.out.println("Comprimento: " + rodizio.obterComprimento());
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    static void mostrarTodasEstruturas() {
        filaEspera.imprimirVisual();
        historico.imprimirVisual();
        rodizio.imprimirVisual();
    }

    // ============== UTILITARIOS ==============

    static int lerInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Digite um numero: ");
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    static String lerString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    static void carregarDadosIniciais() {
        // Lembre: a lista insere no INICIO. Por isso, para a fila ficar
        // com Ana-Bruno-Carla, precisamos inserir na ordem inversa.
        filaEspera.inserir("Carla - 28 - BAIXA");
        filaEspera.inserir("Bruno - 50 - MEDIA");
        filaEspera.inserir("Ana - 35 - ALTA");

        historico.inserir("Elena - 33 - retorno");
        historico.inserir("Diego - 41 - consulta");

        rodizio.inserir("Dr. Silva - Clinico");
        rodizio.inserir("Dra. Souza - Pediatra");
        rodizio.inserir("Dr. Lima - Cardiologista");

        System.out.println(">> Dados iniciais carregados.");
    }
}
