package wslistobj;

import java.util.ArrayList;
import java.util.List;

import modelbean.ProjectTypes;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class ProjectTypesList
{
	private List<ProjectTypes> projecttypes = new ArrayList<ProjectTypes>();
	
	public ProjectTypesList()
	{
		
	}
	
	public XStream getProjectTypesListXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("projecttype", ProjectTypes.class);
		xstream.alias("ProjectTypesList", ProjectTypesList.class);
		
		return xstream;
	}
	
	public void addProjectTypes(ProjectTypes item)
	{
		this.projecttypes.add(item);
	}
	
	public void clearProjectTypes()
	{
		this.projecttypes.clear();
	}

	public List<ProjectTypes> getProjecttypes()
	{
		return projecttypes;
	}

	public void setProjecttypes(List<ProjectTypes> projecttypes)
	{
		this.projecttypes = projecttypes;
	}
}
