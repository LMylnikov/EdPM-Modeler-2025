package jMDIForm;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.*;

public class GridPanel extends JPanel {
    public static Color color = Color.GRAY;
    public static Boolean isVisible;
    public static Preferences prefs = Preferences.userNodeForPackage(GridPanel.class);
    static {
        // Проверяем, существует ли узел
        try {
            if (!prefs.nodeExists("")) {
                // Узел не существует - устанавливаем значения по умолчанию и сохраняем их
                color = Color.GRAY;
                isVisible = true;

                prefs.putInt("color", color.getRGB());
                prefs.putBoolean("isVisible", true);
            } else {
                // Узел существует - загружаем значения
                isVisible = prefs.getBoolean("isVisible", true);
                if (isVisible == true)
                    color = new Color(prefs.getInt("color", Color.GRAY.getRGB()));
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
            // В случае ошибки устанавливаем значения по умолчанию
            color = Color.GRAY;
        }
    }
    
    private int cellSize;   
    private int xOffset = 20; // Смещение по X
    private int yOffset = 20; // Смещение по Y
    private int thickLineSpacing = 5; // Каждая пятая линия будет толстой
    
    public int GetCellSize(){
        return this.cellSize;
    }
    
    public void SetCellSize(int value){
        this.cellSize = value;
    }

    public GridPanel(int cellSize) {
        this.cellSize = cellSize;
    }
    
    private static int baseCellSize = 20;
    public static int GetBaseCellSize(){
        return baseCellSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrid(g); // Вызов метода paintGrid для отрисовки сетки
    }

    private void paintGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Установка серого цвета для сетки
        g2d.setColor(color);

        // Определяем размеры панели
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Определяем начальные координаты для рисования сетки с учетом смещения
        //int startX = xOffset;
        //int startY = yOffset;

        int startX = cellSize;
        int startY = cellSize;
 
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, cellSize)); 

        // Рисуем буквенные обозначения столбцов
        for (int i = 0; i < panelWidth / cellSize; i++) {
            char columnChar = (char) ('A' + i);  // Начинаем с буквы 'A' и увеличиваем на i
            g2d.drawString(String.valueOf(columnChar), startX + cellSize * i * 5 + cellSize*5 / 2 - cellSize/ 3 , startY - cellSize/4);
        }

        // Рисуем числовые обозначения строк
        for (int i = 0; i < panelHeight / cellSize; i++) {
            g2d.drawString(String.valueOf(i + 1), cellSize/3, startY + cellSize * i * 5 + cellSize*5 / 2+ cellSize/ 3);
        }

        // Отрисовываем вертикальные линии сетки
        for (int x = startX; x <= panelWidth + cellSize; x += cellSize) {
            if ((x - startX) % (cellSize * thickLineSpacing) == 0) {
                g2d.setStroke(new BasicStroke(2)); // Устанавливаем толщину линии 2
            } else {
                g2d.setStroke(new BasicStroke(1)); // Возвращаем стандартную толщину линии
            }
            g2d.drawLine(x, startY, x, panelHeight);
        }

        // Отрисовываем горизонтальные линии сетки
        for (int y = startY; y <= panelHeight + cellSize; y += cellSize) {
            if ((y - startY) % (cellSize * thickLineSpacing) == 0) {
                g2d.setStroke(new BasicStroke(2)); // Устанавливаем толщину линии 2
            } else {
                g2d.setStroke(new BasicStroke(1)); // Возвращаем стандартную толщину линии
            }
            g2d.drawLine(startX, y, panelWidth, y);
        }
    }
}
