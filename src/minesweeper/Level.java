package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Level {
	//Schwierigkeitsgrad
	@SuppressWarnings("serial")
	public static final List<Level> LEVELS = new ArrayList<Level>() {{
		add(new Level("Leicht", 5, 5, 0.2, 3));
		add(new Level("Mittel", 7, 7, 0.25, 2)); 
		add(new Level("Schwer", 10, 10, 0.3, 1)); 
	}};
	
	private String m_levelBezeichnung;
    private int m_countXFields;
    private int m_countYFields;
    private double m_minesPosibility;
    private int m_countLives;
    
    public Level()
    {
    }
    
	public Level(String levelBezeichnung, int countXFields, int countYFields,
			double minesPosibility, int countLives) 
	{
		this.m_levelBezeichnung = levelBezeichnung;
		this.m_countXFields = countXFields;
		this.m_countYFields = countYFields;
		this.m_minesPosibility = minesPosibility;
		this.m_countLives = countLives;
	}

	public String getLevelBezeichnung() {
		return m_levelBezeichnung;
	}
	public void setLevelBezeichnung(String levelBezeichnung) {
		this.m_levelBezeichnung = levelBezeichnung;
	}
	public int getCountXFields() {
		return m_countXFields;
	}
	public void setCountXFields(int countXFields) {
		this.m_countXFields = countXFields;
	}
	public int getCountYFields() {
		return m_countYFields;
	}
	public void setCountYFields(int countYFields) {
		this.m_countYFields = countYFields;
	}
	public double getMinesPosibility() {
		return m_minesPosibility;
	}
	public void setMinesPosibility(double minesPosibility) {
		this.m_minesPosibility = minesPosibility;
	}
	public int getCountLives() {
		return m_countLives;
	}
	public void setCountLives(int countLives) {
		this.m_countLives = countLives;
	}
	
	@Override
	public String toString()
	{
		return m_levelBezeichnung;
	}
}
