package controllerbean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.xml.rpc.ServiceException;

import modelbean.Utility;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

import com.thoughtworks.xstream.XStream;

import wslistobj.UtilityList;
import wsobj.WebMethodNames;


public class UtilityManager
{
	private String sortMode="single";
	private String selectionMode="multi";
	private List<Utility> dropdownutilities = new ArrayList<Utility>();
	private List<Utility> selectedutl = new ArrayList<Utility>();
	private Object tablestate;
	private String[] selectedutilities;
	private Selection selection = new SimpleSelection();
	private List<SelectItem> utilitynames = new ArrayList<SelectItem>();
	private ExtendedTableDataModel<Utility> datamodel;
	
	private List<Utility> utilitybyzip = new ArrayList<Utility>();
	private List<SelectItem> utilitybyzipcombo = new ArrayList<SelectItem>();

	
	public UtilityManager()
	{
		//System.out.println("Utility manager initiated.");
	}

	
	public void loadUtilityByZipCode(String zipcode)
	{
		//System.out.println("Selected zipcode: " + zipcode);
		//UtilityDAO utilitydao = new UtilityDAO();
		try
		{
			this.utilitybyzip.clear();
			//this.utilitybyzip  = utilitydao.getZipUtilityByZipCode(zipcode);
			//System.out.println("listsize: " + this.utilitybyzip.size());
			
			String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.utility_webservice;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.utility_loadUtilityByZipCode);
			
			String xmlstr = (String) call.invoke(new Object[]{zipcode});
			UtilityList utllist = new UtilityList();
			XStream xstream = utllist.getUtilityXStream();
			
			utllist = (UtilityList) xstream.fromXML(xmlstr);
			this.utilitybyzip = utllist.getUtilities();
			
			generateUtilityByZipCode();
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
	
	public ExtendedTableDataModel<Utility> getUtilityDataModel()
	{
		
			this.datamodel = new ExtendedTableDataModel<Utility>
			(
					new DataProvider<Utility>()
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public Utility getItemByKey(Object key)
						{
							// TODO Auto-generated method stub
							for(Utility utl : dropdownutilities)
							{
								if(key.equals(getKey(utl)))
								{
									return utl;
								}
							}
							return null;
						}

						@Override
						public List<Utility> getItemsByRange(int startrow, int endrow)
						{
							// TODO Auto-generated method stub
							return dropdownutilities.subList(startrow, endrow);
						}

						@Override
						public Object getKey(Utility item)
						{
							// TODO Auto-generated method stub
							return item.getId();
						}

						@Override
						public int getRowCount()
						{
							// TODO Auto-generated method stub
							return dropdownutilities.size();
						}
						
					}
			);

		
		return this.datamodel;
	}

	public void generateUtilityByZipCode()
	{
		this.utilitybyzipcombo.clear();
		 Iterator<Utility> it = this.utilitybyzip.iterator();
		 while(it.hasNext())
		 {
			 Utility tmputl = it.next();
			 this.utilitybyzipcombo.add(new SelectItem(tmputl.getUtilityname(), "" + tmputl.getId()));
		 }
	}
	
	public List<SelectItem> getUtilityCombo(List<Utility> utl)
	{
		List<SelectItem> zuitems = new ArrayList<SelectItem>();
		
		Iterator<Utility> it = utl.iterator();
		 while(it.hasNext())
		 {
			 Utility tmputl = it.next();
			 zuitems.add(new SelectItem(tmputl.getUtilityname(), "" + tmputl.getId()));
		 }
		
		return zuitems;
	}
	
	// helper
	public String convertUtilityArrayToString()
	{
		String statestr = "";
		int i = 0;
		
		for (String selectedItem : this.selectedutilities) 
		{
			if(i > 0)
				statestr = statestr + "|";
			statestr = statestr + selectedItem; 
			i++;
	    }
		
		return statestr;
	}
	
	public String convertSelectedUtilityArrayToString()
	{
		String statestr = "";
		int i = 0;
		
		for (Utility selectedItem : this.selectedutl) 
		{
			if(i > 0)
				statestr = statestr + "|";
			statestr = statestr + selectedItem.getId(); 
			i++;
	    }
		
		return statestr;
	}
	
	public Utility getSelectedUtilityByKey(long key)
	{
		for(Utility utl : this.dropdownutilities)
		{
			System.out.println("key: " + key + " utility key: " + utl.getId());
			if(key == utl.getId())
			{
				return utl;
			}
		}
		return null;
	}
	
	public List<Utility> getDropdownutilities()
	{
		return dropdownutilities;
	}

	public String[] getSelectedutilities()
	{
		return selectedutilities;
	}

	public void setDropdownutilities(List<Utility> dropdownutilities)
	{
		this.dropdownutilities = dropdownutilities;
	}

	public void setSelectedutilities(String[] selectedutilities)
	{
		this.selectedutilities = selectedutilities;
	}
	
	public List<SelectItem> getUtilitynames()
	{
		return utilitynames;
	}

	public void setUtilitynames(List<SelectItem> utilitynames)
	{
		this.utilitynames = utilitynames;
	}
	
	public Object getTablestate()
	{
		return tablestate;
	}

	public void setTablestate(Object tablestate)
	{
		this.tablestate = tablestate;
	}

	
	public Selection getSelection()
	{
		return selection;
	}

	public void setSelection(Selection selection)
	{
		this.selection = selection;
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
	
	public List<Utility> getSelectedutl()
	{
		return selectedutl;
	}

	public void setSelectedutl(List<Utility> selectedutl)
	{
		this.selectedutl = selectedutl;
	}

	public ExtendedTableDataModel<Utility> getDatamodel()
	{
		return datamodel;
	}

	public void setDatamodel(ExtendedTableDataModel<Utility> datamodel)
	{
		this.datamodel = datamodel;
	}

	public List<Utility> getUtilitybyzip()
	{
		return utilitybyzip;
	}

	public void setUtilitybyzip(List<Utility> utilitybyzip)
	{
		this.utilitybyzip = utilitybyzip;
	}

	public List<SelectItem> getUtilitybyzipcombo()
	{
		return utilitybyzipcombo;
	}

	public void setUtilitybyzipcombo(List<SelectItem> utilitybyzipcombo)
	{
		this.utilitybyzipcombo = utilitybyzipcombo;
	}
	
}
