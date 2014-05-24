package controllerbean;


import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.xml.rpc.ServiceException;

import modelbean.MenuItem;
import modelbean.ToolbarItem;

import org.ajax4jsf.component.UIRepeat;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.richfaces.component.html.HtmlMenuItem;
import org.richfaces.component.html.HtmlToolBar;

import com.thoughtworks.xstream.XStream;

import wslistobj.ToolbarList;
import wsobj.WebMethodNames;

import dbobj.StoredProceduresName;

public class ToolbarManager
{
	private HtmlToolBar toolbar = new HtmlToolBar();
	private List<ToolbarItem> toolbaritms;
	private UIRepeat repeater;
	
	{
		generateToolbar(StoredProceduresName.defaultuserid);
	}
	
	public ToolbarManager()
	{
		
	}
	
	public List<ToolbarItem> loadToolbarItems(long usrid, int rootmenuid, int haspermission)
	{
		String endpointaddress = WebMethodNames.endpointurl
				+ WebMethodNames.toolbaritem_webservice;
		List<ToolbarItem> tmp = new ArrayList<ToolbarItem>();
		try
		{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(endpointaddress);
			
			String toolbaritemxml = (String) call.invoke(new Object[]{usrid, rootmenuid});
			
			ToolbarList tllist = new ToolbarList();
			XStream xstream = tllist.toolbaritemsXStreem();
			tllist = (ToolbarList) xstream.fromXML(toolbaritemxml);
			tmp = tllist.getToolbaritems();
		} catch (ServiceException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmp;
	}
	
	private List<ToolbarItem> getToolbarItem(long usrid, int rootmenuid)
	{
		String endpointaddress = WebMethodNames.endpointurl
				+ WebMethodNames.toolbaritem_webservice;
		//System.out.println("endpoint address: " + endpointaddress);
		List<ToolbarItem> tmp = new ArrayList<ToolbarItem>();
		try
		{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.toolbaritem_opt_loadToolbarItems);
			
			String toolbaritemxml = (String) call.invoke(new Object[]{usrid, rootmenuid});
			//System.out.println("endpoint address: " + toolbaritemxml);
			ToolbarList tllist = new ToolbarList();
			XStream xstream = tllist.toolbaritemsXStreem();
			tllist = (ToolbarList) xstream.fromXML(toolbaritemxml);
			tmp = tllist.getToolbaritems();
		} catch (ServiceException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmp;
	}
	
	public void generateToolbar(long usrid)
	{
		this.toolbar.getChildren().clear();
		this.toolbar.setId("rwwtoolbar");
		//this.toolbar.setItemSeparator("|");
		//this.toolbar.setContentClass("toolbarclass");
		//this.toolbar.setStyleClass("toolbarclass");
		List<ToolbarItem> tlitms = this.getToolbarItem(usrid, 0);
		//System.out.println("root menu size" + tlitms.size());
		
		Iterator<ToolbarItem> it = tlitms.iterator();
		while(it.hasNext())
		{
			ToolbarItem tmpitm = it.next();
			List<ToolbarItem> subtlitms = this.getToolbarItem(usrid, tmpitm.getMenuitemid());
			//System.out.println("submenu size" + subtlitms.size());
			if(subtlitms.size() > 0)
			{
				Iterator<ToolbarItem> subit = subtlitms.iterator();
				DropDownMenuItem ddmnu = new DropDownMenuItem();
				ddmnu.getDrpmnu().setId("ddmenu" + tmpitm.getMenuitemid());
				
				HtmlMenuItem dmnu = new HtmlMenuItem();
				dmnu.setId("menu" + tmpitm.getMenuitemid());
				dmnu.setValue(tmpitm.getMenuitemlabel());
				dmnu.setIcon(tmpitm.getMenuitemimage());
				
				ddmnu.getDrpmnu().setValue(tmpitm.getMenuitemlabel());
				//ddmnu.getDrpmnu().setValue(dmnu);
				
				while(subit.hasNext())
				{
					ToolbarItem subtmpitm = subit.next();
					MenuItem mitm = new MenuItem();
					mitm.setTlbaritem(subtmpitm);
					mitm.createMenuItem();
					ddmnu.addMenuIem(mitm.getMnuitem());
				}
				this.toolbar.getChildren().add(ddmnu.getDrpmnu());
			}
			else
			{
				MenuItem mitm = new MenuItem();
				mitm.setTlbaritem(tmpitm);
				mitm.createMenuItem();
				//System.out.println("menu created for: " + mitm.getTlbaritem().getMenuitemlabel());
				this.toolbar.getChildren().add(mitm.getMnuitem());
				//System.out.println("Added to the toolbar: " + mitm.getTlbaritem().getMenuitemlabel());
			}
		}
	}
	
	public boolean hasSubMenu(long usrid, int rootmenuid, int haspermission) 
	{
		boolean containssubmenu = false;
		
		List<ToolbarItem> submenuitems = this.loadToolbarItems(usrid, rootmenuid, haspermission);
		if(submenuitems.size() > 0)
		{
			containssubmenu = true;
		}
		
		return containssubmenu;
	}
	
	
	public boolean hasPermission(long usrid, int rootmenuid, int haspermission)
	{
		boolean perm = false;
		
		try
		{
			String endpointaddress = WebMethodNames.endpointurl
					+ WebMethodNames.toolbaritem_webservice;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.toolbaritem_opt_hasMenuPermission);
			//System.out.println("Call web method : " + WebMethodNames.toolbaritem_opt_hasMenuPermission);
			String hasprvlg = (String) call.invoke(new Object[]{usrid, rootmenuid});
			//System.out.println("has permission: " + hasprvlg);
			if(hasprvlg.equals("granted"))
			{
				perm = true;
			}
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return perm;
	}

	public boolean hasURLPermission(long usrid, long haspermission, String url)
	{
		boolean perm = false;
		
		try
		{
			String endpointaddress = WebMethodNames.endpointurl
					+ WebMethodNames.toolbaritem_webservice;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.toolbaritem_opt_hasURLPermission);
			
			String hasprvlg = (String) call.invoke(new Object[]{usrid, haspermission, url});
			
			if(hasprvlg.equals("granted"))
			{
				perm = true;
			}
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return perm;
	}
	
	public void redirectpage(long usrid, long haspermission, String url)
	{
		boolean hasperm = hasURLPermission(usrid, haspermission, url);
		if(hasperm == false)
		{
			FacesContext facescontext = FacesContext.getCurrentInstance();
			try
			{
				facescontext.getExternalContext().redirect(StoredProceduresName.rootfolder + "/home/home.jsf");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<ToolbarItem> getToolbaritms()
	{
		return toolbaritms;
	}

	public void setToolbaritms(List<ToolbarItem> toolbaritms)
	{
		this.toolbaritms = toolbaritms;
	}

	public UIRepeat getRepeater()
	{
		return repeater;
	}

	public void setRepeater(UIRepeat repeater)
	{
		this.repeater = repeater;
	}

	public HtmlToolBar getToolbar()
	{
		return toolbar;
	}

	public void setToolbar(HtmlToolBar toolbar)
	{
		this.toolbar = toolbar;
	}
	
}
