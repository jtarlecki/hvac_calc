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

import wslistobj.EquipmentTypesList;
import wsobj.WebMethodNames;

import modelbean.EquipmentTypes;

public class EquipmentTypesManager {
	private List<EquipmentTypes> equipmenttypes = new ArrayList<EquipmentTypes>();
	
	{
		loadEquipmentTypesManager();
	}
	
	public EquipmentTypesManager()
	{
		
	}
	
	private void loadEquipmentTypesManager()
	{
		String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.equipmenttypes_webservice;
		Service service = new Service();
		
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.equipmenttypes_getEquipmentTypes);
			
			String xmlstr = (String) call.invoke(new Object[]{});
			
			if(xmlstr.length() > 0)
			{
				EquipmentTypesList eqtplist = new EquipmentTypesList();
				XStream xstream = eqtplist.getEquipmentTypesListXStream();
				eqtplist = (EquipmentTypesList) xstream.fromXML(xmlstr);
				this.equipmenttypes = eqtplist.getEquipmenttypes();
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<SelectItem> getEquipmentTypesCombo()
	{
		List<SelectItem> eqtpcombo = new ArrayList<SelectItem>();
		
		Iterator<EquipmentTypes> it = this.equipmenttypes.iterator();
		
		while(it.hasNext())
		{
			EquipmentTypes tmp = it.next();
			eqtpcombo.add(new SelectItem(tmp.getDescription(), "" + tmp.getId()));
		}
		
		return eqtpcombo;
	}

	public List<EquipmentTypes> getEquipmenttypes() {
		return equipmenttypes;
	}

	public void setEquipmenttypes(List<EquipmentTypes> equipmenttypes) {
		this.equipmenttypes = equipmenttypes;
	}
}
