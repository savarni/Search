package applet;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class TextSearch extends JFrame{
	
	private JButton jb;
	private JTextField text1, text2;
	
	public TextSearch(){
		super("Title");
		setLayout(new FlowLayout());
				
		text1 = new JTextField("Enter Zip Code");
		text1.setEditable(false);
		text1.setBorder(null);
		add(text1);
		
		text2 = new JTextField(50);
		add(text2);
		
		jb = new JButton("Search");
		add(jb);
		
		thehandler handler = new thehandler();
		text1.addActionListener(handler);
		text2.addActionListener(handler);
		jb.addActionListener(handler);
			
	}

	private class thehandler implements ActionListener{

		ArrayList<String> sb;
		
		public void actionPerformed(ActionEvent event){
			
			String string = "";
			String zip = text2.getText();
			if(event.getSource()==jb)
				string = String.format("Zip Code entered: "+zip, event.getActionCommand());
				
			searchIt(zip);
			try
			{
			JOptionPane.showMessageDialog(null, sb.get(0)+"\n"+sb.get(1)+"\n"+sb.get(2)+"\n"+sb.get(3)+"\n"+sb.get(4)+"\n"+sb.get(5)+"\n"+sb.get(6));
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "There are no events");
			}
		}
		
		public void searchIt(String temp){
			
			try {
				sb = new ArrayList<>();
		        Scanner file = new Scanner(new File("hfhLocationsZipCodes.txt"));

		        LinkedList<String> twoPrevLines = new LinkedList<>();
		        while(file.hasNextLine())           
		        {
		            String line = file.nextLine();
		            if(line.indexOf(temp) != -1)
		            {
		                sb.add(twoPrevLines.get(0));
		                sb.add(twoPrevLines.get(2));
		                sb.add(line);
		                file.nextLine();
		                sb.add(file.nextLine());
		                file.nextLine();
		                sb.add(file.nextLine());
		                file.nextLine();
		                sb.add(file.nextLine());
		                file.nextLine();
		                sb.add(file.nextLine());
		                
		            }
		            else
		            {
		            	twoPrevLines.addLast(line);
		                if (twoPrevLines.size() >4) 
		                {
		                   twoPrevLines.removeFirst();
		                }
		            }
		        }
				file.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

}
