package nucleo;

import visual.VisualTree;

/**
 *
 * @author Yan Kaic
 */
public class ArvoreBinaria {

  private Node raiz;
  private int tamanho;
  private final VisualTree visual;

  public ArvoreBinaria() {
    this.raiz = null;
    this.tamanho = 0;
    this.visual = new VisualTree();
  }

  public void inserir(int valor) throws IllegalArgumentException {
    if (raiz == null) {
      raiz = new Node(valor);

      visual.criarNo(raiz);
    }
    else {
      inserir(valor, raiz);
    }
    tamanho++;
  }

  private void inserir(int valor, Node no) throws IllegalArgumentException {
    if (no.getInfo() == valor) {
      throw new IllegalArgumentException("O nó ja está na arvore");
    }

    if (valor > no.getInfo()) {
      if (no.hasRight()) {
        inserir(valor, no.nextRight());
      }
      else {
        no.setRight(new Node(valor));
        tamanho++;

        visual.criarFilho(no.nextRight());
      }
    }
    else {
      if (no.hasLeft()) {
        inserir(valor, no.nextLeft());
      }
      else {
        no.setLeft(new Node(valor));
        tamanho++;

        visual.criarFilho(no.nextLeft());
      }
    }
  }

  public void remover(int valor) throws IllegalArgumentException {
    if (raiz == null) {
      throw new IllegalArgumentException("A árvore está vazia!");
    }
    else {
      remover(raiz, valor);
    }
  }

  private void remover(Node no, int valor) throws IllegalArgumentException {
    
  }

  public boolean existe(int valor) {
    return existe(valor, raiz);
  }

  public boolean existe(int valor, Node no) {
    if (no.getInfo() == valor) {
      return true;
    }
    if (valor > no.getInfo()) {
      if (no.nextRight() == null) {
        return false;
      }
      return existe(valor, no.nextRight());
    }
    else {
      if (no.nextLeft() == null) {
        return false;
      }
      return existe(valor, no.nextLeft());
    }
  }

  public int getTamanho() {
    return this.tamanho;
  }

  public int[] toArray() {
    return new int[0];
  }

  public VisualTree getVisual() {
    return visual;
  }

}
