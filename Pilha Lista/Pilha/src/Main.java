import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite a expressão para validar os delimitadores:");
        String expressao = scanner.nextLine();

        if (verificarDelimitadores(expressao)) {
            System.out.println("A aplicação deve indicar que o uso de delimitadores está correta.");
        } else {
            System.out.println("A aplicação deve indicar que o uso de delimitadores está incorreta.");
        }
        
        scanner.close();
    }

    public static boolean verificarDelimitadores(String exp) {
        // Usamos PilhaLista (Questão 3) para validar (Questão 5)
        Pilha<Character> pilha = new PilhaLista<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // Se for abridor, empilha
            if (c == '(' || c == '[' || c == '{') {
                pilha.push(c);
            } 
            // Se for fechador, verifica o topo da pilha
            else if (c == ')' || c == ']' || c == '}') {
                
                // Erro: fechador sem abridor correspondente na pilha
                if (pilha.estaVazia()) {
                    return false;
                }

                char topo = pilha.pop();

                // Erro: o abridor no topo não forma par com o fechador atual
                if (c == ')' && topo != '(') return false;
                if (c == ']' && topo != '[') return false;
                if (c == '}' && topo != '{') return false;
            }
        }

        // Se sobrar algo na pilha, é porque abriu e não fechou
        return pilha.estaVazia();
    }
}

