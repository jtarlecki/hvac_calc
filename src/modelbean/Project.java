package modelbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Project
{
	private long projectid;
	private String submittedby;
	private String companyname;
	private String phonenumber;
	private String email;
	private long siteid;
	private String sitename; //or projectname
	private String siteaddress;
	private String sitecity;
	private String sitestate;
	private String sitezip;
	private String siteelectricutility;
	private long sitebuildingtypeid;
	private String sitebuildingtype;
	private float sitesqft;
	private float siteoperatinghours;
	private long siteprojecttypeid;
	private String siteprojecttype;
	private List<Equipment> equipments = new ArrayList<Equipment>();
	private Date equipmentorderdate = new Date();
	private Date targetinstal = new Date();
	private String summaryscope;
	private String additionalnotes;
	private boolean utilityknown = false;
	private String projectcontactname = "";
	private String installingcontractorname = "";
	private String installingcontractoremail = "";
	private String installingcontractorphone = "";
	private String buildingownername = "";
	private String buildingowneremail = "";
	private String buildingownerphone = "";
	
	public Project()
	{
		
	}
	
	public void addEquipment(Equipment item)
	{
		this.equipments.add(item);
	}
	
	public XStream getProjectXStream()
	{
		XStream xstream =  new XStream(new DomDriver());
		xstream.alias("Equipments", Equipment.class);
		xstream.alias("Project", Project.class);
		
		return xstream;
	}
	
	public long getProjectid()
	{
		return projectid;
	}



	public void setProjectid(long projectid)
	{
		this.projectid = projectid;
	}



	public String getSubmittedby()
	{
		return submittedby;
	}
	public void setSubmittedby(String submittedby)
	{
		this.submittedby = submittedby;
	}
	public String getCompanyname()
	{
		return companyname;
	}
	public void setCompanyname(String companyname)
	{
		this.companyname = companyname;
	}
	public String getPhonenumber()
	{
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber)
	{
		this.phonenumber = phonenumber;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public long getSiteid()
	{
		return siteid;
	}
	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}
	public String getSitename()
	{
		return sitename;
	}
	public void setSitename(String sitename)
	{
		this.sitename = sitename;
	}
	public String getSiteaddress()
	{
		return siteaddress;
	}
	public void setSiteaddress(String siteaddress)
	{
		this.siteaddress = siteaddress;
	}
	public String getSitecity()
	{
		return sitecity;
	}
	public void setSitecity(String sitecity)
	{
		this.sitecity = sitecity;
	}
	public String getSitestate()
	{
		return sitestate;
	}
	public void setSitestate(String sitestate)
	{
		this.sitestate = sitestate;
	}
	public String getSitezip()
	{
		return sitezip;
	}
	public void setSitezip(String sitezip)
	{
		this.sitezip = sitezip;
	}
	public String getSiteelectricutility()
	{
		return siteelectricutility;
	}
	public void setSiteelectricutility(String siteelectricutility)
	{
		this.siteelectricutility = siteelectricutility;
	}
	public long getSitebuildingtypeid()
	{
		return sitebuildingtypeid;
	}
	public void setSitebuildingtypeid(long sitebuildingtypeid)
	{
		this.sitebuildingtypeid = sitebuildingtypeid;
	}
	public String getSitebuildingtype()
	{
		return sitebuildingtype;
	}
	public void setSitebuildingtype(String sitebuildingtype)
	{
		this.sitebuildingtype = sitebuildingtype;
	}
	public float getSitesqft()
	{
		return sitesqft;
	}
	public void setSitesqft(float sitesqft)
	{
		this.sitesqft = sitesqft;
	}
	public float getSiteoperatinghours()
	{
		return siteoperatinghours;
	}
	public void setSiteoperatinghours(float siteoperatinghours)
	{
		this.siteoperatinghours = siteoperatinghours;
	}
	public long getSiteprojecttypeid()
	{
		return siteprojecttypeid;
	}
	public void setSiteprojecttypeid(long siteprojecttypeid)
	{
		this.siteprojecttypeid = siteprojecttypeid;
	}

	public List<Equipment> getEquipments()
	{
		return equipments;
	}
	public void setEquipments(List<Equipment> equipments)
	{
		this.equipments = equipments;
	}
	public Date getTargetinstal()
	{
		return targetinstal;
	}
	public void setTargetinstal(Date targetinstal)
	{
		this.targetinstal = targetinstal;
	}
	public String getSummaryscope()
	{
		return summaryscope;
	}
	public void setSummaryscope(String summaryscope)
	{
		this.summaryscope = summaryscope;
	}
	public String getAdditionalnotes()
	{
		return additionalnotes;
	}
	public void setAdditionalnotes(String additionalnotes)
	{
		this.additionalnotes = additionalnotes;
	}

	public String getSiteprojecttype()
	{
		return siteprojecttype;
	}

	public void setSiteprojecttype(String siteprojecttype)
	{
		this.siteprojecttype = siteprojecttype;
	}

	public boolean isUtilityknown() {
		return utilityknown;
	}

	public void setUtilityknown(boolean utilityknown) {
		this.utilityknown = utilityknown;
	}

	public String getProjectcontactname() {
		return projectcontactname;
	}

	public void setProjectcontactname(String projectcontactname) {
		this.projectcontactname = projectcontactname;
	}

	public String getInstallingcontractorname() {
		return installingcontractorname;
	}

	public void setInstallingcontractorname(String installingcontractorname) {
		this.installingcontractorname = installingcontractorname;
	}

	public String getInstallingcontractoremail() {
		return installingcontractoremail;
	}

	public void setInstallingcontractoremail(String installingcontractoremail) {
		this.installingcontractoremail = installingcontractoremail;
	}

	public String getInstallingcontractorphone() {
		return installingcontractorphone;
	}

	public void setInstallingcontractorphone(String installingcontractorphone) {
		this.installingcontractorphone = installingcontractorphone;
	}

	public String getBuildingownername() {
		return buildingownername;
	}

	public void setBuildingownername(String buildingownername) {
		this.buildingownername = buildingownername;
	}

	public String getBuildingowneremail() {
		return buildingowneremail;
	}

	public void setBuildingowneremail(String buildingowneremail) {
		this.buildingowneremail = buildingowneremail;
	}

	public String getBuildingownerphone() {
		return buildingownerphone;
	}

	public void setBuildingownerphone(String buildingownerphone) {
		this.buildingownerphone = buildingownerphone;
	}

	public Date getEquipmentorderdate() {
		return equipmentorderdate;
	}

	public void setEquipmentorderdate(Date equipmentorderdate) {
		this.equipmentorderdate = equipmentorderdate;
	}
	
	
}
