package modelbean;



import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @author mhossain
 *
 * Equipment datastructure
 */
public class Equipment
{
	private long oemid;
	private String oemname;
	private long tradeid;
	private String tradename;
	private long equipid;
	private long arirefnumber;
	private String modelstatus;
	private String modelnumber;
	private String modelcoilnumber;
	private long coolingcapacity;
	private double tonage;
	private double eer;
	private double ieer;
	private double seer;
	private String aritype;
	private String typeexplanation;
	private long utilityid;
	private String utilityname;
	private String state;
	private long rebatorid;
	private String rebatorname;
	private double rebateamount;
	private long numofunits = 1;
	private long equiptypeid = 0;
	private long subequiptypeid = 0;
	private float hoursepower;
	private int temperatureid;
	private int phase;
	private String replacingequipment;
	private String temperature;
	private float flv1 = 0;
	private int flv1uom = 0;
	private float flv2 = 0;
	private int flv2uom = 0;
	private float iplv1 = 0;
	private int iplv1uom = 0;
	private float iplv2 = 0;
	private int iplv2uom = 0;
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
	private String designflvuomval = "EER";
	private float designseasonal = 0;
	private int designseasonaluom = 0;
	private String designseasonaluomval = "SEER";
	private float designiplv = 0;
	private int designiplvuom = 0;
	private String designiplvuomval = "IEER";
	private int flvuom = 0;
	private String flvuomval = "EER";
	private int seasonaluom = 0;
	private String seasonaluomval = "SEER";
	private int iplvuom = 0;
	private String iplvuomval = "IEER";
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
	
	private String existingequimentmakemodel = "";
	private String existingequipmentagefunctionality = "";
	
	
	private double calcrebateamt = 0.0;
	
	private boolean editable = false;
	
	public Equipment
	(
			long oemid,
			String oemname,
			long tradeid,
			String tradename,
			long equipid,
			long arirefnumber,
			String modelstatus,
			String modelnumber,
			String modelcoilnumber,
			long coolingcapacity,
			double tonage,
			double eer,
			double ieer,
			String aritype,
			String typeexplanation,
			long utilityid,
			String utilityname,
			String state,
			long rebatorid,
			String rebatorname,
			double rebateamount,
			boolean editable
	)
	{
		this.oemid = oemid;
		this.oemname = oemname;
		this.tradeid = tradeid;
		this.tradename = tradename;
		this.equipid = equipid;
		this.arirefnumber = arirefnumber;
		this.modelstatus = modelstatus;
		this.modelnumber = modelnumber;
		this.modelcoilnumber = modelcoilnumber;
		this.coolingcapacity = coolingcapacity;
		this.tonage = tonage;
		this.eer = eer;
		this.ieer = ieer;
		this.aritype = aritype;
		this.typeexplanation = typeexplanation;
		this.utilityid = utilityid;
		this.utilityname = utilityname;
		this.state = state;
		this.rebatorid = rebatorid;
		this.rebatorname = rebatorname;
		this.rebateamount = rebateamount;
		this.editable = editable;
		this.calcrebateamt = this.numofunits * this.rebateamount;
	}
	
	public Equipment
	(
			long oemid,
			String oemname,
			long tradeid,
			String tradename,
			long equipid,
			long arirefnumber,
			String modelstatus,
			String modelnumber,
			String modelcoilnumber,
			long coolingcapacity,
			double tonage,
			double eer,
			double ieer,
			String aritype,
			String typeexplanation,
			boolean editable
	)
	{
		this.oemid = oemid;
		this.oemname = oemname;
		this.tradeid = tradeid;
		this.tradename = tradename;
		this.equipid = equipid;
		this.arirefnumber = arirefnumber;
		this.modelstatus = modelstatus;
		this.modelnumber = modelnumber;
		this.modelcoilnumber = modelcoilnumber;
		this.coolingcapacity = coolingcapacity;
		this.tonage = tonage;
		this.eer = eer;
		this.ieer = ieer;
		this.aritype = aritype;
		this.typeexplanation = typeexplanation;
		this.editable = editable;
		this.calcrebateamt = this.numofunits * this.rebateamount;
	}
	
	public Equipment()
	{
		
	}
	
	public XStream getEquipmentXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Equipment", Equipment.class);
		
		return xstream;
	}

	public long getOemid()
	{
		return oemid;
	}

	public void setOemid(long oemid)
	{
		this.oemid = oemid;
	}

	public String getOemname()
	{
		return oemname;
	}

	public void setOemname(String oemname)
	{
		this.oemname = oemname;
	}

	public long getTradeid()
	{
		return tradeid;
	}

	public void setTradeid(long tradeid)
	{
		this.tradeid = tradeid;
	}

	public String getTradename()
	{
		return tradename;
	}

	public void setTradename(String tradename)
	{
		this.tradename = tradename;
	}

	public long getEquipid()
	{
		return equipid;
	}

	public void setEquipid(long equipid)
	{
		this.equipid = equipid;
	}

	public long getArirefnumber()
	{
		return arirefnumber;
	}

	public void setArirefnumber(long arirefnumber)
	{
		this.arirefnumber = arirefnumber;
	}

	public String getModelstatus()
	{
		return modelstatus;
	}

	public void setModelstatus(String modelstatus)
	{
		this.modelstatus = modelstatus;
	}

	public String getModelnumber()
	{
		return modelnumber;
	}

	public void setModelnumber(String modelnumber)
	{
		this.modelnumber = modelnumber;
	}

	public String getModelcoilnumber()
	{
		return modelcoilnumber;
	}

	public void setModelcoilnumber(String modelcoilnumber)
	{
		this.modelcoilnumber = modelcoilnumber;
	}

	public long getCoolingcapacity()
	{
		return coolingcapacity;
	}

	public void setCoolingcapacity(long coolingcapacity)
	{
		this.coolingcapacity = coolingcapacity;
	}

	public double getTonage()
	{
		return tonage;
	}

	public void setTonage(double tonage)
	{
		this.tonage = tonage;
	}

	public double getEer()
	{
		return eer;
	}

	public void setEer(double eer)
	{
		this.eer = eer;
	}

	public double getIeer()
	{
		return ieer;
	}

	public void setIeer(double ieer)
	{
		this.ieer = ieer;
	}

	public String getAritype()
	{
		return aritype;
	}

	public void setAritype(String aritype)
	{
		this.aritype = aritype;
	}

	public String getTypeexplanation()
	{
		return typeexplanation;
	}

	public void setTypeexplanation(String typeexplanation)
	{
		this.typeexplanation = typeexplanation;
	}

	public boolean isEditable()
	{
		return editable;
	}

	public void setEditable(boolean editable)
	{
		this.editable = editable;
	}

	public long getUtilityid()
	{
		return utilityid;
	}

	public void setUtilityid(long utilityid)
	{
		this.utilityid = utilityid;
	}

	public String getUtilityname()
	{
		return utilityname;
	}

	public void setUtilityname(String utilityname)
	{
		this.utilityname = utilityname;
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

	public String getRebatorname()
	{
		return rebatorname;
	}

	public void setRebatorname(String rebatorname)
	{
		this.rebatorname = rebatorname;
	}

	public double getRebateamount()
	{
		return rebateamount;
	}

	public void setRebateamount(double rebateamount)
	{
		this.rebateamount = rebateamount;
	}

	public long getNumofunits()
	{
		return numofunits;
	}

	public void setNumofunits(long numofunits)
	{
		this.numofunits = numofunits;
	}

	public double getCalcrebateamt()
	{
		return calcrebateamt;
	}

	public void setCalcrebateamt(double calcrebateamt)
	{
		this.calcrebateamt = calcrebateamt;
	}

	public long getEquiptypeid()
	{
		return equiptypeid;
	}

	public void setEquiptypeid(long equiptypeid)
	{
		this.equiptypeid = equiptypeid;
	}

	public long getSubequiptypeid()
	{
		return subequiptypeid;
	}

	public void setSubequiptypeid(long subequiptypeid)
	{
		this.subequiptypeid = subequiptypeid;
	}

	public float getHoursepower()
	{
		return hoursepower;
	}

	public void setHoursepower(float hoursepower)
	{
		this.hoursepower = hoursepower;
	}

	public int getTemperatureid()
	{
		return temperatureid;
	}

	public void setTemperatureid(int temperatureid)
	{
		this.temperatureid = temperatureid;
	}

	public int getPhase()
	{
		return phase;
	}

	public void setPhase(int phase)
	{
		this.phase = phase;
	}

	public String getReplacingequipment()
	{
		return replacingequipment;
	}

	public void setReplacingequipment(String replacingequipment)
	{
		this.replacingequipment = replacingequipment;
	}

	public String getTemperature()
	{
		return temperature;
	}

	public void setTemperature(String temperature)
	{
		this.temperature = temperature;
	}

	public float getFlv1() {
		return flv1;
	}

	public void setFlv1(float flv1) {
		this.flv1 = flv1;
	}

	public int getFlv1uom() {
		return flv1uom;
	}

	public void setFlv1uom(int flv1uom) {
		this.flv1uom = flv1uom;
	}

	public float getFlv2() {
		return flv2;
	}

	public void setFlv2(float flv2) {
		this.flv2 = flv2;
	}

	public int getFlv2uom() {
		return flv2uom;
	}

	public void setFlv2uom(int flv2uom) {
		this.flv2uom = flv2uom;
	}

	public float getIplv1() {
		return iplv1;
	}

	public void setIplv1(float iplv1) {
		this.iplv1 = iplv1;
	}

	public int getIplv1uom() {
		return iplv1uom;
	}

	public void setIplv1uom(int iplv1uom) {
		this.iplv1uom = iplv1uom;
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

	public double getSeer() {
		return seer;
	}

	public void setSeer(double seer) {
		this.seer = seer;
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

	public int getFlvuom() {
		return flvuom;
	}

	public void setFlvuom(int flvuom) {
		this.flvuom = flvuom;
	}

	public int getSeasonaluom() {
		return seasonaluom;
	}

	public void setSeasonaluom(int seasonaluom) {
		this.seasonaluom = seasonaluom;
	}

	public int getIplvuom() {
		return iplvuom;
	}

	public void setIplvuom(int iplvuom) {
		this.iplvuom = iplvuom;
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
		return designflvuomval;
	}

	public void setDesignflvuomval(String designflvuomval) {
		this.designflvuomval = designflvuomval;
	}

	public String getDesignseasonaluomval() {
		return designseasonaluomval;
	}

	public void setDesignseasonaluomval(String designseasonaluomval) {
		this.designseasonaluomval = designseasonaluomval;
	}

	public String getDesigniplvuomval() {
		return designiplvuomval;
	}

	public void setDesigniplvuomval(String designiplvuomval) {
		this.designiplvuomval = designiplvuomval;
	}

	public String getFlvuomval() {
		return flvuomval;
	}

	public void setFlvuomval(String flvuomval) {
		this.flvuomval = flvuomval;
	}

	public String getSeasonaluomval() {
		return seasonaluomval;
	}

	public void setSeasonaluomval(String seasonaluomval) {
		this.seasonaluomval = seasonaluomval;
	}

	public String getIplvuomval() {
		return iplvuomval;
	}

	public void setIplvuomval(String iplvuomval) {
		this.iplvuomval = iplvuomval;
	}

	public String getDesignheatingflvuomval() {
		return designheatingflvuomval;
	}

	public void setDesignheatingflvuomval(String designheatingflvuomval) {
		this.designheatingflvuomval = designheatingflvuomval;
	}

	public String getDesignheatingseasonaluoomval() {
		return designheatingseasonaluoomval;
	}

	public void setDesignheatingseasonaluoomval(String designheatingseasonaluoomval) {
		this.designheatingseasonaluoomval = designheatingseasonaluoomval;
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

	public String getExistingequimentmakemodel() {
		return existingequimentmakemodel;
	}

	public void setExistingequimentmakemodel(String existingequimentmakemodel) {
		this.existingequimentmakemodel = existingequimentmakemodel;
	}

	public String getExistingequipmentagefunctionality() {
		return existingequipmentagefunctionality;
	}

	public void setExistingequipmentagefunctionality(
			String existingequipmentagefunctionality) {
		this.existingequipmentagefunctionality = existingequipmentagefunctionality;
	}
	
}
