/**
 * NoLista - representa um no de uma Lista Encadeada Simples (generica).
 *
 * Cada no possui:
 *  - info: o dado armazenado (do tipo generico T)
 *  - proximo: referencia para o proximo no
 */
public class NoLista<T> {
    private T info;
    private NoLista<T> proximo;

    public NoLista(T info) {
        this.info = info;
        this.proximo = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoLista<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoLista<T> proximo) {
        this.proximo = proximo;
    }
}
