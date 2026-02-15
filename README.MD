# 🧬 Conway's Game of Life (Jogo da Vida)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-green)

Uma implementação em **Java** do famoso autômato celular criado pelo matemático John Conway em 1970. Este projeto simula a evolução de células em uma grade, onde a vida e a morte são determinadas pelas interações com os vizinhos.

## 📋 Sobre o Projeto

O "Jogo da Vida" não é um jogo tradicional: é um "jogo de zero jogadores". A evolução é determinada pelo estado inicial e não requer nenhuma entrada adicional. É um exemplo clássico para estudar:
* **Lógica de Programação e Condicionais Complexas.**
* **Manipulação de Matrizes (Arrays Bidimensionais).**
* **Algoritmos de Vizinhança.**

### 🎮 As Regras
O universo do jogo é uma grade infinita (neste código, limitada por uma matriz $N \times N$) de células quadradas. Cada célula tem dois estados: **VIVA** ou **MORTA**.

A cada passo de tempo (geração), as seguintes transições ocorrem:

1.  **Solidão:** Qualquer célula viva com menos de 2 vizinhos vivos morre.
2.  **Superpopulação:** Qualquer célula viva com mais de 3 vizinhos vivos morre.
3.  **Sobrevivência:** Qualquer célula viva com 2 ou 3 vizinhos vivos continua viva.
4.  **Reprodução:** Qualquer célula morta com exatamente 3 vizinhos vivos se torna uma célula viva.

---

## 🚀 Como Executar

### Pré-requisitos
* Java JDK 8 ou superior instalado.

### Passo a Passo

1.  Clone este repositório:
    ```bash
    git clone https://github.com/adrianops24/GOL-PROJECT.git
    ```
2.  Entre na pasta do projeto:
    ```bash
    cd GOL-PROJECT/src
    ```
3.  Compile o código:
    ```bash
    javac Main.java
    ```
4.  Execute:
    ```bash
    java Main
    ```

---

## 🛠️ Estrutura e Lógica do Código

A implementação foca na clareza e eficiência da lógica de matrizes.

### Principais Desafios Resolvidos:

* **Double Buffering (Buffer Duplo):** Um erro comum é atualizar a matriz *enquanto* se lê os vizinhos. Isso causa inconsistência nos dados.
    * *Solução:* Utilizei duas matrizes: `tabuleiroAtual` (apenas leitura) e `proximaGeracao` (escrita). Ao final de cada loop, o tabuleiro atual recebe os dados da próxima geração.

* **Verificação de Vizinhos:**
  A lógica percorre as 8 células vizinhas (horizontais, verticais e diagonais) utilizando laços aninhados (`for`), garantindo que bordas da matriz não causem erros de `IndexOutOfBounds`.

```java
// Exemplo simplificado da lógica de transição
if (celulaViva && (vizinhos < 2 || vizinhos > 3)) {
    proximaGeracao[i][j] = 0; // Morre
} else if (!celulaViva && vizinhos == 3) {
    proximaGeracao[i][j] = 1; // Nasce
}