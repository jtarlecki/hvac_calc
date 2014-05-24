package modelbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SubEquipmentTypes {
	
	private long id = 0;
	private String code;
	private String description;
	
	public SubEquipmentTypes()
	{
		
	}
	
	public SubEquipmentTypes
	(
			long id,
			String code,
			String description
	)
	{
		this.id = id;
		this.code = code;
		this.description = description;
	}
	
	public XStream getSubEquipmentTypesXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("SubEquipmentTypes", SubEquipmentTypes.class);
		
		return xstream;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
