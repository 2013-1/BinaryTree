package visual;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import nucleo.Node;

public class ArvoreVisual extends JPanel {

  private Node raiz;

  public ArvoreVisual() {
    super();
    setLayout(null);
    this.raiz = null;
    iniciarAtualizador(this);
  }

  private void iniciarAtualizador(final JPanel painel) {

    new Thread() {

      @Override
      public void run() {
        try {
          while (true) {
            sleep(12);
            painel.repaint();
          }
        }
        catch (InterruptedException ex) {
          System.out.println("erro de interrupcao");
        }
      }
    }.start();
  }

  public void criarNo(Node raiz) {
    this.raiz = raiz;
    raiz.getView().setLocation(10, 10);
    add(raiz.getView());

    iniciarPosicao(raiz);
    centralizarPosicao(raiz);
  }

  public void criarFilho(Node filho) {
    Point posicaoPai = filho.parent.getView().getLocation();
    filho.getView().setLocation(posicaoPai);;
    add(filho.getView());
    atualizarPosicao();

    int altura = raiz.height() * raiz.getView().getHeight() * 2;
    this.setPreferredSize(new Dimension(largura(raiz), altura));
  }

  private void atualizarPosicao() {
    raiz.getView().pontoInicial.x = 0;
    iniciarPosicao(raiz);
    centralizarPosicao(raiz);
  }

  public int largura(Node no) {
    int largura = 0;
    if (no.isLeaf()) {
      return no.getView().getWidth() * 3;
    }
    if (no.hasLeft()) {
      largura += largura(no.nextLeft());
    }
    if (no.hasRight()) {
      largura += largura(no.nextRight());
    }
    return largura;
  }

  public void iniciarPosicao(Node no) {
    NoVisual visualPai = no.getView();
    int x = 0, y, larguraIrmao = 0;
    y = visualPai.pontoInicial.y + visualPai.getHeight() * 2;

    if (no.hasLeft()) {
      x = visualPai.pontoInicial.x;
      larguraIrmao = largura(no.nextLeft());
      no.nextLeft().getView().pontoInicial = new Point(x, y);
      iniciarPosicao(no.nextLeft());
    }
    if (no.hasRight()) {
      x = visualPai.pontoInicial.x + larguraIrmao;
      no.nextRight().getView().pontoInicial = new Point(x, y);
      iniciarPosicao(no.nextRight());
    }
  }

  public void centralizarPosicao(Node no) {
    NoVisual visual = no.getView();
    if (no.isLeaf()) {
      Animacao.moverObjeto(visual, visual.pontoInicial);
      return;
    }
    int xEsquerda = 0;
    int xDireita = 0;

    if (no.hasLeft()) {
      centralizarPosicao(no.nextLeft());
      xEsquerda = no.nextLeft().getView().pontoInicial.x;
    }
    if (no.hasRight()) {
      centralizarPosicao(no.nextRight());
      xDireita = no.nextRight().getView().pontoInicial.x;
    }
    int x = 0;
    if (!no.hasLeft()) {
      x = xDireita;
    }
    else if (!no.hasRight()) {
      x = xEsquerda;
    }
    else {
      x = xEsquerda + (xDireita - xEsquerda) / 2;
    }
    Point posicao = new Point(x, visual.pontoInicial.y);

    Animacao.moverObjeto(visual, posicao);
    visual.pontoInicial = posicao;

  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    if (raiz != null) {
      Graphics2D g2D = (Graphics2D) graphics.create();
      g2D.setStroke(new BasicStroke(2));
      g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //remover serrilhamento

      desenharLinhas(raiz, g2D);
    }
  }

  private void desenharLinhas(Node raiz, Graphics2D graphics) {
    if (raiz.hasRight()) {
      Point inicio = raiz.getView().centro();
      Point chegada = raiz.nextRight().getView().centro();
      graphics.drawLine(inicio.x, inicio.y, chegada.x, chegada.y);
      desenharLinhas(raiz.nextRight(), graphics);
    }
    if (raiz.hasLeft()) {
      Point inicio = raiz.getView().centro();
      Point chegada = raiz.nextLeft().getView().centro();
      graphics.drawLine(inicio.x, inicio.y, chegada.x, chegada.y);
      desenharLinhas(raiz.nextLeft(), graphics);
    }

  }

}
