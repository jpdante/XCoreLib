package com.xgames178.XCore.Game.Scoreboard;

import java.util.ArrayList;

public class ScoreboardElementText extends ScoreboardElement
{
	private String _line;
	
	public ScoreboardElementText(String line)
	{
		_line = line;
	}
	
	@Override
	public ArrayList<String> GetLines()
	{
		ArrayList<String> orderedScores = new ArrayList<String>();
		
		orderedScores.add(_line);
		
		return orderedScores;
	}
	
}
