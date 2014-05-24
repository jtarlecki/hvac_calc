package modelbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ProjectTypes
{
	private long projecttypeid;
	private String projecttypes;
	
	public ProjectTypes()
	{
		
	}
	
	public XStream getProjectTypeXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("ProjectTypes", ProjectTypes.class);
		
		return xstream;
	}

	public long getProjecttypeid()
	{
		return projecttypeid;
	}

	public void setProjecttypeid(long projecttypeid)
	{
		this.projecttypeid = projecttypeid;
	}

	public String getProjecttypes()
	{
		return projecttypes;
	}

	public void setProjecttypes(String projecttypes)
	{
		this.projecttypes = projecttypes;
	}
}
