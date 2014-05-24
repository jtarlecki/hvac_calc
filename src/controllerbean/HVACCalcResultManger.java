package controllerbean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import modelbean.HVACCalcResult;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.richfaces.component.html.HtmlDataTable;

import com.thoughtworks.xstream.XStream;

import wslistobj.HVACCalcResultList;
import wsobj.WebMethodNames;


public class HVACCalcResultManger
{
	private List<HVACCalcResult> results = new ArrayList<HVACCalcResult>();
	private List<HVACCalcResult> hvacinput = new ArrayList<HVACCalcResult>();
	private List<HVACCalcResult> hvacoutput = new ArrayList<HVACCalcResult>();
	private List<HVACCalcResult> hvacoutputsubtotal = new ArrayList<HVACCalcResult>();
	private HtmlDataTable hvacinputdatatable = new HtmlDataTable();
	private List<HVACCalcResult> customhvacinput = new ArrayList<HVACCalcResult>();
	private HtmlDataTable customhvacinputdatatable = new HtmlDataTable();
	private List<HVACCalcResult> customhvacoutput = new ArrayList<HVACCalcResult>();
	private HtmlDataTable customhvacoutputdatatable = new HtmlDataTable();
	
	private long totalsites = 0;
	private double totalrebate = 0;
	private long fullyqualifiedcount = 0;
	private double fullyqualifiedamount = 0;
	private long partialqualifiedcount = 0;
	private double partialqualifiedamount = 0;
	private long programcount = 0;
	private double programamount = 0;
	private long noprogramcount = 0;
	private double noprogramamount = 0;
	private long noqualifycount = 0;
	private double noqualifyamount = 0;
	private long tbdcount = 0;
	private double tbdamount = 0;
	private long notbdcount = 0;
	private double notbdamount = 0;
	private boolean expandalldetails = true;
	private boolean expandallcustomdetails = true;
	private boolean hasbeensubmitted = false;
	
	public HVACCalcResultManger()
	{
		this.addNewHVACInput(new HVACCalcResult());
		this.addNewCustomHVACInput(new HVACCalcResult());
	}
	
	public void addNewResult(HVACCalcResult result)
	{
		
		try
		{
			XStream xstream = result.getHVACCalcResultXStream();
			String inhvacxmlstr = xstream.toXML(result);
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(WebMethodNames.endpointurl
					+ WebMethodNames.hvaccalc_webservice);
			call.setOperation(WebMethodNames.hvaccalc_calculateHVAC);
			String outhvacxmlstr = (String) call.invoke(new Object[]{inhvacxmlstr});
			result = (HVACCalcResult) xstream.fromXML(outhvacxmlstr);
			
			this.results.add(result);
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
	
	public void addNewHVACInput(HVACCalcResult result)
	{
		this.hvacinput.add(result);
	}
	
	public void addNewCustomHVACInput(HVACCalcResult result)
	{
		this.customhvacinput.add(result);
	}
	
	public void calculateCustomHVAC(HVACCalcResult result)
	{
		
		HVACCalcResult rsltout = result;
		
		try
		{
			XStream inxstream = result.getHVACCalcResultXStream();
			String inhvacxmlstr = inxstream.toXML(result);
			
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(WebMethodNames.endpointurl
					+ WebMethodNames.hvaccalc_webservice);
			call.setOperation(WebMethodNames.hvaccalc_calculateCustomHVAC);
			String outhvacxmlstr = (String) call.invoke(new Object[]{inhvacxmlstr});
			
			HVACCalcResultList hvaclist = new HVACCalcResultList();
			XStream outhvacxstream = hvaclist.getHVACCalcResultXStream();
			hvaclist = (HVACCalcResultList) outhvacxstream.fromXML(outhvacxmlstr);
			
			Iterator<HVACCalcResult> it = hvaclist.getHresult().iterator();
			while(it.hasNext())
			{
				HVACCalcResult  tmprslt = it.next();
				rsltout.setRebatorname(tmprslt.getRebatorname());
				if(tmprslt.getIsqualifying().equals("T"))
				{
					rsltout.setNumofqualify(rsltout.getNumberofunits());
					rsltout.setQualifyamount(rsltout.getNumberofunits() * tmprslt.getRebateamount());
				}
				else
				{
					rsltout.setNumofnotqualify(rsltout.getNumberofunits());
					rsltout.setNotqualifyamount(rsltout.getNumberofunits() + tmprslt.getRebateamount());
				}
				
			}
			/*
			if(rsltout.getNumofqualify() > 0 && rsltout.getNumofnotqualify() == 0)
			{
				rsltout.setIsfullyqualified("Y");
				this.fullyqualifiedcount = this.fullyqualifiedcount + rsltout.getNumofqualify();
				this.fullyqualifiedamount = this.fullyqualifiedamount + rsltout.getQualifyamount();
			}
			else if(rsltout.getNumofqualify() > 0 && rsltout.getNumofnotqualify() > 0)
			{
				rsltout.setIspartiallyqualified("Y");
				this.partialqualifiedcount = this.partialqualifiedcount + rsltout.getNumofqualify();
				this.partialqualifiedamount = this.partialqualifiedamount + rsltout.getQualifyamount();
			}
			else
			{
				rsltout.setIsnoqualify("Y");
				this.noqualifycount = this.noqualifycount + rsltout.getNumofnotqualify();
				this.noqualifyamount = this.noqualifyamount + rsltout.getNotqualifyamount();
			}
			*/
			this.addNewHVACOutput(rsltout);
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
	
	public void calculateCustomHVACOverload
	(
			String ziputilities, 
			String utilities,
			String rprojectname,
			long rsessionid,
			String username
	)
	{
		try
		{
			this.clearCustomHVACOut();
			
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(WebMethodNames.endpointurl
					+ WebMethodNames.hvaccalc_webservice);
			call.setOperation(WebMethodNames.hvaccalc_calculateCustomHVACWithEquipments);
			
			String outhvacxmlstr = (String) call.invoke(new Object[]{ziputilities, utilities, rprojectname, rsessionid, username});
			
			HVACCalcResultList hvaclist = new HVACCalcResultList();
			XStream xstream = hvaclist.getHVACCalcResultXStream();
			hvaclist = (HVACCalcResultList) xstream.fromXML(outhvacxmlstr);
			
			this.hvacoutput = hvaclist.getHresult();
			this.calculateCustomHVACTotal();
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
	
	public void estimateCustomHVAC
	(
			String ziputilities, 
			String utilities,
			String rprojectname,
			long rsessionid,
			String username
	)
	{
		try
		{
			//this.clearCustomHVACOut();
			
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(WebMethodNames.endpointurl
					+ WebMethodNames.hvaccalc_webservice);
			call.setOperation(WebMethodNames.hvaccalc_estimateCustomHVACWithEquipments);
			
			//System.out.println("webservice call " + WebMethodNames.hvaccalc_estimateCustomHVACWithEquipments);
			
			String outhvacxmlstr = (String) call.invoke(new Object[]{ziputilities, utilities, rprojectname, rsessionid, username});
			
			//System.out.println(outhvacxmlstr);
			
			HVACCalcResultList hvaclist = new HVACCalcResultList();
			XStream xstream = hvaclist.getHVACCalcResultXStream();
			hvaclist = (HVACCalcResultList) xstream.fromXML(outhvacxmlstr);
			

			
			this.customhvacoutput = hvaclist.getHresult();
			//System.out.println("custom estimate: " + this.customhvacoutput.size());
			//this.calculateCustomHVACTotal();
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
	
	public void calculateCustomHVACTotal()
	{
		this.totalsites = this.hvacoutput.size();
		Iterator<HVACCalcResult> it = this.hvacoutput.iterator();
		while(it.hasNext())
		{
			HVACCalcResult tmprslt = it.next();
			this.totalrebate = this.totalrebate + tmprslt.getTotalrebateamount();
			if(tmprslt.getNumofqualify() > 0 && tmprslt.getNumofnotqualify() > 0)
			{
				this.partialqualifiedcount = this.partialqualifiedcount + tmprslt.getNumofqualify();
				this.partialqualifiedamount = this.partialqualifiedamount + tmprslt.getQualifyamount();
			}
			else if(tmprslt.getNumofqualify() > 0)
			{
				this.fullyqualifiedcount = this.fullyqualifiedcount + tmprslt.getNumofqualify();
				this.fullyqualifiedamount = this.fullyqualifiedamount + tmprslt.getQualifyamount();
			}
			else
			{
				this.noqualifycount =  this.noqualifycount + tmprslt.getNumofnotqualify();
				this.noqualifyamount = this.noqualifyamount + tmprslt.getNotqualifyamount();
			}
			this.programcount = this.programcount + tmprslt.getProgramcount();
			this.programamount = this.programamount + tmprslt.getProgramamount();
			this.noprogramcount = this.noprogramcount + tmprslt.getNoprogramcount();
			this.noprogramamount = this.noprogramamount + tmprslt.getNoprogramamount();
			this.tbdcount =  this.tbdcount + tmprslt.getTbdcount();
			this.tbdamount = this.tbdamount + tmprslt.getTbdamount();
			this.notbdcount = this.notbdcount + tmprslt.getNotbdcount();
			this.notbdamount = this.notbdamount + tmprslt.getNotbdamount();
		}
	}
	
	public void clearCustomHVACOut()
	{
		this.hvacoutput.clear();
		totalsites = 0;
		totalrebate = 0;
		fullyqualifiedcount = 0;
		fullyqualifiedamount = 0;
		partialqualifiedcount = 0;
		partialqualifiedamount = 0;
		programcount = 0;
		programamount = 0;
		noprogramcount = 0;
		noprogramamount = 0;
		noqualifycount = 0;
		noqualifyamount = 0;
		tbdcount = 0;
		tbdamount = 0;
		notbdcount = 0;
		notbdamount = 0;
	}
	
	public int submitCustomRebatesReview(String equipments)
	{
		int succ = 0;
		
		Service service = new Service();
		
		try {
			Call call;
			
			call = (Call) service.createCall();
			
			call.setTargetEndpointAddress(WebMethodNames.endpointurl
					+ WebMethodNames.hvaccalc_webservice);
			call.setOperation(WebMethodNames.hvaccalc_submitCustomRebatesReview);
			
			//System.out.println("webservice call " + WebMethodNames.hvaccalc_estimateCustomHVACWithEquipments);
			
			String outhvacxmlstr = (String) call.invoke(new Object[]{equipments});
			
			succ = Integer.parseInt(outhvacxmlstr);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return succ;
	}
	
	public long getTotalsites()
	{
		return totalsites;
	}

	public void setTotalsites(long totalsites)
	{
		this.totalsites = totalsites;
	}

	public double getTotalrebate()
	{
		return totalrebate;
	}

	public void setTotalrebate(double totalrebate)
	{
		this.totalrebate = totalrebate;
	}

	public long getFullyqualifiedcount()
	{
		return fullyqualifiedcount;
	}

	public void setFullyqualifiedcount(long fullyqualifiedcount)
	{
		this.fullyqualifiedcount = fullyqualifiedcount;
	}

	public double getFullyqualifiedamount()
	{
		return fullyqualifiedamount;
	}

	public void setFullyqualifiedamount(double fullyqualifiedamount)
	{
		this.fullyqualifiedamount = fullyqualifiedamount;
	}

	public long getPartialqualifiedcount()
	{
		return partialqualifiedcount;
	}

	public void setPartialqualifiedcount(long partialqualifiedcount)
	{
		this.partialqualifiedcount = partialqualifiedcount;
	}

	public double getPartialqualifiedamount()
	{
		return partialqualifiedamount;
	}

	public void setPartialqualifiedamount(double partialqualifiedamount)
	{
		this.partialqualifiedamount = partialqualifiedamount;
	}

	public long getNoprogramcount()
	{
		return noprogramcount;
	}

	public void setNoprogramcount(long noprogramcount)
	{
		this.noprogramcount = noprogramcount;
	}

	public double getNoprogramamount()
	{
		return noprogramamount;
	}

	public void setNoprogramamount(double noprogramamount)
	{
		this.noprogramamount = noprogramamount;
	}

	public long getNoqualifycount()
	{
		return noqualifycount;
	}

	public void setNoqualifycount(long noqualifycount)
	{
		this.noqualifycount = noqualifycount;
	}

	public double getNoqualifyamount()
	{
		return noqualifyamount;
	}

	public void setNoqualifyamount(double noqualifyamount)
	{
		this.noqualifyamount = noqualifyamount;
	}

	public long getTbdcount()
	{
		return tbdcount;
	}

	public void setTbdcount(long tbdcount)
	{
		this.tbdcount = tbdcount;
	}

	public double getTbdamount()
	{
		return tbdamount;
	}

	public void setTbdamount(double tbdamount)
	{
		this.tbdamount = tbdamount;
	}

	public void addNewHVACOutput(HVACCalcResult result)
	{
		this.hvacoutput.add(result);
	}
	
	public void clearResults()
	{
		this.results.clear();
	}
	
	public void clearHVACInput()
	{
		this.hvacinput.clear();
	}

	public void clearHVACOutput()
	{
		this.hvacoutput.clear();
	}
	
	public List<HVACCalcResult> getResults()
	{
		return results;
	}

	public void setResults(List<HVACCalcResult> results)
	{
		this.results = results;
	}

	public List<HVACCalcResult> getHvacinput()
	{
		return hvacinput;
	}

	public void setHvacinput(List<HVACCalcResult> hvacinput)
	{
		this.hvacinput = hvacinput;
	}

	public List<HVACCalcResult> getHvacoutput()
	{
		return hvacoutput;
	}

	public void setHvacoutput(List<HVACCalcResult> hvacoutput)
	{
		this.hvacoutput = hvacoutput;
	}

	public HtmlDataTable getHvacinputdatatable()
	{
		return hvacinputdatatable;
	}

	public void setHvacinputdatatable(HtmlDataTable hvacinputdatatable)
	{
		this.hvacinputdatatable = hvacinputdatatable;
	}

	public List<HVACCalcResult> getHvacoutputsubtotal()
	{
		return hvacoutputsubtotal;
	}

	public void setHvacoutputsubtotal(List<HVACCalcResult> hvacoutputsubtotal)
	{
		this.hvacoutputsubtotal = hvacoutputsubtotal;
	}

	public long getProgramcount()
	{
		return programcount;
	}

	public void setProgramcount(long programcount)
	{
		this.programcount = programcount;
	}

	public double getProgramamount()
	{
		return programamount;
	}

	public void setProgramamount(double programamount)
	{
		this.programamount = programamount;
	}

	public long getNotbdcount()
	{
		return notbdcount;
	}

	public void setNotbdcount(long notbdcount)
	{
		this.notbdcount = notbdcount;
	}

	public double getNotbdamount()
	{
		return notbdamount;
	}

	public void setNotbdamount(double notbdamount)
	{
		this.notbdamount = notbdamount;
	}

	public boolean isExpandalldetails()
	{
		return expandalldetails;
	}

	public void setExpandalldetails(boolean expandalldetails)
	{
		this.expandalldetails = expandalldetails;
	}

	public List<HVACCalcResult> getCustomhvacinput() {
		return customhvacinput;
	}

	public void setCustomhvacinput(List<HVACCalcResult> customhvacinput) {
		this.customhvacinput = customhvacinput;
	}

	public HtmlDataTable getCustomhvacinputdatatable() {
		return customhvacinputdatatable;
	}

	public void setCustomhvacinputdatatable(HtmlDataTable customhvacinputdatatable) {
		this.customhvacinputdatatable = customhvacinputdatatable;
	}

	public List<HVACCalcResult> getCustomhvacoutput() {
		return customhvacoutput;
	}

	public void setCustomhvacoutput(List<HVACCalcResult> customhvacoutput) {
		this.customhvacoutput = customhvacoutput;
	}

	public HtmlDataTable getCustomhvacoutputdatatable() {
		return customhvacoutputdatatable;
	}

	public void setCustomhvacoutputdatatable(HtmlDataTable customhvacoutputdatatable) {
		this.customhvacoutputdatatable = customhvacoutputdatatable;
	}

	public boolean isExpandallcustomdetails() {
		return expandallcustomdetails;
	}

	public void setExpandallcustomdetails(boolean expandallcustomdetails) {
		this.expandallcustomdetails = expandallcustomdetails;
	}

	public boolean isHasbeensubmitted() {
		return hasbeensubmitted;
	}

	public void setHasbeensubmitted(boolean hasbeensubmitted) {
		this.hasbeensubmitted = hasbeensubmitted;
	}
	
}
