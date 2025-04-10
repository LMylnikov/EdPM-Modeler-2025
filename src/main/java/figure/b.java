//package figure;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Polygon;
//import java.awt.RenderingHints;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.geom.CubicCurve2D;
//import java.awt.geom.GeneralPath;
//import javax.swing.JComponent;
//import javax.swing.JPanel;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//
//public class  b extends JComponent{
//Font font = new Font("Arial", Font.BOLD, 18);
//    public int x2, y2;
//    int x=100;
//    int y=100;
//    @Override
//    public void paintComponent(Graphics g){
//        Graphics2D g2 = (Graphics2D) g;
//       g2.setColor(Color.DARK_GRAY);
////     g2.drawPolyline(int[] xPoints, int[] yPoints, int nPoints);
//      g2.setFont(font);
//      g2.setColor(Color. black);
//      g2.drawString("cl", getWidth()/2, getHeight()/2);
//GeneralPath gp = new GeneralPath();
//gp.moveTo(getWidth()/2-x/2, getHeight()/2+3*y/14);//startpoint
//gp.lineTo(getWidth()/2-x/2, getHeight()/2-y/2);//nextpoint
//gp.lineTo(getWidth()/2+x/2, getHeight()/2-y/2);//nextpoint
//gp.lineTo(getWidth()/2+x/2, getHeight()/2+y/2);//nextpoint
//gp.curveTo( getWidth()/2+x/8, getHeight()/2+y/2, getWidth()/2-x/8, getHeight()/2-y/8, getWidth()/2-x/2, getHeight()/2+3*y/14);
//g2.draw(gp);
////    }
// 
//}
//}