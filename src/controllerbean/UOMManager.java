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

import wslistobj.UOMList;
import wsobj.WebMethodNames;

import modelbean.UOM;

public class UOMManager {
	private List<UOM> convertableuoms = new ArrayList<UOM>();
	private List<UOM> flvuoms = new ArrayList<UOM>();
	private List<UOM> iplvuoms = new ArrayList<UOM>();
	private List<UOM> sizeuoms = new ArrayList<UOM>();
	
	{
		loadUOMsByTypes("Convertable Units");
		loadUOMsByTypes("Full Load");
		loadUOMsByTypes("Part Load");
		loadUOMsByTypes("Size UOM");
	}
	
	public UOMManager()
	{
		
	}
	
	public void loadUOMsByTypes(String types)
	{
		String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.uom_webservice;
		Service service = new Service();
		
		Call call;
		
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.uom_getUOMsByTypes);
			
			String xmlstr = (String) call.invoke(new Object[]{types});
			
			if(xmlstr.length() > 0)
			{
				UOMList uomlist = new UOMList();
				XStream xstream = uomlist.getUOMListXStream();
				uomlist = (UOMList) xstream.fromXML(xmlstr);
				if(types == "Full Load")
				{
					this.flvuoms = uomlist.getUoms();
				}
				else if(types == "Part Load")
				{
					this.iplvuoms = uomlist.getUoms();
				}
				else if(types == "Size UOM")
				{
					this.sizeuoms = uomlist.getUoms();
				}
				else
				{
					this.convertableuoms = uomlist.getUoms();
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<SelectItem> getUOMByConvertableCombo()
	{
		List<SelectItem> cnvtuom = new ArrayList<SelectItem>();
		
		Iterator<UOM> it = this.convertableuoms.iterator();
		while(it.hasNext())
		{
			UOM tmp = it.next();
			cnvtuom.add(new SelectItem(tmp.getUnits(), "" + tmp.getId()));
		}
		
		return cnvtuom;
	}
	
	public List<SelectItem> getFlvUOMByConvertableCombo()
	{
		List<SelectItem> cnvtuom = new ArrayList<SelectItem>();
		
		Iterator<UOM> it = this.flvuoms.iterator();
		while(it.hasNext())
		{
			UOM tmp = it.next();
			cnvtuom.add(new SelectItem(tmp.getUnits(), "" + tmp.getId()));
		}
		
		return cnvtuom;
	}
	
	public List<SelectItem> getIplvUOMByConvertableCombo()
	{
		List<SelectItem> cnvtuom = new ArrayList<SelectItem>();
		
		Iterator<UOM> it = this.iplvuoms.iterator();
		while(it.hasNext())
		{
			UOM tmp = it.next();
			cnvtuom.add(new SelectItem(tmp.getUnits(), "" + tmp.getId()));
		}
		
		return cnvtuom;
	}
	
	public List<SelectItem> getSizeUOMByConvertableCombo()
	{
		List<SelectItem> cnvtuom = new ArrayList<SelectItem>();
		
		Iterator<UOM> it = this.sizeuoms.iterator();
		while(it.hasNext())
		{
			UOM tmp = it.next();
			cnvtuom.add(new SelectItem(tmp.getUnits(), "" + tmp.getId()));
		}
		
		return cnvtuom;
	}

	public List<UOM> getConvertableuoms() {
		return convertableuoms;
	}

	public void setConvertableuoms(List<UOM> convertableuoms) {
		this.convertableuoms = convertableuoms;
	}

	public List<UOM> getFlvuoms() {
		return flvuoms;
	}

	public void setFlvuoms(List<UOM> flvuoms) {
		this.flvuoms = flvuoms;
	}

	public List<UOM> getIplvuoms() {
		return iplvuoms;
	}

	public void setIplvuoms(List<UOM> iplvuoms) {
		this.iplvuoms = iplvuoms;
	}

	public List<UOM> getSizeuoms() {
		return sizeuoms;
	}

	public void setSizeuoms(List<UOM> sizeuoms) {
		this.sizeuoms = sizeuoms;
	}
}
