package modelbean;

public class HVACCalcResultDetails
{
	private String modelnumber;
	private String modelcoilnumber;
	private long quantity;
	private float tonnage;
	private float flv1;
	private float iplv1;
	private String utilityname;
	private String zipcode;
	private int rebatorid = 0;
	private String rebatorname;
	private float rebateamount;
	private String comments;
	private String iscustom;
	private String ismissing;
	private String programinfo;
	private String isqualifying;
	private String isprogram;
	private long ultseq;
	private long rbtseq;
	private String altutl;
	private String altrbt;
	private String tbd;
	private String sitenum;
	private long qualifyquantity;
	private float qualifyrebateamt;
	private String projectname = "";
	private long rqestinfoid = 0;
	private long rwwutilityid = 0;
	private long equipmentid = 0;
	private float flv2 = 0;
	private float iplv2 = 0;
	private String flv1uom = "";
	private String flv2uom = "";
	private String iplv1uom = "";
	private String iplv2uom = "";
	private String equipmenttype = "";
	private String subequipmenttype = "";
	
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
	
	private int equipmenttypeid = 0;
	private int subequipmenttypeid = 0;
	
	public HVACCalcResultDetails()
	{
		
	}
	
	public HVACCalcResultDetails
	(
			String modelnumber,
			long quantity,
			float tonnage,
			float flv1,
			float iplv1,
			String utilityname,
			String zipcode,
			String rebatorname,
			float rebateamount,
			String comments,
			String iscustom,
			String ismissing,
			String programinfo,
			String isqualifying,
			String isprogram,
			long ultseq,
			long rbtseq,
			String altutl,
			String altrbt,
			String tbd,
			String sitenum
	)
	{
		this.modelnumber = modelnumber;
		this.quantity = quantity;
		this.tonnage = tonnage;
		this.flv1 = flv1;
		this.iplv1 = iplv1;
		this.utilityname = utilityname;
		this.zipcode = zipcode;
		this.rebatorname = rebatorname;
		this.rebateamount = rebateamount;
		this.comments = comments;
		this.iscustom = iscustom;
		this.ismissing = ismissing;
		this.programinfo = programinfo;
		this.isqualifying = isqualifying;
		this.isprogram = isprogram;
		this.ultseq = ultseq;
		this.rbtseq = rbtseq;
		this.altutl = altutl;
		this.altrbt = altrbt;
		this.tbd = tbd;
		this.sitenum = sitenum;
	}

	public String getModelnumber()
	{
		return modelnumber;
	}

	public void setModelnumber(String modelnumber)
	{
		this.modelnumber = modelnumber;
	}

	public long getQuantity()
	{
		return quantity;
	}

	public void setQuantity(long quantity)
	{
		this.quantity = quantity;
	}

	public float getTonnage()
	{
		return tonnage;
	}

	public void setTonnage(float tonnage)
	{
		this.tonnage = tonnage;
	}

	public float getFlv1()
	{
		return flv1;
	}

	public void setFlv1(float flv1)
	{
		this.flv1 = flv1;
	}

	public float getIplv1()
	{
		return iplv1;
	}

	public void setIplv1(float iplv1)
	{
		this.iplv1 = iplv1;
	}

	public String getUtilityname()
	{
		return utilityname;
	}

	public void setUtilityname(String utilityname)
	{
		this.utilityname = utilityname;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	public String getRebatorname()
	{
		return rebatorname;
	}

	public void setRebatorname(String rebatorname)
	{
		this.rebatorname = rebatorname;
	}

	public float getRebateamount()
	{
		return rebateamount;
	}

	public void setRebateamount(float rebateamount)
	{
		this.rebateamount = rebateamount;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public String getIscustom()
	{
		return iscustom;
	}

	public void setIscustom(String iscustom)
	{
		this.iscustom = iscustom;
	}

	public String getIsmissing()
	{
		return ismissing;
	}

	public void setIsmissing(String ismissing)
	{
		this.ismissing = ismissing;
	}

	public String getPrograminfo()
	{
		return programinfo;
	}

	public void setPrograminfo(String programinfo)
	{
		this.programinfo = programinfo;
	}

	public String getIsqualifying()
	{
		return isqualifying;
	}

	public void setIsqualifying(String isqualifying)
	{
		this.isqualifying = isqualifying;
	}

	public String getIsprogram()
	{
		return isprogram;
	}

	public void setIsprogram(String isprogram)
	{
		this.isprogram = isprogram;
	}

	public long getUltseq()
	{
		return ultseq;
	}

	public void setUltseq(long ultseq)
	{
		this.ultseq = ultseq;
	}

	public long getRbtseq()
	{
		return rbtseq;
	}

	public void setRbtseq(long rbtseq)
	{
		this.rbtseq = rbtseq;
	}

	public String getAltutl()
	{
		return altutl;
	}

	public void setAltutl(String altutl)
	{
		this.altutl = altutl;
	}

	public String getAltrbt()
	{
		return altrbt;
	}

	public void setAltrbt(String altrbt)
	{
		this.altrbt = altrbt;
	}

	public String getTbd()
	{
		return tbd;
	}

	public void setTbd(String tbd)
	{
		this.tbd = tbd;
	}

	public String getSitenum()
	{
		return sitenum;
	}

	public void setSitenum(String sitenum)
	{
		this.sitenum = sitenum;
	}

	public long getQualifyquantity()
	{
		return qualifyquantity;
	}

	public void setQualifyquantity(long qualifyquantity)
	{
		this.qualifyquantity = qualifyquantity;
	}

	public float getQualifyrebateamt()
	{
		return qualifyrebateamt;
	}

	public void setQualifyrebateamt(float qualifyrebateamt)
	{
		this.qualifyrebateamt = qualifyrebateamt;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public long getRqestinfoid() {
		return rqestinfoid;
	}

	public void setRqestinfoid(long rqestinfoid) {
		this.rqestinfoid = rqestinfoid;
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

	public float getFlv2() {
		return flv2;
	}

	public void setFlv2(float flv2) {
		this.flv2 = flv2;
	}

	public float getIplv2() {
		return iplv2;
	}

	public void setIplv2(float iplv2) {
		this.iplv2 = iplv2;
	}

	public String getFlv1uom() {
		return flv1uom;
	}

	public void setFlv1uom(String flv1uom) {
		this.flv1uom = flv1uom;
	}

	public String getFlv2uom() {
		return flv2uom;
	}

	public void setFlv2uom(String flv2uom) {
		this.flv2uom = flv2uom;
	}

	public String getIplv1uom() {
		return iplv1uom;
	}

	public void setIplv1uom(String iplv1uom) {
		this.iplv1uom = iplv1uom;
	}

	public String getIplv2uom() {
		return iplv2uom;
	}

	public void setIplv2uom(String iplv2uom) {
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

	public String getModelcoilnumber() {
		return modelcoilnumber;
	}

	public void setModelcoilnumber(String modelcoilnumber) {
		this.modelcoilnumber = modelcoilnumber;
	}

	public int getEquipmenttypeid() {
		return equipmenttypeid;
	}

	public void setEquipmenttypeid(int equipmenttypeid) {
		this.equipmenttypeid = equipmenttypeid;
	}

	public int getSubequipmenttypeid() {
		return subequipmenttypeid;
	}

	public void setSubequipmenttypeid(int subequipmenttypeid) {
		this.subequipmenttypeid = subequipmenttypeid;
	}

	public int getRebatorid() {
		return rebatorid;
	}

	public void setRebatorid(int rebatorid) {
		this.rebatorid = rebatorid;
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

	public String getDesignsizeuomval() {
		return designsizeuomval;
	}

	public void setDesignsizeuomval(String designsizeuomval) {
		this.designsizeuomval = designsizeuomval;
	}
	
}
