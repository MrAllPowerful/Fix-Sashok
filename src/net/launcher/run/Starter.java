package net.launcher.run;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import net.launcher.utils.BaseUtils;

public class Starter
{
	public static void main(String[] args) throws Exception
	{	
		
		File dir = new File(BaseUtils.getAssetsDir().toString());
		if(!dir.exists()) dir.mkdirs();
		InputStream stream = Starter.class.getResourceAsStream("/net/launcher/theme/favicon.png");
	    if (stream == null) {
	    }
	    OutputStream resStreamOut = null;
	    int readBytes;
	    byte[] buffer = new byte[4096];
	    try {
	        resStreamOut = new FileOutputStream(new File(BaseUtils.getAssetsDir().toString()+"/favicon.png"));
	        while ((readBytes = stream.read(buffer)) > 0) {
	            resStreamOut.write(buffer, 0, readBytes);
	        }
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    } finally {
	        stream.close();
	        resStreamOut.close();
	    }
		
		try {

			String jarpath = Starter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			int memory = BaseUtils.getPropertyInt("memory", 512);
			
			ArrayList<String> params = new ArrayList<String>();
           
			params.add("java");
			params.add("-Xmx" + memory + "m");
			params.add("-Xms" + memory + "m");
			if(System.getProperty("os.name").toLowerCase().startsWith("mac"))
			{
				params.add("-Xdock:name=Minecraft");
				params.add("-Xdock:icon=" + BaseUtils.getAssetsDir().toString()+"/favicon.png");
			}
			params.add("-Dsun.java2d.noddraw=true");
			params.add("-Dsun.java2d.d3d=false");
			params.add("-Dsun.java2d.opengl=false");
			params.add("-Dsun.java2d.pmoffscreen=false");
			params.add("-classpath");
			params.add(jarpath);
			params.add("net.launcher.run.Mainclass");

			ProcessBuilder pb = new ProcessBuilder(params);
			pb.directory(new File(BaseUtils.getAssetsDir().toString()));
			Process process = pb.start();
			if (process == null) throw new Exception("Launcher can't be started!");
			System.exit(0);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
