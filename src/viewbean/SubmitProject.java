package viewbean;


import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.html.HtmlDataTable;

import modelbean.Equipment;
import modelbean.Project;
import modelbean.Utility;

import controllerbean.ProjectManager;
import controllerbean.UtilityManager;

import utilitybean.ManagedBeanObject;


public class SubmitProject
{
	private String missingrequired = "";
	
	
	private HtmlDataTable equipmentdatatable = new HtmlDataTable();
	
	
	public SubmitProject()
	{
		
	}
	
	
	public void ZipCodeChanged()
	{
		
		ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");
		UtilityManager utlmngr = (UtilityManager) ManagedBeanObject.getManagedBean("utilityManager");
		utlmngr.loadUtilityByZipCode(prjmngr.getProjectreq().getSitezip());
		List<Utility> utl = utlmngr.getUtilitybyzip();
		if(utl.size() > 0)
		{
			prjmngr.getProjectreq().setSiteelectricutility(utl.get(0).getUtilityname());
		}
		else
		{
			prjmngr.getProjectreq().setSiteelectricutility("");
		}
		
		prjmngr.setHasbeensubmitted(false);
	}

	
	public String AddEquipment()
	{
		ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");
		
		if(prjmngr.getProjectreq().getSiteprojecttype().length() > 0)
		{
			prjmngr.getProjectreq().getEquipments().add(new Equipment());
		}
		
		
		return "Add Equipment";
	}
	
	public String ClearEquipment()
	{
		ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");
		prjmngr.getProjectreq().getEquipments().clear();
		prjmngr.getProjectreq().getEquipments().add(new Equipment());
		return "Clear Equipment";
	}
	
	public void ProjectTypesChanged(ValueChangeEvent event)
	{
		//System.out.println("project type changed ");
		/*
		String projecttype = event.getNewValue().toString();
		EquipmentManager eqmngr = (EquipmentManager) ManagedBeanObject.getManagedBean("equipmentManager");
		eqmngr.loadEquipmentByTrade(projecttype);
		*/
	}
	
	public void modelSelectionChanged(ValueChangeEvent event)
	{
		//String selmodelnumber = event.getNewValue().toString();
		//System.out.println("selected model number" + selmodelnumber);
		//ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");	
	}
	
	public void submitProjectInfo()
	{
		generateMissingRequiredMsg();
		if(this.missingrequired.length() == 0)
		{
			ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");
			int issucc = prjmngr.submitProject();
			if(issucc > 0)
			{
				prjmngr.setProjectreq(new Project());
				prjmngr.getProjectreq().addEquipment(new Equipment());
				this.ZipCodeChanged();
				prjmngr.setHasbeensubmitted(true);
			}
		}
	}
	
	public void submitProjectInfoCustomInquiry()
	{
		generateMissingRequiredMsgCustomInquiry();
		
		if(this.missingrequired.length() == 0)
		{
			ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");
			int issucc = prjmngr.submitProjectCustomInquiry();
			if(issucc > 0)
			{
				prjmngr.setProjectreq(new Project());
				prjmngr.getProjectreq().addEquipment(new Equipment());
				this.ZipCodeChanged();
				prjmngr.setHasbeensubmitted(true);
			}
		}
		
	}
	
	private void generateMissingRequiredMsg()
	{
		ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");
		UtilityManager utlmngr = (UtilityManager) ManagedBeanObject.getManagedBean("utilityManager");
		
		if(utlmngr.getUtilitybyzip().size() > 0  && prjmngr.getProjectreq().getSiteelectricutility().length() <= 0)
		{
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "electricutilityrequiredmsg");
		}
		else if(prjmngr.getProjectreq().getSitebuildingtype().length() <= 0)
		{
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "buildingtyperequiredmsg");
		}
		else if(prjmngr.getProjectreq().getSiteprojecttype().length() <= 0)
		{
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "projecttyperequiredmsg");
		}
		else if(prjmngr.getProjectreq().getTargetinstal() == null)
		{
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "targetinstallrequiredmsg");
		}
		else if(prjmngr.getProjectreq().getTargetinstal() != null)
		{
			Calendar tdate = Calendar.getInstance();
			Calendar anotherdate = Calendar.getInstance();
			anotherdate.setTime(prjmngr.getProjectreq().getTargetinstal());
			if(tdate.compareTo(anotherdate) > 0)
			{
				this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "targetinstallrequiredmsg");
			}
		}
		else if(this.numOfEquipmentSelected() <= 0)
		{
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "equipmentrequiredmsg");
		}
		else
		{
			this.missingrequired = "";
		}
	}
	
	private void generateMissingRequiredMsgCustomInquiry()
	{
		ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");
		UtilityManager utlmngr = (UtilityManager) ManagedBeanObject.getManagedBean("utilityManager");
		
		
		
		if(utlmngr.getUtilitybyzip().size() > 0  && prjmngr.getProjectreq().getSiteelectricutility().length() <= 0)
		{
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "electricutilityrequiredmsg");
		}
		else if(prjmngr.getProjectreq().getSitebuildingtype().length() <= 0)
		{
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "buildingtyperequiredmsg");
		}
		else if(prjmngr.getProjectreq().getSiteprojecttype().length() <= 0)
		{
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "projecttyperequiredmsg");
		}
		else if(prjmngr.getProjectreq().getTargetinstal() == null)
		{
			System.out.println("getTargetinstal is null");
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "targetinstallrequiredmsg");
		}
		else if(prjmngr.getProjectreq().getTargetinstal() != null)
		{
			Calendar tdate = Calendar.getInstance();
			Calendar anotherdate = Calendar.getInstance();
			anotherdate.setTime(prjmngr.getProjectreq().getTargetinstal());
			if(tdate.compareTo(anotherdate) > 0)
			{
				this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "targetinstallrequiredmsg");
			}
		}
		else if(prjmngr.getProjectreq().getEquipmentorderdate() == null)
		{
			System.out.println("equals method called");
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "equipmentorderdaterequiredmsg");
		}
		else if(this.numOfEquipmentSelectedCustomInquiry() <= 0)
		{
			this.missingrequired = ManagedBeanObject.getResourceBundleKeyValue("sbtprj", "equipmentrequiredmsg");
		}
		else
		{
			this.missingrequired = "";
		}
		
		
	}
	
	private int numOfEquipmentSelected()
	{
		ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");
		int numofselequip = 0;
		Iterator<Equipment> it = prjmngr.getProjectreq().getEquipments().iterator();
		while(it.hasNext())
		{
			Equipment eq = it.next();
			if(eq.getModelnumber().length() > 0 && eq.getReplacingequipment().length() > 0)
			{
				numofselequip++;
			}
		}
		
		return numofselequip;
	}
	
	private int numOfEquipmentSelectedCustomInquiry()
	{
		ProjectManager prjmngr = (ProjectManager) ManagedBeanObject.getManagedBean("projectManager");
		int numofselequip = 0;
		Iterator<Equipment> it = prjmngr.getProjectreq().getEquipments().iterator();
		while(it.hasNext())
		{
			Equipment eq = it.next();
			if(eq.getEquipmentdesc().length() > 0)
			{
				numofselequip++;
			}
		}
		
		return numofselequip;
	}

	public String getMissingrequired()
	{
		return missingrequired;
	}

	public void setMissingrequired(String missingrequired)
	{
		this.missingrequired = missingrequired;
	}

	public HtmlDataTable getEquipmentdatatable()
	{
		return equipmentdatatable;
	}

	public void setEquipmentdatatable(HtmlDataTable equipmentdatatable)
	{
		this.equipmentdatatable = equipmentdatatable;
	}

}
