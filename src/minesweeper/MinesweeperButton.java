package minesweeper;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")

//Macht alle Interaktionen mit den Feldern
public class MinesweeperButton extends JButton
{	
	private static final ImageIcon INIT_ICON = new ImageIcon(MinesweeperButton.class.getResource("/gfx/initial.jpg"));
	public static final ImageIcon MINE_ICON = new ImageIcon(MinesweeperButton.class.getResource("/gfx/mine.jpg"));
	private static final List<ImageIcon> NUMBER_ICONS = new ArrayList<ImageIcon>() {{
		add(new ImageIcon(MinesweeperButton.class.getResource("/gfx/zero.jpg")));
		add(new ImageIcon(MinesweeperButton.class.getResource("/gfx/one.jpg")));
		add(new ImageIcon(MinesweeperButton.class.getResource("/gfx/two.jpg")));
		add(new ImageIcon(MinesweeperButton.class.getResource("/gfx/three.jpg")));
		add(new ImageIcon(MinesweeperButton.class.getResource("/gfx/four.jpg")));
		add(new ImageIcon(MinesweeperButton.class.getResource("/gfx/five.jpg")));
		add(new ImageIcon(MinesweeperButton.class.getResource("/gfx/six.jpg")));
		add(new ImageIcon(MinesweeperButton.class.getResource("/gfx/seven.jpg")));
		add(new ImageIcon(MinesweeperButton.class.getResource("/gfx/eight.jpg")));
	}};
	
	private boolean m_isMine = false;
	private boolean m_isHidden = true;
	private GridLocation m_gridLocation;
	
	// Initialisierung Felder
	public MinesweeperButton (int posX, int posY)
	{
		m_gridLocation = new GridLocation(posX, posY);
		
		setIcon(INIT_ICON);
		setMargin(new Insets(0, 0, 0, 0));

		// Debug only
		//setText("0");
	}
	
	public boolean isHidden()
	{
		return m_isHidden;
	}
	
	public void setHidden(boolean hide)
	{
		m_isHidden = hide;
	}
	
	public GridLocation getGridLocation()
	{
		return m_gridLocation;
	}
	
	public void setIcon (int iconNumber)
	{
		this.setIcon(NUMBER_ICONS.get(iconNumber));
	}
	
	public void makeMine ()
	{
		m_isMine = true;
		
		// DEBUG only
		//this.setBackground(new Color(100));
	}
	
	public boolean isMine ()
	{
		return m_isMine;
	}
}
