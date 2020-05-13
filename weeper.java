/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mayýntarlasý;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class weeper implements ActionListener {

    JFrame frame;
    JButton reset;
    JButton[][] box;
    int[][] mayin;
    Container grid;
    final int mayýnlar = 10;
    int a = 0;
    int b = 0;
    public weeper() {
    	// initiliaze
    	grid = new Container();
    	mayin = new int[10][10];
    	box = new JButton[10][10];
    	reset = new JButton("Tekrar Oyna");
    	frame = new JFrame("MayýnTarlasý");
    	
    	
    	frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());
        frame.add(reset, BorderLayout.NORTH);
        reset.addActionListener(this);
        grid.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                box[i][j] = new JButton();
                box[i][j].addActionListener(this);
                grid.add(box[i][j]);
            }
        }
        frame.add(grid, BorderLayout.CENTER);
        mayinata();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

   public static void main(String[] args) {
       new weeper();
    }

    public void mayinata() {
        ArrayList<Integer> diz = new ArrayList<Integer>();
        for (int i = 0; i < mayin.length; i++) {
            for (int j = 0; j < mayin[0].length; j++) {
                diz.add(i * 100 + j);
            }
        }
        mayin = new int[10][10];
        for (int i = 0; i < 10; i++) {
            int bomp = (int) (Math.random() * diz.size());
            mayin[diz.get(bomp) / 100][diz.get(bomp) % 100] = mayýnlar;
            diz.remove(bomp);

        }

        for (int i = 0; i < mayin.length; i++) {
            for (int j = 0; j < mayin[0].length; j++) {

                if (mayin[i][j] != mayýnlar) {
                    int komsumayýn = 0;
                    if (i > 0 && j > 0 && mayin[i - 1][j - 1] == mayýnlar) {
                        komsumayýn++;

                    }
                    if (j > 0 && mayin[i][j - 1] == mayýnlar) {
                        komsumayýn++;
                    }
                    if (i < mayin.length - 1 && j < mayin[0].length - 1 && mayin[i + 1][j + 1] == mayýnlar) {
                        komsumayýn++;
                    }
                    mayin[i][j] = komsumayýn;
                }
            }
        }

    }

    public void theend() {
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                if (box[i][j].isEnabled()) {
                    if (mayin[i][j] != mayýnlar) {
                        box[i][j].setText(mayin[i][j] + "");
                        box[i][j].setEnabled(false);
                    } else {
                        box[i][j].setText("X");
                        box[i][j].setEnabled(false);
                    }
                }
            }
        }
    }

    public void kontrol(ArrayList<Integer> sorun) {

        if (sorun.isEmpty()) {
           
        } else {
        	for(Object obj : sorun){
                int a = sorun.get(0);
                int b = sorun.get(0);
        	}
            sorun.remove(0);
            //sol tarafý kontrol ediyoruz
            if(mayin[a][b]==0){ // sýfýrlarý kontrol ediyoruz
                if(a>0 && b>0){ // sýfýr deðilse komþularý kontrol ediyoruz
                	String str = String.format("",mayin[a-1][b-1]);
                    box[a-1][b-1].setText(str);
                    box[a-1][b-1].setEnabled(false);
                    if(mayin[a-1][b-1]==0){
                       
                        sorun.add((a-1)*100 +(b-1));
                    }
                    
                }
                // üst kýsmý kontrol ediyoruz
                //iki yada üç bomba komþularýný kontrol ediyoruz
                // a sabit kalýyor b deðiþiyor
                if (b>0){
                    box[a][b-1].setText(mayin[a][b-1]+ "");
                    box[a][b-1].setEnabled(false);
                     if(mayin[a][b-1]==0){
                       
                        sorun.add(a*100 +(b-1));
                    }
                }
                // a bir artma durumu
                // sað taraf için
                if(a< mayin.length-1 && b>0 ){
                    box[a+1][b-1].setText(mayin[a+1][b-1]+ "");
                    box[a+1][b-1].setEnabled(false);
                    if(mayin[a+1][b-1]==0){
                       
                        sorun.add((a+1)*100 +(b-1));
                    }
                    
                }
                // sol taraf için b ayný kalýyor
                if(a>0 ){ 
                    box[a-1][b].setText(mayin[a-1][b]+ "");
                    box[a-1][b].setEnabled(false);
                    if(mayin[a-1][b]==0){
                       
                        sorun.add((a-1)*100 +b);
                    }
                    
                }
                
                
                // sað taraf için
                if(a< mayin.length-1  ){
                    box[a+1][b].setText(mayin[a+1][b]+ "");
                    box[a+1][b].setEnabled(false);
                    if(mayin[a+1][b]==0){
                       
                        sorun.add((a+1)*100 +b);
                    }
                    
                }
                // solt alt köþe için b ve mayýnlar karýþaltýrýlmasý
                if(a>0 && b < mayin[0].length-1){ 
                    box[a-1][b+1].setText(mayin[a-1][b+1]+ "");
                    box[a-1][b+1].setEnabled(false);
                    if(mayin[a-1][b+1]==0){
                       
                        sorun.add((a-1)*100 +(b+1));
                    }
                    
                }
                
                // alt köþe için
                if (b <mayin[0].length-1 ){
                    box[a][b+1].setText(mayin[a][b+1]+ "");
                    box[a][b+1].setEnabled(false);
                     if(mayin[a][b+1]==0){
                       
                        sorun.add(a*100 +(b+1));
                    }
                }
               
                //alt  sað köþe için
                if(a< mayin.length-1 && b <mayin[0].length-1 ){
                    box[a+1][b+1].setText(mayin[a+1][b+1]+ "");
                    box[a+1][b+1].setEnabled(false);
                    if(mayin[a+1][b+1]==0){
                       
                        sorun.add((a+1)*100 +(b+1));
                    }
                    
                }
            }
            if(sorun != null)
            	kontrol(sorun);
        }
  
    }
    
    public void win(){
        boolean win =true;
        for(int i= 0; i<mayin.length;i++){
        for(int j=0 ;  j<mayin[0].length;j++){
            if(mayin[i][j]!=mayýnlar && box[i][j].isEnabled()==true){
                win=false;}
            }
        }
        
        if(win==true){
            JOptionPane.showMessageDialog(frame,"Kazandýn");
        }
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(reset)) {
            for (int i = 0; i < box.length; i++) {
                for (int j = 0; j < box[0].length; j++) {
                    box[i][j].setEnabled(true);
                    box[i][j].setText("");
                }
            }

            mayinata();
        } else {
            for (int i = 0; i < box.length; i++) {
                for (int j = 0; j < box[0].length; j++) {
                    if (e.getSource().equals(box[i][j])) {
                        if (mayin[i][j] == mayýnlar) {
                            box[i][j].setForeground(Color.RED);
                            box[i][j].setText("X");
                            theend();

                        } 
                        else if(mayin [i][j]==0) {
                        
                        box[i][j].setText(mayin[i][j]+"");
                        box[i][j].setEnabled(false);
                        ArrayList<Integer>sorun= new ArrayList <>();
                        sorun.add(i*100+j);
                        kontrol(sorun);
                        win();
                        
                    }
                        
                        else {
                            box[i][j].setText(mayin[i][j] + "");
                            box[i][j].setEnabled(false);
                            win();
                        }
                    }
                }
            }
        }
    }

}

