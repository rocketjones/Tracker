package current;


//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

//import javax.naming.NameNotFoundException;
import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

//import java.awt.Graphics;
//import java.awt.Graphics2D;


public class GUI1 extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	
	final JFrame front = new JFrame();
	final JFrame addFrame = new JFrame();
	final JFrame singleFrame = new JFrame();
	final JFrame groupFrame = new JFrame();
	final JPanel dayTotals = new JPanel();
	final JPanel remTotals = new JPanel();
	JButton addButton = new JButton("ADD");
	
	
	/** VARIABLES
	 * 
	 */
	private static double cal;
	private static double prot;
	private static double sug;
	
	//these will be scanned in?
	private static final double CALTOTAL = 2000;
	private static final double PROTEIN = 62;
	private static final double SUG = 20;

	
	static ArrayList<Double> totlist = new ArrayList<Double>();
	static ArrayList<Double> remlist = new ArrayList<Double>();
	
	
	
	
	public static void main(String[] args)
	{	
		new GUI1().setVisible(true);
	}
	
	
	
	public static String getTotList()
	{
		return totlist.toString();
	}
	public static String getRemList()
	{
		return remlist.toString();
	}
	
	
	
	public void addTo() throws FileNotFoundException
	{
	    try {
	    	File inFile = new File("output.txt");
	        Scanner sc = new Scanner(inFile);

	        double c = sc.nextDouble();
	        double p = sc.nextDouble();
	        double s = sc.nextDouble();
	        // (test read) System.out.println(c+"" + p + s);
	        
	        cal+=c;
	        prot+=p;
	        sug+=s;
	        
	        if(totlist.size()==0)
	        {
	        totlist.add(cal);
	        totlist.add(prot);
	        totlist.add(sug);
	        }
	        else{
	        totlist.set(0,cal);
	        totlist.set(1,prot);
	        totlist.set(2,sug);
	        }
	        if(remlist.size()==0)
	        {
	        remlist.add(CALTOTAL-cal);
	        remlist.add(PROTEIN-prot);
	        remlist.add(SUG-sug);
	        }
	        else{
	        remlist.set(0,CALTOTAL-cal);
	        remlist.set(1,PROTEIN-prot);
	        remlist.set(2,SUG-sug);
	        }
	        
	        System.out.println(totlist.toString());
	        System.out.println(remlist.toString());
	        
	        sc.close();
	    } 
	    catch (NoSuchElementException e2) {
	        e2.printStackTrace();
	    }
	}
	
	public void writeFile() throws IOException
	{
		File outFile = new File("output.txt");
		FileWriter fWriter = new FileWriter(outFile);
		PrintWriter pWriter = new PrintWriter(fWriter);
		pWriter.println("210 5 1");
		pWriter.println("690 10 10");
		pWriter.close();
	}
	
	public void eraseMain()
	{	
		front.remove(dayTotals);
		front.remove(remTotals);
		front.remove(addButton);
	}
	
	public void makeMain(){
		
		groupFrame.dispose();
		singleFrame.dispose();

		
		//MAIN frame, size, title, center screen
		front.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		front.setLayout(new FlowLayout());
		front.setTitle("Tracker");
		front.setSize(500,500);	
		front.setLocationRelativeTo(null);
		
		//MAIN JPanel to display day totals
		JLabel totalsName = new JLabel("Total:"+getTotList());
		dayTotals.add(totalsName);

		
		//MAIN JPanel "remtotals" to display remaining
		JLabel remLabel = new JLabel("Remain: "+getRemList());
		remTotals.add(remLabel);

		
		//MAIN Button to ADD item
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				front.dispose();
				addFrame.setVisible(true);
			}
		});
		
		//MAIN components into frame & make visible
		front.add(dayTotals);
		front.add(remTotals);
		front.add(addButton);
		front.repaint();
		front.setVisible(true);
	}
	
	
	
	
	public GUI1()
	{

		makeMain();
		
		//"ADD" frame, size, title, center screen
		addFrame.setLayout(new FlowLayout());
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addFrame.setTitle("ADD");
		addFrame.setSize(500,500);
		addFrame.setLocationRelativeTo(null);
		
		//"SINGLE" frame, size, title, center screen
		singleFrame.setLayout(new FlowLayout());
		singleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		singleFrame.setTitle("SINGLE");
		singleFrame.setSize(500,500);
		singleFrame.setLocationRelativeTo(null);
		
		//"GROUP" frame, size, title, center screen
		groupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		groupFrame.setTitle("GROUP");
		groupFrame.setSize(500,500);
		groupFrame.setLocationRelativeTo(null);
		
		
		
		//ADD window "SINGLE" button 
		JButton singleButton = new JButton("SINGLE");
		singleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				addFrame.dispose();
				singleFrame.setVisible(true);
			}
		});
		
		//ADD window "GROUP" button
		JButton groupButton = new JButton("GROUP");
		groupButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				addFrame.dispose();
				groupFrame.setVisible(true);
			}
		});
		
		///SINGLE window "bread" button
		JButton breadButton = new JButton("bread");
		breadButton.addActionListener(this);
		
		//ADD window "NEW" button
		JButton newButton = new JButton("NEW");
		//ActionListener
		
		//Add buttons
		addFrame.add(singleButton);
		addFrame.add(groupButton);
		addFrame.add(newButton);
		
		singleFrame.add(breadButton);				
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//if (e.getActionCommand().equals("bread")) 
		//{
			try
			{
				writeFile();
				addTo();
				eraseMain();
				makeMain();
			}
			catch(FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
			catch(IOException e3)
			{
				e3.printStackTrace();
			}
		//}
	}
}