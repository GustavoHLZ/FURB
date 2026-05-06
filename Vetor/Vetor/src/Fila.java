public interface Fila<T> {
    void inserir(T valor); // [cite: 97]

    boolean estaVazia(); // [cite: 98]

    T peek(); // [cite: 99]

    T retirar(); // [cite: 100]

    void liberar(); // [cite: 101]
}