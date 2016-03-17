package minesweeper;

public class DifficultySettings 
{	
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
