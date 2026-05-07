package arvore;

import fila.FilaVetor;

public class ArvoreBinaria<T> {
    private NoArvoreBinaria<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void setRaiz(NoArvoreBinaria<T> raiz) {
        this.raiz = raiz;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    // ─────────── MÉTODOS BÁSICOS (anexos) ───────────

    /**
     * Verifica se um valor existe na árvore (busca recursiva).
     */
    public boolean pertence(T info) {
        return pertence(this.raiz, info);
    }

    private boolean pertence(NoArvoreBinaria<T> no, T info) {
        if (no == null) return false;
        if (no.getInfo().equals(info)) return true;
        return pertence(no.getEsquerda(), info)
            || pertence(no.getDireita(), info);
    }

    /**
     * Conta o total de nós na árvore.
     */
    public int contarNos() {
        return contarNos(this.raiz);
    }

    private int contarNos(NoArvoreBinaria<T> no) {
        if (no == null) return 0;
        return 1 + contarNos(no.getEsquerda()) + contarNos(no.getDireita());
    }

    /**
     * Conta apenas os nós folha (sem filhos).
     */
    public int contarFolhas() {
        return contarFolhas(this.raiz);
    }

    private int contarFolhas(NoArvoreBinaria<T> no) {
        if (no == null) return 0;
        if (no.getEsquerda() == null && no.getDireita() == null) return 1;
        return contarFolhas(no.getEsquerda()) + contarFolhas(no.getDireita());
    }

    // ─────────── PERCURSOS ───────────

    /**
     * Pré-ordem: Raiz → Esquerda → Direita
     * Notação textual: <raiz sae sad>
     */
    @Override
    public String toString() {
        return arvorePre(this.raiz);
    }

    private String arvorePre(NoArvoreBinaria<T> no) {
        if (no == null) return "<>";
        return "<" + no.getInfo()
                   + arvorePre(no.getEsquerda())
                   + arvorePre(no.getDireita()) + ">";
    }

    /**
     * Ordem simétrica (in-ordem): Esquerda → Raiz → Direita
     */
    public String arvoreSim() {
        return arvoreSim(this.raiz);
    }

    private String arvoreSim(NoArvoreBinaria<T> no) {
        if (no == null) return "<>";
        return "<" + arvoreSim(no.getEsquerda())
                   + no.getInfo()
                   + arvoreSim(no.getDireita()) + ">";
    }

    /**
     * Pós-ordem: Esquerda → Direita → Raiz
     */
    public String arvorePos() {
        return arvorePos(this.raiz);
    }

    private String arvorePos(NoArvoreBinaria<T> no) {
        if (no == null) return "<>";
        return "<" + arvorePos(no.getEsquerda())
                   + arvorePos(no.getDireita())
                   + no.getInfo() + ">";
    }

    // ─────────── MÉTODOS EXTRAS (PROVA) ───────────

    /**
     * Calcula a altura da árvore.
     * Vazia = -1 | Folha = 0 | Outros = 1 + max(altura_esq, altura_dir)
     */
    public int altura() {
        return altura(this.raiz);
    }

    private int altura(NoArvoreBinaria<T> no) {
        if (no == null) return -1;
        return 1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita()));
    }

    /**
     * Conta os nós internos (que possuem pelo menos um filho).
     */
    public int contarNosInternos() {
        return contarNosInternos(this.raiz);
    }

    private int contarNosInternos(NoArvoreBinaria<T> no) {
        if (no == null) return 0;
        if (no.getEsquerda() == null && no.getDireita() == null) return 0; // folha
        return 1 + contarNosInternos(no.getEsquerda())
                 + contarNosInternos(no.getDireita());
    }

    /**
     * Retorna o nível de um nó (distância até a raiz). Raiz = nível 0.
     * Retorna -1 se o nó não for encontrado.
     */
    public int nivel(T info) {
        return nivel(this.raiz, info, 0);
    }

    private int nivel(NoArvoreBinaria<T> no, T info, int nivelAtual) {
        if (no == null) return -1;
        if (no.getInfo().equals(info)) return nivelAtual;
        int nivelEsq = nivel(no.getEsquerda(), info, nivelAtual + 1);
        if (nivelEsq != -1) return nivelEsq;
        return nivel(no.getDireita(), info, nivelAtual + 1);
    }

    /**
     * Conta quantos nós existem em determinado nível.
     */
    public int contarNosNoNivel(int nivel) {
        return contarNosNoNivel(this.raiz, nivel, 0);
    }

    private int contarNosNoNivel(NoArvoreBinaria<T> no, int nivelAlvo, int nivelAtual) {
        if (no == null) return 0;
        if (nivelAtual == nivelAlvo) return 1;
        return contarNosNoNivel(no.getEsquerda(), nivelAlvo, nivelAtual + 1)
             + contarNosNoNivel(no.getDireita(), nivelAlvo, nivelAtual + 1);
    }

    /**
     * Soma os valores numéricos da árvore (apenas para Number).
     */
    public int somarValores() {
        return somarValores(this.raiz);
    }

    private int somarValores(NoArvoreBinaria<T> no) {
        if (no == null) return 0;
        int valor = ((Number) no.getInfo()).intValue();
        return valor + somarValores(no.getEsquerda()) + somarValores(no.getDireita());
    }

    /**
     * Espelha a árvore (inverte esquerda com direita recursivamente).
     */
    public void espelhar() {
        espelhar(this.raiz);
    }

    private void espelhar(NoArvoreBinaria<T> no) {
        if (no == null) return;
        NoArvoreBinaria<T> temp = no.getEsquerda();
        no.setEsquerda(no.getDireita());
        no.setDireita(temp);
        espelhar(no.getEsquerda());
        espelhar(no.getDireita());
    }

    /**
     * Verifica se duas árvores têm a mesma estrutura e valores.
     */
    public boolean ehIgual(ArvoreBinaria<T> outra) {
        return ehIgual(this.raiz, outra.raiz);
    }

    private boolean ehIgual(NoArvoreBinaria<T> n1, NoArvoreBinaria<T> n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null || n2 == null) return false;
        if (!n1.getInfo().equals(n2.getInfo())) return false;
        return ehIgual(n1.getEsquerda(), n2.getEsquerda())
            && ehIgual(n1.getDireita(), n2.getDireita());
    }

    /**
     * Percorre a árvore por níveis (BFS) usando uma fila.
     * Demonstra a integração entre Árvore e Fila.
     */
    public String percursoNivel() {
        if (estaVazia()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        FilaVetor<NoArvoreBinaria<T>> fila = new FilaVetor<>(contarNos());
        fila.inserir(this.raiz);

        while (!fila.estaVazia()) {
            NoArvoreBinaria<T> atual = fila.retirar();
            sb.append(atual.getInfo()).append(", ");
            if (atual.getEsquerda() != null) fila.inserir(atual.getEsquerda());
            if (atual.getDireita() != null) fila.inserir(atual.getDireita());
        }
        sb.setLength(sb.length() - 2); // remove última vírgula e espaço
        return sb + "]";
    }

    /**
     * Encontra o pai de um determinado nó.
     * Retorna null se for a raiz ou se não for encontrado.
     */
    public T encontrarPai(T info) {
        if (estaVazia() || raiz.getInfo().equals(info)) return null;
        return encontrarPai(this.raiz, info);
    }

    private T encontrarPai(NoArvoreBinaria<T> no, T info) {
        if (no == null) return null;
        if ((no.getEsquerda() != null && no.getEsquerda().getInfo().equals(info)) ||
            (no.getDireita() != null && no.getDireita().getInfo().equals(info))) {
            return no.getInfo();
        }
        T paiEsq = encontrarPai(no.getEsquerda(), info);
        if (paiEsq != null) return paiEsq;
        return encontrarPai(no.getDireita(), info);
    }

    // ─────────── MAIN PARA TESTES ───────────

    public static void main(String[] args) {
        // ═══ EXEMPLO 1: Árvore binária de Strings ═══
        //          "casa"
        //         /      \
        //      "rua"    "carro"
        //      /            \
        //   "luz"         "moto"

        NoArvoreBinaria<String> raiz   = new NoArvoreBinaria<>("casa");
        NoArvoreBinaria<String> rua    = new NoArvoreBinaria<>("rua");
        NoArvoreBinaria<String> carro  = new NoArvoreBinaria<>("carro");
        NoArvoreBinaria<String> luz    = new NoArvoreBinaria<>("luz");
        NoArvoreBinaria<String> moto   = new NoArvoreBinaria<>("moto");

        raiz.setEsquerda(rua);
        raiz.setDireita(carro);
        rua.setEsquerda(luz);
        carro.setDireita(moto);

        ArvoreBinaria<String> arvore = new ArvoreBinaria<>();
        arvore.setRaiz(raiz);

        System.out.println("=== ÁRVORE DE STRINGS ===");
        System.out.println("Pré-ordem:    " + arvore.toString());
        System.out.println("Simétrica:    " + arvore.arvoreSim());
        System.out.println("Pós-ordem:    " + arvore.arvorePos());
        System.out.println("Total nós:    " + arvore.contarNos());
        System.out.println("Folhas:       " + arvore.contarFolhas());
        System.out.println("Internos:     " + arvore.contarNosInternos());
        System.out.println("Altura:       " + arvore.altura());
        System.out.println("Tem 'luz'?    " + arvore.pertence("luz"));
        System.out.println("Tem 'aviao'?  " + arvore.pertence("aviao"));
        System.out.println("Nível 'moto': " + arvore.nivel("moto"));
        System.out.println("Pai de 'luz': " + arvore.encontrarPai("luz"));
        System.out.println("Por nível:    " + arvore.percursoNivel());

        // ═══ EXEMPLO 2: Árvore binária de Integers ═══
        //          1
        //         / \
        //        2   3
        //       / \ / \
        //      4  5 6  7

        NoArvoreBinaria<Integer> r1 = new NoArvoreBinaria<>(1);
        NoArvoreBinaria<Integer> n2 = new NoArvoreBinaria<>(2);
        NoArvoreBinaria<Integer> n3 = new NoArvoreBinaria<>(3);
        NoArvoreBinaria<Integer> n4 = new NoArvoreBinaria<>(4);
        NoArvoreBinaria<Integer> n5 = new NoArvoreBinaria<>(5);
        NoArvoreBinaria<Integer> n6 = new NoArvoreBinaria<>(6);
        NoArvoreBinaria<Integer> n7 = new NoArvoreBinaria<>(7);

        r1.setEsquerda(n2);
        r1.setDireita(n3);
        n2.setEsquerda(n4);
        n2.setDireita(n5);
        n3.setEsquerda(n6);
        n3.setDireita(n7);

        ArvoreBinaria<Integer> arv2 = new ArvoreBinaria<>();
        arv2.setRaiz(r1);

        System.out.println("\n=== ÁRVORE DE INTEIROS ===");
        System.out.println("Pré-ordem:  " + arv2);                 // <1<2<4<><>><5<><>>><3<6<><>><7<><>>>>
        System.out.println("Soma:       " + arv2.somarValores()); // 28
        System.out.println("Altura:     " + arv2.altura());        // 2
        System.out.println("Nível 2:    " + arv2.contarNosNoNivel(2)); // 4 (4,5,6,7)
        System.out.println("Por nível:  " + arv2.percursoNivel()); // [1, 2, 3, 4, 5, 6, 7]
    }
}
