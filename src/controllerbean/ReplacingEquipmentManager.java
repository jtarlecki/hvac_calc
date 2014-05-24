package controllerbean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.xml.rpc.ServiceException;

import modelbean.ReplacingEquipment;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.thoughtworks.xstream.XStream;

import wslistobj.ReplacingEquipmentList;
import wsobj.WebMethodNames;


public class ReplacingEquipmentManager
{
	private List<ReplacingEquipment> repequip = new ArrayList<ReplacingEquipment>();
	
	{
		loadReplacementEquipments();
	}
	
	public ReplacingEquipmentManager()
	{
		
	}
	
	private void loadReplacementEquipments()
	{
		this.repequip.clear();
		
		try
		{
			String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.replacingequipment_webservice;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.replacingequipment_getReplacingEquipments);
			
			String xmlstr = (String) call.invoke(new Object[]{});
			ReplacingEquipmentList reqequiplist = new ReplacingEquipmentList();
			XStream xstream = reqequiplist.getReplacingEquipmentsXStream();
			reqequiplist = (ReplacingEquipmentList) xstream.fromXML(xmlstr);
			this.repequip = reqequiplist.getEquips();
			
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
	
	public List<SelectItem> getReplacementEquipmentCombo()
	{
		List<SelectItem> repreqcombo = new ArrayList<SelectItem>();
		
		Iterator<ReplacingEquipment> it = this.repequip.iterator();
		while(it.hasNext())
		{
			ReplacingEquipment tmp = it.next();
			repreqcombo.add(new SelectItem(tmp.getEquipment(), "" + tmp.getEquipid()));
		}
		
		return repreqcombo;
	}

	public List<ReplacingEquipment> getRepequip()
	{
		return repequip;
	}

	public void setRepequip(List<ReplacingEquipment> repequip)
	{
		this.repequip = repequip;
	}
}
