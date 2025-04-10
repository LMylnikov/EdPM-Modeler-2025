package figure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.util.prefs.Preferences;
import java.util.prefs.BackingStoreException;

public class O extends figures{
//    int s, x, y;
    public static Color BackgroundColor;
    public static Color TextColor;   
        //Применение прошлых настроек
    static {
        // Проверяем, существует ли узел
        try {
            if (!prefs.nodeExists("")) {
                // Узел не существует - устанавливаем значения по умолчанию и сохраняем их
                BackgroundColor = Color.WHITE;
                TextColor = Color.BLACK;

                prefs.putInt("NVBackgroundColor", BackgroundColor.getRGB());
                prefs.putInt("NVTextColor", TextColor.getRGB());
            } else {
                // Узел существует - загружаем значения
                BackgroundColor = new Color(prefs.getInt("NVBackgroundColor", Color.WHITE.getRGB()));
                TextColor = new Color(prefs.getInt("NVTextColor", Color.BLACK.getRGB()));
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
            // В случае ошибки устанавливаем значения по умолчанию
            BackgroundColor = Color.WHITE;
            TextColor = Color.BLACK;
        }
    }
    public O(int x, int y, int s) {
        this.x=x + s/4;
        this.y=y + s/4;
        this.absoluteX = this.x;
        this.absoluteY = this.y;
        this.s=s;
        this.nameF = "O" + this.id;
        this.coef = "1";
    }
    Font font = new Font("Arial", Font.BOLD, 24);
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Arial", Font.BOLD, (int)(24*s/100));    
        g2.setFont(font);
        g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
 
        GeneralPath gp = new GeneralPath();
        gp.moveTo(x-s/2, y+s/4);//startpoint
        gp.lineTo(x-s/6, y-s/4);//nextpoint
        gp.lineTo(x+s/2, y-s/4);//nextpoint
        gp.lineTo(x+s/6, y+s/4);//nextpoint
        gp.lineTo(x-s/2, y+s/4);//nextpoint      
        
        g2.setColor(BackgroundColor);
        g2.fill(gp);
        g2.setColor(TextColor);
        g2.setStroke(new BasicStroke(2));
        g2.draw(gp);
        
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(nameF);
        g2.drawString(nameF, x - width/2, y+9*s/100);
        
//        g2.drawString(nameF, x-8*s/100, y+9*s/100);
        
        shape =gp;
  
        Shape shape2=new Rectangle(x-s/3, y-s/4, 2*s/3, s/2);
        rec=shape2.getBounds2D();
    } 
}
