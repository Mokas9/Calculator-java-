package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


class Panel extends JPanel {
    
    private final JTextField display;
    private final JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;
    
    public Panel() {
        
	setLayout(new BorderLayout());
 
	result = 0;
	lastCommand = "=";
	start=true;
 
	display = new JTextField("0");
	display.setEnabled(true);
	add(display, BorderLayout.NORTH);
 
	ActionListener insert = new InsertAction();
	ActionListener command = new CommandAction();
 
	panel = new JPanel();
	panel.setLayout(new GridLayout(4, 4, 5, 5));
 
	addButton("7", insert);
	addButton("8", insert);
	addButton("9", insert);
	addButton("/", command);
 
	addButton("4", insert);
	addButton("5", insert);
	addButton("6", insert);
	addButton("*", command);
 
	addButton("1", insert);
	addButton("2", insert);
	addButton("3", insert);
	addButton("-", command);
 
	addButton("0", insert);
	addButton(".", insert);
	addButton("=", command);
	addButton("+", command);

	add(panel, BorderLayout.CENTER);
    }
    
    private void addButton(String label, ActionListener listener) {
        
	JButton button = new JButton(label);
	button.addActionListener(listener);
	panel.add(button);
    }
    
    private class InsertAction implements ActionListener {
        
        @Override
	public void actionPerformed(ActionEvent event) {
            
            String input = event.getActionCommand();
		if(start) {
                    display.setText("");
                    start = false;
		}
		display.setText(display.getText() + input);
	}
    }
    
    private class CommandAction implements ActionListener {
        
        @Override
	public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            if(start) {
		if(command.equals("-")) {
                    display.setText(command);
                    start = false;
		}else {
                    lastCommand = command;
                }
            }else {
		calculate(Double.parseDouble(display.getText()));
		lastCommand = command;
		start=true;
            }
	}	
    }
    
    public void calculate(double x){
        switch (lastCommand) {
            case "+":
                result += x;
                break;
            case "-":
                result -= x;
                break;
            case "*":
                result *= x;
                break;
            case "/":
                result /= x;
                break;
            case "=":
                result = x;
                break;
            default:
                break;
        }
	display.setText("" + result);
    }
}