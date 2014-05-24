package wslistobj;

import java.util.ArrayList;
import java.util.List;

import modelbean.OEM;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class OEMList
{
	private List<OEM> oems;
	
	public OEMList()
	{
		oems = new ArrayList<OEM>();
	}
	
	public void addOEM(OEM item)
	{
		this.oems.add(item);
	}
	
	public void clearOEMs()
	{
		this.oems.clear();
	}

	public List<OEM> getOems()
	{
		return oems;
	}

	public void setOems(List<OEM> oems)
	{
		this.oems = oems;
	}
	
	public XStream getOEMXmlStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("oem", OEM.class);
		xstream.alias("oemlist", OEMList.class);
		
		return xstream;
	}
	
}
