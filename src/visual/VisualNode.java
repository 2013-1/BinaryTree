package visual;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class VisualNode extends JLabel {

  private final JLabel info;
  public Point pontoInicial;
  
  public VisualNode(int value) {
    super();
    int raio = 35;
    setSize(raio, raio);
    pontoInicial = new Point(0, 0);

    info = new JLabel(value + "");
    info.setSize(getSize());
    info.setLocation(0, 0);
    info.setFont(new Font("Arial", Font.PLAIN, 20));
    info.setHorizontalAlignment(CENTER);
    info.setVerticalAlignment(CENTER);
    add(info);
  }

  @Override
  public void paintComponent(Graphics gph) {
    //como eh uma funcao sobrescrita, mandamos ele fazer tudo o que ele ja fazia antes.
    super.paintComponent(gph);

    //capturamos o elemento grafico do swing e convertemos para graphic2d.
    Graphics2D graphic = (Graphics2D) gph.create();
    graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //remover serrilhamento

    graphic.setPaint(Color.white);
    graphic.fillOval(1, 1, getWidth() - 3, getHeight() - 3); //circulo de preenchimento

    graphic.setPaint(Color.black);
    graphic.setStroke(new BasicStroke(2)); //espessura da borda
    graphic.drawOval(1, 1, getWidth() - 3, getHeight() - 3); //circulo de borda

  }

  public Point centro() {
    Point posicao = getLocation();
    posicao.x += getWidth() / 2;
    posicao.y += getHeight() / 2;
    return posicao;

  }
  
  

}
