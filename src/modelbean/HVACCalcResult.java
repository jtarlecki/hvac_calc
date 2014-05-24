package modelbean;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author mhossain
 *
 * Store calculated HVAC info
 */
public class HVACCalcResult
{
	//input parameters
	private String zipcodes = "";
	private String state = "";
	private long rebatorid = -1;
	private float tons = 0;
	private int equiptype = 0;
	private float flv1 = 0;
	private int flv1uom = 0;
	private float flv2 = 0;
	private int flv2uom = 0;
	private float iplv1 = 0;
	private int iplv1uom = 0;
	private float iplv2 = 0;
	private int iplv2uom = 0;
	private int subequiptype = 0;
	private String utilityname;
	private long equipseq = 0;
	private long siteseq = 0;
	private String sitenum = "";
	private long equipmentid = 0;
	private String equipmenttype = "";
	private String subequipmenttype = "";
	private String flv1unitofmeasurement = "";
	//private String flv1unitofmeasurement = "KW/TON";
	private String flv2unitofmeasurement = "SEER";
	private String iplv1unitofmearsurement = "";
	private String iplv2unitofmeasurement = "IEER";
	//private String iplv2unitofmeasurement = "";
	private float heatingflv = 0;
	private int heatingflvuom = 0;
	private String heatingflvuomval = "EER";
	private float heatingseasonal = 0;
	private int heatingseasonaluom = 0;
	private String heatingseasonaluomval = "HSPF";
	private float heatingiplv = 0;
	private int heatingiplvuom = 0;
	private String heatingiplvuomval = "IEER";
	private int technologycategory = 0;
	private int technology = 0;
	private int subcategory = 0;
	private int sizeuom = 6;
	private String sizeuomval = "TON";
	private float designflv = 0;
	private int designflvuom = 0;
	//private String designflvuomval = "EER";
	private String designflvuomval = "";
	private float designseasonal = 0;
	private int designseasonaluom = 0;
	private String designseasonaluomval = "SEER";
	private float designiplv = 0;
	private int designiplvuom = 0;
	//private String designiplvuomval = "IEER";
	private String designiplvuomval = "";
	private int flvuom = 0;
	//private String flvuomval = "EER";
	private String flvuomval = "";
	
	private int seasonaluom = 0;
	private String seasonaluomval = "SEER";
	private int iplvuom = 0;
	//private String iplvuomval = "IEER";
	private String iplvuomval = "";
	private float designheatingflv = 0;
	private int designheatingflvuom = 0;
	private String designheatingflvuomval = "EER";
	private float designheatingseasonal = 0;
	private int designheatingseasonaluom = 0;
	private String designheatingseasonaluoomval = "HSPF";
	private float designheatingiplv = 0;
	private int designheatingiplvuom = 0;
	private String designheatingiplvuomval = "IEER";
	private String equipmentcode = "";
	private String equipmentdesc = "";
	private String subequipmentcode = "";
	private String subequipmentdesc = "";
	private float designsize = 0;
	private int designsizeuom = 6;
	private String designsizeuomval = "TON";
	
	//output parameters
	private double rebateamount;
	private String rebatefeedback;
	private String programinfo;
	private String ismissing;
	private String iscustom;
	private double reqflv1;
	private double reqflv2;
	private double reqiplv1;
	private double reqiplv2;
	private String reqflv1uom;
	private String reqflv2uom;
	private String reqiplv1uom;
	private String reqiplv2uom;
	private String programmin;
	private String tradename;
	private String modelnumber;
	private String isqualifying;
	private String rebatorname;
	private long numofqualify = 0;
	private long numofnotqualify = 0;
	private double qualifyamount = 0;
	private double notqualifyamount = 0;
	private String hasaltutl = "N";
	private String hasaltrebateprog = "N";
	private String isnoprogram = "";
	private String isfullyqualified = "";
	private String ispartiallyqualified = "N";
	private String isnoqualify = "N";
	private long numberofrebator = 0;
	private String tbd = "Y";
	private String isprogram = "";
	private long totalquantity = 0;
	private double totalrebateamount = 0;
	private long programcount = 0;
	private double programamount = 0;
	private long noprogramcount = 0;
	private double noprogramamount = 0;
	private long tbdcount = 0;
	private double tbdamount = 0;
	private long notbdcount = 0;
	private double notbdamount = 0;
	private String projectname = "";
	private long requestinfoid = 0;
	private long rwwutilityid = 0;
	
	private List<HVACCalcResultDetails> hvacdtl = new ArrayList<HVACCalcResultDetails>();
	private boolean showdetail = true;
	
	public long getNumberofrebator()
	{
		return numberofrebator;
	}

	public void setNumberofrebator(long numberofrebator)
	{
		this.numberofrebator = numberofrebator;
	}

	private long numberofunits = 1;
	
	

	private boolean editable = false;
	
	public HVACCalcResult()
	{
		
	}
	
	public HVACCalcResult
	(
			String state,
			long rebatorid,
			float tons,
			int equiptype,
			float flv1,
			int flv1uom,
			float flv2,
			int flv2uom,
			float iplv1,
			int iplv1uom,
			int subequiptype
	)
	{
		this.state = state;
		this.rebatorid = rebatorid;
		this.tons = tons;
		this.equiptype = equiptype;
		this.flv1 = flv1;
		this.flv1uom = flv1uom;
		this.flv2 = flv2;
		this.flv2uom = flv2uom;
		this.iplv1 = iplv1;
		this.iplv1uom = iplv1uom;
		this.subequiptype = subequiptype;
	}
	
	public HVACCalcResult
	(
			double rebateamount, 
			String rebatefeedback,
			String programinfo,
			String ismissing,
			String iscustom,
			double reqflv1,
			double reqflv2,
			double reqiplv1,
			double reqiplv2,
			String reqflv1uom,
			String reqflv2uom,
			String reqiplv1uom,
			String reqiplv2uom,
			String programmin
	)
	{
		this.rebateamount = rebateamount;
		this.rebatefeedback = rebatefeedback;
		this.programinfo = programinfo;
		this.ismissing = ismissing;
		this.iscustom = iscustom;
		this.reqflv1 = reqflv1;
		this.reqflv2 = reqflv2;
		this.reqiplv1 = reqiplv1;
		this.reqiplv2 = reqiplv2;
		this.reqflv1uom = reqflv1uom;
		this.reqflv2uom = reqflv2uom;
		this.reqiplv1uom = reqiplv1uom;
		this.reqiplv2uom = reqiplv2uom;
		this.programmin = programmin;
	}
	
	public void addHVACDetail(HVACCalcResultDetails dtl)
	{
		this.hvacdtl.add(dtl);
	}
	
	public void clearHVACDetail()
	{
		this.hvacdtl.clear();
	}
	
	public XStream getHVACCalcResultXStream()
	{
		XStream xstream = new XStream();
		
		xstream.alias("HVACCalcResultdetails", HVACCalcResultDetails.class);
		xstream.alias("HVACCalcResult", HVACCalcResult.class);
		
		return xstream;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public long getRebatorid()
	{
		return rebatorid;
	}

	public void setRebatorid(long rebatorid)
	{
		this.rebatorid = rebatorid;
	}

	public float getTons()
	{
		return tons;
	}

	public void setTons(float tons)
	{
		this.tons = tons;
	}

	public int getEquiptype()
	{
		return equiptype;
	}

	public void setEquiptype(int equiptype)
	{
		this.equiptype = equiptype;
	}

	public float getFlv1()
	{
		return flv1;
	}

	public void setFlv1(float flv1)
	{
		this.flv1 = flv1;
	}

	public int getFlv1uom()
	{
		return flv1uom;
	}

	public void setFlv1uom(int flv1uom)
	{
		this.flv1uom = flv1uom;
	}

	public float getFlv2()
	{
		return flv2;
	}

	public void setFlv2(float flv2)
	{
		this.flv2 = flv2;
	}

	public int getFlv2uom()
	{
		return flv2uom;
	}

	public void setFlv2uom(int flv2uom)
	{
		this.flv2uom = flv2uom;
	}

	public float getIplv1()
	{
		return iplv1;
	}

	public void setIplv1(float iplv1)
	{
		this.iplv1 = iplv1;
	}

	public int getIplv1uom()
	{
		return iplv1uom;
	}

	public void setIplv1uom(int iplv1uom)
	{
		this.iplv1uom = iplv1uom;
	}

	public int getSubequiptype()
	{
		return subequiptype;
	}

	public void setSubequiptype(int subequiptype)
	{
		this.subequiptype = subequiptype;
	}

	public double getRebateamount()
	{
		return rebateamount;
	}

	public void setRebateamount(double rebateamount)
	{
		this.rebateamount = rebateamount;
	}

	public String getRebatefeedback()
	{
		return rebatefeedback;
	}

	public void setRebatefeedback(String rebatefeedback)
	{
		this.rebatefeedback = rebatefeedback;
	}

	public String getPrograminfo()
	{
		return programinfo;
	}

	public void setPrograminfo(String programinfo)
	{
		this.programinfo = programinfo;
	}

	public String getIsmissing()
	{
		return ismissing;
	}

	public void setIsmissing(String ismissing)
	{
		this.ismissing = ismissing;
	}

	public String getIscustom()
	{
		return iscustom;
	}

	public void setIscustom(String iscustom)
	{
		this.iscustom = iscustom;
	}

	public double getReqflv1()
	{
		return reqflv1;
	}

	public void setReqflv1(double reqflv1)
	{
		this.reqflv1 = reqflv1;
	}

	public double getReqflv2()
	{
		return reqflv2;
	}

	public void setReqflv2(double reqflv2)
	{
		this.reqflv2 = reqflv2;
	}

	public double getReqiplv1()
	{
		return reqiplv1;
	}

	public void setReqiplv1(double reqiplv1)
	{
		this.reqiplv1 = reqiplv1;
	}

	public double getReqiplv2()
	{
		return reqiplv2;
	}

	public void setReqiplv2(double reqiplv2)
	{
		this.reqiplv2 = reqiplv2;
	}

	public String getReqflv1uom()
	{
		return reqflv1uom;
	}

	public void setReqflv1uom(String reqflv1uom)
	{
		this.reqflv1uom = reqflv1uom;
	}

	public String getReqflv2uom()
	{
		return reqflv2uom;
	}

	public void setReqflv2uom(String reqflv2uom)
	{
		this.reqflv2uom = reqflv2uom;
	}

	public String getReqiplv1uom()
	{
		return reqiplv1uom;
	}

	public void setReqiplv1uom(String reqiplv1uom)
	{
		this.reqiplv1uom = reqiplv1uom;
	}

	public String getReqiplv2uom()
	{
		return reqiplv2uom;
	}

	public void setReqiplv2uom(String reqiplv2uom)
	{
		this.reqiplv2uom = reqiplv2uom;
	}

	public String getProgrammin()
	{
		return programmin;
	}

	public void setProgrammin(String programmin)
	{
		this.programmin = programmin;
	}

	public String getTradename()
	{
		return tradename;
	}

	public void setTradename(String tradename)
	{
		this.tradename = tradename;
	}

	public String getModelnumber()
	{
		return modelnumber;
	}

	public void setModelnumber(String modelnumber)
	{
		this.modelnumber = modelnumber;
	}

	public String getZipcodes()
	{
		return zipcodes;
	}

	public void setZipcodes(String zipcodes)
	{
		this.zipcodes = zipcodes;
	}

	public boolean isEditable()
	{
		return editable;
	}

	public void setEditable(boolean editable)
	{
		this.editable = editable;
	}

	public String getUtilityname()
	{
		return utilityname;
	}

	public void setUtilityname(String utilityname)
	{
		this.utilityname = utilityname;
	}
	
	public long getNumberofunits()
	{
		return numberofunits;
	}

	public void setNumberofunits(long numberofunits)
	{
		this.numberofunits = numberofunits;
	}

	public String getIsqualifying()
	{
		return isqualifying;
	}

	public void setIsqualifying(String isqualifying)
	{
		this.isqualifying = isqualifying;
	}

	public String getRebatorname()
	{
		return rebatorname;
	}

	public void setRebatorname(String rebatorname)
	{
		this.rebatorname = rebatorname;
	}

	public long getNumofqualify()
	{
		return numofqualify;
	}

	public void setNumofqualify(long numofqualify)
	{
		this.numofqualify = numofqualify;
	}

	public long getNumofnotqualify()
	{
		return numofnotqualify;
	}

	public void setNumofnotqualify(long numofnotqualify)
	{
		this.numofnotqualify = numofnotqualify;
	}

	public double getQualifyamount()
	{
		return qualifyamount;
	}

	public void setQualifyamount(double qualifyamount)
	{
		this.qualifyamount = qualifyamount;
	}

	public double getNotqualifyamount()
	{
		return notqualifyamount;
	}

	public void setNotqualifyamount(double notqualifyamount)
	{
		this.notqualifyamount = notqualifyamount;
	}

	public String getHasaltutl()
	{
		return hasaltutl;
	}

	public void setHasaltutl(String hasaltutl)
	{
		this.hasaltutl = hasaltutl;
	}

	public String getHasaltrebateprog()
	{
		return hasaltrebateprog;
	}

	public void setHasaltrebateprog(String hasaltrebateprog)
	{
		this.hasaltrebateprog = hasaltrebateprog;
	}

	public String getIsnoprogram()
	{
		return isnoprogram;
	}

	public void setIsnoprogram(String isnoprogram)
	{
		this.isnoprogram = isnoprogram;
	}

	public String getIsfullyqualified()
	{
		return isfullyqualified;
	}

	public void setIsfullyqualified(String isfullyqualified)
	{
		this.isfullyqualified = isfullyqualified;
	}

	public String getIspartiallyqualified()
	{
		return ispartiallyqualified;
	}

	public void setIspartiallyqualified(String ispartiallyqualified)
	{
		this.ispartiallyqualified = ispartiallyqualified;
	}

	public String getTbd()
	{
		return tbd;
	}

	public void setTbd(String tbd)
	{
		this.tbd = tbd;
	}

	public String getIsnoqualify()
	{
		return isnoqualify;
	}

	public void setIsnoqualify(String isnoqualify)
	{
		this.isnoqualify = isnoqualify;
	}

	public String getIsprogram()
	{
		return isprogram;
	}

	public void setIsprogram(String isprogram)
	{
		this.isprogram = isprogram;
	}

	public long getTotalquantity()
	{
		return totalquantity;
	}

	public void setTotalquantity(long totalquantity)
	{
		this.totalquantity = totalquantity;
	}

	public double getTotalrebateamount()
	{
		return totalrebateamount;
	}

	public void setTotalrebateamount(double totalrebateamount)
	{
		this.totalrebateamount = totalrebateamount;
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

	public List<HVACCalcResultDetails> getHvacdtl()
	{
		return hvacdtl;
	}

	public void setHvacdtl(List<HVACCalcResultDetails> hvacdtl)
	{
		this.hvacdtl = hvacdtl;
	}

	public boolean isShowdetail()
	{
		return showdetail;
	}

	public void setShowdetail(boolean showdetail)
	{
		this.showdetail = showdetail;
	}

	public long getEquipseq()
	{
		return equipseq;
	}

	public void setEquipseq(long equipseq)
	{
		this.equipseq = equipseq;
	}

	public long getSiteseq()
	{
		return siteseq;
	}

	public void setSiteseq(long siteseq)
	{
		this.siteseq = siteseq;
	}

	public String getSitenum()
	{
		return sitenum;
	}

	public void setSitenum(String sitenum)
	{
		this.sitenum = sitenum;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public long getRequestinfoid() {
		return requestinfoid;
	}

	public void setRequestinfoid(long requestinfoid) {
		this.requestinfoid = requestinfoid;
	}

	public long getRwwutilityid() {
		return rwwutilityid;
	}

	public void setRwwutilityid(long rwwutilityid) {
		this.rwwutilityid = rwwutilityid;
	}

	public long getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(long equipmentid) {
		this.equipmentid = equipmentid;
	}

	public float getIplv2() {
		return iplv2;
	}

	public void setIplv2(float iplv2) {
		this.iplv2 = iplv2;
	}

	public int getIplv2uom() {
		return iplv2uom;
	}

	public void setIplv2uom(int iplv2uom) {
		this.iplv2uom = iplv2uom;
	}

	public String getEquipmenttype() {
		return equipmenttype;
	}

	public void setEquipmenttype(String equipmenttype) {
		this.equipmenttype = equipmenttype;
	}

	public String getSubequipmenttype() {
		return subequipmenttype;
	}

	public void setSubequipmenttype(String subequipmenttype) {
		this.subequipmenttype = subequipmenttype;
	}

	public String getFlv1unitofmeasurement() {
		return flv1unitofmeasurement;
	}

	public void setFlv1unitofmeasurement(String flv1unitofmeasurement) {
		this.flv1unitofmeasurement = flv1unitofmeasurement;
	}

	public String getFlv2unitofmeasurement() {
		return flv2unitofmeasurement;
	}

	public void setFlv2unitofmeasurement(String flv2unitofmeasurement) {
		this.flv2unitofmeasurement = flv2unitofmeasurement;
	}

	public String getIplv1unitofmearsurement() {
		return iplv1unitofmearsurement;
	}

	public void setIplv1unitofmearsurement(String iplv1unitofmearsurement) {
		this.iplv1unitofmearsurement = iplv1unitofmearsurement;
	}

	public String getIplv2unitofmeasurement() {
		return iplv2unitofmeasurement;
	}

	public void setIplv2unitofmeasurement(String iplv2unitofmeasurement) {
		this.iplv2unitofmeasurement = iplv2unitofmeasurement;
	}

	public float getHeatingflv() {
		return heatingflv;
	}

	public void setHeatingflv(float heatingflv) {
		this.heatingflv = heatingflv;
	}

	public int getHeatingflvuom() {
		return heatingflvuom;
	}

	public void setHeatingflvuom(int heatingflvuom) {
		this.heatingflvuom = heatingflvuom;
	}

	public float getHeatingseasonal() {
		return heatingseasonal;
	}

	public void setHeatingseasonal(float heatingseasonal) {
		this.heatingseasonal = heatingseasonal;
	}

	public int getHeatingseasonaluom() {
		return heatingseasonaluom;
	}

	public void setHeatingseasonaluom(int heatingseasonaluom) {
		this.heatingseasonaluom = heatingseasonaluom;
	}

	public float getHeatingiplv() {
		return heatingiplv;
	}

	public void setHeatingiplv(float heatingiplv) {
		this.heatingiplv = heatingiplv;
	}

	public int getHeatingiplvuom() {
		return heatingiplvuom;
	}

	public void setHeatingiplvuom(int heatingiplvuom) {
		this.heatingiplvuom = heatingiplvuom;
	}

	public int getTechnologycategory() {
		return technologycategory;
	}

	public void setTechnologycategory(int technologycategory) {
		this.technologycategory = technologycategory;
	}

	public int getTechnology() {
		return technology;
	}

	public void setTechnology(int technology) {
		this.technology = technology;
	}

	public int getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(int subcategory) {
		this.subcategory = subcategory;
	}

	public int getSizeuom() {
		return sizeuom;
	}

	public void setSizeuom(int sizeuom) {
		this.sizeuom = sizeuom;
	}

	public float getDesignflv() {
		return designflv;
	}

	public void setDesignflv(float designflv) {
		this.designflv = designflv;
	}

	public int getDesignflvuom() {
		return designflvuom;
	}

	public void setDesignflvuom(int designflvuom) {
		this.designflvuom = designflvuom;
	}

	public float getDesignseasonal() {
		return designseasonal;
	}

	public void setDesignseasonal(float designseasonal) {
		this.designseasonal = designseasonal;
	}

	public int getDesignseasonaluom() {
		return designseasonaluom;
	}

	public void setDesignseasonaluom(int designseasonaluom) {
		this.designseasonaluom = designseasonaluom;
	}

	public float getDesigniplv() {
		return designiplv;
	}

	public void setDesigniplv(float designiplv) {
		this.designiplv = designiplv;
	}

	public int getDesigniplvuom() {
		return designiplvuom;
	}

	public void setDesigniplvuom(int designiplvuom) {
		this.designiplvuom = designiplvuom;
	}

	public String getHeatingflvuomval() {
		return heatingflvuomval;
	}

	public void setHeatingflvuomval(String heatingflvuomval) {
		this.heatingflvuomval = heatingflvuomval;
	}

	public String getHeatingseasonaluomval() {
		return heatingseasonaluomval;
	}

	public void setHeatingseasonaluomval(String heatingseasonaluomval) {
		this.heatingseasonaluomval = heatingseasonaluomval;
	}

	public String getHeatingiplvuomval() {
		return heatingiplvuomval;
	}

	public void setHeatingiplvuomval(String heatingiplvuomval) {
		this.heatingiplvuomval = heatingiplvuomval;
	}

	public String getSizeuomval() {
		return sizeuomval;
	}

	public void setSizeuomval(String sizeuomval) {
		this.sizeuomval = sizeuomval;
	}

	public String getDesignflvuomval() {
		//return designflvuomval;
		if (this.getEquiptype() == 2 || this.getEquiptype() == 4)
		{
			return "EER";
		}
		else if (this.getEquiptype() == 3)
		{
			return "KW/TON";
		}	

		else
		{
			return designflvuomval;
		}
	}

	public void setDesignflvuomval(String designflvuomval) {
		//this.designflvuomval = designflvuomval;
		if (this.getEquiptype() == 2 || this.getEquiptype() == 4)
		{
			this.designflvuomval = "EER";
		}
		else if (this.getEquiptype() == 3)
		{
			this.designflvuomval = "KW/TON";
		}		
		else
		{
			this.designflvuomval = designflvuomval;
		}
	}

	public String getDesignseasonaluomval() {
		return designseasonaluomval;
	}

	public void setDesignseasonaluomval(String designseasonaluomval) {
		this.designseasonaluomval = designseasonaluomval;
	}

	public String getDesigniplvuomval() {
		//return designiplvuomval;
		if (this.getEquiptype() == 2)
		{
			return "EER";
		}
		else if (this.getEquiptype() == 3)
		{
			return "KW/TON";
		}	
		else if (this.getEquiptype() == 4)
		{
			return "IEER";
		}
		else
		{
			return designiplvuomval;
		}
	}

	public void setDesigniplvuomval(String designiplvuomval) {
		//this.designiplvuomval = designiplvuomval;
		
		if (this.getEquiptype() == 2)
		{
			this.designiplvuomval = "EER";
		}
		else if (this.getEquiptype() == 3)
		{
			this.designiplvuomval = "KW/TON";
		}		
		else if (this.getEquiptype() == 4)
		{
			this.designiplvuomval = "IEER";
		}
		else
		{
			this.designiplvuomval = designiplvuomval;
		}
	
	}

	public int getFlvuom() {
		return flvuom;
	}

	public void setFlvuom(int flvuom) {
		this.flvuom = flvuom;
	}

	public String getFlvuomval() {
		//return flvuomval;
		if (this.getEquiptype() == 2 || this.getEquiptype() == 4)
		{
			return "EER";
		}
		else if (this.getEquiptype() == 3)
		{
			return "KW/TON";
		}	

		else
		{
			return flvuomval;
		}
	}

	public void setFlvuomval(String flvuomval) {
		//this.flvuomval = flvuomval;
		
		if (this.getEquiptype() == 2 || this.getEquiptype() == 4)
		{
			this.flvuomval = "EER";
		}
		else if (this.getEquiptype() == 3)
		{
			this.flvuomval = "KW/TON";
		}		
		else
		{
			this.flvuomval = flvuomval;
		}
	}

	public int getSeasonaluom() {
		return seasonaluom;
	}

	public void setSeasonaluom(int seasonaluom) {
		this.seasonaluom = seasonaluom;
	}

	public String getSeasonaluomval() {
		return seasonaluomval;
	}

	public void setSeasonaluomval(String seasonaluomval) {
		this.seasonaluomval = seasonaluomval;
	}

	public int getIplvuom() {
		return iplvuom;
	}

	public void setIplvuom(int iplvuom) {
		this.iplvuom = iplvuom;
	}

	public String getIplvuomval() {
		//return iplvuomval;
		
		if (this.getEquiptype() == 2)
		{
			return "EER";
		}
		else if (this.getEquiptype() == 3)
		{
			return "KW/TON";
		}	
		else if (this.getEquiptype() == 4)
		{
			return "IEER";
		}
		else
		{
			return iplvuomval;
		}
		
	}

	public void setIplvuomval(String iplvuomval) {
		//this.iplvuomval = iplvuomval;
		
		if (this.getEquiptype() == 2)
		{
			this.iplvuomval = "EER";
		}
		else if (this.getEquiptype() == 3)
		{
			this.iplvuomval = "KW/TON";
		}		
		else if (this.getEquiptype() == 4)
		{
			this.iplvuomval = "IEER";
		}
		else
		{
			this.iplvuomval = iplvuomval;
		}
		
	}

	public float getDesignheatingflv() {
		return designheatingflv;
	}

	public void setDesignheatingflv(float designheatingflv) {
		this.designheatingflv = designheatingflv;
	}

	public int getDesignheatingflvuom() {
		return designheatingflvuom;
	}

	public void setDesignheatingflvuom(int designheatingflvuom) {
		this.designheatingflvuom = designheatingflvuom;
	}

	public String getDesignheatingflvuomval() {
		return designheatingflvuomval;
	}

	public void setDesignheatingflvuomval(String designheatingflvuomval) {
		this.designheatingflvuomval = designheatingflvuomval;
	}

	public float getDesignheatingseasonal() {
		return designheatingseasonal;
	}

	public void setDesignheatingseasonal(float designheatingseasonal) {
		this.designheatingseasonal = designheatingseasonal;
	}

	public int getDesignheatingseasonaluom() {
		return designheatingseasonaluom;
	}

	public void setDesignheatingseasonaluom(int designheatingseasonaluom) {
		this.designheatingseasonaluom = designheatingseasonaluom;
	}

	public String getDesignheatingseasonaluoomval() {
		return designheatingseasonaluoomval;
	}

	public void setDesignheatingseasonaluoomval(String designheatingseasonaluoomval) {
		this.designheatingseasonaluoomval = designheatingseasonaluoomval;
	}

	public float getDesignheatingiplv() {
		return designheatingiplv;
	}

	public void setDesignheatingiplv(float designheatingiplv) {
		this.designheatingiplv = designheatingiplv;
	}

	public int getDesignheatingiplvuom() {
		return designheatingiplvuom;
	}

	public void setDesignheatingiplvuom(int designheatingiplvuom) {
		this.designheatingiplvuom = designheatingiplvuom;
	}

	public String getDesignheatingiplvuomval() {
		return designheatingiplvuomval;
	}

	public void setDesignheatingiplvuomval(String designheatingiplvuomval) {
		this.designheatingiplvuomval = designheatingiplvuomval;
	}

	public String getEquipmentcode() {
		return equipmentcode;
	}

	public void setEquipmentcode(String equipmentcode) {
		this.equipmentcode = equipmentcode;
	}

	public String getEquipmentdesc() {
		return equipmentdesc;
	}

	public void setEquipmentdesc(String equipmentdesc) {
		this.equipmentdesc = equipmentdesc;
	}

	public String getSubequipmentcode() {
		return subequipmentcode;
	}

	public void setSubequipmentcode(String subequipmentcode) {
		this.subequipmentcode = subequipmentcode;
	}

	public String getSubequipmentdesc() {
		return subequipmentdesc;
	}

	public void setSubequipmentdesc(String subequipmentdesc) {
		this.subequipmentdesc = subequipmentdesc;
	}

	public float getDesignsize() {
		return designsize;
	}

	public void setDesignsize(float designsize) {
		this.designsize = designsize;
	}

	public int getDesignsizeuom() {
		return designsizeuom;
	}

	public void setDesignsizeuom(int designsizeuom) {
		this.designsizeuom = designsizeuom;
	}

	public String getDesignsizeuomval() {
		return designsizeuomval;
	}

	public void setDesignsizeuomval(String designsizeuomval) {
		this.designsizeuomval = designsizeuomval;
	}
	
}
