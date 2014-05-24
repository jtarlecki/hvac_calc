package controllerbean;


import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import utilitybean.ManagedBeanObject;
import wsobj.WebMethodNames;


import loginbean.User;
import modelbean.Equipment;
import modelbean.Project;

public class ProjectManager
{
	private Project projectreq = new Project();
	private boolean hasbeensubmitted = false;

	{
		addEquipment(new Equipment());
	}
	
	public ProjectManager()
	{
		
	}
	
	public int submitProject()
	{
		int issuccessfull = 0;
		try
		{
			
			User usr = (User) ManagedBeanObject.getManagedBean("userBean");
			DateFormat formatter = new SimpleDateFormat(
					ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "targetinstalldateformat"));
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(WebMethodNames.endpointurl
					+ WebMethodNames.project_webservice);
			call.setOperation(WebMethodNames.project_submitProject);
			int utilityknown = (this.projectreq.isUtilityknown() ? 1 : 0);
			String issucc = (String) 
					call.invoke(new Object[]
							{
							usr.getLoginemp().getEmpusername(), //this.projectreq.getSubmittedby(),
							usr.getLoginemp().getEmployeetypedesc(), //this.projectreq.getCompanyname(),
							usr.getLoginemp().getEmpphone(), //this.projectreq.getPhonenumber(),
							usr.getLoginemp().getEmpemail(), //this.projectreq.getEmail(),
							this.projectreq.getSitename(),
							this.projectreq.getSiteaddress(),
							this.projectreq.getSitecity(),
							this.projectreq.getSitestate(),
							this.projectreq.getSitezip(),
							this.projectreq.getSiteelectricutility(),
							this.projectreq.getSitebuildingtype(),
							"" + this.projectreq.getSitesqft(),
							"" + this.projectreq.getSiteoperatinghours(),
							this.projectreq.getSiteprojecttype(),
							this.convertEquipmentToString(this.projectreq.getEquipments()),
							formatter.format(this.projectreq.getTargetinstal()),
							this.projectreq.getSummaryscope(),
							this.projectreq.getAdditionalnotes(),
							utilityknown,
							this.projectreq.getProjectcontactname(),
							this.projectreq.getInstallingcontractorname(),
							this.projectreq.getInstallingcontractoremail(),
							this.projectreq.getInstallingcontractorphone(),
							this.projectreq.getBuildingownername(),
							this.projectreq.getBuildingowneremail(),
							this.projectreq.getBuildingownerphone()
							});
			if(issucc.length() > 0)
			{
				int suc = new Integer(issucc).intValue();
				if(suc == 0)
				{
					issuccessfull = 1;
				}
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
		
		return issuccessfull;
	}
	
	public int submitProjectCustomInquiry()
	{
		int issuccessfull = 0;
		try
		{
			
			User usr = (User) ManagedBeanObject.getManagedBean("userBean");
			DateFormat formatter = new SimpleDateFormat(
					ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "targetinstalldateformat"));
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(WebMethodNames.endpointurl
					+ WebMethodNames.project_webservice);
			call.setOperation(WebMethodNames.project_submitprojectcustominquiry);
			
			String issucc = (String) 
					call.invoke(new Object[]
							{
							usr.getLoginemp().getEmpusername(), //this.projectreq.getSubmittedby(),
							usr.getLoginemp().getEmployeetypedesc(), //this.projectreq.getCompanyname(),
							usr.getLoginemp().getEmpphone(), //this.projectreq.getPhonenumber(),
							usr.getLoginemp().getEmpemail(), //this.projectreq.getEmail(),
							this.projectreq.getSitename(),
							this.projectreq.getSiteaddress(),
							this.projectreq.getSitecity(),
							this.projectreq.getSitestate(),
							this.projectreq.getSitezip(),
							this.projectreq.getSiteelectricutility(),
							this.projectreq.getSitebuildingtype(),
							"" + this.projectreq.getSitesqft(),
							"" + this.projectreq.getSiteoperatinghours(),
							this.projectreq.getSiteprojecttype(),
							this.convertEquipmentToStringCustomInquiry(this.projectreq.getEquipments()),
							formatter.format(this.projectreq.getEquipmentorderdate()),
							formatter.format(this.projectreq.getTargetinstal()),
							this.projectreq.getSummaryscope(),
							this.projectreq.getAdditionalnotes()
							});
			if(issucc.length() > 0)
			{
				int suc = new Integer(issucc).intValue();
				if(suc == 0)
				{
					issuccessfull = 1;
				}
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
		
		return issuccessfull;
	}
	
	public String convertEquipmentToString(List<Equipment> eqps)
	{
		String convertstr = "";
		int numofselequip = 0;
		Iterator<Equipment> it = eqps.iterator();
		while(it.hasNext())
		{
			Equipment eq = it.next();
			if(numofselequip > 0)
			{
				convertstr = convertstr + "|";
			}
			if(eq.getModelnumber().length() > 0 && eq.getReplacingequipment().length() > 0)
			{
				convertstr = convertstr + eq.getModelnumber()
						+ "~"
						+ eq.getReplacingequipment();
				numofselequip++;
			}
		}	
		
		return convertstr;
	}
	
	public String convertEquipmentToStringCustomInquiry(List<Equipment> eqps)
	{
		String convertstr = "";
		int numofselequip = 0;
		Iterator<Equipment> it = eqps.iterator();
		while(it.hasNext())
		{
			Equipment eq = it.next();
			if(numofselequip > 0)
			{
				convertstr = convertstr + "|";
			}
			if(eq.getEquipmentdesc().length() > 0)
			{
				convertstr = convertstr + eq.getEquipmentdesc()
						+ "~" + eq.getSubequipmentdesc()
						+ "~" + eq.getModelnumber()
						+ "~" + eq.getTonage()
						+ "~" + eq.getSizeuomval()
						+ "~" + eq.getFlv1()
						+ "~" + eq.getFlvuomval()
						+ "~" + eq.getIplv2()
						+ "~" + eq.getIplvuomval()
						+ "~" + eq.getExistingequimentmakemodel()
						+ "~" + eq.getExistingequipmentagefunctionality();
				numofselequip++;
			}
		}	
		
		return convertstr;
	}

	public void addEquipment(Equipment item)
	{
		this.projectreq.addEquipment(item);
	}
	
	public Project getProjectreq()
	{
		return projectreq;
	}

	public void setProjectreq(Project projectreq)
	{
		this.projectreq = projectreq;
	}

	public boolean isHasbeensubmitted()
	{
		return hasbeensubmitted;
	}

	public void setHasbeensubmitted(boolean hasbeensubmitted)
	{
		this.hasbeensubmitted = hasbeensubmitted;
	}
}
