/**
 * NoListaCircular - representa um no de uma Lista Circular.
 *
 * Estruturalmente identico ao NoLista (so guarda info e proximo),
 * mas em uma lista circular o ultimo no aponta de volta para o primeiro.
 */
public class NoListaCircular<T> {
    private T info;
    private NoListaCircular<T> proximo;

    public NoListaCircular(T info) {
        this.info = info;
        this.proximo = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoListaCircular<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoListaCircular<T> proximo) {
        this.proximo = proximo;
    }
}
