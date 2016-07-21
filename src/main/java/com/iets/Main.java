package com.iets;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.iets.ding.Life;

public class Main
{
    public static void main(String ... args) {
    	Main main = new Main();

    	GridPane gridPane = new GridPane(50,50,10);
    	//GridPane gridPane = new GridPane(8,8,25);
    	//GridPane gridPane = new GridPane(16,16,25);
    	//GridPane gridPane = new GridPane(25,25,25);
    	main.createWindow(gridPane);

    	//new CharMatrix().doeJeDing(new PaneCanvas(gridPane));
    	//new Unicorn().doeJeDing(new PaneCanvas(gridPane));
    	new Life().doeJeDing(new PaneCanvas(gridPane));
    	//new ComplexLife().doeJeDing(new PaneCanvas(gridPane));
    	//new Clock().doeJeDing(gridPane);

//    	GridPane gridPane2 = new GridPane(25,25,10);
//    	main.createWindow(gridPane2);
//    	new Clock().doeJeDing(gridPane2);

    }

    private void createWindow(JPanel panel) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }

        JFrame frame = new JFrame("GridPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
