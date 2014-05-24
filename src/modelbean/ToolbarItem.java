package modelbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ToolbarItem
{
	private int menuitemid = 0;
	private String menuitemlabel = "";
	private String menuitemimage = "";
	private String menuitemurl = "";
	private int positionintoolbar = 0;
	private String tooltip = "";
	
	public ToolbarItem()
	{
		
	}
	
	public ToolbarItem
	(
			int menuitemid,
			String menuitemlabel,
			String menuitemimage,
			String menuitemurl,
			int positionintoolbar,
			String tooltip
	)
	{
		this.menuitemid = menuitemid;
		this.menuitemlabel = menuitemlabel;
		this.menuitemimage = menuitemimage;
		this.menuitemurl = menuitemurl;
		this.positionintoolbar = positionintoolbar;
		this.tooltip = tooltip;
	}
	
	public XStream getToolbarItemXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("ToolberItem", ToolbarItem.class);
		
		return xstream;
	}

	public int getMenuitemid()
	{
		return menuitemid;
	}

	public void setMenuitemid(int menuitemid)
	{
		this.menuitemid = menuitemid;
	}

	public String getMenuitemlabel()
	{
		return menuitemlabel;
	}

	public void setMenuitemlabel(String menuitemlabel)
	{
		this.menuitemlabel = menuitemlabel;
	}

	public String getMenuitemimage()
	{
		return menuitemimage;
	}

	public void setMenuitemimage(String menuitemimage)
	{
		this.menuitemimage = menuitemimage;
	}

	public String getMenuitemurl()
	{
		return menuitemurl;
	}

	public void setMenuitemurl(String menuitemurl)
	{
		this.menuitemurl = menuitemurl;
	}

	public int getPositionintoolbar()
	{
		return positionintoolbar;
	}

	public void setPositionintoolbar(int positionintoolbar)
	{
		this.positionintoolbar = positionintoolbar;
	}

	public String getTooltip()
	{
		return tooltip;
	}

	public void setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
	}
	
	
}
