package minesweeper;

/**
 * @author diana
 *
 */

//Startet das Game
public class Main 
{
	private static Minesweeper m_startedGame;
	
	public static void main(String[] args) 
	{		
		m_startedGame = new Minesweeper();
		m_startedGame.setDifficulty(Level.LEVELS.get(0));
		m_startedGame.startGame();
	}
}
