# 📚 Estruturas de Dados em Java — Guia Completo de Estudo

> Implementações de **Pilha**, **Fila** e **Árvore Binária** em Java com Generics.
> Material de estudo da disciplina de Algoritmos e Estruturas de Dados (FURB).
> Inclui **métodos básicos dos anexos** + **métodos extras** que podem cair na prova.

[![Java](https://img.shields.io/badge/Java-Generics-orange)]()
[![Status](https://img.shields.io/badge/Status-Completo-green)]()
[![Cobertura](https://img.shields.io/badge/Cobertura-Prova-blue)]()

---

## 📑 Índice

- [Estrutura do Projeto](#-estrutura-do-projeto)
- [PILHA](#-pilha)
  - [Interface Pilha](#interface-pilha)
  - [PilhaVetor — Métodos básicos](#pilhavetor--métodos-básicos)
  - [PilhaVetor — Métodos extras (PROVA)](#pilhavetor--métodos-extras-prova)
  - [PilhaLista (encadeada)](#pilhalista-encadeada)
  - [Exceções](#exceções-da-pilha)
- [FILA](#-fila)
  - [Interface Fila](#interface-fila)
  - [FilaVetor — Métodos básicos](#filavetor--métodos-básicos)
  - [FilaVetor — Métodos extras (PROVA)](#filavetor--métodos-extras-prova)
  - [FilaLista (encadeada)](#filalista-encadeada)
  - [Exceções](#exceções-da-fila)
- [ÁRVORE BINÁRIA](#-árvore-binária)
  - [NoArvoreBinaria](#noarvorebinaria)
  - [ArvoreBinaria — Métodos básicos](#arvorebinaria--métodos-básicos)
  - [ArvoreBinaria — Métodos extras (PROVA)](#arvorebinaria--métodos-extras-prova)
- [Como rodar](#-como-rodar)
- [Cheat Sheet](#-cheat-sheet)

---

## 📁 Estrutura do Projeto

```
src/
├── pilha/
│   ├── Pilha.java
│   ├── PilhaVetor.java
│   ├── PilhaLista.java
│   ├── PilhaCheiaException.java
│   └── PilhaVaziaException.java
├── fila/
│   ├── Fila.java
│   ├── FilaVetor.java
│   ├── FilaLista.java
│   ├── NoLista.java
│   ├── FilaCheiaException.java
│   └── FilaVaziaException.java
└── arvore/
    ├── NoArvoreBinaria.java
    └── ArvoreBinaria.java
```

---

## 🟦 PILHA

Estrutura **LIFO** (Last In, First Out). O último a entrar é o primeiro a sair.

### Interface Pilha

```java
public interface Pilha<T> {
    void push(T info);    // Empilhar
    T pop();              // Desempilhar
    T peek();             // Ver topo sem remover
    boolean estaVazia();  // Verificar se está vazia
    void liberar();       // Limpar a pilha
}
```

### PilhaVetor — Métodos básicos

```java
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
        T valor = peek();      // peek já valida vazia
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
}
```

### PilhaVetor — Métodos extras (PROVA)

> ⚡ **Esses métodos podem cair na prova!** São pedidos comuns em listas de exercícios.

```java
// ─── 1. CONCATENAR PILHAS ───
// Adiciona os dados da pilha p no topo desta pilha
// O novo topo será igual ao topo de p (e p não pode ser modificada)
public void concatenar(PilhaVetor<T> p) {
    if (this.tamanho + p.tamanho > this.limite) {
        throw new PilhaCheiaException();
    }
    for (int i = 0; i < p.tamanho; i++) {
        this.push(p.info[i]);
    }
}

// ─── 2. VERIFICAR DELIMITADORES BALANCEADOS ───
// Aplicação clássica de pilha: ( ) [ ] { }
public boolean verificaDelimitadores(String expressao) {
    PilhaVetor<Character> pilha = new PilhaVetor<>(expressao.length());

    for (int i = 0; i < expressao.length(); i++) {
        char c = expressao.charAt(i);

        if (c == '(' || c == '[' || c == '{') {
            pilha.push(c);
        } else if (c == ')' || c == ']' || c == '}') {
            if (pilha.estaVazia()) return false;
            char topo = pilha.pop();
            if (!formaPar(topo, c)) return false;
        }
    }
    return pilha.estaVazia();
}

private boolean formaPar(char abrindo, char fechando) {
    return (abrindo == '(' && fechando == ')') ||
           (abrindo == '[' && fechando == ']') ||
           (abrindo == '{' && fechando == '}');
}

// ─── 3. INVERTER PILHA (usando fila auxiliar) ───
public void inverter() {
    FilaVetor<T> filaAux = new FilaVetor<>(this.tamanho);
    while (!estaVazia()) {
        filaAux.inserir(this.pop());
    }
    while (!filaAux.estaVazia()) {
        this.push(filaAux.retirar());
    }
}

// ─── 4. CONTAR ELEMENTOS ───
public int tamanho() {
    return this.tamanho;
}

// ─── 5. VERIFICAR SE CONTÉM UM VALOR (sem modificar a pilha) ───
public boolean contem(T valor) {
    for (int i = 0; i < this.tamanho; i++) {
        if (this.info[i].equals(valor)) return true;
    }
    return false;
}

// ─── 6. DUPLICAR O TOPO ───
// Empilha uma cópia do elemento que está no topo
public void duplicarTopo() {
    if (estaVazia()) throw new PilhaVaziaException();
    this.push(this.peek());
}

// ─── 7. CRIAR PILHA INVERTIDA (não modifica a original) ───
public PilhaVetor<T> criarPilhaInvertida() {
    PilhaVetor<T> nova = new PilhaVetor<>(this.limite);
    for (int i = 0; i < this.tamanho; i++) {
        nova.push(this.info[i]); // empilha em ordem reversa naturalmente
    }
    return nova;
}
```

### PilhaLista (encadeada)

```java
// Reaproveita NoLista (mesmo da Fila)
public class PilhaLista<T> implements Pilha<T> {
    private NoLista<T> topo;

    public PilhaLista() {
        this.topo = null;
    }

    @Override
    public void push(T info) {
        NoLista<T> novo = new NoLista<>(info);
        novo.setProximo(topo);   // novo aponta para o antigo topo
        topo = novo;             // novo vira o topo
    }

    @Override
    public T pop() {
        T valor = peek();
        topo = topo.getProximo();
        return valor;
    }

    @Override
    public T peek() {
        if (estaVazia()) throw new PilhaVaziaException();
        return topo.getInfo();
    }

    @Override
    public boolean estaVazia() {
        return topo == null;
    }

    @Override
    public void liberar() {
        topo = null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        NoLista<T> atual = topo;
        while (atual != null) {
            s.append(atual.getInfo());
            if (atual.getProximo() != null) s.append(",");
            atual = atual.getProximo();
        }
        return s.toString();
    }
}
```

### Exceções da Pilha

```java
public class PilhaCheiaException extends RuntimeException {
    public PilhaCheiaException() {
        super("Pilha está cheia");
    }
}
```

```java
public class PilhaVaziaException extends RuntimeException {
    public PilhaVaziaException() {
        super("Pilha está vazia");
    }
}
```

---

## 🟩 FILA

Estrutura **FIFO** (First In, First Out). O primeiro a entrar é o primeiro a sair.

### Interface Fila

```java
public interface Fila<T> {
    void inserir(T valor);
    boolean estaVazia();
    T peek();
    T retirar();
    void liberar();
}
```

### FilaVetor — Métodos básicos

> 🔑 **Fórmula chave:** `(inicio + tamanho) % limite` calcula a próxima posição de inserção.

```java
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

    @Override
    public void inserir(T valor) {
        if (this.tamanho == this.limite) {
            throw new FilaCheiaException();
        }
        // 🔑 Calcula posição usando aritmética modular (vetor circular)
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
        // 🔑 inicio também avança circularmente
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
}
```

### FilaVetor — Métodos extras (PROVA)

```java
// ─── 1. CONCATENAR FILAS (cria nova) ───
// Cria nova fila com tamanho igual à soma dos limites das duas
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

// ─── 2. ENCOLHER (remover espaços vazios) ───
// Reduz a capacidade ao tamanho atual da fila
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

// ─── 3. CONTAR ELEMENTOS ───
public int getTamanho() {
    return this.tamanho;
}

// ─── 4. VERIFICAR SE CONTÉM UM VALOR ───
public boolean contem(T valor) {
    for (int i = 0; i < this.tamanho; i++) {
        if (this.info[(inicio + i) % limite].equals(valor)) return true;
    }
    return false;
}

// ─── 5. INVERTER FILA (usando pilha auxiliar) ───
public void inverter() {
    PilhaVetor<T> pilhaAux = new PilhaVetor<>(this.tamanho);
    while (!estaVazia()) {
        pilhaAux.push(this.retirar());
    }
    while (!pilhaAux.estaVazia()) {
        this.inserir(pilhaAux.pop());
    }
}

// ─── 6. ESTÁ CHEIA ───
public boolean estaCheia() {
    return this.tamanho == this.limite;
}

// ─── 7. DOBRAR CAPACIDADE (crescer) ───
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
```

### FilaLista (encadeada)

```java
public class NoLista<T> {
    private T info;
    private NoLista<T> proximo;

    public NoLista(T info) {
        this.info = info;
        this.proximo = null;
    }

    public T getInfo() { return info; }
    public void setInfo(T info) { this.info = info; }
    public NoLista<T> getProximo() { return proximo; }
    public void setProximo(NoLista<T> proximo) { this.proximo = proximo; }
}
```

```java
public class FilaLista<T> implements Fila<T> {
    private NoLista<T> primeiro;
    private NoLista<T> ultimo;

    public FilaLista() {
        this.primeiro = null;
        this.ultimo = null;
    }

    @Override
    public void inserir(T valor) {
        NoLista<T> novo = new NoLista<>(valor);
        if (estaVazia()) {
            primeiro = novo;
        } else {
            ultimo.setProximo(novo);
        }
        ultimo = novo;
    }

    @Override
    public boolean estaVazia() {
        return primeiro == null;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new FilaVaziaException();
        }
        return primeiro.getInfo();
    }

    @Override
    public T retirar() {
        if (estaVazia()) {
            throw new FilaVaziaException();
        }
        T valor = primeiro.getInfo();
        primeiro = primeiro.getProximo();
        if (primeiro == null) {
            ultimo = null; // fila ficou vazia
        }
        return valor;
    }

    @Override
    public void liberar() {
        primeiro = null;
        ultimo = null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        NoLista<T> atual = primeiro;
        while (atual != null) {
            s.append(atual.getInfo());
            if (atual.getProximo() != null) s.append(",");
            atual = atual.getProximo();
        }
        return s.toString();
    }
}
```

### Exceções da Fila

```java
public class FilaCheiaException extends RuntimeException {
    public FilaCheiaException() {
        super("Fila está cheia");
    }
}
```

```java
public class FilaVaziaException extends RuntimeException {
    public FilaVaziaException() {
        super("Fila está vazia");
    }
}
```

---

## 🌳 ÁRVORE BINÁRIA

Estrutura hierárquica em que cada nó tem **no máximo 2 filhos** (esquerda e direita).
Definição recursiva: árvore binária é vazia OU é um nó raiz com duas subárvores.

### Terminologia (DECORE!)

| Termo | Definição |
|-------|-----------|
| **Raiz** | Único nó no topo (sem pai) |
| **Nó pai** | Nó imediatamente acima de outro |
| **Nó filho** | Nó imediatamente abaixo de outro |
| **Folha (externo)** | Nó SEM filhos (esq e dir são null) |
| **Nó interno** | Nó com pelo menos 1 filho |
| **Caminho** | Trajeto entre dois nós |
| **Nível** | Distância de um nó até a raiz (raiz = nível 0) |
| **Altura** | Maior caminho da raiz até uma folha |
| **Vazia** | Altura = -1 |
| **Só raiz** | Altura = 0 |

### NoArvoreBinaria

```java
public class NoArvoreBinaria<T> {
    private T info;
    private NoArvoreBinaria<T> esquerda;
    private NoArvoreBinaria<T> direita;

    public NoArvoreBinaria(T info) {
        this.info = info;
    }

    public NoArvoreBinaria(T info, NoArvoreBinaria<T> esq, NoArvoreBinaria<T> dir) {
        this.info = info;
        this.esquerda = esq;
        this.direita = dir;
    }

    public T getInfo() { return info; }
    public void setInfo(T info) { this.info = info; }
    public NoArvoreBinaria<T> getEsquerda() { return esquerda; }
    public void setEsquerda(NoArvoreBinaria<T> esquerda) { this.esquerda = esquerda; }
    public NoArvoreBinaria<T> getDireita() { return direita; }
    public void setDireita(NoArvoreBinaria<T> direita) { this.direita = direita; }
}
```

### ArvoreBinaria — Métodos básicos

```java
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

    // ─── PERTENCE (busca recursiva) ───
    public boolean pertence(T info) {
        return pertence(this.raiz, info);
    }

    private boolean pertence(NoArvoreBinaria<T> no, T info) {
        if (no == null) return false;
        if (no.getInfo().equals(info)) return true;
        return pertence(no.getEsquerda(), info)
            || pertence(no.getDireita(), info);
    }

    // ─── CONTAR NÓS ───
    public int contarNos() {
        return contarNos(this.raiz);
    }

    private int contarNos(NoArvoreBinaria<T> no) {
        if (no == null) return 0;
        return 1 + contarNos(no.getEsquerda()) + contarNos(no.getDireita());
    }

    // ─── CONTAR FOLHAS ───
    public int contarFolhas() {
        return contarFolhas(this.raiz);
    }

    private int contarFolhas(NoArvoreBinaria<T> no) {
        if (no == null) return 0;
        if (no.getEsquerda() == null && no.getDireita() == null) return 1;
        return contarFolhas(no.getEsquerda()) + contarFolhas(no.getDireita());
    }

    // ─── PERCURSO PRÉ-ORDEM (R → E → D) ───
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
}
```

### ArvoreBinaria — Métodos extras (PROVA)

> ⚡ **Esses métodos são candidatos fortes a cair na prova!**

```java
// ─── 1. ALTURA DA ÁRVORE ───
// Vazia = -1 | Folha = 0 | Outros = 1 + max(altura_esq, altura_dir)
public int altura() {
    return altura(this.raiz);
}

private int altura(NoArvoreBinaria<T> no) {
    if (no == null) return -1;
    return 1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita()));
}

// ─── 2. PERCURSO ORDEM SIMÉTRICA / IN-ORDEM (E → R → D) ───
public String arvoreSim() {
    return arvoreSim(this.raiz);
}

private String arvoreSim(NoArvoreBinaria<T> no) {
    if (no == null) return "<>";
    return "<" + arvoreSim(no.getEsquerda())
               + no.getInfo()
               + arvoreSim(no.getDireita()) + ">";
}

// ─── 3. PERCURSO PÓS-ORDEM (E → D → R) ───
public String arvorePos() {
    return arvorePos(this.raiz);
}

private String arvorePos(NoArvoreBinaria<T> no) {
    if (no == null) return "<>";
    return "<" + arvorePos(no.getEsquerda())
               + arvorePos(no.getDireita())
               + no.getInfo() + ">";
}

// ─── 4. CONTAR NÓS INTERNOS (não-folha e não-vazio) ───
public int contarNosInternos() {
    return contarNosInternos(this.raiz);
}

private int contarNosInternos(NoArvoreBinaria<T> no) {
    if (no == null) return 0;
    if (no.getEsquerda() == null && no.getDireita() == null) return 0; // folha
    return 1 + contarNosInternos(no.getEsquerda())
             + contarNosInternos(no.getDireita());
}

// ─── 5. NÍVEL DE UM NÓ (distância até a raiz) ───
public int nivel(T info) {
    return nivel(this.raiz, info, 0);
}

private int nivel(NoArvoreBinaria<T> no, T info, int nivelAtual) {
    if (no == null) return -1; // não encontrado
    if (no.getInfo().equals(info)) return nivelAtual;
    int nivelEsq = nivel(no.getEsquerda(), info, nivelAtual + 1);
    if (nivelEsq != -1) return nivelEsq;
    return nivel(no.getDireita(), info, nivelAtual + 1);
}

// ─── 6. CONTAR NÓS EM UM NÍVEL ESPECÍFICO ───
public int contarNosNoNivel(int nivel) {
    return contarNosNoNivel(this.raiz, nivel, 0);
}

private int contarNosNoNivel(NoArvoreBinaria<T> no, int nivelAlvo, int nivelAtual) {
    if (no == null) return 0;
    if (nivelAtual == nivelAlvo) return 1;
    return contarNosNoNivel(no.getEsquerda(), nivelAlvo, nivelAtual + 1)
         + contarNosNoNivel(no.getDireita(), nivelAlvo, nivelAtual + 1);
}

// ─── 7. SOMAR TODOS OS VALORES (útil para árvores numéricas) ───
// Funciona apenas com árvores Integer/Number
public int somarValores() {
    return somarValores(this.raiz);
}

@SuppressWarnings("unchecked")
private int somarValores(NoArvoreBinaria<T> no) {
    if (no == null) return 0;
    int valor = ((Number) no.getInfo()).intValue();
    return valor + somarValores(no.getEsquerda()) + somarValores(no.getDireita());
}

// ─── 8. ENCONTRAR O MAIOR VALOR ───
@SuppressWarnings("unchecked")
public T maiorValor() {
    if (estaVazia()) return null;
    return maiorValor(this.raiz);
}

@SuppressWarnings({"unchecked", "rawtypes"})
private T maiorValor(NoArvoreBinaria<T> no) {
    T maior = no.getInfo();
    if (no.getEsquerda() != null) {
        T maiorEsq = maiorValor(no.getEsquerda());
        if (((Comparable) maiorEsq).compareTo(maior) > 0) maior = maiorEsq;
    }
    if (no.getDireita() != null) {
        T maiorDir = maiorValor(no.getDireita());
        if (((Comparable) maiorDir).compareTo(maior) > 0) maior = maiorDir;
    }
    return maior;
}

// ─── 9. ÁRVORE ESPELHADA (inverter esquerda com direita) ───
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

// ─── 10. VERIFICAR SE DUAS ÁRVORES SÃO IGUAIS ───
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

// ─── 11. PERCURSO POR NÍVEL (BFS) usando FILA ───
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
    sb.setLength(sb.length() - 2); // remove última vírgula
    return sb + "]";
}

// ─── 12. ENCONTRAR PAI DE UM NÓ ───
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

// ─── 13. EXEMPLO DE USO COM STRINGS NA MAIN ───
public static void main(String[] args) {
    // Árvore binária de Strings:
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

    System.out.println("Pré-ordem:    " + arvore.toString());
    System.out.println("Simétrica:    " + arvore.arvoreSim());
    System.out.println("Pós-ordem:    " + arvore.arvorePos());
    System.out.println("Total nós:    " + arvore.contarNos());
    System.out.println("Folhas:       " + arvore.contarFolhas());
    System.out.println("Internos:     " + arvore.contarNosInternos());
    System.out.println("Altura:       " + arvore.altura());
    System.out.println("Tem 'luz'?    " + arvore.pertence("luz"));
    System.out.println("Nível 'moto': " + arvore.nivel("moto"));
    System.out.println("Pai de 'luz': " + arvore.encontrarPai("luz"));
    System.out.println("Por nível:    " + arvore.percursoNivel());
}
```

---

## ▶️ Como rodar

### Compilar
```bash
javac -d bin src/**/*.java
```

### Executar
```bash
java -cp bin arvore.ArvoreBinaria
java -cp bin pilha.PilhaVetor
java -cp bin fila.FilaVetor
```

---

## 🎯 Cheat Sheet

### Comparação rápida

| Estrutura | Padrão | Inserção | Remoção | Acesso |
|-----------|--------|----------|---------|--------|
| **Pilha** | LIFO | topo | topo | apenas topo |
| **Fila** | FIFO | final | início | apenas início |
| **Árvore Binária** | Hierárquico | recursivo | recursivo | percurso |

### Os 3 percursos de árvore

| Percurso | Ordem | Quando visita a raiz |
|----------|-------|----------------------|
| **Pré-ordem** | R → E → D | **Antes** de descer |
| **In-ordem (simétrica)** | E → R → D | **Entre** as subárvores |
| **Pós-ordem** | E → D → R | **Depois** de descer |

### Árvore exemplo
```
        1
       / \
      2   3
     / \ / \
    4  5 6  7
```

| Percurso | Resultado |
|----------|-----------|
| Pré | `1 2 4 5 3 6 7` |
| Sim | `4 2 5 1 6 3 7` |
| Pós | `4 5 2 6 7 3 1` |
| Notação | `<1<2<4<><>><5<><>>><3<6<><>><7<><>>>>` |

### Notação textual
- `<>` = árvore **vazia** (NÃO é folha!)
- `<5<><>>` = folha com valor 5
- `<raiz sae sad>` = padrão geral

### Esqueleto universal de método recursivo em árvore
```java
private TipoRetorno metodo(NoArvoreBinaria<T> no) {
    if (no == null) return /* caso base */;

    // processa no atual + chama recursivamente
    TipoRetorno esq = metodo(no.getEsquerda());
    TipoRetorno dir = metodo(no.getDireita());

    return /* combina esq, dir e no.getInfo() */;
}
```

### Erros comuns

| Erro | Solução |
|------|---------|
| Esquecer `% limite` na fila circular | Use `(inicio + tamanho) % limite` |
| `pop()` sem verificar vazia | Chame `peek()` primeiro (que valida) |
| Confundir altura e nível | Altura = árvore \| Nível = nó |
| Recursão sem caso base | Sempre `if (no == null) return ...` |
| Criar ciclo na árvore | Nunca aponte filho para um ancestral |
| `T[] arr = new T[n];` | Use `(T[]) new Object[n]` |

---

## 📚 Bibliografia
- LAFORE, R. **Estruturas de dados & algoritmos em Java**. Rio de Janeiro: Moderna, 2004.
- PREISS, B. R. **Estruturas de Dados e Algoritmos**. 3ª ed. Rio de Janeiro: Elsevier, 2000.

---

## 📄 Licença
Material livre para fins educacionais.
