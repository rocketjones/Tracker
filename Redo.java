package current;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;

/*Eventually
 * 
 * check for "CAT=ON" in file
 * Prompt for categories
 * write "CAT=ON"
 * write categories to file
 */

public class Redo extends JFrame implements ActionListener {

	// Variable declarations
	private static int cal = 0;
	private static int calAdd;

	public static void main(String[] args) throws IOException {

		onStart();
		Redo gui = new Redo();

	}

	public static void onStart() throws IOException {

		// Declarations
		File data = new File("data.txt");
		// Create file if doesn't exist
		data.createNewFile();
		Scanner in = new Scanner(data);
		FileWriter fWriter = new FileWriter(data,true);
		PrintWriter out = new PrintWriter(fWriter);

		// If file is empty, write cal to it
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		if (br.readLine() == null) {
			out.println(data.length());
			out.close();
			br.close();
		} else {
			// Read text file and update cal value
			try {
				while (in.hasNextLine()) {
					int i = in.nextInt(); // (does not consume white space)
					cal = i;
					String cat = in.nextLine();
					// System.out.println(cat);
				}
				in.close();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void onEnter() throws IOException {
		
		File data = new File("data.txt");
		FileWriter fWriter = new FileWriter(data,true);
		PrintWriter out = new PrintWriter(fWriter);
		out.println(cal);
		out.close();
		
	}

	// Component declarations
	JButton addButton = new JButton("ADD");
	JButton enterButton = new JButton("ENTER");
	JButton resetButton = new JButton("reset");
	JLabel howmuch = new JLabel("How much? ");
	JTextField calText = new JTextField();
	Label frontLabel = new Label("Calories: " + cal);

	// ADD LATER ArrayList<String> cats = new ArrayList<String>();

	// class constructor
	Redo() {
		// Window (JFrame) settings
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Tracker");
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		// Add components & component attributes
		add(frontLabel);
		add(addButton);
		add(resetButton);
		addButton.addActionListener(this);
		enterButton.addActionListener(this);
		resetButton.addActionListener(this);
		calText.setColumns(5);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {

			// reset front screen
			addButton.setVisible(false);
			frontLabel.setVisible(false);
			resetButton.setVisible(false);

			// Show "add" screen
			add(howmuch);
			add(calText);
			add(enterButton);
			howmuch.setVisible(true);
			enterButton.setVisible(true);
			calText.setVisible(true);
		}

		if (e.getSource() == enterButton) {

			// reset "add" screen
			howmuch.setVisible(false);
			enterButton.setVisible(false);
			calText.setVisible(false);

			// Perform operations and update
			calAdd = Integer.parseInt(calText.getText());
			cal += calAdd;
			frontLabel.setText("Calories: " + cal);
			calText.setText("");
			
			//Write new cal value to text file "data.txt"
			try{
			onEnter();
			}catch (IOException e2){
				e2.printStackTrace();
			}

			// Go back to front screen
			addButton.setVisible(true);
			frontLabel.setVisible(true);
			resetButton.setVisible(true);
		}

		if (e.getSource() == resetButton) {
			cal = 0;
			frontLabel.setText("Calories: " + cal);
			
			//Write new cal value to text file "data.txt"
			try{
			onEnter();
			}catch (IOException e2){
				e2.printStackTrace();
			}
		}
	}

}
