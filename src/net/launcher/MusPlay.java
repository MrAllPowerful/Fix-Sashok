package net.launcher;
import java.io.BufferedInputStream;
import java.io.InputStream;

import net.launcher.run.Settings;

import javazoom.jl.player.Player;
public class MusPlay {
	private String filename;
    private Player player; 

    // constructor that takes the name of an MP3 file
    public MusPlay(String filename) {
        this.filename = filename;
    }

    public void close() { if (player != null) player.close(); }

    // play the MP3 file to the sound card
    public void play() {
        try {
        	InputStream is=getClass().getResourceAsStream(Settings.iMusicname);
            @SuppressWarnings("unused")
			BufferedInputStream bis = new BufferedInputStream(is);
            player = new Player(is);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();




    }


    

}
