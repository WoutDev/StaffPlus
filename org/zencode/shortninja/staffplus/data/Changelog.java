package org.zencode.shortninja.staffplus.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.zencode.shortninja.staffplus.StaffPlus;

public class Changelog
{
	public Changelog()
	{
		try
		{
			copyFile();
		}catch(IOException exception)
		{}catch(NullPointerException exception){}
	}
	
	private void copyFile() throws IOException, NullPointerException
	{
	    File file = new File(StaffPlus.get().getDataFolder(), "changelog.txt");
	    if (!file.exists())
	    {
	    	StaffPlus.get().getDataFolder().mkdirs();
	        file.createNewFile();
	    }
	    
	    InputStream in = this.getClass().getResourceAsStream("/changelog.txt");
	    OutputStream out = new FileOutputStream(file);
	    byte[] buffer = new byte[1024];
	    int have;
	    while((have = in.read(buffer)) > -1)
	    {
	        out.write(buffer, 0, have);
	    }
	    
	    in.close();
	    out.close();
	}
}
