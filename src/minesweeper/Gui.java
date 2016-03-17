package minesweeper;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui
{
	//Abstaende zwischen Panels
	private static final int BORDER_SPACING = 20;
	
	//Groesse der Felder
	public static final int FIELD_HEIGHT = 60;
	public static final int FIELD_WIDTH = 60;
	
	//Definierung der einzelnen Komponente
	private JFrame m_mainFrame;
	private GridLayout m_fieldGrid;
	private JPanel m_fieldPanel;
	private Minesweeper m_startedGame;
	
	//Textanzeige definieren
	private JTextField m_minesFound;
	private JTextField m_livesLeft;
	
    private static MinesweeperButton m_fields[][];
	
	public Gui (Minesweeper minesweeper)
	{
		//Minesweeper start
		m_startedGame = minesweeper;
		
		//Erzeugung MainFrame
		m_mainFrame = new JFrame("MineSweeper DR");
		
		//Groesse des Fensters nicht veraenderbar
		m_mainFrame.setResizable(false);
		
		//Beenden beim schliessen des Fensters
		m_mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Neue Panels erstellen
		m_fieldGrid = new GridLayout();
		m_fieldPanel = new JPanel();
		m_fieldPanel.setLayout(m_fieldGrid);
		
		//Anordnung der Panels
		m_mainFrame.add(m_fieldPanel, BorderLayout.CENTER);
		m_mainFrame.add(createInfoPanel(), BorderLayout.NORTH);
		m_mainFrame.add(createMainPanel(), BorderLayout.SOUTH);
		
	}
	
	//Setze groesse des Fensters
	public void setFieldPanelSize(int width, int height)
	{
		m_mainFrame.setSize(width, height+100);
		m_fieldPanel.setSize(width, height);
	}
	
	//Neue Felder erstellen
	public void createFields(int rows, int cols) 
	{
		m_fieldPanel.removeAll();
		m_fieldGrid.setRows(rows);
		m_fieldGrid.setColumns(cols);
	
	//generierung Zufallszahl	
        Random gen = new Random();

        //Felder initialisieren
        MinesweeperButton currButton = null;
        int minesLeft = 0;
		for(int y = 1; y <= rows; y++) 
		{
			for (int x = 1; x <= cols; x++) 
			{
				currButton = new MinesweeperButton(x, y);
				currButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	MinesweeperButton btn = (MinesweeperButton) e.getSource();
			            if (btn.isMine())
			            {
			            	btn.setIcon(MinesweeperButton.MINE_ICON);
			            	m_startedGame.minusLives(1);
			            	m_startedGame.minusMines(1);
			            }
			            else
			            {
			            	int minesAround = countMines (btn.getGridLocation());
			            	if (minesAround == 0)
			            	{
			            		exposeNeighbors(btn);
			            	}
			            	btn.setIcon(minesAround);
			            }
			        }
			    });
				if (gen.nextDouble() < Minesweeper.MINES_PERCENTAGE) 
				{
					currButton.makeMine();
					minesLeft++;
				}
				m_fieldPanel.add(currButton);
			}
		}
		m_startedGame.setMinesLeft(minesLeft);
		System.out.println("Minen gefunden:" + minesLeft);
	}
	
	//Rückgabe Position des gesuchten Buttons
	private MinesweeperButton findButtonAt (int x, int y)
	{
		MinesweeperButton currButton;
		for (Component comp : m_fieldPanel.getComponents())
		{
			if (comp instanceof MinesweeperButton)
			{
				currButton = (MinesweeperButton) comp;
				if (currButton.getGridLocation().equals(new Point(x, y)))
				{
					return currButton;
				}
			}
		}
		return null;
	}
	
	//Rückgabe ob es eine Mine ist oder nicht
	private boolean isMineAt (int x, int y)
	{
		return findButtonAt(x, y).isMine();
	}
	
	//Schaut ob es innerhalb oder ausserhalb des Feldes ist
	private boolean isOutside (int x, int y)
	{
		int xyCount = Minesweeper.LEVELS.get(m_startedGame.getDifficulty());
		return (x <= 0 || y <= 0 || x > xyCount || y > xyCount);
	}
	
	//Anzahl Minen werden gezählt
	public int countMines(Point point) 
	{
		int xPos = (int) point.getX();
		int yPos = (int) point.getY();
		int lookX = xPos;
		int lookY = yPos;

		int count = 0;
        for (int x=-1; x <= 1; x++)
        {
        	for (int y=-1; y <= 1; y++)
        	{
        		lookX = x+xPos;
        		lookY = y+yPos;
        		if (!isOutside(lookX, lookY) && isMineAt (lookX, lookY))
        		{
        			count++;
        		}
        	}
        }
        
        System.out.println(count);
        return count;
    }

	//Aufdeckung der leeren Buttons	
    public void exposeNeighbors(MinesweeperButton btn) {
		int xPos = (int) btn.getGridLocation().getX();
		int yPos = (int) btn.getGridLocation().getY();
		int lookX = xPos;
		int lookY = yPos;
		MinesweeperButton lastFoundBtn;
		
        for (int x=-1; x <= 1; x++)
        {
        	for (int y=-1; y <= 1; y++)
        	{
        		lookX = x+xPos;
        		lookY = y+yPos;
        		if (!isOutside(lookX, lookY) && !isMineAt (lookX, lookY))
        		{
        			lastFoundBtn = findButtonAt(lookX, lookY);
        			if (lastFoundBtn.isEnabled())
        			{
        				lastFoundBtn.doClick();
        			}
        		}
        	}
        }
    }
	
		//Infoanzeige fuer Minen
	public void setMinesFound(int countMines)
	{
		m_minesFound.setText(String.valueOf(countMines));
	}
	
		//Infoanzeige fuer Leben
	public void setLivesLeft(int livesLeft)
	{
		m_livesLeft.setText(String.valueOf(livesLeft));
	}	
	
	private JPanel createInfoPanel()
	{
		//Anordnungs Layout
		FlowLayout flowLayout = createFlowLayout(FlowLayout.LEFT);
		
		//Infopanel erstellen
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(flowLayout);

		//Informationen für Infopanel Minen
		JLabel minesFoundLabel = new JLabel();
		minesFoundLabel.setText("Minen gefunden");
		m_minesFound = new JTextField("00", 2);
		m_minesFound.setEditable(false);
		
		//Informationen für Infopanel Leben
		JLabel livesLeftLabel = new JLabel();
		livesLeftLabel.setText("Noch übrige Leben");
		m_livesLeft = new JTextField("00", 2);
		m_livesLeft.setEditable(false);
		
		//hinzufuegen der Komponenten auf den Infopanel
		infoPanel.add(minesFoundLabel);
		infoPanel.add(m_minesFound);
		infoPanel.add(livesLeftLabel);
		infoPanel.add(m_livesLeft);
		
		//Ausgabe des Infopanels
		return infoPanel;
	}
	
	
	private JPanel createMainPanel()
	{
		//Anordnungs Layout
		FlowLayout flowLayout = createFlowLayout(FlowLayout.RIGHT);
		
		//Mainpanel erstellen
		JPanel mainPanel = new JPanel();	
		mainPanel.setLayout(flowLayout);
				
		//Initialisierung Startknopf
		JButton startGameBtn = new JButton("Start");
		startGameBtn.addActionListener(m_startedGame);
		
		//Dropdown erstellen
		JComboBox<String> difficultyLevelComboBox = new JComboBox<>(
			Minesweeper.LEVELS.keySet().toArray(new String[Minesweeper.LEVELS.size()])
		);
		difficultyLevelComboBox.setSelectedIndex(0);
		difficultyLevelComboBox.addActionListener(m_startedGame);
		
		mainPanel.add(difficultyLevelComboBox);
		mainPanel.add(startGameBtn);
		
		return mainPanel;
	}

	//Layout settings fuer Mainpanel
	private FlowLayout createFlowLayout(int alingment) {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(10);
		flowLayout.setAlignment(alingment);
		
		return flowLayout;
	}
	
	// Sichtbar machen
	public void paint() 
	{
		m_mainFrame.setVisible(true);		
	}
	
	//Spiel refresh erstellen
	public void refresh() {
		m_mainFrame.repaint();		
	}
}
