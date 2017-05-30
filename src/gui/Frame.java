package gui;

import javax.swing.JFrame;

public class Frame extends JFrame{
    public Frame() {
	setTitle("Calculator");
	Panel panel = new Panel();
	add(panel);
	pack();
        setSize(200, 200);
        super.setLocationRelativeTo(panel);
    }
}