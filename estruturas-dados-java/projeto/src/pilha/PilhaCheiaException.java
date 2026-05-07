package pilha;

public class PilhaCheiaException extends RuntimeException {
    public PilhaCheiaException() {
        super("Pilha está cheia");
    }
}
