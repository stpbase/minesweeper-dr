/**
 * 
 */
package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author diana
 *
 */
public class Board {
	
	// Datenfeld mainFrame definieren
	JFrame mainFrame;
	
	//leeres Feld definieren
	JButton field;
	
	//Panels definieren
	JPanel infoPanel;
	JPanel mainPanel;
	JPanel fieldPanel;
	
	//undefinierte Grössen
	private int width;
	private int height;
	private int rows;
	private int cols;
	
	public Board ()
	{
		// Erzeugung MainFrame
		mainFrame = new JFrame("MineSweeper DR");
		
		//Erzeugung leeres Feld
		field = new JButton("");
		
		//Erzeugung Panels
		infoPanel = new JPanel();
		mainPanel = new JPanel();	
		fieldPanel = new JPanel();
		rows = 5;
		cols = 5;
		width = 1200;
		height = 1200;
	}	
	
	//GUI Anzeige
	public void illustrate()
	{
		//Initialisierung Frame und GUI Elemente
		mainFrame.setBackground(Color.orange);
		mainFrame.setResizable(false);
		//Schliessen der Applikation
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Layout der Panels festlegen
		infoPanel.setLayout(new FlowLayout ());
		mainPanel.setLayout(new BorderLayout(20, 20));
		fieldPanel.setLayout(new GridLayout(rows, cols));
		
		//leere Felder initialisieren

		for(int i = 0; i < rows*cols; i++) {
			JButton field = new JButton(" ");
			fieldPanel.add(field);
		}
		
		//Anordnung der Panels
		mainPanel.add(fieldPanel, BorderLayout.CENTER);
		mainFrame.add(infoPanel, BorderLayout.NORTH);
		mainFrame.add(mainPanel, BorderLayout.SOUTH);
		mainFrame.setSize(width, height);
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		Board gui = new Board();
		gui.illustrate();
	}
}
