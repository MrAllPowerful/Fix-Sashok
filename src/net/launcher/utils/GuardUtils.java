package net.launcher.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import net.launcher.components.Frame;
import net.launcher.components.Game;
import net.launcher.run.Settings;


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
	        BaseUtils.send("ANTICHEAT: MD5 of " + filename + " getted, " + formatter.toString());
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
	
	public static List<String> updateMods_first(String result)
	{  
		List<String> files = new ArrayList<String>();
		String[] typeMods = {"mods", "coremods"};
		for(int array = 0; array < result.split("<br>")[2].split("::").length; array++)
		{
			if(result.split("<br>")[2].split("::")[array].equals("nomods"))
			{
				File dir = new File(BaseUtils.getMcDir().getAbsolutePath() + File.separator + typeMods[array]);
				if(dir.exists() && dir.isDirectory()){
				String[] dirFiles = dir.list(new FilenameFilter() { public boolean accept(File folder, String name)
				{
					return name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar") || name.toLowerCase().endsWith("");
				}});
				if(dirFiles == null || dirFiles.length == 0) return files;
				for(String cfile : dirFiles)
				{
					File file = new File(dir.getAbsolutePath() + File.separator + cfile);
					delete(file);
				}
				}
			}
			else
			{
				File dir = new File(BaseUtils.getMcDir().getAbsolutePath() + File.separator + typeMods[array]);
				String mods = result.split("<br>")[2].split("::")[array];
				
				String[] modsArray = result.split("<br>")[2].split("::")[array].split("<:>");
				
				if(Frame.main.updatepr.isSelected())
				{
					for(String mod : modsArray) files.add(typeMods[array] + "/" + mod.split(":>")[0]);
					return files;
				}
				
				if(dir.exists() && dir.isDirectory())
				{
					String[] dirFiles = dir.list(new FilenameFilter() { public boolean accept(File folder, String name)
					{
						return name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar") || name.toLowerCase().endsWith("");
					}});
					for(String cfile : dirFiles)
					{
						File file = new File(dir.getAbsolutePath() + File.separator + cfile);
						String md5 = GuardUtils.getMD5(file.getAbsolutePath());
						if(!mods.contains(cfile + ":>" + md5 + "<:>"))
						{
							delete(file);
							mods = mods.replaceAll(cfile + ":>" + md5 + "<:>", "");
							modsArray = mods.split("<:>");
						}
					}
					String dirFilesString = "";
					for(String file : dirFiles) dirFilesString += file + ":>" + GuardUtils.getMD5(dir.getAbsolutePath() + File.separator + file) + "<:>";
					for(String mod : modsArray) { if(!dirFilesString.contains(mod))
					{					
						files.add(typeMods[array] + "/" + mod.split(":>")[0]);
					}}
				} else for(String mod : modsArray) files.add(typeMods[array] +"/" + mod.split(":>")[0]);
			}
		}
		return files;
	  }
	
	
	public static List<String> updateMods(String result)
	{  
		List<String> files = new ArrayList<String>();
		String[] typeMods = {"mods", "coremods"};
		for(int array = 0; array < result.split("<br>")[2].split("::").length; array++)
		{
			if(result.split("<br>")[2].split("::")[array].equals("nomods"))
			{
				File dir = new File(BaseUtils.getMcDir().getAbsolutePath() + File.separator + typeMods[array]);
				if(dir.exists() && dir.isDirectory()){
				String[] dirFiles = dir.list(new FilenameFilter() { public boolean accept(File folder, String name)
				{
					return name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar") || name.toLowerCase().endsWith("");
				}});
				if(dirFiles == null || dirFiles.length == 0) return files;
				for(String cfile : dirFiles)
				{
					File file = new File(dir.getAbsolutePath() + File.separator + cfile);
					delete(file);
				}
				}
			}
			else
			{
				File dir = new File(BaseUtils.getMcDir().getAbsolutePath() + File.separator + typeMods[array]);
				String mods = result.split("<br>")[2].split("::")[array];
				
				String[] modsArray = result.split("<br>")[2].split("::")[array].split("<:>");
				
				if(Frame.main.updatepr.isSelected())
				{
					for(String mod : modsArray) files.add(typeMods[array] + "/" + mod.split(":>")[0]);
					return files;
				}
				
				if(dir.exists() && dir.isDirectory())
				{
					String[] dirFiles = dir.list(new FilenameFilter() { public boolean accept(File folder, String name)
					{
						return name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar") || name.toLowerCase().endsWith("");
					}});
					for(String cfile : dirFiles)
					{
						File file = new File(dir.getAbsolutePath() + File.separator + cfile);
						String md5 = GuardUtils.getMD5(file.getAbsolutePath());
						if(!mods.contains(cfile + ":>" + md5 + "<:>"))
						{
							delete(file);
							mods = mods.replaceAll(cfile + ":>" + md5 + "<:>", "");
							modsArray = mods.split("<:>");
							Game.mcapplet.stop();
							Game.mcapplet.destroy();
							System.exit(0);
						}
					}
					String dirFilesString = "";
					for(String file : dirFiles) dirFilesString += file + ":>" + GuardUtils.getMD5(dir.getAbsolutePath() + File.separator + file) + "<:>";
					for(String mod : modsArray) { if(!dirFilesString.contains(mod))
					{					
						files.add(typeMods[array] + "/" + mod.split(":>")[0]);
					}}
				} else for(String mod : modsArray) files.add(typeMods[array] +"/" + mod.split(":>")[0]);
			}
		}
		return files;
	  }

	public static void checkMods(String answer, boolean action)
	{
		if(!Frame.main.offline.isSelected())
		{
			BaseUtils.send("ANTICHEAT: Rechecking jars...");
			boolean ret = false;
			String binfolder = BaseUtils.getMcDir() + File.separator + ThreadUtils.b + File.separator;
			if(!EncodingUtils.xorencode(EncodingUtils.inttostr(answer.split("<br>")[0].split("<:>")[3]), Settings.protectionKey).equals(GuardUtils.getMD5(binfolder + net.launcher.utils.ThreadUtils.l))) ret = true;
			if(!EncodingUtils.xorencode(EncodingUtils.inttostr(answer.split("<br>")[0].split("<:>")[4]), Settings.protectionKey).equals(GuardUtils.getMD5(binfolder + net.launcher.utils.ThreadUtils.f))) ret = true;
			if(!EncodingUtils.xorencode(EncodingUtils.inttostr(answer.split("<br>")[0].split("<:>")[5]), Settings.protectionKey).equals(GuardUtils.getMD5(binfolder + net.launcher.utils.ThreadUtils.e))) ret = true;
			if(!EncodingUtils.xorencode(EncodingUtils.inttostr(answer.split("<br>")[0].split("<:>")[2]), Settings.protectionKey).equals(GuardUtils.getMD5(binfolder + net.launcher.utils.ThreadUtils.m))) ret = true;

			if(GuardUtils.updateMods(answer).size() != 0) ret = true;
			
			if(ret && action)
			{
				Frame.main.setError("Ошибка вторичной проверки кеша.");
				return;
			} else if(ret && !action)
			{
				BaseUtils.send("ANTICHEAT: Strange mods detected");
				Game.mcapplet.stop();
				Game.mcapplet.destroy();
				System.exit(0);
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
}
