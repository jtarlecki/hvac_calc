package wslistobj;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import modelbean.UOM;

public class UOMList {
	private List<UOM> uoms;
	
	public UOMList()
	{
		
	}
	
	public XStream getUOMListXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		
		xstream.alias("UOM", UOM.class);
		xstream.alias("UOMList", UOMList.class);
		
		return xstream;
	}

	public List<UOM> getUoms() {
		return uoms;
	}

	public void setUoms(List<UOM> uoms) {
		this.uoms = uoms;
	}
}
