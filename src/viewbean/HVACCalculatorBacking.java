package viewbean;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


import org.hibernate.validator.Pattern;

import controllerbean.EquipmentManager;
import controllerbean.HVACCalcResultManger;
import controllerbean.OEMManager;
import controllerbean.RebatorManager;
import controllerbean.SubEquipmentTypesManager;
import controllerbean.UtilityManager;
import controllerbean.ZipUtilitiesManager;
import dbobj.StoredProceduresName;

import utilitybean.ManagedBeanObject;


import loginbean.User;
import modelbean.Equipment;
import modelbean.HVACCalcResult;
import modelbean.HVACCalcResultDetails;
import modelbean.Rebator;
import modelbean.Utility;
import modelbean.ZipUtilities;



public class HVACCalculatorBacking
{

	@Pattern(regex="^[\\w]*[^\\W][\\w]*$", 
		message="Please enter zip code.")
	private String zipcode = "";
	
	private String ziplist = "";
	private String customziplist = "";
	private String parsedziplist = "";
	private String recordseperator = "new line";
	private String fieldseparator = "tab";
	private String otherseparatorchar = "";
	private String selectedtab = "custom";
	private String projecttype = "replacement";
	private String allequipselectedutility = "";
	private String allequipinputerrmsg = "";
	private boolean allequipnewconstruction = false;
	private boolean allequipretrofit = false;
	private boolean newconstruction = false;
	private boolean retrofit = false;
	private boolean customnewconstruction = false;
	private boolean customretrofit = false;
	private String hvacerrmsg = "";
	private String customhvacerrmsg = "";
	private String equiptableheader = "";
	private String hvacresulttableheader = "";
	private String customresulttableheader = "";
	private boolean displaystatus = false;
	private boolean calculationcompleted = true;
	private String projectname = "";
	private String projectnamealleqtab = "";
	private String projectnamecustomtab = "";
	private String customreviewsubmitconfirmation = "";
	
	{
		redirectToLoginpage();
	}
	

	private long numofunits = 1;
	
	public HVACCalculatorBacking()
	{
		
	}
	
	public void selectCheckBoxChanged()
	{
		EquipmentManager eqmngr = (EquipmentManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipmentManager");
		eqmngr.loadEquipmentByTrade("");
	}

	public void oemSelectionChanged()
	{
		OEMManager oemmngr = 
				(OEMManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("oEMManager");
		oemmngr.oemSelectionChanged();
		/*
		if(oemmngr.getSelectedoem().size() > 0)
		{
			EquipmentManager eqmngr = (EquipmentManager) 
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipmentManager");
			eqmngr.loadEquipmentByOEM(oemmngr.convertSelectedOEMToString());
			//calculateHVAC();
			calculateHVACByOEMAndZip();
		}
		*/
	}
	
	public void equpSelectionChanged()
	{
		EquipmentManager eqmngr = (EquipmentManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipmentManager");
		
		eqmngr.equipbyoemSelectionChanged();
		
		calculateHVAC();
	}
	
	public void rebatorSelectionChanged()
	{
		RebatorManager rbmngr = (RebatorManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rebatorManager");
		rbmngr.rebatorByZipSelectionChanged();
		calculateHVAC();
		
	}
	
	public void calculateHVAC()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		hvacmngr.clearResults();
		RebatorManager rbmngr = 
				(RebatorManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rebatorManager");
		EquipmentManager eqmngr = (EquipmentManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipmentManager");
		
		if(eqmngr.getSelectedequipbyoem().size() > 0
				&& rbmngr.getSelectedrebatorbyzip().size() > 0)
		{
			
			Iterator<Equipment> eq = eqmngr.getSelectedequipbyoem().iterator();
			while(eq.hasNext())
			{
				Equipment tmpeq = eq.next();
				Iterator<Rebator> rb = rbmngr.getSelectedrebatorbyzip().iterator();
				while(rb.hasNext())
				{
					Rebator tmprb = rb.next();
					HVACCalcResult rslt =  new HVACCalcResult();
					rslt.setState(tmprb.getState());
					rslt.setRebatorid(tmprb.getRebatorid());
					rslt.setTons(new Float(tmpeq.getTonage()).floatValue());
					rslt.setEquiptype(4);
					rslt.setFlv1(new Float(tmpeq.getEer()).floatValue());
					rslt.setFlv1uom(1);
					rslt.setFlv2((float) 0.0);
					rslt.setFlv2uom(0);
					rslt.setIplv1(new Float(tmpeq.getIeer()).floatValue());
					rslt.setIplv1uom(3);
					rslt.setSubequiptype(8);
					rslt.setModelnumber(tmpeq.getModelcoilnumber());
					rslt.setTradename(tmpeq.getTradename());
					
					hvacmngr.addNewResult(rslt);
				}
			}
			System.out.println("hvac size: " + hvacmngr.getResults().size());
		}
		else
		{
			hvacmngr.clearResults();
		}
	}
	
	public void calculateHVACByOEMAndZip()
	{
		//System.out.println("Selected zipcode: " + this.zipcode);
		generateAllEquipErrMsg();
		
		//if(this.zipcode.length() >= 5 && this.allequipinputerrmsg.length() <= 0)
		//{
		if(this.allequipinputerrmsg.length() <= 0 && (this.allequipnewconstruction == true || this.allequipretrofit == true))
		{
			User usr = (User) ManagedBeanObject.getManagedBean("userBean");
			//OEMManager oemmngr = (OEMManager) 
			//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("oEMManager");
			EquipmentManager eqmngr = (EquipmentManager) 
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipmentManager");
			//System.out.println("Selected zip and oem " + this.zipcode + "|" + oemmngr.convertSelectedOEMToString());
			eqmngr.loadEquipmentByOEMAndZip(this.zipcode, 
					usr.getLoginemp().getCompanyname(),
					this.allequipselectedutility,
					(this.allequipnewconstruction ? 1 : 0),
					(this.allequipretrofit ? 1 : 0),
					usr.getRqstsession().getId(),
					this.projectnamealleqtab,
					usr.getLoginemp().getEmpusername());
			System.out.println("hvac cal list size: " + eqmngr.getEquipmentsbyoem().size());
		}
		//}
	}
	
	public void numberofUnitChanged()
	{
		
	}
	
	public String parseZipCode()
	{
		
		//System.out.println("parse option: " + this.parseseperatoroption);
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
		if(this.recordseperator.equals("other"))
		{
			zumngr.parseHVACZipUtility(this.ziplist, this.otherseparatorchar, this.fieldseparator, "hvac");
		}
		else
		{
			zumngr.parseHVACZipUtility(this.ziplist, this.recordseperator, this.fieldseparator, "hvac");
		}
		
		EquipmentManager eqmngr = (EquipmentManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipmentManager");
		eqmngr.loadEquipmentByTrade("Trane");
		//System.out.println("Equipment drowpdown size: " + eqmngr.getEquipmentbytradecombo().size());
		
		this.ziplist = "";
		
		return "Parse Zips";
	}
	
	public String parseCustomHVACZipCode()
	{
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
		if(this.recordseperator.equals("other"))
		{
			zumngr.getHvaccustomzulist().clear();
			zumngr.parseHVACZipUtility(this.customziplist, this.otherseparatorchar, this.fieldseparator, "customhvac");
		}
		else
		{
			zumngr.getHvaccustomzulist().clear();
			zumngr.parseHVACZipUtility(this.customziplist, this.recordseperator, this.fieldseparator, "customhvac");
		}
		
		//EquipmentManager eqmngr = (EquipmentManager) 
		//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipmentManager");
		//eqmngr.loadEquipmentByTrade("Trane");
		//System.out.println("Equipment drowpdown size: " + eqmngr.getEquipmentbytradecombo().size());
		
		this.customziplist = "";
		
		return "Parse Custom Zips";
	}
	
	public String clearSites()
	{
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
		
		zumngr.getHvaczulist().clear();
		
		return "Clear";
	}
	
	public String clearCustomHVACSites()
	{
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
		
		zumngr.getHvaccustomzulist().clear();
		
		return "Clear Custom HVAC Sites";
	}
	
	public void refreshUtilityList()
	{
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
		ZipUtilities zu = (ZipUtilities) zumngr.getHvaczulistdatatable().getRowData();
		zu.setEditable(true);
		//String zuzipcode = zu.getZipcode();
		//System.out.println("zu zipcode: " + zuzipcode);
		
		//UtilityManager utlmngr = (UtilityManager) 
			//	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("utilityManager");

		//utlmngr.loadUtilityByZipCode(zuzipcode);
		
	}
	
	public void modelnumberSelectionChanged(ValueChangeEvent event)
	{
		String selmodelnumber = event.getNewValue().toString();
		EquipmentManager eqmngr = (EquipmentManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipmentManager");
		//Equipment tmpeq = eqmngr.getSelectedEquipmnetByTradeModel(selmodelnumber);
		Equipment tmpeq = eqmngr.getSelectedEquipmnetByOEMModel(selmodelnumber);
		//System.out.println("Selected equipment: " + tmpeq.getEer() + "|" + tmpeq.getIeer() + "|"
		//		+ tmpeq.getTonage());
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		HVACCalcResult hrslt = (HVACCalcResult) hvacmngr.getHvacinputdatatable().getRowData();
		hrslt.setTons((float) tmpeq.getTonage());
		hrslt.setFlv1((float) tmpeq.getEer());
		hrslt.setIplv2((float) tmpeq.getIeer());
		hrslt.setEquiptype((int) tmpeq.getEquiptypeid());
		hrslt.setSubequiptype((int) tmpeq.getSubequiptypeid());
		hrslt.setEquipmentid(tmpeq.getEquipid());
		hrslt.setFlv2((float) tmpeq.getSeer());
		hrslt.setHeatingflv((float) tmpeq.getHeatingflv());
		hrslt.setHeatingflvuom((int) tmpeq.getHeatingflvuom());
		hrslt.setHeatingflvuomval(tmpeq.getHeatingflvuomval());
		hrslt.setHeatingseasonal((float) tmpeq.getHeatingseasonal());
		hrslt.setHeatingseasonaluom((int) tmpeq.getHeatingseasonaluom());
		hrslt.setHeatingseasonaluomval(tmpeq.getHeatingseasonaluomval());
		hrslt.setHeatingiplv((float) tmpeq.getHeatingiplv());
		hrslt.setHeatingiplvuom((int) tmpeq.getHeatingiplvuom());
		hrslt.setHeatingiplvuomval(tmpeq.getHeatingiplvuomval());
		hrslt.setSizeuom((int) tmpeq.getSizeuom());
		hrslt.setSizeuomval(tmpeq.getSizeuomval());
		hrslt.setTechnologycategory((int) tmpeq.getTechnologycategory());
		hrslt.setTechnology((int) tmpeq.getTechnology());
		hrslt.setSubcategory((int) tmpeq.getSubcategory());
		hrslt.setDesignflv((int) tmpeq.getDesignflv());
		hrslt.setDesignflvuom((int) tmpeq.getDesignflvuom());
		hrslt.setDesignflvuomval(tmpeq.getDesignflvuomval());
		hrslt.setDesignseasonal((int) tmpeq.getDesignseasonal());
		hrslt.setDesignseasonaluom((int) tmpeq.getHeatingseasonaluom());
		hrslt.setDesignseasonaluomval(tmpeq.getDesignseasonaluomval());
		hrslt.setDesigniplv((float) tmpeq.getDesigniplv());
		hrslt.setDesigniplvuom((int) tmpeq.getDesigniplvuom());
		hrslt.setDesigniplvuomval(tmpeq.getDesigniplvuomval());
		hrslt.setFlvuom(tmpeq.getFlvuom());
		hrslt.setFlvuomval(tmpeq.getFlvuomval());
		hrslt.setSeasonaluom(tmpeq.getSeasonaluom());
		hrslt.setSeasonaluomval(tmpeq.getSeasonaluomval());
		hrslt.setIplvuom(tmpeq.getIplvuom());
		hrslt.setIplvuomval(tmpeq.getIplvuomval());
		hrslt.setDesignheatingflv(tmpeq.getDesignflv());
		hrslt.setDesignheatingflvuom(tmpeq.getDesignheatingflvuom());
		hrslt.setDesignheatingflvuomval(tmpeq.getDesignheatingflvuomval());
		hrslt.setDesignheatingseasonal(tmpeq.getDesignheatingseasonal());
		hrslt.setDesignheatingseasonaluom(tmpeq.getDesignheatingseasonaluom());
		hrslt.setDesignheatingseasonaluoomval(tmpeq.getDesignheatingseasonaluoomval());
		hrslt.setDesignheatingiplv(tmpeq.getDesignheatingiplv());
		hrslt.setDesignheatingiplvuom(tmpeq.getDesignheatingiplvuom());
		hrslt.setDesignheatingiplvuomval(tmpeq.getDesignheatingiplvuomval());
		hrslt.setEquipmentcode(tmpeq.getEquipmentcode());
		hrslt.setEquipmentdesc(tmpeq.getEquipmentdesc());
		hrslt.setSubequipmentcode(tmpeq.getSubequipmentcode());
		hrslt.setSubequipmentdesc(tmpeq.getSubequipmentdesc());
		hrslt.setDesignsize(tmpeq.getDesignsize());
		hrslt.setDesignsizeuom(tmpeq.getDesignsizeuom());
		hrslt.setDesignsizeuomval(tmpeq.getDesignsizeuomval());
		
		
		System.out.println("Selected equipment set: "
				+ "TONS = " + hrslt.getTons() + " | " 
				+ "FLV1 = " + hrslt.getFlv1() + " | " 
				+ "FLV2 = " + hrslt.getFlv2() + " | " 
				+ "IPLV1 = " + hrslt.getIplv1() + " | "
				+ "IPLV2 = " + hrslt.getIplv2() + " | "
				);
		
	}
	
	public String calculateHVACCustom()
	{
		//this.displaystatus = true;
		
		this.generateHVACCalcErrMsg();
		this.generateResultEquipTableHeader();
		this.generateHVACRebateTableHeader();
		
		if(this.hvacerrmsg.length() <= 0)
		{
			//this.calculationcompleted = false;
			//ProgressBarBean prgbar = (ProgressBarBean) 
			//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("progressBarBean");
			//prgbar.startProcess();
			User usr = (User) ManagedBeanObject.getManagedBean("userBean");
			HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
			ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
			
			////prgbar.setPrcntcompleted(30);
			List<HVACCalcResult> rmindx = new ArrayList<HVACCalcResult>();
			
			List<HVACCalcResult> hvacrslt = hvacmngr.getHvacinput();
			Iterator<HVACCalcResult> it = hvacrslt.iterator();
			while(it.hasNext())
			{
				HVACCalcResult tmprslt = it.next();
				if(tmprslt.getModelnumber().length() == 0)
				{
					rmindx.add(tmprslt);
				}
			}
			
			
			Iterator<HVACCalcResult> idxit = rmindx.iterator();
			while(idxit.hasNext())
			{
				HVACCalcResult tmpobj = idxit.next();
				hvacrslt.remove(tmpobj);
			}
			
			
			hvacmngr.calculateCustomHVACOverload
			(
					this.convertZipUtilitesListToString(zumngr.getHvaczulist()), 
					this.convertEquipmentsListToString(hvacrslt),
					this.projectname,
					usr.getRqstsession().getId(),
					usr.getUsername()
			);
			
			//prgbar.setPrcntcompleted(101);
			
			//this.calculationcompleted = true;
			
			/*
			 
			List<HVACCalcResult> hvacoutput = hvacmngr.getHvacoutput();
			
			Iterator<HVACCalcResult> outputit = hvacoutput.iterator();
			int customcount = 0;
			
			while(outputit.hasNext())
			{
				HVACCalcResult outputitem = outputit.next();
				List<HVACCalcResultDetails> hvacdtl = outputitem.getHvacdtl();
				
				Iterator<HVACCalcResultDetails> itdtl = hvacdtl.iterator();
				
				while(itdtl.hasNext())
				{
					HVACCalcResultDetails outdtl = itdtl.next();
					if(outdtl.getComments().toLowerCase().indexOf("custom") >= 0)
					{
						customcount++;
					}
				}
			}
			
			hvacmngr.setExpandalldetails(true);
			
			if(customcount > 0)
			{
				hvacmngr.setHasbeensubmitted(false);
			}
			else
			{
				hvacmngr.setHasbeensubmitted(true);
			}
			
			*/
			
			hvacmngr.setExpandalldetails(true);
			
			hvacmngr.setHasbeensubmitted(false);
			
			this.customreviewsubmitconfirmation = "";
			
			this.selectedtab = "resulttab";
		}

		//prgbar.setPrcntcompleted(101);
		
		//this.displaystatus = false;
		
		return "calculate";
	}
	
	public String estimateCustomHVAC()
	{
		//this.displaystatus = true;
		
		this.generateCustomHVACCalcErrMsg();
		//this.generateResultEquipTableHeader();
		this.generateCustomHVACRebateTableHeader();
		
		if(this.customhvacerrmsg.length() <= 0)
		{
			
			User usr = (User) ManagedBeanObject.getManagedBean("userBean");
			HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
					ManagedBeanObject.getManagedBean("hVACCalcResultManger");
			ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
					ManagedBeanObject.getManagedBean("zipUtilitiesManager");
			
			////prgbar.setPrcntcompleted(30);
			List<HVACCalcResult> rmindx = new ArrayList<HVACCalcResult>();
			
			List<HVACCalcResult> hvacrslt = hvacmngr.getCustomhvacinput();
			Iterator<HVACCalcResult> it = hvacrslt.iterator();
			while(it.hasNext())
			{
				HVACCalcResult tmprslt = it.next();
				if(tmprslt.getEquipmenttype().length() == 0
						|| tmprslt.getSubequipmenttype().length() == 0
						|| tmprslt.getFlv1unitofmeasurement().length() == 0)
				{
					rmindx.add(tmprslt);
				}
			}
			
			System.out.println("number of items will be removed: " + rmindx.size());
			
			Iterator<HVACCalcResult> idxit = rmindx.iterator();
			while(idxit.hasNext())
			{
				HVACCalcResult tmpobj = idxit.next();
				hvacrslt.remove(tmpobj);
			}
			
			//System.out.println("Remaining: " + hvacrslt.size());
			
			
			String zputl = this.convertZipUtilitesListToString(zumngr.getHvaccustomzulist());
			//System.out.println(zputl);
			String eqps = this.convertCustomEquipmentsListToString(hvacrslt);
			System.out.println("SET @equipments2 = '" + eqps + "'");
			
			
			hvacmngr.estimateCustomHVAC
			(
					zputl, 
					eqps,
					this.projectnamecustomtab,
					usr.getRqstsession().getId(),
					usr.getUsername()
			);
			
			
			//prgbar.setPrcntcompleted(101);
			
			//this.calculationcompleted = true;
			
			hvacmngr.setExpandallcustomdetails(false);
		}
		
		return "custom estimate";
	}
	public void utilitySelectionChanged(ValueChangeEvent event)
	{
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
		ZipUtilities zu = (ZipUtilities) zumngr.getHvaczulistdatatable().getRowData();
		zu.setTbd("N");
		//System.out.println("zip utility selection changed: " + zu.getTbd());
	}
	
	public void addHVACEquipmentInput()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		hvacmngr.addNewHVACInput(new HVACCalcResult());
		
	}
	
	public void clearHVACEquipmentInput()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		hvacmngr.clearHVACInput();
		hvacmngr.addNewHVACInput(new HVACCalcResult());
	}
	
	public void clearLastHVACEquipmentInput()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		
		if(hvacmngr.getHvacinput().size() > 0)
		{
			hvacmngr.getHvacinput().remove(hvacmngr.getHvacinput().size() - 1);
			if(hvacmngr.getHvacinput().size() == 0)
			{
				hvacmngr.addNewHVACInput(new HVACCalcResult());
			}
		}
	}
	
	public String convertEquipmentsListToString(List<HVACCalcResult> hvacinput)
	{
		int replacement = 0;
		int newconst = 0;
		
		if(this.newconstruction == true)
		{
			newconst = 1;
		}
		
		if(this.retrofit == true)
		{
			replacement = 1;
		}
		
		long linenum = 1;
		String hvacinputstr = "";
		Iterator<HVACCalcResult> it = hvacinput.iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			
			if(tmprslt.getModelnumber().length() > 0 
					&& tmprslt.getNumberofunits() > 0
					&& (tmprslt.getTons() + tmprslt.getDesignsize()) > 0
					&& tmprslt.getFlv1() >= 0
					&& tmprslt.getFlv2() >= 0
					&& tmprslt.getIplv2() >= 0
					&& tmprslt.getHeatingflv() >= 0
					&& tmprslt.getHeatingseasonal() >= 0
					&& tmprslt.getHeatingiplv() >= 0
					&& tmprslt.getDesignflv() >= 0
					&& tmprslt.getDesignseasonal() >= 0
					&& tmprslt.getDesigniplv() >= 0
					&& tmprslt.getDesignheatingflv() >= 0
					&& tmprslt.getDesignheatingseasonal() >= 0
					&& tmprslt.getDesignheatingiplv() >= 0)
			{ //equipment selected
				hvacinputstr = hvacinputstr 
					+ tmprslt.getModelnumber()
					+ "~" + tmprslt.getNumberofunits()
					+ "~" + tmprslt.getTons()
					+ "~" + tmprslt.getEquiptype()
					+ "~" + tmprslt.getFlv1()
					+ "~" + 1
					+ "~" + tmprslt.getFlv2()
					+ "~" + 2
					+ "~" + 0
					+ "~" + 0
					+ "~" + tmprslt.getSubequiptype()
					+ "~" + replacement
					+ "~" + newconst
					+ "~" + linenum
					+ "~" + tmprslt.getEquipmentid()
					+ "~" + tmprslt.getIplv2()
					+ "~" + 3
					+ "~" + tmprslt.getHeatingflv()
					+ "~" + tmprslt.getHeatingflvuomval()
					+ "~" + tmprslt.getHeatingseasonal()
					+ "~" + tmprslt.getHeatingseasonaluomval()
					+ "~" + tmprslt.getHeatingiplv()
					+ "~" + tmprslt.getHeatingiplvuomval()
					+ "~" + tmprslt.getDesignflv()
					+ "~" + tmprslt.getDesignflvuomval()
					+ "~" + tmprslt.getDesignseasonal()
					+ "~" + tmprslt.getDesignseasonaluomval()
					+ "~" + tmprslt.getDesigniplv()
					+ "~" + tmprslt.getDesigniplvuomval()
					+ "~" + tmprslt.getFlvuomval()
					+ "~" + tmprslt.getSeasonaluomval()
					+ "~" + tmprslt.getIplvuomval()
					+ "~" + tmprslt.getDesignheatingflv()
					+ "~" + tmprslt.getDesignheatingflvuomval()
					+ "~" + tmprslt.getDesignheatingseasonal()
					+ "~" + tmprslt.getDesignheatingseasonaluoomval()
					+ "~" + tmprslt.getDesignheatingiplv()
					+ "~" + tmprslt.getDesignheatingiplvuomval()
					+ "~" + tmprslt.getEquipmentcode()
					+ "~" + tmprslt.getSubequipmentcode()
					+ "~" + tmprslt.getDesignsize()
					+ "~" + tmprslt.getDesignsizeuomval()
					+ "~" + tmprslt.getSizeuomval()
					+ "|";
			}
			
			linenum++;
		}
		
		System.out.println("SET @equipments = '" + hvacinputstr + "'");
		
		return hvacinputstr;
	}

	//jt made adjustments to this on 11/12/2013
	public String convertCustomEquipmentsListToString(List<HVACCalcResult> hvacinput)
	{
		int replacement = 0;
		int newconst = 0;
		
		if(this.customnewconstruction == true)
		{
			newconst = 1;
		}
		
		if(this.customretrofit == true)
		{
			replacement = 1;
		}
		
		long linenum = 1;
		String hvacinputstr = "";
		Iterator<HVACCalcResult> it = hvacinput.iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			
			if(tmprslt.getNumberofunits() > 0
					&& tmprslt.getEquipmenttype().length() > 0
					&& tmprslt.getSubequipmenttype().length() > 0
					&& tmprslt.getTons() > 0
					&& tmprslt.getFlv1() > 0
					&& tmprslt.getFlv1unitofmeasurement().length() > 0
					&& tmprslt.getFlv2() >= 0
					&& tmprslt.getIplv1() >= 0
					&& tmprslt.getIplv2() >= 0
					&& (	tmprslt.getIplv1() > 0 
							|| tmprslt.getIplv2() > 0
						)
					&& (	tmprslt.getIplv1unitofmearsurement().length() > 0 
							|| tmprslt.getIplv2unitofmeasurement().length() > 0 
						)
					)
			{ //equipment selected
				
				// pure filth, i really am ashamed of myself this time
				/*
				double dummyFlv1 = 0.000001;
				double dummyIplv1 = 0.000001;
				
				dummyFlv1 = (tmprslt.getFlv1unitofmeasurement().equals("KW/TON") ?  12/dummyFlv1 : dummyFlv1);
				dummyIplv1 = (tmprslt.getIplv1unitofmearsurement().equals("KW/TON") ?  12/dummyIplv1 : dummyIplv1);
				//
				*/
				
				/*
				System.out.println(tmprslt.getFlv1unitofmeasurement().equals("KW/TON"));
				
				System.out.println("tmprslt.getFlv1unitofmeasurement()" + ": " + tmprslt.getFlv1unitofmeasurement());
				System.out.println("tmprslt.getFlv2unitofmeasurement()" + ": " + tmprslt.getFlv2unitofmeasurement());
				System.out.println("tmprslt.getIplv1unitofmearsurement()" + ": " + tmprslt.getIplv1unitofmearsurement());
				System.out.println("tmprslt.getIplv2unitofmeasurement()" + ": " + tmprslt.getIplv2unitofmeasurement());
				System.out.println("tmprslt.getEquipmenttype()" + ": " + tmprslt.getEquipmenttype());
				System.out.println("tmprslt.getSubequipmenttype()" + ": " + tmprslt.getSubequipmenttype());
				*/
				
				
				hvacinputstr = hvacinputstr 
					+ "custom" + linenum
					+ "~" + tmprslt.getNumberofunits()
					+ "~" + tmprslt.getTons()
					+ "~" + tmprslt.getEquipmenttype()
					+ "~" + tmprslt.getFlv1()
					+ "~" + tmprslt.getFlv1unitofmeasurement()
					+ "~" + tmprslt.getFlv2()
					+ "~" + (tmprslt.getFlv2unitofmeasurement().length() <= 0 ? "NA" : tmprslt.getFlv2unitofmeasurement())
					+ "~" + tmprslt.getIplv1()
					+ "~" + (tmprslt.getIplv1unitofmearsurement().length() <= 0 ? "NA" : tmprslt.getIplv1unitofmearsurement())
					+ "~" + tmprslt.getSubequipmenttype()
					+ "~" + replacement
					+ "~" + newconst
					+ "~" + linenum
					+ "~" + tmprslt.getEquipmentid()
					+ "~" + tmprslt.getIplv2()
					+ "~" + tmprslt.getIplv2unitofmeasurement()
					+ "|";
			}
			
			linenum++;
		}
		
		return hvacinputstr;
	}
	
	public String convertZipUtilitesListToString(List<ZipUtilities> zu)
	{
		String zustring = "";
		long linenum = 1;
		Iterator<ZipUtilities> it = zu.iterator();
		while(it.hasNext())
		{
			ZipUtilities tmpzu = it.next();
			zustring = zustring 
					+ tmpzu.getZipcode()
					+ "~" + tmpzu.getPlattsutilityname()
					+ "~" + tmpzu.getTbd()
					+ "~" + linenum
					+ "~" + tmpzu.getSitenum()
					+ "|";
			linenum++;
		}
		
		 System.out.println("SET @ziputilities = '" + zustring + "'");
		
		return zustring;
	}
	
	private void redirectToLoginpage()
	{
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		User usr = (User) ctx.getExternalContext().getSessionMap().get("userBean");
		
		//HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		//session.setMaxInactiveInterval(10);
		try
		{
			if(usr.getEmpid() < 0)
			{
				ctx.getExternalContext().redirect(StoredProceduresName.rootfolder + "/login/login.jsf");
			}
			else
			{
				/*
				ToolbarManager tlbrmngr = (ToolbarManager) ctx.getExternalContext().getRequestMap().get("toolbarManager");
				boolean hasprv = tlbrmngr.hasPermission(usr.getEmpid(), 4, 1);
				System.out.println("Has hvac calculator priveleges: " + hasprv);
				if(hasprv == false)
				{
					ctx.getExternalContext().redirect("/RealWinWinUI_DEV/login/login.jsf");
				}
				*/
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void zipCodeChanged()
	{
		UtilityManager utlmngr = (UtilityManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("utilityManager");
		utlmngr.loadUtilityByZipCode(this.zipcode);
		
		List<Utility> utl = utlmngr.getUtilitybyzip();
		if(utl.size() > 0)
		{
			this.allequipselectedutility = utl.get(0).getUtilityname();
		}
		else
		{
			this.allequipselectedutility = "";
		}
	}
	
	public void generateAllEquipErrMsg()
	{
		OEMManager oemmngr = (OEMManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("oEMManager");
		if(oemmngr.getOperatorsoem().length() <= 0)
		{
			this.allequipinputerrmsg = "OEM not found";
		}
		else if(this.zipcode.length() <= 0)
		{
			this.allequipinputerrmsg = "Please enter 5 digit zip code";
		}
		else if(this.allequipselectedutility.length() <= 0)
		{
			this.allequipinputerrmsg = "Please select utility";
		}
		else if(this.allequipnewconstruction == false && this.allequipretrofit == false)
		{
			this.allequipinputerrmsg = "Please select new construction or retrofit";
		}
		else if(this.projectnamealleqtab.trim().length() <= 0)
		{
			this.allequipinputerrmsg = "Please enter job name";
		}
		else
		{
			this.allequipinputerrmsg = "";
		}
	}
	
	public void generateHVACCalcErrMsg()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
		
		if(this.newconstruction == false && this.retrofit == false)
		{
			this.hvacerrmsg = "Please select either new construction or retrofit";
		}
		else if(zumngr.getHvaczulist().size() <= 0)
		{
			this.hvacerrmsg = "Please input at least one zip code";
		}
		else if(this.convertEquipmentsListToString(hvacmngr.getHvacinput()).length() <= 0)
		{
			this.hvacerrmsg = "Please ensure that all fields in the Equipment section are complete";
		}
		else if(this.projectname.trim().length() <= 0)
		{
			this.hvacerrmsg = "Please enter a job name";
		}
		else
		{
			this.hvacerrmsg = "";
		}
	}
	
	public void generateCustomHVACCalcErrMsg()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
		
		if(this.customnewconstruction == false && this.customretrofit == false)
		{
			this.customhvacerrmsg = "Please select either new construction or retrofit";
		}
		else if(zumngr.getHvaccustomzulist().size() <= 0)
		{
			this.customhvacerrmsg = "Please input at least one zip code";
		}
		else if(this.convertCustomEquipmentsListToString(hvacmngr.getCustomhvacinput()).length() <= 0)
		{
			this.customhvacerrmsg = "Please ensure that all fields in the Equipment section are complete";
		}
		else if(this.projectnamecustomtab.trim().length() <= 0)
		{
			this.customhvacerrmsg = "Please enter a job name";
		}
		else
		{
			this.customhvacerrmsg = "";
		}
	}
	
	public void generateResultEquipTableHeader()
	{
		String equitableheader = "";
		
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		
		long eqcount = 0;
		Iterator<HVACCalcResult> it = hvacmngr.getHvacinput().iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			
			if(tmprslt.getModelnumber().length() > 0)
			{
				eqcount++;
			}
		}
		
		equitableheader = "" + eqcount + " Equipment(s) selected";
		
		this.equiptableheader = equitableheader;
	}
	
	public void generateHVACRebateTableHeader()
	{
		String rbttableheader = "";
		
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zipUtilitiesManager");
		
		long eqcount = 0;
		Iterator<HVACCalcResult> it = hvacmngr.getHvacinput().iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			
			if(tmprslt.getModelnumber().length() > 0)
			{
				eqcount++;
			}
		}
		
		rbttableheader = "Result of " + zumngr.getHvaczulist().size() + " site(s) and "
				+ eqcount + " equipment(s)";
		
		this.hvacresulttableheader = rbttableheader;
	}
	
	public void generateCustomHVACRebateTableHeader()
	{
		String rbttableheader = "";
		
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				ManagedBeanObject.getManagedBean("hVACCalcResultManger");
		ZipUtilitiesManager zumngr = (ZipUtilitiesManager) 
				ManagedBeanObject.getManagedBean("zipUtilitiesManager");
		
		long eqcount = 0;
		Iterator<HVACCalcResult> it = hvacmngr.getCustomhvacinput().iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			
			if(tmprslt.getEquipmenttype().length() > 0 
					|| tmprslt.getSubequipmenttype().length() > 0
					|| tmprslt.getFlv1unitofmeasurement().length() > 0
					//added 11/12/13
					//|| tmprslt.getIplv1unitofmearsurement().length() > 0
					)
			{
				eqcount++;
			}
		}
		
		rbttableheader = "Result of " + zumngr.getHvaccustomzulist().size() + " site(s) and "
				+ eqcount + " equipment(s)";
		
		//System.out.println(rbttableheader);
		
		this.customresulttableheader = rbttableheader;
	}
	
	public void expandAllHvacOutputDetail()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		Iterator<HVACCalcResult> it = hvacmngr.getHvacoutput().iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			tmprslt.setShowdetail(true);
		}
		hvacmngr.setExpandalldetails(true);
	}
	
	public void expandAllCustomHvacOutputDetail()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		Iterator<HVACCalcResult> it = hvacmngr.getCustomhvacoutput().iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			tmprslt.setShowdetail(true);
		}
		hvacmngr.setExpandallcustomdetails(true);
	}
	
	public void collapseAllHvacOutputDetail()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		Iterator<HVACCalcResult> it = hvacmngr.getHvacoutput().iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			tmprslt.setShowdetail(false);
		}		
		hvacmngr.setExpandalldetails(false);
	}
	
	public void collapseAllCustomHvacOutputDetail()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hVACCalcResultManger");
		Iterator<HVACCalcResult> it = hvacmngr.getCustomhvacoutput().iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			tmprslt.setShowdetail(false);
		}		
		hvacmngr.setExpandallcustomdetails(false);
	}
	
	public void equipmentTypeChanged(ValueChangeEvent event)
	{
		String equipmenttype = event.getNewValue().toString();
		//System.out.println("Equipment Type " + equipmenttype);
		SubEquipmentTypesManager subeqmngr = (SubEquipmentTypesManager) ManagedBeanObject.getManagedBean("subEquipmentTypesManager");
		//System.out.println("sub equipment manager initialize: " + subeqmngr.getSubequiptypes().size() );
		subeqmngr.loadSubEquipmentTypesByEquipment(equipmenttype);
		//System.out.println("Sub equipment manager: " + subeqmngr.getSubequiptypes().size());
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) ManagedBeanObject.getManagedBean("hVACCalcResultManger");
		HVACCalcResult hvacrslt = (HVACCalcResult) hvacmngr.getCustomhvacinputdatatable().getRowData();
		if(equipmenttype.equals("Rooftop Unit"))
		{
			hvacrslt.setFlv1unitofmeasurement("EER");
		}
		/*
		else if (equipmenttype.equals("Air Cooled Chiller") || equipmenttype.equals("Water Cooled Chiller"))
		{
			hvacrslt.setFlv1unitofmeasurement("KW/TON");
		}
		*/
		else
		{
			hvacrslt.setFlv1unitofmeasurement("KW/TON");
			hvacrslt.setIplv1unitofmearsurement("KW/TON");
			//hvacrslt.setFlv1unitofmeasurement("");
		}
		hvacrslt.setSubequipmenttype("");
	}
	
	public String addCustomHVACInputEquipment()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) ManagedBeanObject.getManagedBean("hVACCalcResultManger");
		hvacmngr.getCustomhvacinput().add(new HVACCalcResult());
		return "addcustomhvacequipment";
	}
	
	public String clearCustomHVACInputEquipment()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) ManagedBeanObject.getManagedBean("hVACCalcResultManger");
		hvacmngr.getCustomhvacinput().clear();
		hvacmngr.getCustomhvacinput().add(new HVACCalcResult());
		
		return "clearcustomhvacequipment";
	}
	
	public String submitCustomRebatesReview()
	{
		HVACCalcResultManger hvacmngr = (HVACCalcResultManger) ManagedBeanObject.getManagedBean("hVACCalcResultManger");
		List<HVACCalcResult> hvacoutput = hvacmngr.getHvacoutput();
		User usr = (User) ManagedBeanObject.getManagedBean("userBean");
		
		String username = usr.getUsername();
		String submitstr = "";
		
		
		
		Iterator<HVACCalcResult> it = hvacoutput.iterator();
		while(it.hasNext())
		{
			HVACCalcResult rslt = it.next();
			List<HVACCalcResultDetails> hvacdtl = rslt.getHvacdtl();
			
			Iterator<HVACCalcResultDetails> itdtl = hvacdtl.iterator();
			while(itdtl.hasNext())
			{
				HVACCalcResultDetails dtl = itdtl.next();
				if(dtl.getComments().toLowerCase().indexOf("custom") >= 0)
				{
					submitstr = submitstr + dtl.getRbtseq()
							+ "~" + dtl.getZipcode()
							+ "~" + dtl.getUtilityname()
							+ "~" + dtl.getRebatorid()
							+ "~" + dtl.getEquipmenttypeid()
							+ "~" + dtl.getSubequipmenttypeid()
							+ "~" + dtl.getEquipmentid()
							+ "~" + dtl.getModelnumber()
							+ "~" + dtl.getModelcoilnumber()
							+ "~" + dtl.getQuantity()
							+ "~" + dtl.getTonnage()
							+ "~" + dtl.getSizeuom()
							+ "~" + dtl.getFlv1()
							+ "~" + dtl.getFlvuom()
							+ "~" + dtl.getFlv2()
							+ "~" + dtl.getSeasonaluom()
							+ "~" + dtl.getIplv2()
							+ "~" + dtl.getIplvuom()
							+ "~" + dtl.getHeatingflv()
							+ "~" + dtl.getHeatingflvuom()
							+ "~" + dtl.getHeatingseasonal()
							+ "~" + dtl.getHeatingseasonaluom()
							+ "~" + dtl.getHeatingiplv()
							+ "~" + dtl.getHeatingiplvuom()
							+ "~" + dtl.getDesignflv()
							+ "~" + dtl.getDesignflvuom()
							+ "~" + dtl.getDesignseasonal()
							+ "~" + dtl.getDesignseasonaluom()
							+ "~" + dtl.getDesigniplv()
							+ "~" + dtl.getDesigniplvuom()
							+ "~" + dtl.getDesignheatingflv()
							+ "~" + dtl.getDesignheatingflvuom()
							+ "~" + dtl.getDesignheatingseasonal()
							+ "~" + dtl.getDesignheatingseasonaluom()
							+ "~" + dtl.getDesignheatingiplv()
							+ "~" + dtl.getDesignheatingiplvuom()
							+ "~" + dtl.getDesignsize()
							+ "~" + dtl.getDesignsizeuom()
							+ "~" + dtl.getRebateamount()
							+ "~" + dtl.getComments()
							+ "~" + username
							+ "~" + (this.newconstruction ? 1 : 0)
							+ "~" + (this.retrofit ? 1 : 0)
							+ "~" + this.projectname
							+ "|";
				}
				
			}
		}
		
		System.out.println(submitstr);
		
		int succ = hvacmngr.submitCustomRebatesReview(submitstr);
		
		if(succ == 0)
		{
			hvacmngr.setHasbeensubmitted(true);
			this.customreviewsubmitconfirmation = ManagedBeanObject.getResourceBundleKeyValue("hvaccalcmsg", "customreviewsubmitconfirmation");
		}
		
		return "submitcustomrebatesreview";
	}
	
	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	public long getNumofunits()
	{
		return numofunits;
	}

	public void setNumofunits(long numofunits)
	{
		this.numofunits = numofunits;
	}

	public String getZiplist()
	{
		return ziplist;
	}

	public void setZiplist(String ziplist)
	{
		this.ziplist = ziplist;
	}

	public String getParsedziplist()
	{
		return parsedziplist;
	}

	public void setParsedziplist(String parsedziplist)
	{
		this.parsedziplist = parsedziplist;
	}

	public String getParseseperatoroption()
	{
		return recordseperator;
	}

	public void setParseseperatoroption(String parseseperatoroption)
	{
		this.recordseperator = parseseperatoroption;
	}

	public String getSelectedtab()
	{
		return selectedtab;
	}

	public void setSelectedtab(String selectedtab)
	{
		this.selectedtab = selectedtab;
	}

	public String getProjecttype()
	{
		return projecttype;
	}

	public void setProjecttype(String projecttype)
	{
		this.projecttype = projecttype;
	}
	
	public String getOtherseparatorchar()
	{
		return otherseparatorchar;
	}

	public void setOtherseparatorchar(String otherseparatorchar)
	{
		this.otherseparatorchar = otherseparatorchar;
	}

	public String getAllequipselectedutility()
	{
		return allequipselectedutility;
	}

	public void setAllequipselectedutility(String allequipselectedutility)
	{
		this.allequipselectedutility = allequipselectedutility;
	}

	public String getAllequipinputerrmsg()
	{
		return allequipinputerrmsg;
	}

	public void setAllequipinputerrmsg(String allequipinputerrmsg)
	{
		this.allequipinputerrmsg = allequipinputerrmsg;
	}

	public boolean isNewconstruction()
	{
		return newconstruction;
	}

	public void setNewconstruction(boolean newconstruction)
	{
		this.newconstruction = newconstruction;
	}

	public boolean isRetrofit()
	{
		return retrofit;
	}

	public void setRetrofit(boolean retrofit)
	{
		this.retrofit = retrofit;
	}

	public String getHvacerrmsg()
	{
		return hvacerrmsg;
	}

	public void setHvacerrmsg(String hvacerrmsg)
	{
		this.hvacerrmsg = hvacerrmsg;
	}

	public String getEquiptableheader()
	{
		return equiptableheader;
	}

	public void setEquiptableheader(String equiptableheader)
	{
		this.equiptableheader = equiptableheader;
	}

	public String getHvacresulttableheader()
	{
		return hvacresulttableheader;
	}

	public void setHvacresulttableheader(String hvacresulttableheader)
	{
		this.hvacresulttableheader = hvacresulttableheader;
	}

	public String getRecordseperator()
	{
		return recordseperator;
	}

	public void setRecordseperator(String recordseperator)
	{
		this.recordseperator = recordseperator;
	}

	public String getFieldseparator()
	{
		return fieldseparator;
	}

	public void setFieldseparator(String fieldseparator)
	{
		this.fieldseparator = fieldseparator;
	}

	public boolean isDisplaystatus()
	{
		return displaystatus;
	}

	public void setDisplaystatus(boolean displaystatus)
	{
		this.displaystatus = displaystatus;
	}

	public boolean isCalculationcompleted()
	{
		return calculationcompleted;
	}

	public void setCalculationcompleted(boolean calculationcompleted)
	{
		this.calculationcompleted = calculationcompleted;
	}

	public boolean isAllequipnewconstruction()
	{
		return allequipnewconstruction;
	}

	public void setAllequipnewconstruction(boolean allequipnewconstruction)
	{
		this.allequipnewconstruction = allequipnewconstruction;
	}

	public boolean isAllequipretrofit()
	{
		return allequipretrofit;
	}

	public void setAllequipretrofit(boolean allequipretrofit)
	{
		this.allequipretrofit = allequipretrofit;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectnamealleqtab() {
		return projectnamealleqtab;
	}

	public void setProjectnamealleqtab(String projectnamealleqtab) {
		this.projectnamealleqtab = projectnamealleqtab;
	}

	public String getCustomziplist() {
		return customziplist;
	}

	public void setCustomziplist(String customziplist) {
		this.customziplist = customziplist;
	}

	public boolean isCustomnewconstruction() {
		return customnewconstruction;
	}

	public void setCustomnewconstruction(boolean customnewconstruction) {
		this.customnewconstruction = customnewconstruction;
	}

	public boolean isCustomretrofit() {
		return customretrofit;
	}

	public void setCustomretrofit(boolean customretrofit) {
		this.customretrofit = customretrofit;
	}

	public String getCustomhvacerrmsg() {
		return customhvacerrmsg;
	}

	public void setCustomhvacerrmsg(String customhvacerrmsg) {
		this.customhvacerrmsg = customhvacerrmsg;
	}

	public String getProjectnamecustomtab() {
		return projectnamecustomtab;
	}

	public void setProjectnamecustomtab(String projectnamecustomtab) {
		this.projectnamecustomtab = projectnamecustomtab;
	}

	public String getCustomresulttableheader() {
		return customresulttableheader;
	}

	public void setCustomresulttableheader(String customresulttableheader) {
		this.customresulttableheader = customresulttableheader;
	}

	public String getCustomreviewsubmitconfirmation() {
		return customreviewsubmitconfirmation;
	}

	public void setCustomreviewsubmitconfirmation(
			String customreviewsubmitconfirmation) {
		this.customreviewsubmitconfirmation = customreviewsubmitconfirmation;
	}
	
}
