package net.launcher.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.List;

import net.launcher.components.Game;
import net.launcher.utils.java.eURLClassLoader;


public class UpdaterThread extends Thread
{
	public int procents = 0;
	public long totalsize = 0;
	public long currentsize = 0;
	public String currentfile = "...";
	public int downloadspeed = 0;
	public List<String> files;
	public String state = "...";
	public boolean error = false;
	public boolean zipupdate = false;
	public boolean zipupdate2 = false;
	public String answer;
	
	public UpdaterThread(List<String> files, boolean zipupdate, boolean zipupdate2, String answer)
	{
		this.files = files;
		this.zipupdate = zipupdate;
		this.zipupdate2 = zipupdate2;
		this.answer = answer;
	}
	
	public void run()
	{ try {
		String pathTo = BaseUtils.getAssetsDir().getAbsolutePath() + File.separator;
		String urlTo = BaseUtils.buildUrl("clients/");
		File dir;
		
		state = "Определение размера...";
		
		for (int i = 0; i < files.size(); i++)
		{
			String list = urlTo + files.get(i);
			int test2 = list.split("/").length;
			String folder = urlTo + files.get(i).replace("/"+list.split("/")[test2-1], "");
            dir = new File(pathTo + folder.replace(urlTo, ""));
    		if(!dir.exists() ) dir.mkdirs();
			URLConnection urlconnection = new URL(urlTo + files.get(i)).openConnection();
			urlconnection.setDefaultUseCaches(false);
			totalsize += urlconnection.getContentLength();
		}
		
		state = "Закачка файлов...";
		
		byte[] buffer = new byte[65536];
		for (int i = 0; i < files.size(); i++)
		{
			currentfile = files.get(i);
			String URLEncodedFileName = URLEncoder.encode(currentfile, "UTF-8");
			String file = URLEncodedFileName.replace("%2F", "/").replace("+", "%20");
			BaseUtils.send("Downloading file: " + currentfile);
			InputStream is = new BufferedInputStream(new URL(urlTo + file).openStream());
			FileOutputStream fos = new FileOutputStream(pathTo + currentfile);
			long downloadStartTime = System.currentTimeMillis();
			int downloadedAmount = 0, bs = 0;
			MessageDigest m = MessageDigest.getInstance("MD5");
			while((bs = is.read(buffer, 0, buffer.length)) != -1)
			{
				fos.write(buffer, 0, bs);
				m.update(buffer, 0, bs);
				currentsize += bs;
				procents = (int)(currentsize * 100 / totalsize);
				downloadedAmount += bs;
				long timeLapse = System.currentTimeMillis() - downloadStartTime;
				if (timeLapse >= 1000L)
				{
					downloadspeed = (int)((int) (downloadedAmount / (float) timeLapse * 100.0F) / 100.0F);
					downloadedAmount = 0;
					downloadStartTime += 1000L;
				}
			}
			is.close();
			fos.close();
			BaseUtils.send("File downloaded: " + currentfile);
		}
		state = "Закачка завершена";
		
		if(zipupdate)
		{
			BaseUtils.setProperty(BaseUtils.getClientName() + "_zipmd5", GuardUtils.getMD5(BaseUtils.getMcDir().getAbsolutePath() + File.separator + "bin" + File.separator + "client.zip"));
			ZipUtils.unzip();
		}
		
		URLClassLoader cl;
		int t = 1;
        String bin = BaseUtils.getMcDir().toString() + File.separator + ThreadUtils.b + File.separator;
        URL[] urls = new URL[1];
        try {
            urls[0] = new File(bin, net.launcher.utils.ThreadUtils.m).toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        try
        {   
        	t = 1;
            cl = new eURLClassLoader(urls);
            cl.loadClass("net.minecraft.client.Minecraft");
 		} catch(Exception e)
 		{
 			t = 2;
 		}

	    if (t > 1)
	    {			
		  if(zipupdate2)
		  {
			BaseUtils.setProperty("assetsmd5", GuardUtils.getMD5(BaseUtils.getMcDir().getAbsolutePath() + File.separator + "bin" + File.separator + "assets.zip"));
			ZipUtils2.unzip();
		  }
	    }	
		new Game(answer);
	} catch (Exception e) { e.printStackTrace(); state = e.toString(); error = true; }}
}
