package wslistobj;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import modelbean.EquipmentTypes;

public class EquipmentTypesList {
	private List<EquipmentTypes> equipmenttypes = new ArrayList<EquipmentTypes>();
	
	public EquipmentTypesList()
	{
		
	}
	
	public XStream getEquipmentTypesListXStream()
	{
		XStream xstream = new XStream();
		xstream.alias("EquipmentTypes", EquipmentTypes.class);
		xstream.alias("EquipmentTypesList", EquipmentTypesList.class);
		
		return xstream;
	}

	public List<EquipmentTypes> getEquipmenttypes() {
		return equipmenttypes;
	}

	public void setEquipmenttypes(List<EquipmentTypes> equipmenttypes) {
		this.equipmenttypes = equipmenttypes;
	}
}
