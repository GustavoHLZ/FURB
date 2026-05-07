package pilha;

import fila.FilaVetor;

public class PilhaVetor<T> implements Pilha<T> {
    private T[] info;
    private int limite;
    private int tamanho;

    @SuppressWarnings("unchecked")
    public PilhaVetor(int limite) {
        this.info = (T[]) new Object[limite];
        this.limite = limite;
        this.tamanho = 0;
    }

    // ─────────── MÉTODOS BÁSICOS ───────────

    @Override
    public void push(T info) {
        if (this.tamanho == this.limite) {
            throw new PilhaCheiaException();
        }
        this.info[this.tamanho] = info;
        this.tamanho++;
    }

    @Override
    public T pop() {
        T valor = peek(); // peek já valida vazia
        this.tamanho--;
        return valor;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        return this.info[this.tamanho - 1];
    }

    @Override
    public boolean estaVazia() {
        return this.tamanho == 0;
    }

    @Override
    public void liberar() {
        this.tamanho = 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = this.tamanho - 1; i >= 0; i--) {
            s.append(this.info[i]);
            if (i > 0) s.append(",");
        }
        return s.toString();
    }

    // ─────────── MÉTODOS EXTRAS (PROVA) ───────────

    /**
     * Concatena os dados da pilha p no topo desta pilha.
     * O novo topo será igual ao topo de p, e p NÃO é modificada.
     */
    public void concatenar(PilhaVetor<T> p) {
        if (this.tamanho + p.tamanho > this.limite) {
            throw new PilhaCheiaException();
        }
        for (int i = 0; i < p.tamanho; i++) {
            this.push(p.info[i]);
        }
    }

    /**
     * Verifica se uma expressão tem delimitadores balanceados: ( ) [ ] { }
     * Aplicação clássica de pilha.
     */
    public boolean verificaDelimitadores(String expressao) {
        PilhaVetor<Character> pilha = new PilhaVetor<>(expressao.length());

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            // Se for abertura, empilha
            if (c == '(' || c == '[' || c == '{') {
                pilha.push(c);
            }
            // Se for fechamento
            else if (c == ')' || c == ']' || c == '}') {
                if (pilha.estaVazia()) return false; // Fechamento sem abertura
                char topo = pilha.pop();
                if (!formaPar(topo, c)) return false; // Par errado: ex (]
            }
        }
        return pilha.estaVazia(); // Se sobrou algo aberto, está errado
    }

    private boolean formaPar(char abrindo, char fechando) {
        return (abrindo == '(' && fechando == ')') ||
               (abrindo == '[' && fechando == ']') ||
               (abrindo == '{' && fechando == '}');
    }

    /**
     * Inverte a ordem dos elementos da pilha usando uma fila auxiliar.
     */
    public void inverter() {
        FilaVetor<T> filaAux = new FilaVetor<>(this.tamanho);
        while (!estaVazia()) {
            filaAux.inserir(this.pop());
        }
        while (!filaAux.estaVazia()) {
            this.push(filaAux.retirar());
        }
    }

    /**
     * Retorna a quantidade atual de elementos.
     */
    public int tamanho() {
        return this.tamanho;
    }

    /**
     * Verifica se a pilha contém um valor específico (sem modificá-la).
     */
    public boolean contem(T valor) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.info[i].equals(valor)) return true;
        }
        return false;
    }

    /**
     * Empilha uma cópia do elemento que está no topo.
     */
    public void duplicarTopo() {
        if (estaVazia()) throw new PilhaVaziaException();
        this.push(this.peek());
    }

    /**
     * Cria uma nova pilha contendo os mesmos elementos, mas em ordem inversa.
     * A pilha original não é modificada.
     */
    public PilhaVetor<T> criarPilhaInvertida() {
        PilhaVetor<T> nova = new PilhaVetor<>(this.limite);
        for (int i = 0; i < this.tamanho; i++) {
            nova.push(this.info[i]); // empilha em ordem reversa naturalmente
        }
        return nova;
    }

    // ─────────── MAIN PARA TESTES ───────────

    public static void main(String[] args) {
        PilhaVetor<Integer> pilha = new PilhaVetor<>(5);

        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        System.out.println("Pilha: " + pilha);          // 30,20,10
        System.out.println("Topo: " + pilha.peek());    // 30
        System.out.println("Pop: " + pilha.pop());      // 30
        System.out.println("Pilha: " + pilha);          // 20,10

        // Teste delimitadores
        PilhaVetor<Character> aux = new PilhaVetor<>(20);
        System.out.println("'{[()]}' balanceado? " + aux.verificaDelimitadores("{[()]}")); // true
        System.out.println("'([)]'   balanceado? " + aux.verificaDelimitadores("([)]"));   // false

        // Teste com Strings
        PilhaVetor<String> palavras = new PilhaVetor<>(4);
        palavras.push("primeiro");
        palavras.push("segundo");
        palavras.push("terceiro");
        System.out.println("Strings: " + palavras);     // terceiro,segundo,primeiro
        System.out.println("Tem 'segundo'? " + palavras.contem("segundo")); // true
    }
}
