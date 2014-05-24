package controllerbean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import modelbean.OEM;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

import com.thoughtworks.xstream.XStream;

import wslistobj.OEMList;
import wsobj.WebMethodNames;


public class OEMManager
{
	private List<OEM> oems = new ArrayList<OEM>();
	private String sortMode="single";
	private String selectionMode="multi";
	private Selection selectionoems = new SimpleSelection();
	private ExtendedTableDataModel<OEM> datamodeloems;
	
	private String operatorsoem = "Trane";
	
	private List<OEM> selectedoem = new ArrayList<OEM>();
	
	
	{
		//loadOEMs();
		loadOEMsWithWebService();
	}
	
	public OEMManager()
	{
		
	}
	
	/*
	public void loadOEMs()
	{
		OEMDAO oemdao = new OEMDAO();
		try
		{
			this.selectedoem.clear();
			this.selectionoems = null;
			this.selectionoems = new SimpleSelection();
			this.datamodeloems = null;
			
			this.oems.clear();
			this.oems = oemdao.getOEMList();
		} catch (JDOMException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public void loadOEMsWithWebService()
	{
		
		try
		{
			//User usr = (User) 
			//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userBean");
			//if(usr.getEmpid() > 0)
			//{
				String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.oem_webservice;
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpointaddress);
				call.setOperation(WebMethodNames.oem_opt_OEMsXML);
			
				OEMList oemlist = new OEMList();
				XStream xstream = oemlist.getOEMXmlStream();
				String oemsxmlstr = (String) call.invoke(new Object[]{});
				oemlist = (OEMList) xstream.fromXML(oemsxmlstr);
				this.oems.clear();
				this.oems = oemlist.getOems();
			//}
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
	
	public ExtendedTableDataModel<OEM> getOEMDataModel()
	{
		this.datamodeloems = new ExtendedTableDataModel<OEM>
		(
				new DataProvider<OEM>()
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public OEM getItemByKey(Object key)
					{
						// TODO Auto-generated method stub
						for(OEM tmpoem : oems)
						{
							if(key.equals(getKey(tmpoem)))
							{
								return tmpoem;
							}
						}
						return null;
					}

					@Override
					public List<OEM> getItemsByRange(int firstrow, int lastrow)
					{
						// TODO Auto-generated method stub
						return oems.subList(firstrow, lastrow);
					}

					@Override
					public Object getKey(OEM item)
					{
						// TODO Auto-generated method stub
						return item.getOemid();
					}

					@Override
					public int getRowCount()
					{
						// TODO Auto-generated method stub
						return oems.size();
					}
					
				}
		);
		return this.datamodeloems;
	}
	
	public void oemSelectionChanged()
	{
		this.selectedoem.clear();
		Iterator<Object> it = this.getSelectionoems().getKeys();
		while(it.hasNext())
		{
			Object key = it.next();
			this.selectedoem.add(this.datamodeloems.getObjectByKey(key));
		}
	}
	
	public String convertSelectedOEMToString()
	{
		String concatoem = "";
		Iterator<OEM> it = this.selectedoem.iterator();
		while(it.hasNext())
		{
			OEM tmpoem = it.next();
			if(concatoem.length() > 0)
			{
				concatoem = concatoem + "|";
			}
			concatoem = concatoem + tmpoem.getOemname();
		}
		return concatoem;
	}

	public List<OEM> getOems()
	{
		return oems;
	}

	public void setOems(List<OEM> oems)
	{
		this.oems = oems;
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

	public Selection getSelectionoems()
	{
		return selectionoems;
	}

	public void setSelectionoems(Selection selectionoems)
	{
		this.selectionoems = selectionoems;
	}

	public ExtendedTableDataModel<OEM> getDatamodeloems()
	{
		return datamodeloems;
	}

	public void setDatamodeloems(ExtendedTableDataModel<OEM> datamodeloems)
	{
		this.datamodeloems = datamodeloems;
	}

	public List<OEM> getSelectedoem()
	{
		return selectedoem;
	}

	public void setSelectedoem(List<OEM> selectedoem)
	{
		this.selectedoem = selectedoem;
	}

	public String getOperatorsoem()
	{
		return operatorsoem;
	}

	public void setOperatorsoem(String operatorsoem)
	{
		this.operatorsoem = operatorsoem;
	}
	
	
}
