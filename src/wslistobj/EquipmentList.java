package wslistobj;

import java.util.ArrayList;
import java.util.List;

import modelbean.Equipment;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class EquipmentList
{
	private List<Equipment> equipments = new ArrayList<Equipment>();
	
	public EquipmentList()
	{
		
	}
	
	public void addEquipment(Equipment item)
	{
		this.equipments.add(item);
	} 
	
	public void clearEquipments()
	{
		this.equipments.clear();
	}
	
	public XStream getEquipmentListXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Equipment", Equipment.class);
		xstream.alias("EquipmentList", EquipmentList.class);
		
		return xstream;
	}

	public List<Equipment> getEquipments()
	{
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments)
	{
		this.equipments = equipments;
	}
	
	
}
