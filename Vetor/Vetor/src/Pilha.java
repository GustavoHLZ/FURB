public interface Pilha<T> {
    void push(T info);    // Empilhar
    T pop();             // Desempilhar
    T peek();            // Ver topo
    boolean estaVazia(); // Verificar se está vazia
    void liberar();      // Limpar a pilha[cite: 1]
}