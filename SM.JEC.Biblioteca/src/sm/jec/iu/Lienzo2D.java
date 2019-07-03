/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.jec.iu;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author jescobar
 */
public class Lienzo2D extends javax.swing.JPanel {    
    private Point2D.Float pAux;
    private Color color;
    private boolean fill;
    private ModoDibujo modo;
    private ArrayList<Shape> vShape;
    private Shape figura; // ultima figura en crearse
    
    private float grosor;
    private Stroke trazo;
    private Paint relleno;
    private Composite composicion; 
    private AffineTransform at; 
    private Font fuente; 
    private RenderingHints render; 
    private Shape clipArea;
    private boolean editar;
    private boolean alisar;
    private boolean transparencia;
    /**
     * Creates new form Lienzo2D
     */
    public Lienzo2D() {
        this.pAux = new Point2D.Float(-10, -10);
        this.color = Color.black;
        this.fill = false;
        this.modo = ModoDibujo.LINEA;
        
        this.grosor = 1.0f;
        this.trazo = new BasicStroke(grosor);
        this.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        this.composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        this.vShape = new ArrayList();
        this.editar = false;
        this.alisar = false;
        this.transparencia = false;
        initComponents();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        //Atributos
        g.setColor(this.getColor());
        this.setAttributes(g2d);
        //Dibujar formas        
        if(this.fill){
            for(Shape s: vShape){
                g2d.fill(s);
            }
        }else{
            for(Shape s: vShape){
                g2d.draw(s);
            }
        }
    }
    
    void setAttributes(Graphics2D g2d){
        this.trazo = new BasicStroke(grosor);
        g2d.setStroke(trazo);
        if(alisar)
            this.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        else
            this.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHints(render);
        if(transparencia)
            this.composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        else
            this.composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        g2d.setComposite(composicion);
    }

    void createShape(){
        switch(this.modo){
            case PUNTO: Line2D.Float punto = new Line2D.Float(pAux, pAux);
                        vShape.add((Shape) punto);
                    break;
            
            case LINEA: Line2D.Float linea = new Line2D.Float(pAux, pAux);
                            figura = linea;
                            vShape.add(linea);
                    break;
            
            case RECTANGULO: Rectangle2D.Float rect = new Rectangle2D.Float(pAux.x, pAux.y, 0, 0);
                            figura = rect;
                            vShape.add(rect);
                    break;
            case ELIPSE: Ellipse2D.Float elipse = new Ellipse2D.Float(pAux.x, pAux.y, 0, 0);
                            figura = elipse;
                            vShape.add(elipse);
                    break;
            default: throw new IllegalArgumentException();
        }
    }
    
    void updateShape (java.awt.event.MouseEvent evt){
        switch(this.modo){
            case PUNTO:     
//                Point2D.Float punto = (Point2D.Float)figura;
//                            punto.setLocation(pAux);
                    break;
            
            case LINEA:     Line2D.Float linea = (Line2D.Float)figura;
                            linea.setLine(pAux, evt.getPoint());
                    break;
            
            case RECTANGULO: Rectangle2D.Float r = (Rectangle2D.Float)figura;
                            r.setFrameFromDiagonal(pAux, evt.getPoint());
                    break;
            case ELIPSE:    Ellipse2D.Float elipse = (Ellipse2D.Float)figura;
                            elipse.setFrameFromDiagonal(pAux, evt.getPoint());
                    break;
            default: throw new IllegalArgumentException();
        }
    }
    
    private Shape getSelectedShape(Point2D p){
        for(Shape s:vShape)
            if(s.contains(p))
                return s;
        return null;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FormListener formListener = new FormListener();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(formListener);
        addMouseListener(formListener);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener {
        FormListener() {}
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == Lienzo2D.this) {
                Lienzo2D.this.formMousePressed(evt);
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }

        public void mouseDragged(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == Lienzo2D.this) {
                Lienzo2D.this.formMouseDragged(evt);
            }
        }

        public void mouseMoved(java.awt.event.MouseEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if(!editar){
            this.pAux.x = evt.getPoint().x;
            this.pAux.y = evt.getPoint().y;
            createShape();
            this.repaint();
        }else{
            Shape f = this.getSelectedShape(evt.getPoint());
//            if(f != null)
                
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(!editar){
            updateShape(evt);
            this.repaint();
        }
    }//GEN-LAST:event_formMouseDragged

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public ModoDibujo getModo() {
        return modo;
    }

    public void setModo(ModoDibujo modo) {
        this.modo = modo;
    }

    public Stroke getTrazo() {
        return trazo;
    }

    public void setTrazo(Stroke trazo) {
        this.trazo = trazo;
    }

    public Paint getRelleno() {
        return relleno;
    }

    public void setRelleno(Paint relleno) {
        this.relleno = relleno;
    }

    public Composite getComposicion() {
        return composicion;
    }

    public void setComposicion(Composite composicion) {
        this.composicion = composicion;
    }

    public AffineTransform getAt() {
        return at;
    }

    public void setAt(AffineTransform at) {
        this.at = at;
    }

    public Font getFuente() {
        return fuente;
    }

    public void setFuente(Font fuente) {
        this.fuente = fuente;
    }

    public RenderingHints getRender() {
        return render;
    }

    public void setRender(RenderingHints render) {
        this.render = render;
    }

    public Shape getClipArea() {
        return clipArea;
    }

    public void setClipArea(Shape clipArea) {
        this.clipArea = clipArea;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public boolean isAlisar() {
        return alisar;
    }

    public void setAlisar(boolean alisar) {
        this.alisar = alisar;
    }

    public boolean isTransparencia() {
        return transparencia;
    }

    public void setTransparencia(boolean transparencia) {
        this.transparencia = transparencia;
    }

    public float getGrosor() {
        return grosor;
    }

    public void setGrosor(float grosor) {
        this.grosor = grosor;
    }

    public ArrayList<Shape> getvShape() {
        return vShape;
    }

    public void setvShape(ArrayList<Shape> vShape) {
        this.vShape = vShape;
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
