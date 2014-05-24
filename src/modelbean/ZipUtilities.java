package modelbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.thoughtworks.xstream.XStream;

public class ZipUtilities
{
	private long mapinfoid;
	private String zipcode;
	private long utilityid;
	private double overlap;
	private long energytypeid;
	private long rwwutilityid;
	private String state;
	private String plattsutilityname = "No Utilities Available";
	private String datasorcedesc;
	private String energy;
	private String utilitydesc;
	private String rwwutilityname;
	private String city;
	private String tbd = "Y";
	private long empid =0;
	private String empname ="";
	private String empinit ="";
	
	
	private List<Utility> utilitybyzip = new ArrayList<Utility>();
	private List<SelectItem> utilitybyzipcombo = new ArrayList<SelectItem>();
	private long hvacziputlseq = 0;
	private long numofutility = 0;
	private String sitenum = "";
	
	private boolean editable = false;
	
	public ZipUtilities()
	{
		
	}
	
	public ZipUtilities
	(
			long mapinfoid,
			String zipcode,
			long utilityid,
			double overlap,
			long energytypeid,
			long rwwutilityid,
			String state,
			String plattsutilityname,
			String datasorcedesc,
			String energy,
			String utilitydesc,
			String rwwutilityname
	)
	{
		this.mapinfoid = mapinfoid;
		this.zipcode = zipcode;
		this.utilityid = utilityid;
		this.overlap = overlap;
		this.energytypeid = energytypeid;
		this.rwwutilityid = rwwutilityid;
		this.state = state;
		this.plattsutilityname = plattsutilityname;
		this.datasorcedesc = datasorcedesc;
		this.energy = energy;
		this.utilitydesc = utilitydesc;
		this.rwwutilityname = rwwutilityname;
	}
	
	public ZipUtilities
	(
			long mapinfoid,
			String zipcode,
			long utilityid,
			double overlap,
			long energytypeid,
			long rwwutilityid,
			String state,
			String plattsutilityname,
			String datasorcedesc,
			String energy,
			String utilitydesc,
			String rwwutilityname,
			boolean editable
	)
	{
		this
		(
				mapinfoid,
				zipcode,
				utilityid,
				overlap,
				energytypeid,
				rwwutilityid,
				state,
				plattsutilityname,
				datasorcedesc,
				energy,
				utilitydesc,
				rwwutilityname
		);
		this.editable = editable;
	}
	
	public ZipUtilities
	(
			long mapinfoid,
			String zipcode,
			long utilityid,
			double overlap,
			long energytypeid,
			long rwwutilityid,
			String state,
			String plattsutilityname,
			String datasorcedesc,
			String energy,
			String utilitydesc,
			String rwwutilityname,
			String city,
			boolean editable
	)
	{
		this
		(
				mapinfoid,
				zipcode,
				utilityid,
				overlap,
				energytypeid,
				rwwutilityid,
				state,
				plattsutilityname,
				datasorcedesc,
				energy,
				utilitydesc,
				rwwutilityname,
				editable
		);
		this.city = city;
	}
	
	public void generateUtilityCombo()
	{
		this.utilitybyzipcombo.clear();
		
		Iterator<Utility> it = this.utilitybyzip.iterator();
		 while(it.hasNext())
		 {
			 Utility tmputl = it.next();
			 this.utilitybyzipcombo.add(new SelectItem(tmputl.getUtilityname(), "" + tmputl.getId()));
		 }
		
	}
	
	public XStream getZipUtilitiesXStream()
	{
		XStream xstream =  new XStream();
		
		xstream.alias("utility", Utility.class);
		xstream.alias("ZipUtilities", ZipUtilities.class);
		
		return xstream;
	}
	
	//GETTERS
	public long getMapinfoid()
	{
		return mapinfoid;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public long getUtilityid()
	{
		return utilityid;
	}

	public double getOverlap()
	{
		return overlap;
	}

	public long getEnergytypeid()
	{
		return energytypeid;
	}

	public long getRwwutilityid()
	{
		return rwwutilityid;
	}

	public String getState()
	{
		return state;
	}

	public String getPlattsutilityname()
	{
		return plattsutilityname;
	}

	public String getDatasorcedesc()
	{
		return datasorcedesc;
	}

	public String getEnergy()
	{
		return energy;
	}

	public String getUtilitydesc()
	{
		return utilitydesc;
	}

	public String getRwwutilityname()
	{
		return rwwutilityname;
	}

	public boolean isEditable()
	{
		return editable;
	}

	public String getCity()
	{
		return city;
	}

	//SETTERS
	public void setMapinfoid(long mapinfoid)
	{
		this.mapinfoid = mapinfoid;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	public void setUtilityid(long utilityid)
	{
		this.utilityid = utilityid;
	}

	public void setOverlap(double overlap)
	{
		this.overlap = overlap;
	}

	public void setEnergytypeid(long energytypeid)
	{
		this.energytypeid = energytypeid;
	}

	public void setRwwutilityid(long rwwutilityid)
	{
		this.rwwutilityid = rwwutilityid;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public void setPlattsutilityname(String plattsutilityname)
	{
		this.plattsutilityname = plattsutilityname;
	}

	public void setDatasorcedesc(String datasorcedesc)
	{
		this.datasorcedesc = datasorcedesc;
	}

	public void setEnergy(String energy)
	{
		this.energy = energy;
	}

	public void setUtilitydesc(String utilitydesc)
	{
		this.utilitydesc = utilitydesc;
	}

	public void setRwwutilityname(String rwwutilityname)
	{
		this.rwwutilityname = rwwutilityname;
	}

	public void setEditable(boolean editable)
	{
		this.editable = editable;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public List<Utility> getUtilitybyzip()
	{
		return utilitybyzip;
	}

	public void setUtilitybyzip(List<Utility> utilitybyzip)
	{
		this.utilitybyzip = utilitybyzip;
		this.numofutility = this.utilitybyzip.size();
	}

	public List<SelectItem> getUtilitybyzipcombo()
	{
		return utilitybyzipcombo;
	}

	public void setUtilitybyzipcombo(List<SelectItem> utilitybyzipcombo)
	{
		this.utilitybyzipcombo = utilitybyzipcombo;
	}

	public String getTbd()
	{
		return tbd;
	}

	public void setTbd(String tbd)
	{
		this.tbd = tbd;
	}

	public long getEmpid()
	{
		return empid;
	}

	public void setEmpid(long empid)
	{
		this.empid = empid;
	}

	public String getEmpinit()
	{
		return empinit;
	}

	public void setEmpinit(String empinit)
	{
		this.empinit = empinit;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public long getHvacziputlseq()
	{
		return hvacziputlseq;
	}

	public void setHvacziputlseq(long hvacziputlseq)
	{
		this.hvacziputlseq = hvacziputlseq;
	}

	public long getNumofutility()
	{
		return numofutility;
	}

	public void setNumofutility(long numofutility)
	{
		this.numofutility = numofutility;
	}

	public String getSitenum()
	{
		return sitenum;
	}

	public void setSitenum(String sitenum)
	{
		this.sitenum = sitenum;
	}	
	
	
}
