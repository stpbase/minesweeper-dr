package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class Minesweeper implements ActionListener
{
    public final static double MINES_PERCENTAGE = 0.15;
    private int m_minesLeft;
    private int m_livesLeft = 3;
	
	//Gui fuer Minesweeper
	private Gui m_minesweeperGui;
	private boolean m_gameStarted = false;
	private String m_difficulty;
	
	//Minesweeper Gui wird abgerufen
	public Minesweeper()
	{
		m_minesweeperGui = new Gui(this);
	}
	
	//
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
		System.out.println("Das Spiel wurde richtig gestartet - Viel Spass!");
	}
	
	//Dropdown Feld für die Schwierigkeit des Spiels
	public void actionPerformed (ActionEvent e) 
	{
		if (e.getSource() instanceof JComboBox<?>)
		{
			JComboBox<?> diffLevel = (JComboBox<?>)e.getSource();			
			setDifficulty(diffLevel.getSelectedItem().toString());
			m_minesweeperGui.refresh();
			m_minesweeperGui.setLivesLeft(3);
		}
		startGame();
		
	}
	
	//Gibt aus wieviele Mine vorhanden sind
	public int getMinesLeft() {
		return m_minesLeft;
	}
	
	//Setzt die Minen
	public void setMinesLeft(int mines) {
		this.m_minesLeft = mines;
	}

	//Gibt Anzahl Leben zurück
	public int getLivesLeft() {
		return m_livesLeft;
	}
	
	//Setzt Anzahl Leben
	public void setLivesLeft(int lives) {
		this.m_livesLeft = lives;
	}

	//Sobald eine Mine gefunden wurde zieht es ein Leben ab
	public void mineExploded() {
		m_livesLeft--;
		m_minesLeft--;
	}

	public String getDifficulty() {
		return m_difficulty;
	}

	//Setzt Groesse des Fensters bei der Schwierigkeit und Anzahl Felder
	public void setDifficulty(String level) {
		this.m_difficulty = level;
		DifficultySettings diffSettings = new DifficultySettings (
				DifficultySettings.LEVELS.get(level), 
				DifficultySettings.LEVELS.get(level));

		m_minesweeperGui.setFieldPanelSize(
				diffSettings.getWindowWidth(), 
				diffSettings.getWindowHeight());
		m_minesweeperGui.createFields(
				diffSettings.getXFields(), 
				diffSettings.getYFields());
	}
}
