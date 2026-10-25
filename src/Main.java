/**
 * Programa principal.
 * Por enquanto só monta a árvore para conferir se a inserção funcionou.
 */
public class Main {

    public static void main(String[] args) {
        ArvoreMorse arvore = new ArvoreMorse();

        System.out.println("Árvore montada!");
        System.out.println("Esquerda da raiz (ponto): " + arvore.getRaiz().getEsquerda().getLetra());
        System.out.println("Direita da raiz (traço): " + arvore.getRaiz().getDireita().getLetra());
    }
}
