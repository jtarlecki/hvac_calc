package wslistobj;

import java.util.ArrayList;
import java.util.List;

import modelbean.Utility;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class UtilityList
{
	private List<Utility> utilities = new ArrayList<Utility>();
	
	public UtilityList()
	{
		
	}
	
	public void addUtility(Utility item)
	{
		this.utilities.add(item);
	}
	
	public void clearUtilities()
	{
		this.utilities.clear();
	}
	
	public XStream getUtilityXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Utility", Utility.class);
		xstream.alias("Utilities", UtilityList.class);
		
		return xstream;
	}

	public List<Utility> getUtilities()
	{
		return utilities;
	}

	public void setUtilities(List<Utility> utilities)
	{
		this.utilities = utilities;
	}
	
	
}
