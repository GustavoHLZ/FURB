package pilha;

public interface Pilha<T> {
    void push(T info);    // Empilhar
    T pop();              // Desempilhar
    T peek();             // Ver topo sem remover
    boolean estaVazia();  // Verificar se está vazia
    void liberar();       // Limpar a pilha
}
