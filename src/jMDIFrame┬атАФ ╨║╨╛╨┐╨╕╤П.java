package jMDIForm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import EPM.mdi;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.JsonNode;
import figure.*;
import java.awt.Cursor;
import java.awt.Shape;
import java.awt.geom.Point2D;
import static java.lang.Math.abs;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;



public class jMDIFrame extends JInternalFrame {

//    DatabaseHandler dbHandler = new DatabaseHandler();

    public int s;
    public final int k = 100;
    public ArrayList<figures> all = new ArrayList();//массив хранящий фугуры по порядку расположения !!!!!!!!!!!!!!!!!!!!!!!!!!
    public int x;//координаты мыши
    public int y;
    Point2D p;// текущая точка
    int id;//index of current object
    int dx = 0;//смещенные координаты курсора относительно фигуры при захвате объекта
    int dy = 0;
    public static int oldX, oldY;//coordinates before moving
    int newX, newY;//новые координаты после преобразований
    static double zoom = 1;// коэффициент масштаба
    Shape ss;
    boolean touch;
    
    boolean change_idx=false; //Индикатор который показывает были или нет изменения в схеме
    boolean draw_idx=true; //Показывает можно рисовать или нет
    
    public String fileName=""; // Имя файла в котором храниться схема
   
    long thisTimeFirstClick;//начальный замер времени
    
    public jMDIFrame(String title, Boolean resizable, Boolean closable, Boolean maximizable, Boolean iconifiable, String file) {
        super(title, resizable, closable, maximizable, iconifiable);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        zplus = new javax.swing.JButton();
        zminus = new javax.swing.JButton();
        jWorkSpace = new javax.swing.JScrollPane();

        setMaximizable(true);
        setResizable(true);
        setAutoscrolls(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(null));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                moveobj(evt);
            }
        });
        jPanel1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                Resizing(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });
        jPanel1.setLayout(new javax.swing.OverlayLayout(jPanel1));

        zplus.setText("+");
        zplus.setEnabled(false);
        zplus.setPreferredSize(new java.awt.Dimension(27, 23));
        zplus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zplusActionPerformed(evt);
            }
        });

        zminus.setText("-");
        zminus.setEnabled(false);
        zminus.setPreferredSize(new java.awt.Dimension(27, 23));
        zminus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zminusActionPerformed(evt);
            }
        });

        jWorkSpace.setBackground(new java.awt.Color(255, 255, 255));
        jWorkSpace.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(zplus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(zminus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jWorkSpace, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jWorkSpace, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(221, 221, 221)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zminus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zplus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VActionPerformed(java.awt.event.ActionEvent evt) {                                  
        s = k;
        x = jPanel1.getWidth();
        y = jPanel1.getHeight();
        V Vn = new V(x, y, s);
        Vn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        Vn.setVisible(true);
        jPanel1.removeAll();
        all.add(0, Vn);
        for (figures b : all) {
            jPanel1.add(b);
        }
        // jPanel1.revalidate();
        change_idx=true;
        jPanel1.repaint();
        ButtonActivated();
    }   
    
    private void RActionPerformed(java.awt.event.ActionEvent evt) {                                  
        s = k;
        x = jPanel1.getWidth();
        y = jPanel1.getHeight();
        R Rn = new R(x, y, s);
        Rn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        Rn.setVisible(true);
        jPanel1.removeAll();
        all.add(0, Rn);
        for (figures b : all) {
            jPanel1.add(b);
        }
        // jPanel1.revalidate()+++
        change_idx=true;
        jPanel1.repaint();
        ButtonActivated();
    }  
    
        private void NVActionPerformed(java.awt.event.ActionEvent evt) {                                   
        s = k;
        x = jPanel1.getWidth();
        y = jPanel1.getHeight();
        NV NVn = new NV(x, y, s);
        NVn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        NVn.setVisible(true);
        jPanel1.removeAll();
        all.add(0, NVn);
        for (figures b : all) {
            jPanel1.add(b);
        }
        // jPanel1.revalidate();
        change_idx=true;
        jPanel1.repaint();
        ButtonActivated();
    }                                  

    
    
    private void zplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zplusActionPerformed
        zoom = zoom + 0.15;
        s = (int) (k * zoom);
        x = jPanel1.getWidth();
        y = jPanel1.getHeight();

        for (figures b : all) {
            b.setS(s);
            oldX = b.getXX();
            oldY = b.getYY();
            dx = (int) zoom * abs((x / 2 - oldX));
            if (oldX > x / 2) {
                newX = oldX + dx;
            } else {
                newX = oldX - dx;
            }
            dy = (int) zoom * abs((y / 2 - oldY));
            if (oldY > y / 2) {
                newY = oldY + dy;
            } else {
                newY = oldY - dy;
            }
            b.setXX(newX);
            b.setYY(newY);
        }
        jPanel1.repaint();
    }//GEN-LAST:event_zplusActionPerformed

   private void DActionPerformed(java.awt.event.ActionEvent evt) {                                  
        s = k;
        x = jPanel1.getWidth();
        y = jPanel1.getHeight();
        d dn = new d(x, y, s);
        dn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        dn.setVisible(true);
        jPanel1.removeAll();
        all.add(0, dn);
        for (figures b : all) {
            jPanel1.add(b);
        }
        //jPanel1.revalidate();
        change_idx=true;
        jPanel1.repaint();
        
        ButtonActivated();
    }                                 
    
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {                                      

        jPanel1.removeAll();
        jPanel1.repaint();
        all.clear();
        zoom = 1;
        ButtonActivated();
    }   
    
    private void zminusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zminusActionPerformed
        zoom = zoom - 0.15;
        s = (int) (k * zoom);
        x = jPanel1.getWidth();
        y = jPanel1.getHeight();

        for (figures b : all) {
            b.setS(s);
            oldX = b.getXX();
            oldY = b.getYY();
            dx = (int) zoom * abs((x / 2 - oldX));
            if (oldX > x / 2) {
                newX = oldX - dx;
            } else {
                newX = oldX + dx;
            }
            dy = (int) zoom * abs((y / 2 - oldY));
            if (oldY > y / 2) {
                newY = oldY - dy;
            } else {
                newY = oldY + dy;
            }
            b.setXX(newX);
            b.setYY(newY);
        }
        jPanel1.repaint();
    }//GEN-LAST:event_zminusActionPerformed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        oldX = evt.getX();
        oldY = evt.getY();
        p = evt.getPoint();
        boolean is_dc = false;
        for (figures b : all) {
            ss = b.getShape();
            if (ss.contains(p) == true) {       
                if (b.doubleCl == true){ //если уже был клик на фигуру
                   long thisTimeSecondClick = System.currentTimeMillis();   //время второго клика               
                   if (abs(thisTimeSecondClick - thisTimeFirstClick) < 300){ //если разница в кликах невелика
                        //Тут вызывем окно с свойствами
                        //JOptionPane.showMessageDialog(this, "X: " +b.getXX() + " Y " +b.getYY() , "О фигуре", JOptionPane.ERROR_MESSAGE);
                        Properties prop = new Properties("Figure",false,true,true,false,null,b);
                        //prop.setPreferredSize(new Dimension(200, 200));
                        //prop.setSize(prop.getPreferredSize());

                        //prop.setResizable(true);
                        //prop.pack();
                        //prop.setLocation(2, 2);
                        
                        jPanel1.add(prop);
                        jPanel1.repaint();
                        prop.toFront();
                        prop.setVisible(true);   
                        jPanel1.repaint();
                        is_dc = true;
                        //Пытаюсь открыть окно, но не получается. Думаю на оюновление через removeall мешает.
                        
                   }
                   else{ //Иначе ничиго
                       //JOptionPane.showMessageDialog(this, "LOL." , "Ошибка", JOptionPane.ERROR_MESSAGE);       
                   }
                   b.doubleCl = false;  //Убираем воспоминание о первом клике
                }
                else{ //Есди не было первого клика
                    thisTimeFirstClick = System.currentTimeMillis(); //Берем время первого клика
                    b.doubleCl = true; //Обновляем флаг о первом клике                 
                } //Далее код для переноса фигуры, не относ к двойному клику
                
                if (is_dc = false){
                    jPanel1.removeAll();
                }

                all.remove(b);
                all.add(0, b);
                for (JComponent c : all) {
                jPanel1.add(c);
                } 

                jPanel1.repaint();
                ButtonActivated();
                break;
            }
        }
        touch = ss.contains(p) == true;
        
    }//GEN-LAST:event_jPanel1MousePressed

    private void Resizing(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_Resizing
        //        p=evt.getPoint();
        //        zoom=evt.getUnitsToScroll();
        //
        //            jPanel1.repaint();

    }//GEN-LAST:event_Resizing

    private void moveobj(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveobj
        figures b = all.get(0);
        ss = b.getShape();
        if (touch == true) {
            
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            dx = -oldX + b.getXX();
            dy = -oldY + b.getYY();
            newX = evt.getX() + dx;
            newY = evt.getY() + dy;
            b.setXX(newX);
            b.setYY(newY);
            change_idx=true;                  
            jPanel1.repaint();
            oldX = evt.getX();
            oldY = evt.getY();
            ButtonActivated();
        }
       

    }//GEN-LAST:event_moveobj

  //  private void SaveInDb(ArrayList<figures> all, String saveName) {
  //      //// код для сохранения всех элементов
 //
 //       //с тем же названием нет, пока такой случай не обработан.
 //       System.out.println("start saving");
 //       for (figures f : all) { //Перебираем все фигуры и заносим данные в функцию
 //           Figure_s fig = new Figure_s();
//
  //          String gType = f.getClass().toString();
  //          gType = gType.replace("class figure.", "");
  //          fig.setX_pos(Integer.toString(f.getXX()));
  //          fig.setY_pos(Integer.toString(f.getYY()));
  //          fig.setShape(gType);
  //          fig.setSize(Integer.toString(f.getSises()));
  //          fig.setId(Integer.toString(f.getId()));
//
 //           //System.out.println(f.getClass().toString() + " ;" + f.getSises() + " , " + f.getXX() + " , " + f.getYY() );
 //           //        String gX_pos = Integer.toString(f.getXX());
 //           //        String gY_pos = Integer.toString(f.getYY());
 //           //        String gSize = Integer.toString(f.getSises());
 //           //        String gId = Integer.toString(f.getId());
 //           dbHandler.addFig(saveName, fig/*gX_pos,gY_pos,gType,gSize,gId*/);
 //       }
 //       System.out.println("end saving");
 //   }

//    private ArrayList<figures> LoadFromDb(ArrayList<figures> all, String saveName) {
//        DatabaseHandler dbHandler = new DatabaseHandler();
//        ResultSet resSet = dbHandler.getFigure(saveName);
//        try {
//            while (resSet.next()) { //Пробегаемся по всем найденым фигурам
//                Figure_s fig = new Figure_s();
//                fig.setX_pos(resSet.getNString(3));
//                fig.setY_pos(resSet.getNString(4));
//                fig.setShape(resSet.getNString(5));
//                fig.setSize(resSet.getNString(6));
//                readSaveData rs = new readSaveData();
//                all = rs.getElement(all, fig.getShape(), fig.getX_pos(), fig.getY_pos(), fig.getSize()/*Sshape,Sx,Sy,Ss*/); //Заносим фигуру в список используемых в проекте фигур
//            }
//        } catch (SQLException error) {
//            error.printStackTrace();
//        }
//        return all;
//    }





    //Сохранение файла
    public void SaveInJSON(String fn) {
        try {

            // Создаем список для хранения объектов Figure_s
            List<Figure_s> figuresList = new ArrayList<>();
            for (figures f : all) {
                Figure_s fig = new Figure_s();
                String gType = f.getClass().toString();
                //System.out.println(f.getShape().toString());
                gType = gType.replace("class figure.", "");
                fig.setX_pos(Integer.toString(f.getXX()));
                fig.setY_pos(Integer.toString(f.getYY()));
                fig.setShape(gType);
                fig.setSize(Integer.toString(f.getSises()));
                fig.setId(Integer.toString(f.getId()));
                // Добавляем объект Figure_s в список
                figuresList.add(fig);
            }
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get(fn).toFile(), figuresList);
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(jMDIFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // *** Загружаем модель из файла JSON ***
    public void LoadFromJSON(String saveName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Читаем JSON из файла в виде строки
            //String jsonString = Files.readString(Paths.get(saveName));
            Path filePath = Path.of(saveName);
            String jsonString = Files.readString(filePath);
            
            
            //File file = new File(saveName);
            //String content = FileUtils.readFileToString(file, "UTF-8");
            
            
            
            
            //String jsonString;
            //jsonString = objectMapper.readValue(Paths.get(saveName));// . .read readValue(Paths.get(saveName));
            // Преобразуем JSON в список объектов Figure_s
            List<Figure_s> figuresList;
            figuresList = objectMapper.readValue(jsonString, new TypeReference<List<Figure_s>>() { });
            jPanel1.removeAll();
            jPanel1.repaint();
            all.clear();
            // Преобразуем объекты Figure_s в объекты Figure
            for (Figure_s fig : figuresList) {
                readSaveData rs = new readSaveData();
                all = rs.getElement(all, fig.getShape(), fig.getX_pos(), fig.getY_pos(), fig.getSize()/*Sshape,Sx,Sy,Ss*/); //Заносим фигуру в список используемых в проекте фигур
            }
            // Выводим результат
            for (figures b : all) { //добавляем фигуры
                b.setSize(jPanel1.getWidth(), jPanel1.getHeight());
                b.setVisible(true);
                jPanel1.add(b);
            }
            jPanel1.repaint();
            fileName = saveName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
       ButtonActivated();
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        
        if (change_idx){
            Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            int dialogResult = JOptionPane.showConfirmDialog (this, "Project wasn't saved. Would you like to save your project?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(image));
            if (dialogResult == JOptionPane.YES_OPTION) {
               if (!("".equals(fileName))) {
                   SaveInJSON(fileName);
               } else {
                   JFileChooser SaveChooser = new javax.swing.JFileChooser();
                   SaveChooser.setDialogTitle("Specify a file to save");
                   SaveChooser.setCurrentDirectory(new File("."));
                   SaveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                   SaveChooser.setFileFilter(new FileNameExtensionFilter("Event-driven Process Methodology", "epm"));
                   SaveChooser.approveSelection();
                   int option = SaveChooser.showSaveDialog(this);

                    Field field2 = null;
        
                    if(option != JFileChooser.CANCEL_OPTION) {
            
                        File file1 = SaveChooser.getSelectedFile(); 
                        String file = null;
            
                        try {
                            file = file1.getCanonicalPath();
                        } catch (IOException ex) {
                            Logger.getLogger(mdi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        fileName=file;
                        SaveInJSON(fileName);
                     }                   
                   
               } 
            }
        }
        
        change_idx=false;
        draw_idx=false;
        ButtonActivated();
    }//GEN-LAST:event_formInternalFrameClosing

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
        // TODO add your handling code here:
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jPanel1MouseReleased

    
  // Активация / деактивация кнопки Save 
  public void ButtonActivated() {                                            
        if (change_idx) {
            mdi.Save.setEnabled(true); 
            mdi.jButtonSave.setEnabled(true);
            mdi.Saveas.setEnabled(true);
        } else {
           mdi.Save.setEnabled(false); 
           mdi.jButtonSave.setEnabled(false);
           mdi.Saveas.setEnabled(false);
        }
        
        if (draw_idx) {
           mdi.jButtonS.setEnabled(true);
           mdi.jButtonV.setEnabled(true); 
           mdi.jButtonNV.setEnabled(true);
           mdi.jButtonR.setEnabled(true);
           mdi.jButtonIF.setEnabled(true);
           mdi.jMenuItemS.setEnabled(true);
           mdi.jMenuItemV.setEnabled(true);
           mdi.jMenuItemR.setEnabled(true);
           mdi.jMenuItemNV.setEnabled(true);
           mdi.jMenuItemIF.setEnabled(true);
           mdi.jMenuItemClear.setEnabled(true);
        } else {
           mdi.jButtonS.setEnabled(false);
           mdi.jButtonV.setEnabled(false); 
           mdi.jButtonNV.setEnabled(false);
           mdi.jButtonR.setEnabled(false);
           mdi.jButtonIF.setEnabled(false);
           mdi.jMenuItemS.setEnabled(false);
           mdi.jMenuItemV.setEnabled(false);
           mdi.jMenuItemR.setEnabled(false);
           mdi.jMenuItemNV.setEnabled(false);
           mdi.jMenuItemIF.setEnabled(false);
           mdi.jMenuItemClear.setEnabled(false);            
        }
    }          
  

 private void SActionPerformed(java.awt.event.ActionEvent evt) {                                  
        s = k;
        x = jPanel1.getWidth();
        y = jPanel1.getHeight();
        S1 Sn = new S1(x, y, s);
        Sn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        Sn.setVisible(true);
        jPanel1.removeAll();
        all.add(0, Sn);
        for (figures c : all) {
            jPanel1.add(c);
        }
        // jPanel1.revalidate();
        change_idx=true;
        jPanel1.repaint();

        ButtonActivated();
    }     
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jWorkSpace;
    private javax.swing.JButton zminus;
    private javax.swing.JButton zplus;
    // End of variables declaration//GEN-END:variables

}
