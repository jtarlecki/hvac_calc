package wslistobj;

import java.util.ArrayList;
import java.util.List;

import modelbean.ToolbarItem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class ToolbarList
{
	List<ToolbarItem> toolbaritems = new ArrayList<ToolbarItem>();
	
	public ToolbarList()
	{
		
	}
	
	public void addToolbarItem(ToolbarItem item)
	{
		this.toolbaritems.add(item);
	}
	
	public void clearToolbarItems()
	{
		this.toolbaritems.clear();
	}
	
	public XStream toolbaritemsXStreem()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("ToolbarItem", ToolbarItem.class);
		xstream.alias("ToolbarItems", ToolbarList.class);
		
		return xstream;
	}

	public List<ToolbarItem> getToolbaritems()
	{
		return toolbaritems;
	}

	public void setToolbaritems(List<ToolbarItem> toolbaritems)
	{
		this.toolbaritems = toolbaritems;
	}
	
	
}
