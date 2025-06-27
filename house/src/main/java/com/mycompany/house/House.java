package com.mycompany.house;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class House extends JFrame{
    public House(){
       setLocation(300 , 100);
        setSize(600 , 500);
        setBackground(Color.white);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
     @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D p = (Graphics2D) g;
       drawGround(p);
       drawHouse(p);
       drawTriangle(p);
        drawDoor(p);
       drawWindow(p);
       drawTree(p);
    }
    
    private void drawGround(Graphics2D p){
        p.setColor(Color.black);
        p.setStroke(new BasicStroke(5));
        p.draw(new Line2D.Double(0, 420, 600, 420));
        
    }
            
    private void drawHouse(Graphics2D p){
        p.setBackground(Color.black);
        p.setStroke(new BasicStroke(5));
        Rectangle2D house=new Rectangle2D.Double(299,200,251,215);
        p.draw(house);
        }
            
   private void  drawTriangle(Graphics2D p){
        p.setBackground(Color.black);
        p.setStroke(new BasicStroke(5));
        Path2D Triangle=new Path2D.Double();
        Triangle.moveTo(425, 100);
        Triangle.lineTo(300,200);
        Triangle.lineTo(550, 200);
        Triangle.closePath();
        p.draw(Triangle);
   }         
            
   private void  drawDoor(Graphics2D p){
       p.setColor(new Color(139,69,19));
       p.setStroke(new BasicStroke(5));
       Rectangle2D door=new Rectangle2D.Double(405,320,50,100);
       p.fill(door);
       p.setColor(Color.black);
       p.fillOval(445,370,7,7);
       p.draw(door);
   }
            
   private void   drawWindow(Graphics2D p){
     p.setColor(new Color(139,69,19));
       p.setStroke(new BasicStroke(5));
       Rectangle2D right=new Rectangle2D.Double(480,230,50,50);   
       Rectangle2D left=new Rectangle2D.Double(330,230,50,50); 
       p.fill(right);
       p.fill(left);
       p.setColor(Color.black);
       p.draw(new Line2D.Double(480,230,530,280));
       p.draw(new Line2D.Double(330,230,380,280));
       p.draw(right);
       p.draw(left);
   }
   
   private void drawTree(Graphics2D p){
        p.setBackground(Color.black);
        p.setStroke(new BasicStroke(5));
        p.draw(new Line2D.Double(100,250,100,420));
        
        p.setColor(new Color(139,69,19));
        for(int i=1;i<=8;i++){
            double z=Math.toRadians(i*45);
            double x=100+Math.cos(z)*40;
            double y=250+Math.sin(z)*40;
            p.draw(new Line2D.Double(100,250,x,y));
        }
   }
   
    public static void main(String[] args) {
        House h=new House();
    }
}
