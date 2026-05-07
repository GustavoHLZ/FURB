package fila;

import pilha.PilhaVetor;

public class FilaVetor<T> implements Fila<T> {
    private T[] info;
    private int limite;
    private int tamanho;
    private int inicio;

    @SuppressWarnings("unchecked")
    public FilaVetor(int limite) {
        this.limite = limite;
        this.info = (T[]) new Object[limite];
        this.tamanho = 0;
        this.inicio = 0;
    }

    public int getLimite() {
        return limite;
    }

    public int getTamanho() {
        return tamanho;
    }

    // ─────────── MÉTODOS BÁSICOS ───────────

    @Override
    public void inserir(T valor) {
        if (this.tamanho == this.limite) {
            throw new FilaCheiaException();
        }
        // 🔑 Fórmula mágica: vetor circular
        int posicaoInserir = (inicio + tamanho) % limite;
        info[posicaoInserir] = valor;
        tamanho++;
    }

    @Override
    public boolean estaVazia() {
        return this.tamanho == 0;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new FilaVaziaException();
        }
        return info[inicio];
    }

    @Override
    public T retirar() {
        if (estaVazia()) {
            throw new FilaVaziaException();
        }
        T valor = peek();
        // inicio também avança circularmente
        this.inicio = (inicio + 1) % limite;
        tamanho--;
        return valor;
    }

    @Override
    public void liberar() {
        while (tamanho > 0) {
            this.retirar();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < tamanho; i++) {
            str.append(this.info[(inicio + i) % limite]);
            if (i < tamanho - 1) str.append(", ");
        }
        return str + "]";
    }

    // ─────────── MÉTODOS EXTRAS (PROVA) ───────────

    /**
     * Cria nova fila com tamanho igual à soma dos limites das duas filas.
     * As filas originais não são modificadas.
     */
    public FilaVetor<T> criarFilaConcatenada(FilaVetor<T> f2) {
        FilaVetor<T> novaFila = new FilaVetor<>(this.limite + f2.limite);
        for (int i = 0; i < this.tamanho; i++) {
            novaFila.inserir(this.info[(this.inicio + i) % this.limite]);
        }
        for (int i = 0; i < f2.tamanho; i++) {
            novaFila.inserir(f2.info[(f2.inicio + i) % f2.limite]);
        }
        return novaFila;
    }

    /**
     * Reduz a capacidade ao tamanho atual da fila.
     * Remove os espaços vazios do vetor interno.
     */
    @SuppressWarnings("unchecked")
    public void encolher() {
        T[] filaMenor = (T[]) new Object[this.tamanho];
        for (int i = 0; i < this.tamanho; i++) {
            filaMenor[i] = this.info[(inicio + i) % limite];
        }
        this.limite = this.tamanho;
        this.inicio = 0;
        this.info = filaMenor;
    }

    /**
     * Verifica se a fila contém um valor (busca linear).
     */
    public boolean contem(T valor) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.info[(inicio + i) % limite].equals(valor)) return true;
        }
        return false;
    }

    /**
     * Inverte a ordem dos elementos da fila usando uma pilha auxiliar.
     */
    public void inverter() {
        PilhaVetor<T> pilhaAux = new PilhaVetor<>(this.tamanho);
        while (!estaVazia()) {
            pilhaAux.push(this.retirar());
        }
        while (!pilhaAux.estaVazia()) {
            this.inserir(pilhaAux.pop());
        }
    }

    /**
     * Verifica se a fila atingiu sua capacidade máxima.
     */
    public boolean estaCheia() {
        return this.tamanho == this.limite;
    }

    /**
     * Dobra a capacidade do vetor interno (útil para evitar overflow).
     */
    @SuppressWarnings("unchecked")
    public void crescer() {
        T[] filaMaior = (T[]) new Object[this.limite * 2];
        for (int i = 0; i < this.tamanho; i++) {
            filaMaior[i] = this.info[(inicio + i) % limite];
        }
        this.limite = this.limite * 2;
        this.inicio = 0;
        this.info = filaMaior;
    }

    // ─────────── MAIN PARA TESTES ───────────

    public static void main(String[] args) {
        FilaVetor<Integer> fila = new FilaVetor<>(5);

        fila.inserir(1);
        fila.inserir(2);
        fila.inserir(3);
        fila.inserir(4);
        fila.inserir(5);
        System.out.println("Inicial: " + fila); // [1, 2, 3, 4, 5]

        fila.retirar();
        fila.retirar();
        fila.inserir(6);
        fila.inserir(7);
        // Demonstra a circularidade: 6 e 7 vão para os índices 0 e 1
        System.out.println("Após circular: " + fila); // [3, 4, 5, 6, 7]

        // Concatenação
        FilaVetor<Integer> f2 = new FilaVetor<>(3);
        f2.inserir(8);
        f2.inserir(9);
        FilaVetor<Integer> f3 = fila.criarFilaConcatenada(f2);
        System.out.println("Concatenada: " + f3);

        // Teste com Strings
        FilaVetor<String> nomes = new FilaVetor<>(3);
        nomes.inserir("Ana");
        nomes.inserir("Bruno");
        nomes.inserir("Carla");
        System.out.println("Nomes: " + nomes);
        System.out.println("Primeiro: " + nomes.peek());
    }
}
