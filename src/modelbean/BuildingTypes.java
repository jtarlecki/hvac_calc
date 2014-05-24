package modelbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BuildingTypes
{
	private long buildingtypeid;
	private String buildingtypecode;
	private String buildingtypedescription;
	
	public BuildingTypes()
	{
		
	}
	
	public XStream getBuildingTypesXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("BuildingTypes", BuildingTypes.class);
		
		return xstream;
	}

	public long getBuildingtypeid()
	{
		return buildingtypeid;
	}

	public void setBuildingtypeid(long buildingtypeid)
	{
		this.buildingtypeid = buildingtypeid;
	}

	public String getBuildingtypecode()
	{
		return buildingtypecode;
	}

	public void setBuildingtypecode(String buildingtypecode)
	{
		this.buildingtypecode = buildingtypecode;
	}

	public String getBuildingtypedescription()
	{
		return buildingtypedescription;
	}

	public void setBuildingtypedescription(String buildingtypedescription)
	{
		this.buildingtypedescription = buildingtypedescription;
	}
	
	

}
