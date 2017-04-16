package passwordMaker;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PasswordMaker extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH             = 400;
	private static final int HEIGHT            = 300;
	
	private JLabel bdayLabel, websiteLabel, lengthLabel;
	private JTextField bdayTextField, websiteTextField, lengthTextField, passwordTextField;
	private JButton createButton, exitButton;
	
	//Button handlers:
	private CalculateButtonHandler createButtonHandler;
	private ExitButtonHandler exitButtonHandler;
	
	public PasswordMaker() {
	bdayLabel    = new JLabel("Your Bday(MMDDYY): ", SwingConstants.RIGHT);
	websiteLabel = new JLabel("Website: ", SwingConstants.RIGHT);
	lengthLabel  = new JLabel("Password length: ", SwingConstants.RIGHT);
		
		
		bdayTextField     = new JTextField(10);
		websiteTextField  = new JTextField(10);
		lengthTextField   = new JTextField(10);
		passwordTextField = new JTextField(30);
		
		//Specify handlers for each button and 
		//add (register) ActionListeners to each button.
		createButton        = new JButton("Create");
		createButtonHandler = new CalculateButtonHandler();
		
		createButton.addActionListener(createButtonHandler);
		
		exitButton        = new JButton("Exit");
		exitButtonHandler = new ExitButtonHandler();
		
		exitButton.addActionListener(exitButtonHandler);
		
		setTitle("Password Maker");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(6, 6));
		
		//Add things to the pane in the order you
		//want them to appear (left to right, top to bottom)
		pane.add(bdayLabel);
		pane.add(bdayTextField);
		pane.add(websiteLabel);
		pane.add(websiteTextField);
		pane.add(lengthLabel);
		pane.add(lengthTextField);
		pane.add(createButton);
		pane.add(exitButton);
		pane.add(passwordTextField);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class CalculateButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			Integer bday   = Integer.valueOf(bdayTextField.getText()); 
			String website = websiteTextField.getText();
			Integer length = Integer.valueOf(lengthTextField.getText());
			
			String password = "";
			
			List<String> letterset = Arrays.asList("a","b","c",
					"d","e","f","g","h","i","j","k",
					"l","m","n","o","p","q","r","s",
					"t","u","v","w","x","y","z","A","B","C",
					"D","E","F","G","H","I","J","K",
					"L","M","N","O","P","Q","R","S",
					"T","U","V","W","X","Y","Z",
					"1","2","3","4","5","6","7",
					"8","9","0");
			
			int websiteToNum = 0;
			website.replaceAll("[^a-zA-Z0-9]", "");
			
			for(int i = 0; i < website.length(); i++){
				websiteToNum = websiteToNum + 
					letterset.indexOf(website.substring(i,i+1));
			}
			
			int start = bday + websiteToNum + length;
			
			Random r = new Random();
			r.setSeed(start);
			
			for(int i = 0; i<length;i++){
				int letter = r.nextInt(61);
				password = password + letterset.get(letter);
			}
			
			passwordTextField.setText(password);
		}
	}
	
	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		PasswordMaker passwordObj = new PasswordMaker();
	}
	
}

