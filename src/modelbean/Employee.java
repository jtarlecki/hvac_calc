package modelbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Employee 
{
	// Properties
	
	private long id = 0;
	private boolean active;
	private String empusername;
	private int empprivileges;
	private String empinit = "na";
	private String empname;
	private String empphone;
	private String phoneext;
	private String empemail;
	private float emprate;
	private int emptype;
	private String employeetypecode;
	private String employeetypedesc;
	private boolean editable;
	private long clientid = 0;
	private String companyname;
	
	// Constructors
	
	/**
	 * Default constructor
	 */
	public Employee()
	{
		
	}
	
	/**
	 * Minimal construction. Contains required field
	 * @param id
	 * @param empusername
	 * @param empname
	 * @param isactive
	 * @param empinit
	 */
	public Employee
	(
			long id, 
			boolean active, 
			String empusername, 
			String empname, 
			String empinit
	)
	{
		this.id =  id;
		this.empusername = empusername;
		this.empname = empname;
		this.active = active;
		this.empinit = empinit;
	}
	
	
	/**
	 * Full constructor contains required and options field
	 * @param id
	 * @param isactive
	 * @param empusername
	 * @param empprivileges
	 * @param empinit
	 * @param empname
	 * @param empphone
	 * @param phonetext
	 * @param empmail
	 * @param emprate
	 * @param emptype
	 */
	public Employee(
			long id, 
			boolean active, 
			String empusername, 
			int empprivileges,
			String empinit, 
			String empname, 
			String empphone, 
			String phoneext,
			String empemail, 
			float emprate, 
			int emptype)
	{
		this(id, active, empusername, empname, empinit);
		this.empprivileges =  empprivileges;
		this.empphone = empphone;
		this.phoneext = phoneext;
		this.empemail = empemail;
		this.emprate = emprate;
		this.emptype = emptype;
	}
	
	public Employee(
			long id, 
			boolean active, 
			String empusername, 
			int empprivileges,
			String empinit, 
			String empname, 
			String empphone, 
			String phoneext,
			String empemail, 
			float emprate, 
			int emptype,
			boolean editable)
	{
		this(
				id, 
				active, 
				empusername, 
				empprivileges,
				empinit, 
				empname, 
				empphone, 
				phoneext,
				empemail, 
				emprate, 
				emptype);
		this.editable = editable;
	}

	
	// Getters
	
	/**
	 * returns employ id
	 * @return employ id
	 */
	public long getId()
	{
		return id;
	}
	

	/**
	 * returns employee user name
	 * @return employee user name
	 */
	public String getEmpusername()
	{
		return empusername;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * returns employee privileges
	 * @return employee privileges
	 */
	public int getEmpprivileges()
	{
		return empprivileges;
	}

	/**
	 * returns employee initials
	 * @return employee initials
	 */
	public String getEmpinit()
	{
		return empinit;
	}

	/**
	 * returns employee full name
	 * @return employee full name
	 */
	public String getEmpname()
	{
		return empname;
	}

	/**
	 * returns employee phone number
	 * @return employee phone number
	 */
	public String getEmpphone()
	{
		return empphone;
	}

	/**
	 * returns employee phone extension
	 * @return employee phone extension
	 */
	public String getPhoneext()
	{
		return phoneext;
	}

	/**
	 * return employees email address
	 * @return employees email address
	 */
	public String getEmpemail()
	{
		return empemail;
	}

	/**
	 * returns employee rate
	 * @return employee rate
	 */
	public float getEmprate()
	{
		return emprate;
	}

	/**
	 * return employee type
	 * @return employee type
	 */
	public int getEmptype()
	{
		return emptype;
	}

	/**
	 * returns if the Employee records is being edited
	 * @return
	 */
	public boolean isEditable()
	{
		return editable;
	}

	// Setters
	/**
	 * set employee id
	 * @param id
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	
	/**
	 * set employee user name
	 * @param empusername
	 */
	public void setEmpusername(String empusername)
	{
		this.empusername = empusername;
	}

	/**
	 * set is active
	 * @param active
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * set employee usage privileges
	 * @param empprivileges
	 */
	public void setEmpprivileges(int empprivileges)
	{
		this.empprivileges = empprivileges;
	}


	/**
	 * set employee initials
	 * @param empinit
	 */
	public void setEmpinit(String empinit)
	{
		this.empinit = empinit;
	}

	/**
	 * set employee full name
	 * @param empname
	 */
	public void setEmpname(String empname)
	{
		this.empname = empname;
	}

	/**
	 * set employee phone number
	 * @param empphone
	 */
	public void setEmpphone(String empphone)
	{
		this.empphone = empphone;
	}

	/**
	 * set employee phone extension
	 * @param phoneext
	 */
	public void setPhoneext(String phoneext)
	{
		this.phoneext = phoneext;
	}

	/**
	 * set employee email address
	 * @param empemail
	 */
	public void setEmpemail(String empemail)
	{
		this.empemail = empemail;
	}

	/**
	 * set employee rate
	 * @param emprate
	 */
	public void setEmprate(float emprate)
	{
		this.emprate = emprate;
	}

	/**
	 * set employee type
	 * @param emptype
	 */
	public void setEmptype(int emptype)
	{
		this.emptype = emptype;
	}
	
	/**
	 * set employee record editing status
	 * @param editable
	 */
	public void setEditable(boolean editable)
	{
		this.editable = editable;
	}
	
	public String getEmployeetypecode()
	{
		return employeetypecode;
	}

	public void setEmployeetypecode(String employeetypecode)
	{
		this.employeetypecode = employeetypecode;
	}

	public String getEmployeetypedesc()
	{
		return employeetypedesc;
	}

	public void setEmployeetypedesc(String employeetypedesc)
	{
		this.employeetypedesc = employeetypedesc;
	}
	
	public long getClientid() {
		return clientid;
	}

	public void setClientid(long clientid) {
		this.clientid = clientid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public XStream getEmployeeXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Employee", Employee.class);
		return xstream;
	}
	

	@Override
	public String toString() {
		return "Employee [id=" + id + ", active=" + active + ", empusername="
				+ empusername + ", empprivileges=" + empprivileges
				+ ", empinit=" + empinit + ", empname=" + empname
				+ ", empphone=" + empphone + ", phoneext=" + phoneext
				+ ", empemail=" + empemail + ", emprate=" + emprate
				+ ", emptype=" + emptype + ", editable=" + editable + "]";
	}
	
	
}
