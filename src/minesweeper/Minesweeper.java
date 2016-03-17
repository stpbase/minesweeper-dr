package minesweeper;

public class Minesweeper
{
    private int m_minesLeft;
    private int m_lifesLeft;
	
	//Gui fuer Minesweeper
	private Gui m_minesweeperGui;
	private boolean m_gameStarted = false;
	private Level m_difficulty;
	
	//Minesweeper Gui wird abgerufen
	public Minesweeper()
	{
		m_minesweeperGui = new Gui(this);
	}
	
	//
	public void startGame()
	{
		m_minesLeft = 0;
		m_lifesLeft = 0;
		
		if (m_gameStarted)
		{
			m_minesweeperGui.refresh();
		}
		else
		{
		    m_minesweeperGui.paint();
		    m_gameStarted = true;
		}
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
	public int getLifesLeft() {
		return m_lifesLeft;
	}
	
	public void setLifesLeft(int lifesLeft)
	{
		m_lifesLeft = lifesLeft;
	}

	//Sobald verloren wurde zählt es die Leben ab
	public void mineExploded() {
		m_lifesLeft--;
		m_minesLeft--;
	}

	public Level getDifficulty() {
		return m_difficulty;
	}

	//Setzt Schwierigkeit
	public void setDifficulty(Level level) {
		this.m_difficulty = level;
		this.m_lifesLeft = level.getCountLives();
	}
}
