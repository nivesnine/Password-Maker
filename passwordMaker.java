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
	
	private JLabel bdayL, websiteL, lengthL;
	private JTextField bdayTF, websiteTF, lengthTF, passwordTF;
	private JButton createB, exitB;
	
	//Button handlers:
	private CalculateButtonHandler cbHandler;
	private ExitButtonHandler ebHandler;
	
	public passwordMaker() {
	bdayLabel    = new JLabel("Your Bday(MMDDYY): ", SwingConstants.RIGHT);
	websiteLabel = new JLabel("Website: ", SwingConstants.RIGHT);
	lengthLabel  = new JLabel("Password length: ", SwingConstants.RIGHT);
		
		
		bdayTextFeild     = new JTextField(10);
		websiteTextFeild  = new JTextField(10);
		lengthTextFeild   = new JTextField(10);
		passwordTextFeild = new JTextField(30);
		
		//Spcify handlers for each button and 
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
		pane.add(bdayTextFeild);
		pane.add(websiteLabel);
		pane.add(websiteTextFeild);
		pane.add(lengthLabel);
		pane.add(lengthTextFeild);
		pane.add(createButton);
		pane.add(exitButton);
		pane.add(passwordTextFeild);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class CalculateButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			Integer bday   = Integer.valueOf(bdayTextFeild.getText()); 
			String website = websiteTextFeild.getText();
			Integer length = Integer.valueOf(lengthTextFeild.getText());
			
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
			
			passwordTF.setText(password);
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
		passwordMaker passwordObj = new passwordMaker();
	}
	
}
