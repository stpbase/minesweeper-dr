package minesweeper;

//Startet das Game
public class Main 
{
	private static Minesweeper m_startedGame;
	
	public static void main(String[] args) 
	{		
		m_startedGame = new Minesweeper();
		m_startedGame.setDifficulty("Leicht");
		m_startedGame.startGame();
	}
}
