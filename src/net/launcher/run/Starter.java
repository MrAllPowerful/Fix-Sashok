package net.launcher.run;

import java.util.ArrayList;

import net.launcher.utils.BaseUtils;

public class Starter
{
	public static void main(String[] args) throws Exception
	{	
		try
		{
			String jarpath = Starter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			int memory = BaseUtils.getPropertyInt("memory", 512);
			
			ArrayList<String> params = new ArrayList<String>();

			if (BaseUtils.getPlatform() == 2) params.add("javaw"); else params.add("java");
			params.add("-Xmx" + memory + "m");
			params.add("-Xms" + memory + "m");
			params.add("-Dsun.java2d.noddraw=true");
			params.add("-Dsun.java2d.d3d=false");
			params.add("-Dsun.java2d.opengl=false");
			params.add("-Dsun.java2d.pmoffscreen=false");
			params.add("-classpath");
			params.add(jarpath);
			params.add("net.launcher.run.Mainclass");

			ProcessBuilder pb = new ProcessBuilder(params);
			Process process = pb.start();
			if (process == null) throw new Exception("Launcher can't be started!");
			System.exit(0);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
