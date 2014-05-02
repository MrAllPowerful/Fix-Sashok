package net.launcher.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import net.launcher.components.Frame;


public class GuardUtils
{
	public static String getMD5(String filename)
	{
		FileInputStream fis = null;
		DigestInputStream dis = null;
		BufferedInputStream bis = null;
		Formatter formatter = null;
		try
		{	
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			dis = new DigestInputStream(bis, messagedigest);
			while(dis.read() != -1);
	        byte abyte0[] = messagedigest.digest();
	        formatter = new Formatter();
	        byte abyte1[] = abyte0;
	        int i = abyte1.length;
	        for(int j = 0; j < i; j++)
	        {
	            byte byte0 = abyte1[j];
	            formatter.format("%02x", new Object[] { Byte.valueOf(byte0) });
	        }
	        return formatter.toString();
		} catch(Exception e) { return BaseUtils.empty; }
		finally
		{
			try { fis.close(); } catch (Exception e){}
			try { dis.close(); } catch (Exception e){}
			try { bis.close(); } catch (Exception e){}
			try { formatter.close(); } catch (Exception e){}
		}
	}
	
	public static boolean ret = false;
	
	public static List<URL> url = new ArrayList<URL>();
	
	public static List<String> updateMods(String answer)
	{  
		ret = false;
		List<String> files = new ArrayList<String>();	
			{
				String dir = BaseUtils.getAssetsDir().getAbsolutePath().replace("\\", "/");
				String[] modsArray = answer.split("<br>")[2].split("<::>")[0].split("<:>");
				List<String> site = new ArrayList<String>();
				List<String> cl = new ArrayList<String>();
				List<String> client = new ArrayList<String>();
				
				String[] scn_dirs = answer.split("<::>")[1].split("<:b:>");
				for (int i = 0; i < scn_dirs.length; i++) {
						cl.addAll(getLibs(new File(dir+File.separator+scn_dirs[i])));
					  }
				for(String rpl : cl)
				{
					client.add(rpl.replace("\\", "/"));
				}
				for(String add : modsArray)
				{
					site.add(dir+"/"+add);
					
			        if (add.contains(BaseUtils.getClientName()+"/"+"bin")) {
			        	File file = new File(dir+File.separator+add.split(":>")[0]);
			        	try {
							url.add(file.toURI().toURL());
						} catch (MalformedURLException e) {}
			        }
				}
				for (String check : client) {
			        if (!site.contains(check)) {
			            File file = new File(check.split(":>")[0]);
			            delete(file);
			            ret = true;
			        }
			    }
				for (String check : site) {
			        if (!client.contains(check)) {
			            files.add(check.replace(dir, "").split(":>")[0]);
			            System.err.println(check.replace(dir, "").split(":>")[0]);
			        }
			    }
			}
		return files;
	}

	public static void checkMods(String answer, boolean action)
	{
		if(!Frame.main.offline.isSelected())
		{
			BaseUtils.send("ANTICHEAT: Rechecking jars...");
			GuardUtils.updateMods(answer);
			if(ret && action)
			{
				Frame.main.setError("Ошибка вторичной проверки кеша.");
				return;
			} else if(ret && !action)
			{
				BaseUtils.send("ANTICHEAT: Strange mods detected");
				System.exit(0);
				Runtime.getRuntime().exit(0);
				return;
			}
			
			BaseUtils.send("ANTICHEAT: Mod checking done");
		}
	}
	
    public static void delete(File file)
    {
        try {
            if (!file.exists()) return;
            if (file.isDirectory())
            {
                for (File f : file.listFiles()) delete(f);
                file.delete();
            } else file.delete();
        } catch (Exception e)
        {}
    }
    
    private static List<String> getLibs(File libsfolder) {
    	  List<String> libs = new ArrayList<String>();
    	  if (!libsfolder.exists()) libsfolder.mkdirs();
    	  for (File file : libsfolder.listFiles()) {
    	   if (file.isDirectory()) {
    	    libs.addAll(getLibs(file));
    	   } else {
    	    libs.add(file.getAbsolutePath() + ":>" + GuardUtils.getMD5(file.getAbsolutePath()));
    	   }
    	  }
    	  return libs;
    }
}
