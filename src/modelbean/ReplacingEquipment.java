package modelbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ReplacingEquipment
{
	private long equipid;
	private String equipment;

	public ReplacingEquipment()
	{
		
	}
	
	public XStream getReplacingEquipmentXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("ReplacingEquimpent", ReplacingEquipment.class);
		
		return xstream;
	}

	public long getEquipid()
	{
		return equipid;
	}

	public void setEquipid(long equipid)
	{
		this.equipid = equipid;
	}

	public String getEquipment()
	{
		return equipment;
	}

	public void setEquipment(String equipment)
	{
		this.equipment = equipment;
	}
	
	
}
