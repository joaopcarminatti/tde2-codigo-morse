/**
 * Um nó (uma "caixinha") da árvore Morse.
 *
 * Cada nó guarda:
 *  - uma letra (ou um espaço ' ' quando o nó está vazio)
 *  - o filho da esquerda (caminho do PONTO .)
 *  - o filho da direita  (caminho do TRAÇO -)
 */
public class No {

    private char letra;
    private No esquerda;
    private No direita;

    // Todo nó nasce vazio. A letra só é colocada depois, na inserção.
    public No() {
        this.letra = ' ';
        this.esquerda = null;
        this.direita = null;
    }

    // Diz se o nó tem uma letra de verdade ou se é só um caminho vazio.
    public boolean temLetra() {
        return letra != ' ';
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
}
