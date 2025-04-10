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
import javax.swing.*;
import java.util.prefs.Preferences;
import java.util.prefs.BackingStoreException;

public class R extends figures{
//    int s, x, y;
    public static Color BackgroundColor;
    public static Color TextColor;
    static {
        // Проверяем, существует ли узел
        try {
            if (!prefs.nodeExists("")) {
                // Узел не существует - устанавливаем значения по умолчанию и сохраняем их
                BackgroundColor = Color.WHITE;
                TextColor = Color.BLACK;

                prefs.putInt("RBackgroundColor", BackgroundColor.getRGB());
                prefs.putInt("RTextColor", TextColor.getRGB());
            } else {
                // Узел существует - загружаем значения
                BackgroundColor = new Color(prefs.getInt("RBackgroundColor", Color.WHITE.getRGB()));
                TextColor = new Color(prefs.getInt("RTextColor", Color.BLACK.getRGB()));
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
            // В случае ошибки устанавливаем значения по умолчанию
            BackgroundColor = Color.WHITE;
            TextColor = Color.BLACK;
        }
    }
    public R(int x, int y, int s) {
        this.x=x + s/4;
        this.y=y + s/4;
        this.absoluteX = this.x;
        this.absoluteY = this.y;
        this.s=s;
        this.nameF = "R" + this.id;
    }
    Font font = new Font("Arial", Font.BOLD, 24);
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        Font font = new Font("Arial", Font.BOLD, (int)(24*s/100));//Иванов А.А. перемещен в конструктор для обновления при перерисовке
        g2.setFont(font);
       // Polygon p = new Polygon();
       // p.addPoint(x-7*s/10, y+s/4);
       // p.addPoint(x-3*s/10, y-s/4);
       // p.addPoint(x+7*s/10, y-s/4);
       // p.addPoint(x+3*s/10, y+s/4);
       // p.addPoint(x-7*s/10, y+s/4);
            g2.setColor(BackgroundColor);
        g2.drawRect(x-s/2, y-s/4, s, s/2);
        //g2.setColor(new Color(223,231, 252));

        shape = new Rectangle(x-s/2, y-s/4, s, s/2);        
        
        g2.fill(shape);
        g2.setColor(TextColor);
        g2.setStroke(new BasicStroke(2));
        g2.draw(shape);
        
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(nameF);
        g2.drawString(nameF, x - width/2, y+9*s/100);
        
//        g2.drawString(nameF, x-10*s/100, y+9*s/100);//Иванов А.А. надпись центруется с учетом масштаба
 
        rec=shape.getBounds2D();

    }
}
