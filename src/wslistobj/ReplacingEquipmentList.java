package wslistobj;

import java.util.ArrayList;
import java.util.List;

import modelbean.ReplacingEquipment;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class ReplacingEquipmentList
{
	private List<ReplacingEquipment> equips = new ArrayList<ReplacingEquipment>();
	
	public ReplacingEquipmentList()
	{
		
	}
	
	public XStream getReplacingEquipmentsXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("ReplacingEquipment", ReplacingEquipment.class);
		xstream.alias("ReplacingEquipmentList", ReplacingEquipment.class);
		
		return xstream;
	}
	
	public void addReplacingEquipments(ReplacingEquipment item)
	{
		this.equips.add(item);
	}
	
	public void clearReplacingEquipments()
	{
		this.equips.clear();
	}

	public List<ReplacingEquipment> getEquips()
	{
		return equips;
	}

	public void setEquips(List<ReplacingEquipment> equips)
	{
		this.equips = equips;
	}
	
	
}
