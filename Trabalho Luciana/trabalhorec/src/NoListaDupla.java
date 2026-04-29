/**
 * NoListaDupla - representa um no de uma Lista Duplamente Encadeada.
 *
 * Cada no possui:
 *  - info: o dado armazenado (do tipo generico T)
 *  - anterior: referencia para o no anterior
 *  - proximo: referencia para o proximo no
 */
public class NoListaDupla<T> {
    private T info;
    private NoListaDupla<T> anterior;
    private NoListaDupla<T> proximo;

    public NoListaDupla(T info) {
        this.info = info;
        this.anterior = null;
        this.proximo = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoListaDupla<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NoListaDupla<T> anterior) {
        this.anterior = anterior;
    }

    public NoListaDupla<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoListaDupla<T> proximo) {
        this.proximo = proximo;
    }
}
