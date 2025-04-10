package jMDIForm;

//import point.pointStraight;
//import point.points;
//import line.LineStraight;
//import line.Line;
//import properties.PropertiesDialog;
import java.awt.Toolkit;//для буфера обмена
import java.awt.datatransfer.Clipboard;//для буфера обмена
import java.awt.datatransfer.StringSelection;//для буфера обмена

import converter.Figure_s;
import converter.Line_s;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import EPM.mdi;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.SerializationFeature;
import converter.ConvertedObject;
import descritGen.generatorObj;
import figure.*;
import java.awt.Cursor;
import java.awt.Shape;
import java.awt.geom.Point2D;
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
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.Collections;
import java.util.Dictionary;
import rtranslator.CreateRCode;
import rtranslator.RTranslatorClass;
import rtranslator.newPrecodeGenerator;

public class jMDIFrame extends JInternalFrame {

//    DatabaseHandler dbHandler = new DatabaseHandler();
    public int s;
    public final int k = 100;
    public ArrayList<figures> all = new ArrayList();//массив хранящий фугуры по порядку расположения !
//    public ArrayList<points> points = new ArrayList();//массив хранящий точки по порядку расположения
    public ArrayList<points> points = new ArrayList();//массив хранящий точки по порядку расположения
    public ArrayList<Line> lines = new ArrayList();//массив линий 
    public ArrayList<Shape> pointShape = new ArrayList();//массив форм точек на 1 фигуре//обновляется для каждой отдельной фигуры
    public int x;//координаты мыши
    public int y;
    Point2D p;// текущая точка
    int id;//index of current object
    int dx = 0;//смещенные координаты курсора относительно фигуры при захвате объекта
    int dy = 0;
    public static int oldX, oldY;//coordinates before moving
    int newX, newY;//новые координаты после преобразований
    int zoom = 100;// коэффициент масштаба
    Shape ss;
    boolean touch;
    boolean pointed = false;//есть ли точки
    boolean checkLine = false;//для проведения соединений объектов
    boolean lined = false;//есть линии или нет
    int id11, id22;// индексы расположения точек, соединенных линиями
    String ID1, ID2;
    int countp = 0;

    boolean change_idx = false; //Индикатор который показывает были или нет изменения в схеме
    boolean draw_idx = true; //Показывает можно рисовать или нет
    public boolean cleaned = false;//индикатор проведена была очистка или нет

    public String fileName = ""; // Имя файла в котором храниться схема
    public int dificult = 0; //Сложность проекта указывается в настройках
    //long thisTimeFirstClick;//начальный замер времени
    GridPanel grid;

    public jMDIFrame(String title, Boolean resizable, Boolean closable, Boolean maximizable, Boolean iconifiable, String file) {
        super(title, resizable, closable, maximizable, iconifiable);
        initComponents();

        // Добавляем GridPanel на jPanel1
        grid = new GridPanel(GridPanel.GetBaseCellSize());
        jPanel1.add(grid);

    }

    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rcMenu = new javax.swing.JPopupMenu();
        jMenuItemDelette = new javax.swing.JMenuItem();
        jMenuItemClear = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemGenDesc = new javax.swing.JMenuItem();
        canvas1 = new java.awt.Canvas();
        SaveChooser = new javax.swing.JFileChooser();
        descrShowDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        textDescription = new javax.swing.JTextArea();
        closeDescrBut = new javax.swing.JButton();
        copyDescrBut = new javax.swing.JButton();
        rCodeActivatorBut = new javax.swing.JButton();
        copyDescrButRCode = new javax.swing.JButton();
        scrollPaneR = new javax.swing.JScrollPane();
        textDescriptionRCode = new javax.swing.JTextArea();
        toRCodeBut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        zminus = new javax.swing.JButton();
        jSize = new javax.swing.JTextField();
        zplus = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        zminus1 = new javax.swing.JButton();
        jSize1 = new javax.swing.JTextField();
        zplus1 = new javax.swing.JButton();

        jMenuItemDelette.setText("Delete");
        jMenuItemDelette.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeletteActionPerformed(evt);
            }
        });
        rcMenu.add(jMenuItemDelette);

        jMenuItemClear.setText("Clear all");
        jMenuItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        rcMenu.add(jMenuItemClear);
        rcMenu.add(jSeparator1);

        jMenuItemGenDesc.setText("Model generation ...");
        jMenuItemGenDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGenDescActionPerformed(evt);
            }
        });
        rcMenu.add(jMenuItemGenDesc);

        descrShowDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        descrShowDialog.setTitle("Model generation");
        descrShowDialog.setResizable(false);

        textDescription.setEditable(false);
        textDescription.setColumns(20);
        textDescription.setRows(5);
        scrollPane.setViewportView(textDescription);

        closeDescrBut.setText("Close");
        closeDescrBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeDescrButActionPerformed(evt);
            }
        });

        copyDescrBut.setText("Copy");
        copyDescrBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyDescrButActionPerformed(evt);
            }
        });

        rCodeActivatorBut.setText("Save code");
        rCodeActivatorBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rCodeActivatorButActionPerformed(evt);
            }
        });

        copyDescrButRCode.setText("Copy code");
        copyDescrButRCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyDescrButRCodeActionPerformed(evt);
            }
        });

        textDescriptionRCode.setEditable(false);
        textDescriptionRCode.setColumns(20);
        textDescriptionRCode.setRows(5);
        scrollPaneR.setViewportView(textDescriptionRCode);

        toRCodeBut.setText("R code ->");
        toRCodeBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toRCodeButActionPerformed(evt);
            }
        });

        jLabel1.setText("Target language:");

        jLabel2.setText("Source language (internal functional language):");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toRCodeBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(closeDescrBut)
                        .addGap(85, 85, 85)
                        .addComponent(copyDescrBut)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(copyDescrButRCode)
                        .addGap(74, 74, 74)
                        .addComponent(rCodeActivatorBut))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(scrollPaneR, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPaneR, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(toRCodeBut))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeDescrBut)
                    .addComponent(copyDescrBut)
                    .addComponent(rCodeActivatorBut)
                    .addComponent(copyDescrButRCode))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout descrShowDialogLayout = new javax.swing.GroupLayout(descrShowDialog.getContentPane());
        descrShowDialog.getContentPane().setLayout(descrShowDialogLayout);
        descrShowDialogLayout.setHorizontalGroup(
            descrShowDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descrShowDialogLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        descrShowDialogLayout.setVerticalGroup(
            descrShowDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descrShowDialogLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

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

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jScrollPane1MouseWheelMoved(evt);
            }
        });
        jScrollPane1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jScrollPane1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setComponentPopupMenu(rcMenu);
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                moveobj(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
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
        jScrollPane1.setViewportView(jPanel1);

        zminus.setText("-");
        zminus.setEnabled(true);
        zminus.setPreferredSize(new java.awt.Dimension(27, 23));
        zminus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zminusActionPerformed(evt);
            }
        });

        jSize.setEditable(false);
        jSize.setBackground(getBackground());
        jSize.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSize.setText("100%");
        jSize.setToolTipText("");
        jSize.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSize.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jSize.setFocusable(false);
        jSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jSizeMouseExited(evt);
            }
        });

        zplus.setText("+");
        zplus.setEnabled(true);
        zplus.setPreferredSize(new java.awt.Dimension(27, 23));
        zplus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zplusActionPerformed(evt);
            }
        });

        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setAutoscrolls(true);
        jInternalFrame1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jInternalFrame1.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                jInternalFrame1formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                jInternalFrame1formInternalFrameClosing(evt);
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

        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setComponentPopupMenu(rcMenu);
        jScrollPane2.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jScrollPane2MouseWheelMoved(evt);
            }
        });
        jScrollPane2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jScrollPane2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2moveobj(evt);
            }
        });
        jPanel2.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jPanel2Resizing(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });
        jPanel2.setLayout(new javax.swing.OverlayLayout(jPanel2));
        jScrollPane2.setViewportView(jPanel2);

        zminus1.setText("-");
        zminus1.setEnabled(true);
        zminus1.setPreferredSize(new java.awt.Dimension(27, 23));
        zminus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zminus1ActionPerformed(evt);
            }
        });

        jSize1.setEditable(false);
        jSize1.setBackground(getBackground());
        jSize1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSize1.setText("100%");
        jSize1.setToolTipText("");
        jSize1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSize1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jSize1.setFocusable(false);
        jSize1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSize1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jSize1MouseExited(evt);
            }
        });

        zplus1.setText("+");
        zplus1.setEnabled(true);
        zplus1.setPreferredSize(new java.awt.Dimension(27, 23));
        zplus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zplus1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(zminus1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSize1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zplus1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(zplus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zminus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSize1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(zminus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSize, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zplus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(14, 14, 14))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(zplus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zminus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Получения центра видимой области панели по координате X
    public int GetCenterX() {
        // Получаем размеры панели
        int panelWidth = jPanel1.getWidth();

        // Рассчитываем размеры видимой области панели
        int visibleWidth = jScrollPane1.getViewport().getViewRect().width;

        // Максимальное значение скроллера
        int maxScrollX = jScrollPane1.getHorizontalScrollBar().getMaximum();

        // Получаем текущее положение скроллеров
        int scrollX = jScrollPane1.getHorizontalScrollBar().getValue();

        // Рассчитываем координаты центра видимой области панели относительно текущего положения скроллеров
        int centerX = panelWidth / maxScrollX * scrollX + visibleWidth / 2 - s / 4;

        return centerX;
    }

    //Получения центра видимой области панели по координате Y
    public int GetCenterY() {
        // Получаем размеры панели
        int panelHeight = jPanel1.getHeight();

        // Рассчитываем размеры видимой области панели
        int visibleHeight = jScrollPane1.getViewport().getViewRect().height;

        // Максимальное значение скроллера
        int maxScrollY = jScrollPane1.getVerticalScrollBar().getMaximum();

        // Получаем текущее положение скроллеров
        int scrollY = jScrollPane1.getVerticalScrollBar().getValue();

        // Рассчитываем координаты центра видимой области панели относительно текущего положения скроллеров
        int centerY = panelHeight / maxScrollY * scrollY + visibleHeight / 2 - s / 4;

        return centerY;
    }

//Вставляем элемент S
    private void SActionPerformed(java.awt.event.ActionEvent evt) {
        s = k;
        CleanNumbers(cleaned);

        // Рассчитываем координаты центра фигуры
        int x = GetCenterX();
        int y = GetCenterY();

        // Создаем экземпляр фигуры и устанавливаем его координаты
        S1 Sn = new S1(x, y, (int) (s * zoom / 100));

        // Добавляем фигуру на панель
        jPanel1.removeAll();
        all.add(0, Sn);
        this.drawObjects();

        // Устанавливаем флаг изменения
        change_idx = true;

        cleaned = false;
        ButtonActivated();
    }

    //Вставляем элемент V
    private void VActionPerformed(java.awt.event.ActionEvent evt) {
        s = k;
        CleanNumbers(cleaned);
        // Рассчитываем координаты верхнего левого угла фигуры
        int x = GetCenterX();
        int y = GetCenterY();

        V Vn = new V(x, y, (int) (s * zoom / 100));
        Vn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        Vn.setVisible(true);
        Vn.setOpaque(false); // Сделаем фигуру прозрачной

        jPanel1.removeAll();
        all.add(0, Vn);
        this.drawObjects();

        change_idx = true;
        cleaned = false;
        ButtonActivated();
    }

    //Вставляем элемент R
    private void RActionPerformed(java.awt.event.ActionEvent evt) {
        s = k;
        CleanNumbers(cleaned);
        // Рассчитываем координаты верхнего левого угла фигуры
        int x = GetCenterX();
        int y = GetCenterY();

        R Rn = new R(x, y, (int) (s * zoom / 100));
        Rn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        Rn.setVisible(true);

        jPanel1.removeAll();
        all.add(0, Rn);
        this.drawObjects();

        change_idx = true;
        cleaned = false;
        ButtonActivated();
    }

    //Вставляем элемент NV
    private void NVActionPerformed(java.awt.event.ActionEvent evt) {
        s = k;
        CleanNumbers(cleaned);
        int x = GetCenterX();
        int y = GetCenterY();

        NV NVn = new NV(x, y, (int) (s * zoom / 100));
        NVn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        NVn.setVisible(true);

        jPanel1.removeAll();
        all.add(0, NVn);
        this.drawObjects();

        change_idx = true;
        cleaned = false;
        ButtonActivated();
    }

    //Вставляем элемент условие (IF)
    private void DActionPerformed(java.awt.event.ActionEvent evt) {
        s = k;
        CleanNumbers(cleaned);
        int x = GetCenterX();
        int y = GetCenterY();

        //d dn = new d(x, y, s);
        d dn = new d(x, y, (int) (s * zoom / 100));
        dn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        dn.setVisible(true);

        jPanel1.removeAll();
        all.add(0, dn);
        this.drawObjects();

        change_idx = true;
        cleaned = false;
        ButtonActivated();
    }

    private void OActionPerformed(java.awt.event.ActionEvent evt) {
        s = k;
        CleanNumbers(cleaned);
        int x = GetCenterX();
        int y = GetCenterY();

        //d dn = new d(x, y, s);
        O on = new O(x, y, (int) (s * zoom / 100));
        on.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        on.setVisible(true);

        jPanel1.removeAll();
        all.add(0, on);
        this.drawObjects();

        change_idx = true;
        cleaned = false;
        ButtonActivated();
    }

    private void drawObjects() {//перерисовка всех объектов
        //jPanel1.removeAll();
        for (figures b : all) {
            jPanel1.add(b);
        }
        for (Line line : lines) {
            jPanel1.add(line);
        }
        jPanel1.add(grid); // Добавляем сетку перед добавлением фигур
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    private void addPoints() {//добавление точек на все фигуры + их отображение
        points.clear();
        for (figures ff : all) {//заполнение массива точками для каждой фигуры с соответствием их идексам
            if (ff.getRec() != null) {
                pointStraight pn = new pointStraight(ff.getRec());
                pn.setSize(jPanel1.getWidth(), jPanel1.getHeight());
                pn.setVisible(true);
                points.add(all.indexOf(ff), pn);//по итогу полностью заполнится всеми точками на текущий момент
            }
        }
        //drawObjects();
    }

    private void oneShapePoints(int i) {//обновляет массив с внутренними точками фигуры
        pointShape.clear();
        pointShape = points.get(i).getShape();//
    }

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        oldX = evt.getX();
        oldY = evt.getY();
        p = evt.getPoint();

        id11 = 0;//индекс по точкам
        id22 = 0;
        int countf = 0;//счет нерасмотренных фигур
        countp = 0;//счет нерассмотренных точек//на них не попал курсор
        addPoints();

        for (figures currentFigure : all) {
            ss = currentFigure.getShape();
            if (ss.contains(p) == true) {

                //если двойной клик открываем окно со свойствами фгуры
                if (evt.getClickCount() == 2) {
                    String oldName = currentFigure.getNameF();
                    PropertiesDialog pDialog = new PropertiesDialog(null, true, currentFigure,all);
                    pDialog.setVisible(true);
                    //изменение названия фигуры в линиях для корректной привязки линий к фигурам
                    for (Line line: lines){
                        if (line.getID1().equals(oldName)){
                            line.setID1(currentFigure.getNameF());
                        }
                        if (line.getID2().equals(oldName)){
                            line.setID2(currentFigure.getNameF());
                        }
                    }
                    evt.consume();
                }

                //Далее код для переноса фигуры, не относ к двойному клику
                evt.consume();
                all.remove(currentFigure);
                all.add(0, currentFigure);
                //addPoints();

                //установить новые координты для x и y, прибавить к координате значение положения скроллера
                jPanel1.removeAll();
                for (JComponent c : all) {
                    jPanel1.add(c);
                }

                //if (!pointed) {//если нет, то добавление точек на фигуру
                //    
                //    pointed = true;
                //    jPanel1.removeAll();
                //    jPanel1.add(points.get(0));
                //    if (lined) {
                //        for (Line ln : lines) jPanel1.add(ln);
                //    }
                //    for (JComponent c : all) jPanel1.add(c);
                //    //jPanel1.add(grid);
                //    //jPanel1.revalidate();
                //    //jPanel1.repaint();
                //} else {//если да, то проверка попадания на фигуру или точку
                oneShapePoints(0);
                for (Shape l : pointShape) {
                    if (l.contains(p)) {
                        //выделение всех точек и соединение
                        id11 = pointShape.indexOf(l);
                        jPanel1.removeAll();
                        for (points a : points) {
                            jPanel1.add(a);
                            if (lined) {
                                for (Line ln : lines) {
                                    jPanel1.add(ln);
                                }
                            }
                            jPanel1.add(all.get(points.indexOf(a)));
                        }
                        LineStraight ls = new LineStraight((Point2D) points.get(0).getPoint().get(id11), (Point2D) evt.getPoint(), currentFigure.getNameF(), id11, currentFigure.getNameF(), id11);
                        ls.setSize(jPanel1.getWidth(), jPanel1.getHeight());
                        ls.setVisible(true);
                        jPanel1.add(ls);
                        lines.add(0, ls);
                        checkLine = true;
                        //jPanel1.add(grid);
                        //jPanel1.revalidate();
                        //jPanel1.repaint();
                    } else {
                        countp++;
                    }
                }

                //}
                // Нажате правой кнопки
                if (evt.isPopupTrigger()) {
                    // create a popup menu
                    rcMenu.show(this, oldX, oldY);
                }

                jPanel1.add(grid); // Добавляем сетку перед добавлением фигур
                jPanel1.revalidate();
                jPanel1.repaint();
                ButtonActivated();
                break;
            } else {//не попадает на фигуру
                countf++;
            }
        }

        if ((countp == pointShape.size())) { //Не попали в точку соединения
            jPanel1.removeAll();

            if (lined) {
                for (Line ln : lines) {
                    jPanel1.add(ln);
                }
            }

            if (countf != all.size()) {
                jPanel1.add(points.get(0));
                pointed = true;
            } else {
                pointed = false;
            }

            for (JComponent c : all) {
                jPanel1.add(c);
            }

            jPanel1.add(grid);
            jPanel1.revalidate();
            jPanel1.repaint();

        }

        countp = 0;

        if (countf == all.size()) {//при нажатии не попали в фигуру??
            //if (pointed) {
            // pointShape = points.get(0).getShape();//!
            oneShapePoints(0);
            //for (Shape l : pointShape) {
            //    if (l.contains(p)) {
            //        //выделение всех точек и соединение
            //        id11 = pointShape.indexOf(l);
            //        jPanel1.removeAll();
            //        for (points a : points) {
            //            jPanel1.add(a);
            //            if (lined) {
            //                for (Line ln : lines) {
            //                    jPanel1.add(ln);
            //                }
            //            }
            //            //id1=all.get(0).getId();
            //            jPanel1.add(all.get(points.indexOf(a)));
            //        }
            //       LineStraight ls = new LineStraight((Point2D) points.get(0).getPoint().get(id11),
            //                (Point2D) evt.getPoint(), ID1, id11, ID1, id11);
            //        ls.setSize(jPanel1.getWidth(), jPanel1.getHeight());
            //        ls.setVisible(true);
            //        jPanel1.add(ls);
            //        lines.add(0, ls);
            //        checkLine = true;
            //
            //        jPanel1.add(grid);
            //        jPanel1.revalidate();
            //        jPanel1.repaint();
            //        break;
            //    } else {
            //        countp++;
            //    }
            //}
            //if (countp == 4) {
            jPanel1.removeAll();
            if (lined) {
                for (Line ln : lines) {
                    jPanel1.add(ln);
                }
            }
            for (JComponent c : all) {
                jPanel1.add(c);
            }
            jPanel1.add(grid);
            jPanel1.revalidate();
            jPanel1.repaint();
            pointed = false;
            //}
            //}
        }

        touch = ss.contains(p) == true;

    }//GEN-LAST:event_jPanel1MousePressed

    private void chcord(figures b) {
        b.setXX(newX);
        b.setYY(newY);
    }

    //Перемещение мышки с нажатой кнопкой
    private void moveobj(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveobj

        figures b = all.get(0);
        ss = b.getShape();

        if (pointed == true && checkLine == false) {
            // Убираем точки текущего объекта при перетаскивании
            jPanel1.remove(points.get(0));
            jPanel1.add(new GridPanel((int) (GridPanel.GetBaseCellSize() * zoom / 100))); // Добавляем сетку перед добавлением фигур
            jPanel1.revalidate();
            jPanel1.repaint();
            pointed = false;
        }

        if (touch == true && checkLine == false) {
            // Логика перетаскивания фигуры
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            dx = -oldX + b.getXX();
            dy = -oldY + b.getYY();
            newX = evt.getX() + dx;
            newY = evt.getY() + dy;
            this.OutOfBounds();
            // Логика перетаскивания линии, если связана
            if (lined) {
                for (Line ln : lines) {
                    // for (figures f : all) {
                    double dxx;
                    double dyy;
//                        if (b.getNameF().equals(ln.getID1())) {
//                            dxx = ln.getC1().getX() - oldX;
//                            dyy = ln.getC1().getY() - oldY;
//                            Point2D.Double p1 = new Point2D.Double(dxx + evt.getX(), dyy + evt.getY());
//                            ln.setC1(p1);
//                        }
//                        if (b.getNameF().equals(ln.getID2())) {
//                            dxx = ln.getC2().getX() - oldX;
//                            dyy = ln.getC2().getY() - oldY;
//                            Point2D.Double p2 = new Point2D.Double(dxx + evt.getX(), dyy + evt.getY());
//                            ln.setC1(p2);
//                        }
                    if (b.getNameF().equals(ln.getID1())) {
//                            dxx = ln.getC1().getX() - oldX;
//                            dyy = ln.getC1().getY() - oldY;
//                            Point2D.Double p1 = new Point2D.Double(dxx + evt.getX(), dyy + evt.getY());
//                            ln.setC1(p1);
//                            dxx = ln.getC2().getX() - oldX;
//                            dyy = ln.getC2().getY() - oldY;
//                            Point2D.Double p2 = new Point2D.Double(dxx + evt.getX(), dyy + evt.getY());
//                            ln.setC1(p2);
//                        oneShapePoints(0);
//                        //Point2D.Double p2 = new Point2D.Double(pointShape.get(ln.getID11()));
//                        ln.setC1((Point2D) pointShape.get(ln.getID11()));
//                        ln.setC2((Point2D) pointShape.get(ln.getID22()));
                        dxx = ln.getC1().getX() - oldX;
                        dyy = ln.getC1().getY() - oldY;
                        Point2D.Double p1 = new Point2D.Double(dxx + evt.getX(), dyy + evt.getY());
                        ln.setC1(p1);
                        ln.arrow.x1 = p1.x;
                        ln.arrow.y1 = p1.y;
                        ln.arrow.repaint();
                        //ln.setCC();
                    }
                    if (b.getNameF().equals(ln.getID2())) {
                        dxx = ln.getC2().getX() - oldX;
                        dyy = ln.getC2().getY() - oldY;
                        Point2D.Double p2 = new Point2D.Double(dxx + evt.getX(), dyy + evt.getY());
                        ln.setC2(p2);
                        ln.arrow.x2 = p2.x;
                        ln.arrow.y2 = p2.y;
                        ln.arrow.repaint();
                    }
                    // }
                }
//                jPanel1.add(grid);
//                jPanel1.revalidate();
//                //jPanel1.repaint();
//                jPanel1.repaint();
            }

            // Устанавливаем новые координаты для фигуры
            b.setXX(newX);
            b.setYY(newY);
            b.setAbsoluteX((int) (b.getXX() / (double) zoom * 100));
            b.setAbsoluteY((int) (b.getYY() / (double) zoom * 100));
            change_idx = true;
            jPanel1.revalidate();
            jPanel1.repaint();
            oldX = evt.getX();
            oldY = evt.getY();
            ButtonActivated();
        }
        //Проверка, если ведем стрелку
        //Перерисовка стрелки
        if (checkLine == true) {
            Line l = lines.get(0);

            // 1. Проверяем попадание в площадку для соединения целевой фигуры и если попадаем то меняем курсор на крестик
            boolean targetPoint = false; //Флаг для проверки попадания на точки фигуры
            for (points ps : points) {
                ArrayList<Shape> pointShape1 = new ArrayList();
                pointShape1 = points.get(points.indexOf(ps)).getShape();
                
                for (Shape l1 : pointShape1) {
                    if (l1.contains(evt.getPoint())) {
                        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        targetPoint = true;
                    } else {
                        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    if (targetPoint) {
                        break;
                    }
                }
                if (targetPoint) {
                    break;
                }
            }
            // 1. Конец

            //this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            l.setC2((Point2D) evt.getPoint());//обновление второй точки
            l.arrow.x2 = l.getC2().getX();
            l.arrow.y2 = l.getC2().getY();
            l.arrow.repaint();
            jPanel1.add(grid);
            jPanel1.revalidate();
            jPanel1.repaint();
        }


    }//GEN-LAST:event_moveobj

    public ConvertedObject CreatorConvertObject() {
        // Создаем список для хранения объектов Figure_s
        ArrayList<Figure_s> figuresList = new ArrayList<>();

        //Сохранение файла
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
            fig.setName(f.getNameF());
            fig.setDescription(f.getDescriptionF());
            fig.setCode(f.getCodeF());
            fig.setNameNvElement(f.getNameNvElement());
            fig.setVarNvElement(f.getVarNvElement());
            fig.setSwork(String.valueOf(f.getSwork()));
            fig.setLikelihood(f.getLikelihood());
            fig.setPeriod(f.getPeriod());
            fig.setCoef(f.getCoef());
            fig.setVSelected(f.getVSelected());
            fig.setIfSelected(f.getIfSelected());
            fig.setIfNvElement(f.getIfNvElement());
            fig.setSignIfSelected(f.getSignIfSelected());
            fig.setCompareNumber(f.getCompareNumber());
            // Добавляем объект Figure_s в список
            figuresList.add(fig);
        }
        //сохраняем линии
        ArrayList<Line_s> linesList = new ArrayList<>();
        for (Line currentLine : lines) {
            Line_s ln = new Line_s();
            ln.SetID1(currentLine.getID1());
            ln.SetID2(currentLine.getID2());
            ln.SetId11(currentLine.getID11());
            ln.SetId22(currentLine.getID22());
            ln.SetC1(currentLine.getC1());
            ln.SetC2(currentLine.getC2());

            linesList.add(ln);
        }
        ConvertedObject cv = new ConvertedObject(linesList, figuresList);

        return cv;
    }

    //Сохранение файла
    public void SaveInJSON(String fn) {
        try {
            //создание объекта со всеми фигурами и связями
            ConvertedObject co = CreatorConvertObject();
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.writeValue(Paths.get(fn).toFile(), co);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(jMDIFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // *** Загружаем модель из файла JSON ***
    public void LoadFromJSON(String saveName) {
        try {
            ObjectMapper om = new ObjectMapper();
            // Читаем JSON из файла в виде строки
            Path filePath = Path.of(saveName);
            String jsonString = Files.readString(filePath);
            ConvertedObject cv = om.readValue(jsonString, ConvertedObject.class);
            // Преобразуем JSON в список объектов Figure_s
            List<Figure_s> figuresList;
            figuresList = cv.getCurrentFigures();
            jPanel1.removeAll();
            jPanel1.revalidate();
            jPanel1.repaint();
            all.clear();
            // Преобразуем объекты Figure_s в объекты Figure
            for (Figure_s fig : figuresList) {
                readSaveData rs = new readSaveData();
                all.add(rs.getElement(fig/*fig.getShape(), fig.getX_pos(), fig.getY_pos(), 
                        fig.getSize(), fig.getName(), fig.getCode(), fig.getDescription(), 
                        fig.getInVariable(), fig.getOutVariable()/*, fig.getSwork()/*, fig.getCoef(), 
                fig.getLikelihood(), fig.getPeriod()*/)); //Заносим фигуру в список используемых в проекте фигур
            }
            // Выводим результат
            for (figures b : all) { //добавляем фигуры
                b.setSize(jPanel1.getWidth(), jPanel1.getHeight());
                b.setVisible(true);
                jPanel1.add(b);
            }
            //выводим линии из объекта сохранения
            List<Line_s> lineList = cv.getCurrentLine();
            for (Line_s line_from_file : lineList) {
                LineStraight ls = new LineStraight(line_from_file.GetC1(), line_from_file.GetC2(), line_from_file.GetID1(),
                        line_from_file.GetId11(), line_from_file.GetID2(), line_from_file.GetId22());
                ls.setSize(jPanel1.getWidth(), jPanel1.getHeight());
                ls.setVisible(true);
                jPanel1.add(ls);
                lines.add(0, ls);
            }
            //if (lines.isEmpty() == false)
            lined = !lines.isEmpty();
            jPanel1.add(new GridPanel(GridPanel.GetBaseCellSize()));
            jPanel1.revalidate();
            jPanel1.repaint();
            fileName = saveName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        ButtonActivated();
    }

    //Рисование сетки / миллимитровки
    private void paintGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Определяем размер ячейки сетки
        int cellSize = grid.GetCellSize();

        // Определяем размеры панели
        int panelWidth = jPanel1.getWidth();
        int panelHeight = jPanel1.getHeight();

        // Определяем начальные координаты для рисования сетки
        int startX = 0;
        int startY = 0;

        // Отрисовываем вертикальные линии сетки
        for (int x = startX; x <= panelWidth; x += cellSize) {
            g2d.drawLine(x, 0, x, panelHeight);
        }

        // Отрисовываем горизонтальные линии сетки
        for (int y = startY; y <= panelHeight; y += cellSize) {
            g2d.drawLine(0, y, panelWidth, y);
        }
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if (change_idx) {
            Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            int dialogResult = JOptionPane.showConfirmDialog(this, "Project wasn't saved. Would you like to save your project?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(image));
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

                    if (option != JFileChooser.CANCEL_OPTION) {

                        File file1 = SaveChooser.getSelectedFile();
                        String file = null;

                        try {
                            file = file1.getCanonicalPath();
                        } catch (IOException ex) {
                            Logger.getLogger(mdi.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        fileName = file;
                        SaveInJSON(fileName);
                    }

                }
            }
        }

        change_idx = false;
        draw_idx = false;
        ButtonActivated();
    }//GEN-LAST:event_formInternalFrameClosing

    //Отпускание нажатой кнопки мыши
    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased

        //evt.consume();
        // Когда закончили перетаскивать объект и отпкстили мышку снова делаем курсор-стрелочку (по умолчанию)
        if (touch == true) { //&& checkLine == false) {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            touch = false;
        }

        countp = 0;
        if (checkLine) {//при нажатии уже начали отрисовку линий
            for (points ps : points) {
                oneShapePoints(points.indexOf(ps));
                for (Shape l : pointShape) {
                    if (l.contains(evt.getPoint())) { //курсор находится на точке соединения 
//                        id22 = pointShape.indexOf(l);
//                        id2 = all.get(points.indexOf(ps)).getId();
                        //jPanel1.removeAll();
                        lines.get(0).setC2((Point2D) ps.getPoint().get(pointShape.indexOf(l)));
                        lines.get(0).setID2(all.get(points.indexOf(ps)).getNameF());
                        figures first = null, second = null;
//                        Class first = null, second = null;
                        for (figures currentFigure : all) {
                            if (currentFigure.getNameF().equals(lines.get(0).getID1())) {
                                first = currentFigure; //.getClass();
                            }
                            if (currentFigure.getNameF().equals(lines.get(0).getID2())) {
                                second = currentFigure; //.getClass();
                            }
                        }
                        int cc = 0;// количество линий между объектами
                        for (Line ln : lines) {
                            if ((lines.get(0).getID1() == ln.getID1()) && (lines.get(0).getID2() == ln.getID2())) {
                                cc += 1;
                            }
                        }

                        //Если выполняется одно из условий то добавляем соединительную линию
                        if (((first.getClass().equals(NV.class) && second.getClass().equals(V.class) && findLinkedFigToFig("NV",second.getNameF()))
                                || //первая фигура - NV, вторая фигура - V ИЛИ
                                (first.getClass().equals(S1.class) && second.getClass().equals(V.class) && findLinkedFigToFig("S1",second.getNameF()))
                                || //первая фигура - S, вторая фигура - V ИЛИ
                                (first.getClass().equals(V.class) && second.getClass().equals(R.class) && findLinkedFigToFig("V",second.getNameF()))
                                || //первая фигура - V, вторая фигура - R ИЛИ
                                (first.getClass().equals(R.class) && second.getClass().equals(NV.class) && findLinkedFigToFig("R",second.getNameF()))
                                || //первая фигура - R, вторая фигура - NV ИЛИ
                                (first.getClass().equals(R.class) && second.getClass().equals(V.class) && findLinkedFigToFig("R",second.getNameF()))
                                || //первая фигура - R, вторая фигура - V ИЛИ
                                (first.getClass().equals(R.class) && second.getClass().equals(d.class))
                                || //первая фигура - R, вторая фигура - IF ИЛИ
                                (first.getClass().equals(O.class) && second.getClass().equals(V.class) && findLinkedFigToFig("O",second.getNameF()))
                                ||//первая фигура - O, вторая фигура - V ИЛИ
                                (first.getClass().equals(d.class))) //первая фигура - IF
                                && (cc == 1)//новая линия единственная между объектами
                                ) {
                            if (!lined) {
                                jPanel1.add(lines.get(0));
                            }
                            this.drawObjects();
                            lined = true;
                            checkLine = false;
                            //break;
                        } else {
                            lines.remove(0); //Если соединение не из обобренного списка то линию не создаем (бахаем заготовку)
                        }

                    }
                    if (!checkLine) {
                        break; //Если сделали соединение то заканчиваем поиск точек для соединения                  
                    }
                }
                if (!checkLine) {
                    break; //Если сделали соединение то заканчиваем поиск точек для соединения          
                }
            }
            if (lines.size()!=0){
                if (lines.get(0).getID1().equals(lines.get(0).getID2())) {
                    lines.remove(0); //Если не попали в конечный элемент то линию не создаем, а бахаем заготовку
                }
            }
            //Перерисовываем
            jPanel1.removeAll();
            if (lined) {
                for (Line ln : lines) {
                    jPanel1.add(ln);
                }
            }
            for (JComponent c : all) {
                jPanel1.add(c);
            }
            jPanel1.add(grid);
            jPanel1.revalidate();
            jPanel1.repaint();
            checkLine = false; //устанавливаем флаг окончания рисования
            countp = 0;
        }

    }//GEN-LAST:event_jPanel1MouseReleased
    private boolean findLinkedFigToFig(String startShape, String endName){
        //поиск присоединенных классов к нужной фигуре
        boolean isFirst = true;
        for (Line ln : lines){
            if (ln.getID2().equals(endName)){
                for (figures fig : all){
                    if (ln.getID1().equals(fig.getNameF())){
                        if (fig.getClass().toString().replace("class figure.","").equals(startShape)){ //Если такой класс есть, то отмена
                            if (isFirst){
                                isFirst = false;
                            }else{
                                return false;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("3");
        return true; //Если нету, то продолжаем
    }
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        // очищаем все
        //mdi.jMenuIt
        //emClear.clearActionPerformed(evt);
        //jPanel1.clear

        //нужно сделать вызов из jPanel чтобы не переписывать несколько раз
        //jPanel1.removeAll();
        //jPanel1.repaint();
        //all.clear();
        //Удаление всего, отрисовка сетки
        jPanel1.removeAll();
        jPanel1.add(grid);
        jPanel1.repaint();
        all.clear();
        lines.clear();

        zoom = 100;
        //ButtonActivated();
//        
        // jPanel1.removeAll();
        // all.clear();
        // lines.clear();
        // points.clear();
        // pointShape.clear();

        cleaned = true;

        // grid.SetCellSize((int) ((GridPanel.GetBaseCellSize() * zoom) / 100));
        // int HorizontalScrollBarScale = (int) (jScrollPane1.getHorizontalScrollBar().getMaximum() * zoom / 100);
        // int VerticalScrollBarScale = (int) (jScrollPane1.getVerticalScrollBar().getMaximum() * zoom / 100);
        // jScrollPane1.getHorizontalScrollBar().setMaximum(HorizontalScrollBarScale);
        // jScrollPane1.getVerticalScrollBar().setMaximum(VerticalScrollBarScale);
        // jSize.setText(String.format("%d", zoom) + '%');
        // jPanel1.add(grid);
        // jPanel1.revalidate();
        // jPanel1.repaint();
        // 

    }//GEN-LAST:event_clearActionPerformed

    private void CleanNumbers(boolean c) {
        s = k;
        if (c == true) {
            S1 Sn = new S1(x, y, (int) (s * zoom / 100));
            Sn.idChange();
            Sn = null;

        }
    }

    private void jScrollPane1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jScrollPane1MouseWheelMoved

    }//GEN-LAST:event_jScrollPane1MouseWheelMoved

    private void jScrollPane1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jScrollPane1CaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jScrollPane1CaretPositionChanged

    //При изменении масштаба учитывать, что должны меняться и координаты линии
    private void zminusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zminusActionPerformed

        if (zoom > 30) {
            zoom = zoom - 20;
        } else {
            return;
        }
        jPanel1.remove(points.get(0));
        // конец работы Иванова А.А.
        s = (int) Math.round(k * zoom / 100);

        // Определяем центр видимой области
        //double centerX = jPanel1.getVisibleRect().getWidth() / 2.0;
        //double centerY = jPanel1.getVisibleRect().getHeight() / 2.0;
        for (figures b : all) {
            b.setS(s);

            // абсолютное смещение от центра видимой области
            //double dx = b.getAbsoluteX() - centerX;
            //double dy = b.getAbsoluteY() - centerY;
            //double newX = (centerX + dx) * zoom/100;
            //double newY = (centerY + dy) * zoom/100;
            b.setXX((int) (b.getAbsoluteX() * zoom / 100));
            b.setYY((int) (b.getAbsoluteY() * zoom / 100));

            // Рассчитываем правую и нижнюю границы видимой области панели
            int visibleWidth = jScrollPane1.getViewport().getViewRect().width;
            int visibleHeight = jScrollPane1.getViewport().getViewRect().height;
            int rightBorder = jScrollPane1.getHorizontalScrollBar().getValue() + visibleWidth;
            int bottomBorder = jScrollPane1.getVerticalScrollBar().getValue() + visibleHeight;

            // Рассчитываем текущие размеры панели
            double panelWidth = (jPanel1.getPreferredSize().width);
            double panelHeight = (jPanel1.getPreferredSize().height);

            // Рассчитываем новые размеры панели с учетом масштаба
            int newPanelWidth = (int) (jPanel1.getWidth() * zoom / 100);
            int newPanelHeight = (int) (jPanel1.getHeight() * zoom / 100);

            // Устанавливаем новые размеры панели
            jPanel1.setPreferredSize(new Dimension(newPanelWidth, newPanelHeight));

            // Проверяем, выходит ли фигура за границы видимой области
            boolean outOfBoundsX = newX > rightBorder;
            boolean outOfBoundsY = newY > bottomBorder;

            if (outOfBoundsX || outOfBoundsY) {
                // Рассчитываем на сколько фигура выходит за границы
                int distanceX = outOfBoundsX ? Math.max(0, (int) newX - rightBorder) + 100 : 0;
                int distanceY = outOfBoundsY ? Math.max(0, (int) newY - bottomBorder) + 100 : 0;

                // Проверяем, выходит ли фигура за границы размера панели           
                boolean outOfPanelBounds = newX > panelWidth || newY > panelHeight;

                if (outOfPanelBounds) {
                    // Увеличиваем размеры панели на это расстояние
                    jPanel1.setPreferredSize(new Dimension(
                            jPanel1.getPreferredSize().width + distanceX,
                            jPanel1.getPreferredSize().height + distanceY
                    ));
                }
            }
        }

        //изменение линий
        for (Line currentLine : lines) {
            Point2D p1 = new Point2D.Double(currentLine.getC1().getX() * zoom / (zoom + 20), currentLine.getC1().getY() * zoom / (zoom + 20));
            currentLine.setC1(p1);
            Point2D p2 = new Point2D.Double(currentLine.getC2().getX() * zoom / (zoom + 20), currentLine.getC2().getY() * zoom / (zoom + 20));
            currentLine.setC2(p2);
            currentLine.arrow.x1 = p1.getX();
            currentLine.arrow.y1 = p1.getY();
            currentLine.arrow.x2 = p2.getX();
            currentLine.arrow.y2 = p2.getY();
        }

        // изменение сетки
        grid.SetCellSize((int) ((GridPanel.GetBaseCellSize() * zoom) / 100));

        // изменение скроллеров
        int HorizontalScrollBarScale = (int) (jScrollPane1.getHorizontalScrollBar().getMaximum() * zoom / 100);
        int VerticalScrollBarScale = (int) (jScrollPane1.getVerticalScrollBar().getMaximum() * zoom / 100);

        jScrollPane1.getHorizontalScrollBar().setMaximum(HorizontalScrollBarScale);
        jScrollPane1.getVerticalScrollBar().setMaximum(VerticalScrollBarScale);

        jSize.setText(String.format("%d", zoom) + '%');

        // Перерисовываем панель
        jPanel1.revalidate();
        jPanel1.repaint();

    }//GEN-LAST:event_zminusActionPerformed


    private void zplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zplusActionPerformed
        //начало работы Иванова А.А. максимальное значение масштаба и шаг

        // начало работы Иванова А.А. максимальное значение масштаба и шаг
        if (zoom < 180) {
            zoom = zoom + 20;
        } else {
            return;
        }
        jPanel1.remove(points.get(0));
        // конец работы Иванова А.А.
        s = (int) Math.round((k * zoom) / 100);

        // Определяем центр видимой области
        //int centerX = (int) Math.round(jPanel1.getVisibleRect().getWidth() / 2);
        //int centerY = (int) Math.round(jPanel1.getVisibleRect().getHeight() / 2);
        for (figures b : all) {
            b.setS(s);

            // абсолютное смещение от центра видимой области
            //double dx = b.getAbsoluteX() - centerX;
            //double dy = b.getAbsoluteY() - centerY;
            //double dx = b.getAbsoluteX() - centerX;
            //double dy = b.getAbsoluteY() - centerY;
            //double newX = (centerX + dx) * zoom;
            //double newY = (centerY + dy) * zoom;
            //double newX = (centerX + dx ) * zoom + 20;
            //double newY = (centerY + dy) * zoom + 20;
            //double newX = (b.getAbsoluteX()) * zoom ;
            //double newY = (b.getAbsoluteY())* zoom;
            int newX = b.getAbsoluteX();
            int newY = b.getAbsoluteY();

            b.setXX((int) ((b.getAbsoluteX()) * zoom / 100));
            b.setYY((int) ((b.getAbsoluteY()) * zoom / 100));

            // Рассчитываем правую и нижнюю границы видимой области панели
            int visibleWidth = jScrollPane1.getViewport().getViewRect().width;
            int visibleHeight = jScrollPane1.getViewport().getViewRect().height;
            int rightBorder = jScrollPane1.getHorizontalScrollBar().getValue() + visibleWidth;
            int bottomBorder = jScrollPane1.getVerticalScrollBar().getValue() + visibleHeight;

            // Рассчитываем текущие размеры панели
            int panelWidth = (int) (jPanel1.getPreferredSize().width);
            int panelHeight = (int) (jPanel1.getPreferredSize().height);

            // Рассчитываем новые размеры панели с учетом масштаба
            int newPanelWidth = (int) ((jPanel1.getWidth() * zoom) / 100);
            int newPanelHeight = (int) ((jPanel1.getHeight() * zoom) / 100);

            // Устанавливаем новые размеры панели
            jPanel1.setPreferredSize(new Dimension(newPanelWidth, newPanelHeight));

            // Проверяем, выходит ли фигура за границы видимой области
            boolean outOfBoundsX = newX > rightBorder;
            boolean outOfBoundsY = newY > bottomBorder;

            if (outOfBoundsX || outOfBoundsY) {
                // Рассчитываем на сколько фигура выходит за границы
                int distanceX = outOfBoundsX ? Math.max(0, (int) newX - rightBorder) + 100 : 0;
                int distanceY = outOfBoundsY ? Math.max(0, (int) newY - bottomBorder) + 100 : 0;
            }
        }

        //изменение линий
        for (Line currentLine : lines) {
            Point2D p1 = new Point2D.Double(currentLine.getC1().getX() * zoom / (zoom - 20), currentLine.getC1().getY() * zoom / (zoom - 20));
            currentLine.setC1(p1);
            Point2D p2 = new Point2D.Double(currentLine.getC2().getX() * zoom / (zoom - 20), currentLine.getC2().getY() * zoom / (zoom - 20));
            currentLine.setC2(p2);
            currentLine.arrow.x1 = p1.getX();
            currentLine.arrow.y1 = p1.getY();
            currentLine.arrow.x2 = p2.getX();
            currentLine.arrow.y2 = p2.getY();
        }

        // изменение сетки
        grid.SetCellSize((int) ((GridPanel.GetBaseCellSize() * zoom) / 100));

        // изменение скроллеров
        int HorizontalScrollBarScale = (int) ((jScrollPane1.getHorizontalScrollBar().getMaximum() * zoom) / 100);
        int VerticalScrollBarScale = (int) ((jScrollPane1.getVerticalScrollBar().getMaximum() * zoom) / 100);

        jScrollPane1.getHorizontalScrollBar().setMaximum(HorizontalScrollBarScale);
        jScrollPane1.getVerticalScrollBar().setMaximum(VerticalScrollBarScale);

        jSize.setText(String.format("%d", zoom) + '%');
        // Конец работы Иванова А.А.

        // Перерисовываем панель
        jPanel1.revalidate();
        jPanel1.repaint();


    }//GEN-LAST:event_zplusActionPerformed

    static BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    // Create a new blank cursor.
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            cursorImg, new Point(0, 0), "blank cursor");
    private void jSizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSizeMouseEntered
        setCursor(blankCursor);
    }//GEN-LAST:event_jSizeMouseEntered

    private void jSizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSizeMouseExited
        //setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jSizeMouseExited

    private void DeletteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletteActionPerformed
//        удаление выделеного объекта и связей с ним              
       ArrayList<Integer> array = new ArrayList();
        for (Line ln : lines) {
            if ((ln.getID1() == all.get(0).getNameF()) || (ln.getID2() == all.get(0).getNameF())) {
                array.add(lines.indexOf(ln));
            }
        }
        
        Collections.reverse(array);
        for (int a:array){
            lines.remove(a);
        }
        all.remove(0);
        array.clear();
        // Перерисовываем панель
        jPanel1.removeAll();
        this.drawObjects();
    }//GEN-LAST:event_DeletteActionPerformed

    private void jMenuItemDeletteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeletteActionPerformed
//        удаление выделеного объекта и связей с ним              
        ArrayList<Integer> array = new ArrayList();
        for (Line ln : lines) {
            if ((ln.getID1() == all.get(0).getNameF()) || (ln.getID2() == all.get(0).getNameF())) {
                array.add(lines.indexOf(ln));
            }
        }
        
        Collections.reverse(array);
        for (int a:array){
            lines.remove(a);
        }
        all.remove(0);
        array.clear();
        // Перерисовываем панель
        jPanel1.removeAll();
        this.drawObjects();
    }//GEN-LAST:event_jMenuItemDeletteActionPerformed

    private void jPanel2moveobj(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2moveobj
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2moveobj

    private void jPanel2Resizing(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jPanel2Resizing
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2Resizing

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseReleased

    private void jScrollPane2MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jScrollPane2MouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseWheelMoved

    private void jScrollPane2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jScrollPane2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2CaretPositionChanged

    private void zminus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zminus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zminus1ActionPerformed

    private void jSize1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSize1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jSize1MouseEntered

    private void jSize1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSize1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jSize1MouseExited

    private void zplus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zplus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zplus1ActionPerformed

    private void jInternalFrame1formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_jInternalFrame1formInternalFrameActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_jInternalFrame1formInternalFrameActivated

    private void jInternalFrame1formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_jInternalFrame1formInternalFrameClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_jInternalFrame1formInternalFrameClosing

    private void jMenuItemGenDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGenDescActionPerformed
        GenerateDescription();
    }//GEN-LAST:event_jMenuItemGenDescActionPerformed

    private void closeDescrButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeDescrButActionPerformed
        descrShowDialog.dispose();
    }//GEN-LAST:event_closeDescrButActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved

        // 1. --- Если при выделении объекта попадаем в площадку для соединения то меняем курсор на крестик ---
        for (Shape l : pointShape) {
            if (l.contains(evt.getPoint())) {
                this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                break;
            } else {
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
        // 1. --- конец ---
    }//GEN-LAST:event_jPanel1MouseMoved

    public static void copyToClipboard(String text) { //сохранение в буфер обмена
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private void copyDescrButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyDescrButActionPerformed
        copyToClipboard(textDescription.getText());
    }//GEN-LAST:event_copyDescrButActionPerformed

    private void rCodeActivatorButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rCodeActivatorButActionPerformed
        SaveInRFile();    
    }//GEN-LAST:event_rCodeActivatorButActionPerformed

    private void copyDescrButRCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyDescrButRCodeActionPerformed
        copyToClipboard(textDescriptionRCode.getText());
    }//GEN-LAST:event_copyDescrButRCodeActionPerformed

    private void toRCodeButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toRCodeButActionPerformed
        //генерация R кода
        newPrecodeGenerator preCode = new newPrecodeGenerator(all);
        RTranslatorClass newRTC = new RTranslatorClass(preCode.getPrecodeString()); //Создаем объект для перевода 
        newRTC.addString(textDescription.getText()); //Передаем текст с псевдокодом
        textDescriptionRCode.setText(newRTC.getStringRCode()); //Записываем R код
        
        
        rCodeActivatorBut.setEnabled(true); //активируем кнопки сохранения
        copyDescrButRCode.setEnabled(true);
    }//GEN-LAST:event_toRCodeButActionPerformed

    private void GenerateDescription() {
        //Генерация псевдокогда
        generatorObj genOb = new generatorObj(CreatorConvertObject());
        String selfMaidCode = genOb.generateString();
        textDescription.setText(selfMaidCode); //записываем псевдокод
        textDescriptionRCode.setText(""); //обнуляем код R
        rCodeActivatorBut.setEnabled(false); //диактивируем кнопки сохранения
        copyDescrButRCode.setEnabled(false);
        
        descrShowDialog.setDefaultCloseOperation(descrShowDialog.DISPOSE_ON_CLOSE);
        descrShowDialog.pack();
        descrShowDialog.setModal(true);
        descrShowDialog.setLocationRelativeTo(this);
        descrShowDialog.setVisible(true);       
    }
    public void SaveInRFile() {     
        SaveChooser.setDialogTitle("Saving R File");// ("+fn+")");
        SaveChooser.setCurrentDirectory(new File("."));
        SaveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        SaveChooser.setFileFilter(new FileNameExtensionFilter("Event-driven Process Methodology", "R"));
        SaveChooser.approveSelection();
        int option = SaveChooser.showSaveDialog(this);
        if(option != JFileChooser.CANCEL_OPTION) {
            File file1 = SaveChooser.getSelectedFile();
            String file = null;
            try {
                file = file1.getCanonicalPath()+".R";
            } catch (IOException ex) {
                Logger.getLogger(mdi.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(file);
            CreateRCode.saveInFile(textDescriptionRCode.getText(),file);
        }
    }
    private void OutOfBounds() {
        // Рассчитываем правую и нижнюю границы видимой области панели
        int visibleWidth = jScrollPane1.getViewport().getViewRect().width;
        int visibleHeight = jScrollPane1.getViewport().getViewRect().height;
        int rightBorder = jScrollPane1.getHorizontalScrollBar().getValue() + visibleWidth;
        int bottomBorder = jScrollPane1.getVerticalScrollBar().getValue() + visibleHeight;

        // Рассчитываем текущие размеры панели
        int panelWidth = jPanel1.getWidth();
        int panelHeight = jPanel1.getHeight();

        // Проверяем, выходит ли фигура за границы видимой области
        boolean outOfBoundsX = newX > rightBorder;
        boolean outOfBoundsY = newY > bottomBorder;

        if (outOfBoundsX || outOfBoundsY) {
            // Рассчитываем на сколько фигура выходит за границы
            int distanceX = outOfBoundsX ? Math.max(0, newX - rightBorder) + 100 : 0;
            int distanceY = outOfBoundsY ? Math.max(0, newY - bottomBorder) + 100 : 0;

            // Проверяем, выходит ли фигура за границы размера панели           
            boolean outOfPanelBounds = newX > panelWidth || newY > panelHeight;

            if (outOfPanelBounds) {
                // Увеличиваем размеры панели на это расстояние
                jPanel1.setPreferredSize(new Dimension(
                        jPanel1.getPreferredSize().width + distanceX,
                        jPanel1.getPreferredSize().height + distanceY
                ));

                // Перерисовываем панель
                jPanel1.revalidate();
            }
        }
    }

    // Активация / деактивация кнопки Save 
    public void ButtonActivated() {
        if (change_idx) {
            mdi.Save.setEnabled(true);
            mdi.jButtonSave.setEnabled(true);
            mdi.Saveas.setEnabled(true);
            mdi.Code_Generation.setEnabled(true);
        } else {
            mdi.Save.setEnabled(false);
            mdi.jButtonSave.setEnabled(false);
            mdi.Saveas.setEnabled(false);
            mdi.Code_Generation.setEnabled(false);            
        }

        if (draw_idx) {
            mdi.jButtonS.setEnabled(true);
            mdi.jButtonV.setEnabled(true);
            mdi.jButtonNV.setEnabled(true);
            mdi.jButtonR.setEnabled(true);
            mdi.jButtonIF.setEnabled(true);
            mdi.jButtonO.setEnabled(true);
            mdi.jMenuItemS.setEnabled(true);
            mdi.jMenuItemV.setEnabled(true);
            mdi.jMenuItemR.setEnabled(true);
            mdi.jMenuItemNV.setEnabled(true);
            mdi.jMenuItemIF.setEnabled(true);
            mdi.jMenuItemO.setEnabled(true);
            mdi.jMenuItemClear.setEnabled(true);

        } else {
            mdi.jButtonS.setEnabled(false);
            mdi.jButtonV.setEnabled(false);
            mdi.jButtonNV.setEnabled(false);
            mdi.jButtonR.setEnabled(false);
            mdi.jButtonIF.setEnabled(false);
            mdi.jButtonO.setEnabled(false);
            mdi.jMenuItemS.setEnabled(false);
            mdi.jMenuItemV.setEnabled(false);
            mdi.jMenuItemR.setEnabled(false);
            mdi.jMenuItemNV.setEnabled(false);
            mdi.jMenuItemIF.setEnabled(false);
            mdi.jMenuItemO.setEnabled(false);
            mdi.jMenuItemClear.setEnabled(false);

        }
    }
    
    String[] names;
    public String[] GetNames() {
        String[] toReturn = new String[names.length];
        for (int i = 0; i < names.length; i++)
            toReturn[i] = names[names.length-1-i];
        return toReturn;
    }
    
    public double[][] PageRank(int iteration, double delta, double d) {
        //инициализация матрицы
        double[][] matrix = new double[all.size()][all.size()];
        int size = matrix.length;
        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                matrix[column][row] = 0;
            }
        }
        
        //заполнение матрицы связями        
        for (Line line : lines) {
            int start = 0; 
            int end = 0;
            int counter = 0;
            for (figures figure : all) {
                if (line.getID1().equals(figure.getNameF())) {
                    start = counter;
                    break;
                }
                counter++;
            }
            counter = 0;
            for (figures figure : all) {
                if (line.getID2().equals(figure.getNameF())) {
                    end = counter;
                    break;
                }
                counter++;
            }
            matrix[start][end] = 1;
        }
        
        //нерабочий вариант заполнения матрицы связями
//        for (Line line : lines) {
//            matrix[line.getID22()-1][line.getID11()-1] = 1;
//            }
        
        //объединение объектов V NV R   
        ArrayList<figures> fig = (ArrayList<figures>) all.clone();
        names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = fig.get(i).getNameF();
        }
        boolean flag;
        do {
            flag = false;
            for (int nv = 0; nv < size; nv++) {
                if (nv >= fig.size() || !fig.get(nv).getNameF().startsWith("NV")) continue;
                for (int v = 0; v < size; v++) {
                    if (v >= fig.size() || matrix[nv][v] != 1.0 || !fig.get(v).getNameF().startsWith("V")) continue;
                    for (int r = 0; r < size; r++) {
                        if (r >= fig.size() || matrix[v][r] != 1.0 || !fig.get(r).getNameF().startsWith("R")) continue;
                        matrix = MergeMatrix(matrix, r, nv, v);

                        // Создаём новый массив имён
                        String[] newNames = new String[size - 2];
                        int counter = 0;

                        for (int i = 0; i < size; i++) {
                            if (i == v || i == r) continue;
                            if (i == nv) {
                                newNames[counter] = names[nv] + "->" + names[v] + "->" + names[r];
                            } else {
                                newNames[counter] = names[i];
                            }
                            counter++;
                        }
                        names = newNames;

                        if (v > nv) {
                            fig.remove(v);
                            fig.remove(nv);
                        } else {
                            fig.remove(nv);
                            fig.remove(v);
                        }
                        size -= 2;
                        flag = true;
                        break;
                    }
                    if (flag) break;
                }
                if (flag) break;
            }
        } while (flag);
        
        
        //меняем на количество ссылок
        for (int row = 0; row < size; row++) {
            int counter = 0;
            for (int column = 0; column < size; column++) {
                if (matrix[column][row] == 1)
                    counter++;
            }
            for (int column = 0; column < size; column++) {
                if (matrix[column][row] == 1)
                    matrix[column][row] = 1.0/counter;
            }
        }
        
        //заменяем 0 столбцы на 1/N
        for (int j = 0; j < size; j++) {
            int columnSum = 0;
            for (int i = 0; i < size; i++) {
                columnSum += matrix[i][j];
            }
            if (columnSum == 0) {
                for (int i = 0; i < size; i++) {
                    matrix[i][j] = 1.0/size;
                }
            }
        }
        
        //собираем в формулу
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = d*matrix[i][j]+(1-d)/size;
            }
        }
        
        double[][] pageRank;
        //вычисление за n итераций
        if (iteration > 0)
            pageRank = MultiplyMatrixIteration(matrix, iteration);
        
        //вычисление с заданной точностью
        else
            pageRank = MultiplyMatrixUntilDelta(matrix, delta);
        
        return pageRank;
    }
    
    protected double[][] MergeMatrix(double[][] matrix, int row1, int row2, int row3) {
        int n = matrix.length;
        double[][] newMatrix = new double[n-2][n-2];
        
        
        for (int i = 0; i < n; i++) {
            if (i != row1) {
                matrix[i][row1] = Math.max(matrix[i][row1], Math.max(matrix[i][row2], matrix[i][row3]));
                matrix[i][row2] = Math.max(matrix[i][row1], Math.max(matrix[i][row2], matrix[i][row3]));
                matrix[i][row3] = Math.max(matrix[i][row1], Math.max(matrix[i][row2], matrix[i][row3]));
            }
        }
        
        for (int j = 0; j < n; j++) {
            if (j != row1 && j != row2 && j != row3) {
                matrix[row1][j] = Math.max(matrix[row1][j], Math.max(matrix[row2][j], matrix[row3][j]));
                matrix[row2][j] = Math.max(matrix[row1][j], Math.max(matrix[row2][j], matrix[row3][j]));
                matrix[row3][j] = Math.max(matrix[row1][j], Math.max(matrix[row2][j], matrix[row3][j]));
            }
        }
        
        int newRow = 0;
        for (int i = 0; i < n; i++) {
            if (i == row2 || i == row3)
                continue;
            int newCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == row2 || j == row3)
                    continue;
                newMatrix[newRow][newCol] = matrix[i][j];
                newCol++;
            }
            newRow++;
        }
        
        
        
        return newMatrix;
    }


    
    protected double[][] MultiplyMatrixIteration(double[][] matrix, int iteration) {
        int size = matrix.length;
        //инициализация вектора
        double[] vector = new double[size];
        for (int i = 0; i < size; i++)
            vector[i] = 1;
        double[] newVector = new double[size];
        double[][] pageRank = new double[iteration][size];
        
        //вычисление
        for (int iter = 0; iter < iteration; iter++) {
            for (int i = 0; i < size; i++) {
                newVector[i] = 0;
                for (int j = 0; j < size; j++) {
                    newVector[i] += matrix[i][j] * vector[j];
                }
            }
            vector = newVector.clone();
            pageRank[iter] = vector.clone();
        }
        return pageRank;
    }
    
    protected double[][] MultiplyMatrixUntilDelta(double[][] matrix, double delta) {
        int size = matrix.length;
        double[] newVector = new double[size];
        double maxDifference;
        double[] vector = new double[size];
        for (int i = 0; i < size; i++)
            vector[i] = 1;

        // Список для хранения всех вектор-столбцов
        List<double[]> history = new ArrayList<>();

        do {
            maxDifference = 0;

            for (int i = 0; i < size; i++) {
                newVector[i] = 0;
                for (int j = 0; j < size; j++) {
                    newVector[i] += matrix[i][j] * vector[j];
                }
                maxDifference = Math.max(maxDifference, Math.abs(newVector[i] - vector[i]));
            }

            vector = newVector.clone();
            history.add(vector.clone());

        } while (maxDifference >= delta);

        // Преобразуем список в массив
        double[][] pageRank = new double[history.size()][size];
        for (int i = 0; i < history.size(); i++) {
            pageRank[i] = history.get(i);
        }

        return pageRank;
    }

    
    //определитель матрицы
    protected double Determinant(double[][] matrix) {
        double determinant = 0;
        if (matrix.length == 1)
            return matrix[0][0];
        else {
            for (int j = 0; j < matrix.length; j++) {
                double[][] minor_matrix = MatrixMinor(matrix, 0, j);
                determinant += matrix[0][j] * Math.pow(-1, j) * Determinant(minor_matrix);
            }
            return determinant;
        }
    }
    
    //минор матрицы
    protected double[][] MatrixMinor(double[][] matrix, int rowToDelete, int columnToDelete) {
        int size = matrix.length;
        double[][] minorMatrix = new double[size - 1][size - 1];

        int newRow = 0;
        for (int i = 0; i < size; i++) {
            if (i == rowToDelete) continue;
        
            int newCol = 0;
            for (int j = 0; j < size; j++) {
                if (j == columnToDelete) continue;
            
                minorMatrix[newRow][newCol] = matrix[i][j];
                newCol++;
            }
            newRow++;
        }
        return minorMatrix;
    }
    
    //транспонирование матрицы
    protected double[][] Transposition(double[][] matrix) {
        double[][] newMatrix = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }
        
        return newMatrix;
    }
    
    //обратная матрица
    protected double[][] InverseMatrix(double matrix[][], double determinant) {
        double[][] inverseMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                inverseMatrix[i][j] = Determinant(MatrixMinor(matrix, j, i))/determinant;
            }
        }
        return inverseMatrix;
    }
    
    public int СyclomaticСomplexity() {
        int E = lines.size();
        int N = all.size();
        int P = Component();
        int M = E - N + 2*P;
        return M;
    }
    
    boolean[] used;    
    protected int Component() {
        used = new boolean[all.size()];
        int component = 0;
        for (int i = 0; i < all.size(); i++) {
            if (!used[i]) {
                DFS(i);
                component++;
            }
        }
        return component;
    }
    
    
    protected void DFS(int index) {
        used[index] = true;
        String nodeName = all.get(index).getNameF();

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).getID1().equals(nodeName)) {
                for (int j = 0; j < all.size(); j++) {
                    if (all.get(j).getNameF().equals(lines.get(i).getID2()) && !used[j]) {
                        DFS(j);
                    }
                }
            } else if (lines.get(i).getID2().equals(nodeName)) { // Добавляем обратное направление
                for (int j = 0; j < all.size(); j++) {
                    if (all.get(j).getNameF().equals(lines.get(i).getID1()) && !used[j]) {
                        DFS(j);
                    }
                }
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser SaveChooser;
    private java.awt.Canvas canvas1;
    private javax.swing.JButton closeDescrBut;
    private javax.swing.JButton copyDescrBut;
    private javax.swing.JButton copyDescrButRCode;
    private javax.swing.JDialog descrShowDialog;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItemClear;
    private javax.swing.JMenuItem jMenuItemDelette;
    private javax.swing.JMenuItem jMenuItemGenDesc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jSize;
    private javax.swing.JTextField jSize1;
    private javax.swing.JButton rCodeActivatorBut;
    private javax.swing.JPopupMenu rcMenu;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPaneR;
    private javax.swing.JTextArea textDescription;
    private javax.swing.JTextArea textDescriptionRCode;
    private javax.swing.JButton toRCodeBut;
    private javax.swing.JButton zminus;
    private javax.swing.JButton zminus1;
    private javax.swing.JButton zplus;
    private javax.swing.JButton zplus1;
    // End of variables declaration//GEN-END:variables

}
