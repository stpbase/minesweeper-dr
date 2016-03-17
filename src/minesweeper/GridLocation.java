package minesweeper;

public class GridLocation 
{
	int m_xPos;
	int m_yPos;

	public GridLocation(int m_xPos, int m_yPos) {
		super();
		this.m_xPos = m_xPos;
		this.m_yPos = m_yPos;
	}
	
	public GridLocation() {
	}

	@Override
	 public boolean equals(java.lang.Object arg0)
	{
		GridLocation chkGrid = (GridLocation) arg0;
		return (m_xPos == chkGrid.getXPos() 
				&& m_yPos == chkGrid.getYPos());
	}
	
	public int getXPos() {
		return m_xPos;
	}
	public void setXPos(int m_xPos) {
		this.m_xPos = m_xPos;
	}
	public int getYPos() {
		return m_yPos;
	}
	public void setYPos(int m_yPos) {
		this.m_yPos = m_yPos;
	}
}
