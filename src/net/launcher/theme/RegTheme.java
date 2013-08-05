package net.launcher.theme;

import java.awt.Color;

import javax.swing.border.EmptyBorder;

import net.launcher.components.Align;
import net.launcher.components.ButtonStyle;
import net.launcher.components.CheckboxStyle;
import net.launcher.components.ComboboxStyle;
import net.launcher.components.ComponentStyle;
import net.launcher.components.DragbuttonStyle;
import net.launcher.components.DraggerStyle;
import net.launcher.components.LinklabelStyle;
import net.launcher.components.PassfieldStyle;
import net.launcher.components.ServerbarStyle;
import net.launcher.components.TextfieldStyle;

public class RegTheme
{
    public static TextfieldStyle	loginReg		= new TextfieldStyle(390, 164, 220, 36, "textfield", "font", 16F, Color.DARK_GRAY, Color.WHITE, new EmptyBorder(0, 10, 0, 10));
	public static TextfieldStyle	passwordReg		= new TextfieldStyle(390, 208, 220, 36, "textfield", "font", 16F, Color.DARK_GRAY, Color.WHITE, new EmptyBorder(0, 10, 0, 10));
	public static TextfieldStyle	password2Reg    = new TextfieldStyle(390, 254, 220, 36, "textfield", "font", 16F, Color.DARK_GRAY, Color.WHITE, new EmptyBorder(0, 10, 0, 10));
	public static TextfieldStyle	mailReg		    = new TextfieldStyle(390, 300, 220, 36, "textfield", "font", 16F, Color.DARK_GRAY, Color.WHITE, new EmptyBorder(0, 10, 0, 10));
	
	public static ComponentStyle textloginReg		  = new ComponentStyle(380, 164, -1, -1, "font", 16F, Color.WHITE, true);
	public static ComponentStyle textpasswordReg      = new ComponentStyle(380, 208, -1, -1, "font", 16F, Color.WHITE, true);
	public static ComponentStyle textpassword2Reg     = new ComponentStyle(380, 254, -1, -1, "font", 16F, Color.WHITE, true);
	public static ComponentStyle textmailReg		  = new ComponentStyle(380, 300, -1, -1, "font", 16F, Color.WHITE, true);
	 
        
        
        
    public static ButtonStyle	closereg		= new ButtonStyle	(500, 360, 120, 40, "font", "button", 16F, Color.RED, true, Align.CENTER);
	public static ButtonStyle	okreg		    = new ButtonStyle	(300, 360, 130, 40, "font", "button", 16F, Color.RED, true, Align.CENTER);
	
	public static int titleRegX 		= 362;
	public static int titleRegY 		= 140;
}