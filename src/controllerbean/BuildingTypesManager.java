package controllerbean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.xml.rpc.ServiceException;

import modelbean.BuildingTypes;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.thoughtworks.xstream.XStream;

import wslistobj.BuildingTypesList;
import wsobj.WebMethodNames;


public class BuildingTypesManager
{
	private List<BuildingTypes> buildingtypes = new ArrayList<BuildingTypes>();
	
	{
		loadBuildingTypes();
	}
	
	public BuildingTypesManager()
	{
		
	}
	
	private void loadBuildingTypes()
	{
		this.buildingtypes.clear();
		
		
		try
		{
			String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.buildingtypes_webservice;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.buildingtypes_getBuildingTypes);
			
			String xmlstr = (String) call.invoke(new Object[]{});
			BuildingTypesList bldtplist = new BuildingTypesList();
			XStream xstream = bldtplist.getBuildingTypesXStream();
			bldtplist = (BuildingTypesList) xstream.fromXML(xmlstr);
			this.buildingtypes = bldtplist.getBuildingtypes();
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
	
	public List<SelectItem> getBuildingTypesCombo()
	{
		List<SelectItem> bldtpcombo = new ArrayList<SelectItem>();
		
		Iterator<BuildingTypes> it = this.buildingtypes.iterator();
		while(it.hasNext())
		{
			BuildingTypes tmp = it.next();
			bldtpcombo.add(new SelectItem(tmp.getBuildingtypecode(), "" + tmp.getBuildingtypeid()));
		}
		
		return bldtpcombo;
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
