package figure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.prefs.Preferences;
import java.util.prefs.BackingStoreException;

public class  d extends figures{//document
//int s, x, y;
    public static Color BackgroundColor;
    public static Color TextColor;
    static {
        // Проверяем, существует ли узел
        try {
            if (!prefs.nodeExists("")) {
                // Узел не существует - устанавливаем значения по умолчанию и сохраняем их
                BackgroundColor = Color.WHITE;
                TextColor = Color.BLACK;

                prefs.putInt("IFBackgroundColor", BackgroundColor.getRGB());
                prefs.putInt("IFTextColor", TextColor.getRGB());
            } else {
                // Узел существует - загружаем значения
                BackgroundColor = new Color(prefs.getInt("IFBackgroundColor", Color.WHITE.getRGB()));
                TextColor = new Color(prefs.getInt("IFTextColor", Color.BLACK.getRGB()));
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
            // В случае ошибки устанавливаем значения по умолчанию
            BackgroundColor = Color.WHITE;
            TextColor = Color.BLACK;
        }
    }
    public d(int x, int y, int s) {
        this.x=x + s/2;
        this.y=y + s/4;
        this.absoluteX = this.x;
        this.absoluteY = this.y;
        this.s=s;
//        this.nameF = "D(IF)" + this.id;
        this.nameF = "IF" + this.id;
    }
    Font font = new Font("Arial", Font.BOLD, 20);
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Arial", Font.BOLD, (int)(24*s/100)); //Иванов А.А. перемещен в конструктор для обновления при перерисовке
        g2.setFont(font);
        g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        GeneralPath gp = new GeneralPath();
        gp.moveTo(x-s/4, y-s/4);//startpoint
        gp.lineTo(x, y);//nextpoint
        gp.lineTo(x-s/4, y+s/4);//nextpoint
        gp.lineTo(x-s/2, y);//nextpoint
        gp.lineTo(x-s/4, y-s/4);//nextpoint
        
        
               //g2.drawRect(x-s/2, y-s/4, s, s/2);
        
        //gp.curveTo( x-s/64, y-s/8,x+s/64, y+s/2, x-s/2, y+3*s/14);
        g2.setColor(BackgroundColor);
        g2.fill(gp);
        g2.setColor(TextColor);
        g2.setStroke(new BasicStroke(2));
        g2.draw(gp);
        
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(nameF);
        g2.drawString(nameF, x - width/2-s/4, y+7*s/100);
        
//        g2.drawString(nameF, x-34*s/100, y+7*s/100); //Иванов А.А. надпись центруется с учетом масштаба
        shape =gp;
        rec=shape.getBounds2D();
        }
}