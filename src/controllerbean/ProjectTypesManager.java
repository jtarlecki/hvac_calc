package controllerbean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.xml.rpc.ServiceException;

import modelbean.ProjectTypes;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.thoughtworks.xstream.XStream;

import wslistobj.ProjectTypesList;
import wsobj.WebMethodNames;


public class ProjectTypesManager
{
	private List<ProjectTypes> projecttypes = new ArrayList<ProjectTypes>();
	{
		loadProjectTypes();
	}
	
	public ProjectTypesManager()
	{
		
	}
	
	private void loadProjectTypes()
	{
		this.projecttypes.clear();
		
		try
		{
			String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.projecttypes_webservice;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.projecttypes_getProjectTypes);
			
			String xmlstr = (String) call.invoke(new Object[]{});
			ProjectTypesList prjtplist = new ProjectTypesList();
			XStream xstream = prjtplist.getProjectTypesListXStream();
			prjtplist = (ProjectTypesList) xstream.fromXML(xmlstr);
			this.projecttypes = prjtplist.getProjecttypes();
			
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<SelectItem> getProjectTypesCombo()
	{
		List<SelectItem> prjlistcombo = new ArrayList<SelectItem>();
		
		Iterator<ProjectTypes> it = this.projecttypes.iterator();
		while(it.hasNext())
		{
			ProjectTypes tmp = it.next();
			prjlistcombo.add(new SelectItem(tmp.getProjecttypes(), "" + tmp.getProjecttypeid()));
		}
		
		return prjlistcombo;
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
