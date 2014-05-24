package wslistobj;

import java.util.ArrayList;
import java.util.List;

import modelbean.BuildingTypes;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class BuildingTypesList
{
	private List<BuildingTypes> buildingtypes = new ArrayList<BuildingTypes>();
	
	public BuildingTypesList()
	{
		
	}
	
	public void addBuildingTypes(BuildingTypes item)
	{
		this.buildingtypes.add(item);
	}
	
	public void clearBuildingTypes()
	{
		this.buildingtypes.clear();
	}
	
	public XStream getBuildingTypesXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("buildingtype", BuildingTypes.class);
		xstream.alias("BuildingTypesList", BuildingTypesList.class);
		
		return xstream;
	}

	public List<BuildingTypes> getBuildingtypes()
	{
		return buildingtypes;
	}

	public void setBuildingtypes(List<BuildingTypes> buildingtypes)
	{
		this.buildingtypes = buildingtypes;
	}
	
	
}
