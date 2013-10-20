package net.launcher.run;

import net.launcher.MusPlay;
import net.launcher.components.Frame;

public class Mainclass
{
	public static void main(String[] args) throws Exception
	{	
		Frame.start();
		if(Settings.Music)
		{	
        MusPlay mp3 = new MusPlay(Settings.iMusicname);
        mp3.play();}
	}
}
