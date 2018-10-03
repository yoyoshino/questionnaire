package todocontroller;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
public class Dialog extends JApplet {
  private static final long serialVersionUID = 1L;
  private static Container c;
  private Canvas canvas;
  private BufferedImage bi;
  private Graphics2D offs;
  private int grpWidth = 250;
  private int grpHeight = 100;
  public void init() {
    bi = new BufferedImage(
               grpWidth, 
               grpHeight, 
               BufferedImage.TYPE_INT_ARGB);
    offs = (Graphics2D) bi.getGraphics();
    offs.setBackground(Color.WHITE);
    offs.clearRect(0,0,grpWidth, grpHeight);
    c = getContentPane();
    canvas = new Canvas(){
      private static final long serialVersionUID = 1L;
      public void paint(Graphics g) {
        g.drawImage(bi, 0, 0, null);
      }
    };
    setLayout(new BorderLayout());
    add(canvas, BorderLayout.CENTER);
    int ret = JOptionPane.showConfirmDialog(
                c.getParent(), 
                "ダイアログ内表示メッセージ",
                "確認ダイアログサンプルA",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    offs.setColor(Color.BLACK);
    if ( ret == JOptionPane.YES_OPTION ) {
      offs.drawString("確認ダイアログで「はい」が押されたよ", 10, 10);
    } else if ( ret == JOptionPane.NO_OPTION ) {
      offs.drawString("確認ダイアログで「いいえ」が押されたよ", 10, 10);
    } else if ( ret == JOptionPane.CANCEL_OPTION ) {
      offs.drawString("確認ダイアログで「取消」が押されたよ", 10, 10);
    }
  }
}
