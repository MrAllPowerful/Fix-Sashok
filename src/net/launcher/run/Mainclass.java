package net.launcher.run;

import net.launcher.MusPlay;
import net.launcher.components.Frame;
import net.launcher.utils.BaseUtils;

public class Mainclass
{
	public static void main(String[] args) throws Exception
	{	
		Frame.start();
		if(BaseUtils.getPropertyBoolean("Music", true))
		{
               MusPlay mp3 = new MusPlay(Settings.iMusicname);
               mp3.play();}
	}
}
