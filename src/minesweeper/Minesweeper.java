package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Minesweeper implements ActionListener
{
	//Schwierigkeitsgrad
	public static final Map<String, Integer> LEVELS = new HashMap<String, Integer>() {{
		put("Leicht", 5);
		put("Mittel", 7); 
		put("Schwer", 10); 
	}};

    public final static double MINES_PERCENTAGE = 0.15;
    private int m_minesLeft;
    private int m_livesLeft;
	
	//Gui fuer Minesweeper
	private Gui m_minesweeperGui;
	private boolean m_gameStarted = false;
	private String m_difficulty;
	
	//Minesweeper Gui wird abgerufen
	public Minesweeper()
	{
		m_minesweeperGui = new Gui(this);
		m_minesweeperGui.setLivesLeft(4);
		m_minesweeperGui.setMinesFound(4);
	}
	
	public void startGame()
	{
		if (m_gameStarted)
		{
			m_minesweeperGui.refresh();
		}
		else
		{
		    m_minesweeperGui.paint();
		    
		}
		System.out.println("Das ist ein Test");
	}
	
	public void actionPerformed (ActionEvent e) 
	{
		if (e.getSource() instanceof JComboBox<?>)
		{
			JComboBox<?> diffLevel = (JComboBox<?>)e.getSource();			
			setDifficulty(diffLevel.getSelectedItem().toString());
			m_minesweeperGui.refresh();
		}
		startGame();
	}

	public int getMinesLeft() {
		return m_minesLeft;
	}

	public void minusMines(int mines) {
		this.m_minesLeft = m_minesLeft - mines;
	}
	
	public void setMinesLeft(int mines) {
		this.m_minesLeft = mines;
	}

	public int getLivesLeft() {
		return m_livesLeft;
	}

	public void minusLives(int lives) {
		this.m_livesLeft = m_livesLeft - lives;
	}

	public String getDifficulty() {
		return m_difficulty;
	}

	public void setDifficulty(String level) {
		this.m_difficulty = level;
		DifficultySettings diffSettings = new DifficultySettings (LEVELS.get(level), LEVELS.get(level));

		m_minesweeperGui.setFieldPanelSize(
				diffSettings.getWindowWidth(), 
				diffSettings.getWindowHeight());
		m_minesweeperGui.createFields(
				diffSettings.getXFields(), 
				diffSettings.getYFields());
	}
}
