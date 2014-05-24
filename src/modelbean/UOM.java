package modelbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class UOM {
	private long id = 0;
	private String units;
	private long uomtypeid = 0;
	
	public UOM()
	{
		
	}
	
	public UOM
	(
			long id,
			String units,
			long uomtypeid
	)
	{
		this.id = id;
		this.units = units;
		this.uomtypeid = uomtypeid;
	}

	public XStream getUOMXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		
		xstream.alias("UOM", UOM.class);
		
		return xstream;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public long getUomtypeid() {
		return uomtypeid;
	}

	public void setUomtypeid(long uomtypeid) {
		this.uomtypeid = uomtypeid;
	}
	
	
}
