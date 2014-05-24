package wslistobj;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import modelbean.SubEquipmentTypes;

public class SubEquipmentTypesList {
	
	private List<SubEquipmentTypes> subequipmenttypes = new ArrayList<SubEquipmentTypes>();
	
	public SubEquipmentTypesList()
	{
		
	}
	
	public XStream getSubEquipmentTypesListXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("SubEquipmentType", SubEquipmentTypes.class);
		xstream.alias("subequipmenttypelist", SubEquipmentTypesList.class);
		
		return xstream;
	}
	
	public void addItems(SubEquipmentTypes item)
	{
		this.subequipmenttypes.add(item);
	}
	
	public void clearSubEquipmentTypesList()
	{
		this.subequipmenttypes.clear();
	}

	public List<SubEquipmentTypes> getSubequipmenttypes() {
		return subequipmenttypes;
	}

	public void setSubequipmenttypes(List<SubEquipmentTypes> subequipmenttypes) {
		this.subequipmenttypes = subequipmenttypes;
	}
	
}
