# TDE 2 — Código Morse com Árvore Binária

Resolução de Problemas Estruturados em Computação — PUCPR — Profa. Lisiane Reips

**Equipe:** João Pedro Carminatti,
Felipe Braga, 
Gustavo Nery.

## Como rodar

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

| Opção | O que faz | Exemplo |
|---|---|---|
| 1 | Decodifica (Morse → texto) | `... --- ... / --- -.-` → `SOS OK` |
| 2 | Codifica (texto → Morse) | `SOS OK` → `... --- ... / --- -.-` |
| 3 | Imprime a árvore | — |
| 0 | Sai | — |

No Morse, as letras são separadas por espaço e as palavras por `/`.

## Organização

| Classe | Responsabilidade |
|---|---|
| `No` | Uma "caixinha" da árvore: uma letra e os filhos esquerdo e direito |
| `ArvoreMorse` | Monta a árvore e faz a inserção, a decodificação, a codificação e a impressão |
| `Main` | Mostra o menu e lê o que o usuário digita |

## Como funciona

A árvore funciona como um caminho com bifurcações. Saindo da raiz, cada
**ponto** manda ir para a **esquerda** e cada **traço** manda ir para a
**direita**. Onde o caminho termina, está a letra.

- **Inserção:** segue o código de cada letra. Se o caminho ainda não existe,
  cria um nó vazio e continua. Esses nós vazios aparecem como `*` na impressão.
- **Decodificação:** segue o caminho do código e devolve a letra onde parou.
- **Codificação:** procura a letra na árvore, anotando `.` ou `-` a cada
  descida. Quando acha a letra, o que foi anotado é o código dela.
- **Impressão:** a árvore é impressa deitada. Girando a cabeça para a esquerda,
  a raiz fica no topo, os traços à direita e os pontos à esquerda.
