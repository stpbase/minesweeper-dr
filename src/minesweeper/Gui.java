package minesweeper;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui
{
	//Groesse der Felder
	public static final int FIELD_HEIGHT = 60;
	public static final int FIELD_WIDTH = 60;
	
	//Definierung der einzelnen Komponente
	private JFrame m_mainFrame;
	private GridLayout m_fieldGrid;
	private JPanel m_fieldPanel;
	private Minesweeper m_startedGame;
	
	//Textanzeige definieren
	private JTextField m_minesLeft;
	private JTextField m_lifesLeft;
		
	public Gui (Minesweeper minesweeper)
	{
		//Minesweeper start
		m_startedGame = minesweeper;
	}
	
	//Setze groesse des Fensters
	public void setFieldPanelSize()
	{
		int width = m_startedGame.getDifficulty().getCountYFields() * Gui.FIELD_WIDTH;
		int height = m_startedGame.getDifficulty().getCountXFields() * Gui.FIELD_HEIGHT;
		
		m_mainFrame.setSize(width, height+100);
		m_fieldPanel.setSize(width, height);
	}
	
	//Neue Felder erstellen
	public void createFields() 
	{
		m_fieldPanel.setVisible(false);
		
		int rows = m_startedGame.getDifficulty().getCountYFields();
		int cols = m_startedGame.getDifficulty().getCountXFields();
		
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
			            	if (btn.isHidden())
			            	{
			            		m_startedGame.mineExploded();
			            		setLifesLeft(m_startedGame.getLifesLeft());
			            		setMinesLeft(m_startedGame.getMinesLeft());
			            		btn.setHidden(false);
			            	}
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
				if (gen.nextDouble() < m_startedGame.getDifficulty().getMinesPosibility()) 
				{
					currButton.makeMine();
					minesLeft++;
				}
				m_fieldPanel.add(currButton);
			}
		}
		m_startedGame.setMinesLeft(minesLeft);
    	setMinesLeft(minesLeft);
    	m_fieldPanel.setVisible(true);
    	
    	// Debug only
    	// System.out.println("Minen gefunden:" + minesLeft);
	}
	
	//Rückgabe Position des gesuchten Buttons
	private MinesweeperButton findButtonOnGrid (GridLocation loc)
	{
		MinesweeperButton currButton;
		for (Component comp : m_fieldPanel.getComponents())
		{
			if (comp instanceof MinesweeperButton)
			{
				currButton = (MinesweeperButton) comp;
				if (currButton.getGridLocation().equals(loc))
				{
					return currButton;
				}
			}
		}
		return null;
	}
	
	//Rückgabe ob es eine Mine ist oder nicht
	private boolean isMineAt (GridLocation loc)
	{
		return findButtonOnGrid(loc).isMine();
	}
	
	//Schaut ob es innerhalb oder ausserhalb des Feldes ist
	private boolean isOutside (GridLocation loc)
	{
		int xCount = m_startedGame.getDifficulty().getCountXFields();
		int yCount = m_startedGame.getDifficulty().getCountYFields();
		return (loc.getXPos() <= 0 
				|| loc.getYPos() <= 0 
				|| loc.getXPos() > xCount 
				|| loc.getYPos() > yCount);
	}
	
	//Anzahl Minen werden gezählt
	public int countMines(GridLocation loc) 
	{
		GridLocation currLoc = new GridLocation ();

		int count = 0;
        for (int x=-1; x <= 1; x++)
        {
        	for (int y=-1; y <= 1; y++)
        	{
        		currLoc.setXPos(x+loc.getXPos());
        		currLoc.setYPos(y+loc.getYPos());
        		if (!isOutside(currLoc) && isMineAt (currLoc))
        		{
        			count++;
        		}
        	}
        }
        
        // Debug only
        // System.out.println(count);
        return count;
    }

	//Aufdeckung der leeren Buttons	
    public void exposeNeighbors(MinesweeperButton btn) {
    	GridLocation btnLoc = btn.getGridLocation();
		GridLocation currLoc = new GridLocation ();
		MinesweeperButton lastFoundBtn;
		
        for (int x=-1; x <= 1; x++)
        {
        	for (int y=-1; y <= 1; y++)
        	{
        		currLoc.setXPos(x+btnLoc.getXPos());
        		currLoc.setYPos(y+btnLoc.getYPos());
        		if (!isOutside(currLoc) && !isMineAt (currLoc))
        		{
        			lastFoundBtn = findButtonOnGrid(currLoc);
        			if (lastFoundBtn.isHidden())
        			{
        				lastFoundBtn.doClick();
        			}
        		}
        	}
        }
    }
    
    public void exposeAll()
    {
		MinesweeperButton currButton;
		for (Component comp : m_fieldPanel.getComponents())
		{
			if (comp instanceof MinesweeperButton)
			{
				currButton = (MinesweeperButton) comp;
				currButton.doClick();
			}
		}
    }
	
		//Infoanzeige fuer Minen
	public void setMinesLeft(int countMines)
	{
		m_minesLeft.setText(String.valueOf(countMines));
	}
	
		//Infoanzeige fuer Leben
	public void setLifesLeft(int livesLeft)
	{
		m_lifesLeft.setText(String.valueOf(livesLeft));
	}	
	
	private JPanel createInfoPanel()
	{
		//Anordnungs Layout
		FlowLayout flowLayout = createFlowLayout(FlowLayout.LEFT);
		
		//Infopanel erstellen
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(flowLayout);

		//Informationen für Infopanel Minen
		JLabel minesLeftLabel = new JLabel();
		minesLeftLabel.setText("Minen");
		m_minesLeft = new JTextField("00", 2);
		m_minesLeft.setEditable(false);
		
		//Informationen für Infopanel Leben
		JLabel livesLeftLabel = new JLabel();
		livesLeftLabel.setText("Noch übrige Leben");
		m_lifesLeft = new JTextField("00", 2);
		m_lifesLeft.setEditable(false);
		
		//hinzufuegen der Komponenten auf den Infopanel
		infoPanel.add(minesLeftLabel);
		infoPanel.add(m_minesLeft);
		infoPanel.add(livesLeftLabel);
		infoPanel.add(m_lifesLeft);
		
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
		
		ActionListener startGameActionListener = new ActionListener()
		{
			public void actionPerformed (ActionEvent e) 
			{
				if (e.getSource() instanceof JComboBox<?>)
				{
					JComboBox<?> diffLevel = (JComboBox<?>)e.getSource();			
					m_startedGame.setDifficulty((Level) diffLevel.getSelectedItem());
					refresh();
				}
				m_startedGame.startGame();
			}
		};
		
		//Initialisierung Startknopf
		JButton startGameBtn = new JButton("Reset");
		startGameBtn.addActionListener(startGameActionListener);
		
		//Dropdown erstellen
		JComboBox<Level> difficultyLevelComboBox = new JComboBox<>(
				new DefaultComboBoxModel<Level>());
		// Einträge erstellen
		for (Level lvl : Level.LEVELS)
		{
		    difficultyLevelComboBox.addItem(lvl);
		}
		
		difficultyLevelComboBox.setSelectedIndex(0);
		difficultyLevelComboBox.addActionListener(startGameActionListener);
		
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
	
	private void setLifesLeft()
	{
		int lifesLeft = m_startedGame.getDifficulty().getCountLives();
		m_lifesLeft.setText(String.valueOf(lifesLeft));
		m_startedGame.setLifesLeft(lifesLeft);
	}
	
	// Felder erstellen und sichtbar machen
	public void paint() 
	{
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

		setLifesLeft();
		
		setFieldPanelSize();
		createFields();
		m_mainFrame.setVisible(true);		
	}
	
	//Spiel refresh erstellen
	public void refresh() 
	{
		setLifesLeft();
		setFieldPanelSize();
		createFields();
		m_mainFrame.repaint();
	}
}
