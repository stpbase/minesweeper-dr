package minesweeper;

import java.util.HashMap;
import java.util.Map;

//Passt die Anzahl Felder und die grösse des Fensters an die Schwierigkeitsgrade
public class DifficultySettings 
{	
	//Schwierigkeitsgrad
	@SuppressWarnings("serial")
	public static final Map<String, Integer> LEVELS = new HashMap<String, Integer>() {{
		put("Leicht", 5);
		put("Mittel", 7); 
		put("Schwer", 10); 
	}};

	private int m_xFields;
	private int m_yFields;
	
	private int m_minesCount;
	private int m_livesCount;
	
	public DifficultySettings(int xFields, int yFields) {
		this.m_xFields = xFields;
		this.m_yFields = yFields;
	}
	
	public int getXFields()
	{
		return m_xFields;
	}
	
	public int getYFields()
	{
		return m_yFields;
	}
	
	public int getWindowWidth()
	{
		return m_xFields * Gui.FIELD_WIDTH;
	}
	
	public int getWindowHeight()
	{
		return m_yFields * Gui.FIELD_HEIGHT;
	}

	public int getMinesCount() {
		return m_minesCount;
	}

	public void setMinesCount(int minesCount) {
		this.m_minesCount = minesCount;
	}

	public int getLivesCount() {
		return m_livesCount;
	}

	public void setLivesCount(int livesCount) {
		this.m_livesCount = livesCount;
	}
}
