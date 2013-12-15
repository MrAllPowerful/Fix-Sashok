package net.launcher.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

import net.minecraft.Launcher;
import net.launcher.run.Settings;
import net.launcher.utils.BaseUtils;
import net.launcher.utils.EncodingUtils;
import net.launcher.utils.GuardUtils;
import net.launcher.utils.ThreadUtils;

public class Game extends JFrame
{
        private static final long serialVersionUID = 1L;
        public static Launcher mcapplet;
        
        public Game(final String answer)
        {
                String user = Frame.main.offline.isSelected() ? Settings.defaultUsername : answer.split("<br>")[1].split("<:>")[0];
                String session = Frame.main.offline.isSelected() ? Settings.defaultSession : EncodingUtils.xorencode(EncodingUtils.inttostr(answer.split("<br>")[1].split("<:>")[1]), Settings.protectionKey);
                
                int i = Integer.parseInt(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[4]);
                if (i == 1)
                {
                  GuardUtils.checkMods(answer, true);
                
                 if(Settings.useModCheckerTimer ) new Timer(30000, new ActionListener()
                 {
                        public void actionPerformed(ActionEvent e)
                        {
                                GuardUtils.checkMods(answer, false);
                        }
                }).start();
                try
                {
                        addWindowListener(new WindowListener()
                        {
                                public void windowOpened(WindowEvent e) {}
                                public void windowIconified(WindowEvent e) {}
                                public void windowDeiconified(WindowEvent e) {}
                                public void windowDeactivated(WindowEvent e) {}
                                public void windowClosed(WindowEvent e) {}
                                public void windowActivated(WindowEvent e) {}
                                public void windowClosing(WindowEvent e)
                                {
                                        mcapplet.stop();
                                        mcapplet.destroy();
                                        System.exit(0);
                                }
                        });
                        
                        String bin = BaseUtils.getMcDir().toString() + File.separator + ThreadUtils.b + File.separator;
                        setForeground(Color.BLACK);
                        setBackground(Color.BLACK);
                        URL[] urls = new URL[4];
                        

                        urls[0] = new File(bin, net.launcher.utils.ThreadUtils.l).toURI().toURL();
                        urls[1] = new File(bin, net.launcher.utils.ThreadUtils.e).toURI().toURL();
                        urls[2] = new File(bin, net.launcher.utils.ThreadUtils.f).toURI().toURL();
                        urls[3] = new File(bin, net.launcher.utils.ThreadUtils.m).toURI().toURL();
                        
            			mcapplet = new Launcher(bin, urls);
            			mcapplet.customParameters.put("username", user);
            			mcapplet.customParameters.put("sessionid", session);
            			mcapplet.customParameters.put("stand-alone", "true");
            			if(Settings.useAutoenter && !Frame.main.offline.isSelected())
            			{
            				mcapplet.customParameters.put("server", Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[1]);
            				mcapplet.customParameters.put("port", Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[2]);
            			}
            			setTitle(Settings.titleInGame);
            			if(Frame.main != null)
            			{
            				Frame.main.setVisible(false);
            				setBounds(Frame.main.getBounds());
            				setExtendedState(Frame.main.getExtendedState());
            				setMinimumSize(Frame.main.getMinimumSize());
            			}
                        setSize(Settings.width, Settings.height+28);
                        setMinimumSize(new Dimension(Settings.width, Settings.height+28));
                        setLocationRelativeTo(null);
            			mcapplet.setForeground(Color.BLACK);
            			mcapplet.setBackground(Color.BLACK);
            			setLayout(new BorderLayout());
            			add(mcapplet, BorderLayout.CENTER);
            			validate();
            			if(BaseUtils.getPropertyBoolean("fullscreen"))
            			setExtendedState(JFrame.MAXIMIZED_BOTH);
            			setIconImage(BaseUtils.getLocalImage("favicon"));
            			setVisible(true);
            			
            			if(Settings.useConsoleHider)
            			{
            				System.setErr(new PrintStream(new NulledStream()));
            				System.setOut(new PrintStream(new NulledStream()));
            			}
            			mcapplet.init();
            			mcapplet.start();
            		} catch(Exception e)
            		{
            			e.printStackTrace();
            		}
                }
                else
                {
                        try
                   {
                          String cps;
                          int t = Integer.parseInt(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[5]);
                          if (BaseUtils.getPlatform() == 2) cps = ";"; else cps = ":";
                                  int memory = BaseUtils.getPropertyInt("memory", 512);
                          System.out.println("Running Minecraft");
                          String jarpath = BaseUtils.getMcDir().toString() + File.separator + ThreadUtils.b + File.separator;
                          String minpath = BaseUtils.getMcDir().toString();
                          String assets = BaseUtils.getAssetsDir().toString() + File.separator;
                          ArrayList<String> params = new ArrayList<String>();
                          if (BaseUtils.getPlatform() == 2) params.add("javaw"); else params.add("java");
                          params.add("-Xmx" + memory + "m");
                          params.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
                          params.add("-Dfml.ignorePatchDiscrepancies=true");
                          params.add("-Djava.library.path="+jarpath+"natives");
                          params.add("-cp");
                          params.add(jarpath+net.launcher.utils.ThreadUtils.l+cps+jarpath+net.launcher.utils.ThreadUtils.e+cps+jarpath+net.launcher.utils.ThreadUtils.f+cps+jarpath+net.launcher.utils.ThreadUtils.m);
                          if (t == 1)
                          {
                             params.add("net.minecraft.client.main.Main");  
                          }
                          else
                          {
                             params.add("net.minecraft.launchwrapper.Launch");
                          }
                          if(BaseUtils.getPropertyBoolean("fullscreen"))
                          {          
                             params.add("--fullscreen");
                             params.add("true");
                          }
                          else
                          {
                             params.add("--width");
                             params.add(String.valueOf(Settings.width));
                             params.add("--height");
                             params.add(String.valueOf(Settings.height));
                          }
                          params.add("--username");
                          params.add(user);
                          params.add("--session");
                          params.add(session);
                          params.add("--version");
                          params.add(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[3]);
                          params.add("--gameDir");
                          params.add(minpath);
                          params.add("--assetsDir");
                          params.add(assets+"assets");
                          params.add("--assetIndex");
                          params.add(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[3]);
                          if(Settings.useAutoenter) {
                          if (!Frame.main.offline.isSelected()) {          
                            params.add("--server");
                            params.add(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[1]);
                            params.add("--port");
                            params.add(Settings.servers[Frame.main.servers.getSelectedIndex()].split(", ")[2]);
                         }
                         }
                         if (t == 2)
                         {
                            params.add("--tweakClass");
                            params.add("cpw.mods.fml.common.launcher.FMLTweaker");
                         }
                         if (t == 3)
                         {
                            params.add("--tweakClass");  
                            params.add("com.mumfrey.liteloader.launch.LiteLoaderTweaker");
                            params.add("--cascadedTweaks");
                            params.add("cpw.mods.fml.common.launcher.FMLTweaker");
                         }
                         if (t == 4)
                         {
                           params.add("--tweakClass");
                           params.add("com.mumfrey.liteloader.launch.LiteLoaderTweaker");
                           params.add("--tweakClass");
                           params.add("cpw.mods.fml.common.launcher.FMLTweaker");
                         }
                          params.add("--accessToken");
                          params.add(user);
                          params.add("--uuid");
                          params.add(session);
                          params.add("--userProperties");
                          params.add("{}");
                          ProcessBuilder pb = new ProcessBuilder(params);
                          pb.directory(new File(BaseUtils.getMcDir().toString()));
                          pb.start();
                          System.exit(0);
                       } catch (Exception e) {}
                }
        }
}
