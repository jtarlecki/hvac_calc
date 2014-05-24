package controllerbean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.xml.rpc.ServiceException;

import loginbean.User;
import modelbean.Equipment;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

import com.thoughtworks.xstream.XStream;

import utilitybean.ManagedBeanObject;
import wslistobj.EquipmentList;
import wsobj.WebMethodNames;


public class EquipmentManager
{
	
	private String sortMode="single";
	private String selectionMode="multi";
	private Selection selectionequipbyoem= new SimpleSelection();
	private ExtendedTableDataModel<Equipment> datamodelequipbyoem;
	
	private List<Equipment> selectedequipbyoem = new ArrayList<Equipment>();
	
	private List<Equipment> equipmentbytrade = new ArrayList<Equipment>();
	private List<SelectItem> equipmentbytradecombo = new ArrayList<SelectItem>();
	
	private List<Equipment> equipmentsbyoem = new ArrayList<Equipment>();
	private List<SelectItem> equipmentbyoemcombobox = new ArrayList<SelectItem>();
	
	private List<Equipment> equipmentsrebatesbyoem = new ArrayList<Equipment>();
	
	{
		loadEquipmentByOEM("Trane");
	}
	
	public EquipmentManager()
	{
		
	}
	
	public void loadEquipmentByOEM(String oems)
	{
		//EquipmentDAO equipmentdao = new EquipmentDAO();
		
		User usr = (User) ManagedBeanObject.getManagedBean("userBean");
		String emptype = usr.getLoginemp().getCompanyname();
		
		
		try
		{
			this.selectedequipbyoem.clear();
			this.selectionequipbyoem = null;
			this.selectionequipbyoem= new SimpleSelection();
			this.datamodelequipbyoem = null;
			this.equipmentsbyoem.clear();
			//this.equipmentsbyoem = equipmentdao.getEquipmentByOEM(oems);
			
			String endpointaddress = WebMethodNames.endpointurl
					+ WebMethodNames.equipment_webservice;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.equipment_loadEquipmentByOEM);
			
			String xmlstr = (String) call.invoke(new Object[]{emptype});
			EquipmentList equiplist = new EquipmentList();
			XStream xstream = equiplist.getEquipmentListXStream();
			equiplist = (EquipmentList) xstream.fromXML(xmlstr);
			
			this.equipmentsbyoem = equiplist.getEquipments();
			
			generateEquipmnetByOEMeCombo();
			
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
	
	public void loadEquipmentByOEMAndZip
	(
			String zipcodes, 
			String oems, 
			String utilityname,
			int newconstruction,
			int retrofit,
			long sessionid,
			String projectname,
			String username
	)
	{
		//EquipmentDAO equipmentdao = new EquipmentDAO();
		try
		{
			this.selectedequipbyoem.clear();
			this.selectionequipbyoem = null;
			this.selectionequipbyoem= new SimpleSelection();
			this.datamodelequipbyoem = null;
			this.equipmentsrebatesbyoem.clear();
			//this.equipmentsbyoem = equipmentdao.getEquipmentByOEMAndZip(zipcodes, oems);
			
			String endpointaddress = WebMethodNames.endpointurl
					+ WebMethodNames.equipment_webservice;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.equipment_loadEquipmentByOEMAndZip);
			
			String xmlstr = (String) call.invoke(new Object[]{zipcodes, oems, utilityname, newconstruction, retrofit, sessionid, projectname, username});
			EquipmentList equiplist = new EquipmentList();
			XStream xstream = equiplist.getEquipmentListXStream();
			equiplist = (EquipmentList) xstream.fromXML(xmlstr);
			
			this.equipmentsrebatesbyoem = equiplist.getEquipments();
			
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
	
	public void loadEquipmentByTrade(String tradenames)
	{
		//EquipmentDAO eqdao = new EquipmentDAO();
		User usr = (User) ManagedBeanObject.getManagedBean("userBean");
		String emptype = usr.getLoginemp().getCompanyname();
		try
		{
			this.equipmentbytradecombo.clear();
			this.equipmentbytrade.clear();
			//this.equipmentbytrade = eqdao.getEquipmentByTrade(tradenames);
			
			String endpointaddress = WebMethodNames.endpointurl
					+ WebMethodNames.equipment_webservice;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.equipment_loadEquipmentByTrade);
			
			String xmlstr = (String) call.invoke(new Object[]{emptype});
			EquipmentList equiplist = new EquipmentList();
			XStream xstream = equiplist.getEquipmentListXStream();
			equiplist = (EquipmentList) xstream.fromXML(xmlstr);
			
			this.equipmentbytrade = equiplist.getEquipments();
			
			generateEquipmnetByTradeCombo();
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
	
	public void generateEquipmnetByTradeCombo()
	{
		this.equipmentbytradecombo.clear();
		Iterator<Equipment> it = this.equipmentbytrade.iterator();
		while(it.hasNext())
		{
			Equipment tmpeq = it.next();
			this.equipmentbytradecombo.add(new SelectItem(tmpeq.getModelnumber(), tmpeq.getModelnumber()));
		}
	}
	
	public void generateEquipmnetByOEMeCombo()
	{
		this.equipmentbyoemcombobox.clear();
		Iterator<Equipment> it = this.equipmentsbyoem.iterator();
		while(it.hasNext())
		{
			Equipment tmpeq = it.next();
			this.equipmentbyoemcombobox.add(new SelectItem(tmpeq.getModelnumber(), tmpeq.getModelnumber()));
		}
	}
	
	public Equipment getSelectedEquipmnetByTradeModel(String modelnumber)
	{
		Iterator<Equipment> it = this.equipmentbytrade.iterator();
		
		while(it.hasNext())
		{
			Equipment tmpeq = it.next();
			if(tmpeq.getModelnumber().equals(modelnumber))
			{
				return tmpeq;
			}
		}
		return null;
	}
	
	public Equipment getSelectedEquipmnetByOEMModel(String modelnumber)
	{
		Iterator<Equipment> it = this.equipmentsbyoem.iterator();
		
		while(it.hasNext())
		{
			Equipment tmpeq = it.next();
			if(tmpeq.getModelnumber().equals(modelnumber))
			{
				return tmpeq;
			}
		}
		return null;
	}
	
	public ExtendedTableDataModel<Equipment> getEquipmentDataModelByOEM()
	{
		this.datamodelequipbyoem = new ExtendedTableDataModel<Equipment>
		(
				new DataProvider<Equipment>()
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Equipment getItemByKey(Object key)
					{
						// TODO Auto-generated method stub
						for(Equipment eq : equipmentsrebatesbyoem)
						{
							if(key.equals(getKey(eq)))
							{
								return eq;
							}
						}
						return null;
					}

					@Override
					public List<Equipment> getItemsByRange(int firstrow, int lastrow)
					{
						// TODO Auto-generated method stub
						return equipmentsrebatesbyoem.subList(firstrow, lastrow);
					}

					@Override
					public Object getKey(Equipment item)
					{
						// TODO Auto-generated method stub
						return item.getEquipid();
					}

					@Override
					public int getRowCount()
					{
						// TODO Auto-generated method stub
						return equipmentsrebatesbyoem.size();
					}
					
				}
		);
		return this.datamodelequipbyoem;
	}

	public void equipbyoemSelectionChanged()
	{
		this.selectedequipbyoem.clear();
		Iterator<Object> it = this.getSelectionequipbyoem().getKeys();
		while(it.hasNext())
		{
			Object key = it.next();
			this.selectedequipbyoem.add(this.getDatamodelequipbyoem().getObjectByKey(key));
		}
	}
	
	
	
	public List<Equipment> getEquipmentsbyoem()
	{
		return equipmentsbyoem;
	}

	public void setEquipmentsbyoem(List<Equipment> equipmentsbyoem)
	{
		this.equipmentsbyoem = equipmentsbyoem;
	}

	public String getSortMode()
	{
		return sortMode;
	}

	public void setSortMode(String sortMode)
	{
		this.sortMode = sortMode;
	}

	public String getSelectionMode()
	{
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode)
	{
		this.selectionMode = selectionMode;
	}

	public Selection getSelectionequipbyoem()
	{
		return selectionequipbyoem;
	}

	public void setSelectionequipbyoem(Selection selectionequipbyoem)
	{
		this.selectionequipbyoem = selectionequipbyoem;
	}

	public ExtendedTableDataModel<Equipment> getDatamodelequipbyoem()
	{
		return datamodelequipbyoem;
	}

	public void setDatamodelequipbyoem(
			ExtendedTableDataModel<Equipment> datamodelequipbyoem)
	{
		this.datamodelequipbyoem = datamodelequipbyoem;
	}

	public List<Equipment> getSelectedequipbyoem()
	{
		return selectedequipbyoem;
	}

	public void setSelectedequipbyoem(List<Equipment> selectedequipbyoem)
	{
		this.selectedequipbyoem = selectedequipbyoem;
	}

	public List<Equipment> getEquipmentbytrade()
	{
		return equipmentbytrade;
	}

	public void setEquipmentbytrade(List<Equipment> equipmentbytrade)
	{
		this.equipmentbytrade = equipmentbytrade;
	}

	public List<SelectItem> getEquipmentbytradecombo()
	{
		return equipmentbytradecombo;
	}

	public void setEquipmentbytradecombo(List<SelectItem> equipmentbytradecombo)
	{
		this.equipmentbytradecombo = equipmentbytradecombo;
	}

	public List<SelectItem> getEquipmentbyoemcombobox() {
		return equipmentbyoemcombobox;
	}

	public void setEquipmentbyoemcombobox(List<SelectItem> equipmentbyoemcombobox) {
		this.equipmentbyoemcombobox = equipmentbyoemcombobox;
	}

	public List<Equipment> getEquipmentsrebatesbyoem() {
		return equipmentsrebatesbyoem;
	}

	public void setEquipmentsrebatesbyoem(List<Equipment> equipmentsrebatesbyoem) {
		this.equipmentsrebatesbyoem = equipmentsrebatesbyoem;
	}
	
	
}
