package controllerbean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.thoughtworks.xstream.XStream;

import wslistobj.SubEquipmentTypesList;
import wsobj.WebMethodNames;

import modelbean.SubEquipmentTypes;

public class SubEquipmentTypesManager {
	
	private List<SubEquipmentTypes> subequiptypes = new ArrayList<SubEquipmentTypes>();
	private List<SelectItem> subeqtypesbyeqcombo = new ArrayList<SelectItem>();
	
	public SubEquipmentTypesManager()
	{
		
	}
	
	public void loadSubEquipmentTypesByEquipment(String equipment)
	{
		String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.subequipmenttypes_webservice;
		Service service = new Service();
		Call call;
		
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.subequipmenttypes_getSubEquipmentTypesByEquipment);
			
			String xmlstr = (String) call.invoke(new Object[]{equipment});
			
			if(xmlstr.length() > 0)
			{
				//System.out.println(xmlstr);
				SubEquipmentTypesList subeqlist = new SubEquipmentTypesList();
				XStream xstream = subeqlist.getSubEquipmentTypesListXStream();
				//System.out.println(xmlstr);
				subeqlist = (SubEquipmentTypesList) xstream.fromXML(xmlstr);
				//System.out.println("Subequipment list size: " + subeqlist.getSubequipmenttypes().size());
				this.subequiptypes = subeqlist.getSubequipmenttypes();
				//System.out.println("Sub equipment list size: " + this.subequiptypes.size());
				this.generateSubEquipmentTypes();
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateSubEquipmentTypes()
	{
		this.subeqtypesbyeqcombo.clear();
		
		Iterator<SubEquipmentTypes> it = this.subequiptypes.iterator();
		
		while(it.hasNext())
		{
			SubEquipmentTypes tmp = it.next();
			this.subeqtypesbyeqcombo.add(new SelectItem(tmp.getDescription(), "" + tmp.getId()));
		}
		
	}

	public List<SubEquipmentTypes> getSubequiptypes() {
		return subequiptypes;
	}

	public void setSubequiptypes(List<SubEquipmentTypes> subequiptypes) {
		this.subequiptypes = subequiptypes;
	}

	public List<SelectItem> getSubeqtypesbyeqcombo() {
		return subeqtypesbyeqcombo;
	}

	public void setSubeqtypesbyeqcombo(List<SelectItem> subeqtypesbyeqcombo) {
		this.subeqtypesbyeqcombo = subeqtypesbyeqcombo;
	}
	
}
