package loginbean;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;


import modelbean.Employee;
import modelbean.RequestSession;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;
import org.jdom.JDOMException;

import utilitybean.ManagedBeanObject;
import wsobj.WebMethodNames;

import com.thoughtworks.xstream.XStream;

import controllerbean.ToolbarManager;

import dao.DAOException;
import dbobj.StoredProceduresName;


public class User
{
	@NotNull
	@Pattern(regex=".*[^\\s].*", message="User name contains spaces only")
	@Length(min=5,max=25)
	private String username;
	
	/*
	 * ((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})
	 * 
	 * (				# Start of group
	 * (?=.*\d)			#   must contains one digit from 0-9
	 * (?=.*[a-z])		#   must contains one lowercase characters
	 * (?=.*[A-Z])		#   must contains one uppercase characters
	 * (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
	 * .				#     match anything with previous condition checking
	 * {6,20}			#        length at least 6 characters and maximum of 20	
	 * )				# End of group
	 */
	@NotNull
	@Pattern(regex="((?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,20})", 
		message="Password must be at leas 5 characters long including at least 1 uppercase and 1 lowercase character, and 1 digit")
	@Length(min=5,max=25)
	private String password;
	
	//@AssertTrue
	private boolean loginsuccessful = false;
	
	private long empid = StoredProceduresName.defaultuserid;
	private String fullname = "";
	
	private Employee loginemp = new Employee();
	private RequestSession rqstsession = new RequestSession();
	
	//User name
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	//Password
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	//login successful
	public boolean isLoginsuccessful()
	{
		return loginsuccessful;
	}
	
	
	public void setLoginsuccessful(boolean loginsuccessful)
	{
		this.loginsuccessful = loginsuccessful;
	}
	
	//employee id
	public long getEmpid()
	{
		return empid;
	}
	public void setEmpid(long empid)
	{
		this.empid = empid;
	}
	
	
	public String getFullname()
	{
		return fullname;
	}
	public void setFullname(String fullname)
	{
		this.fullname = fullname;
	}
	// Actions
	public String login() throws JDOMException, DAOException
	{
		String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.emp_webservice;
		String home = ManagedBeanObject.getResourceBundleKeyValue("loginmsg", "homepage");
		
		try
		{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.emp_opt_getLoginEmployeeId);
			
			FacesContext ctx=null;
			
			String empidstr = (String) call.invoke(new Object[]{this.username, this.password});
			
			this.empid = new Long(empidstr);
			
			System.out.println("Logged in user id: " + this.empid);
			
			//EmployeeDAO emp = new EmployeeDAO();
			//this.empid = emp.getLoginEmployeeId(this.username, this.password);
			
		
			
			if(this.empid > 0)
			{
				
				call.clearOperation();
				call.setOperation(WebMethodNames.emp_opt_getLoginEmployeeInfo);
				String empxmlstr = (String) call.invoke(new Object[]{this.empid});
				//System.out.println("Employee: " + empxmlstr);
				if(empxmlstr.length() > 0)
				{
					Employee uemp = new Employee();
					XStream xstream = uemp.getEmployeeXStream();
					uemp = (Employee) xstream.fromXML(empxmlstr);
					this.loginemp = uemp;
					this.setFullname(uemp.getEmpname());
					
					 ctx = FacesContext.getCurrentInstance();
					
					ToolbarManager tlbarmngr = (ToolbarManager) ctx.getExternalContext().getSessionMap().get("toolbarManager");
					tlbarmngr.generateToolbar(this.empid);
					
					this.setPassword(null);
					this.setLoginsuccessful(true);
					
					call.clearOperation();
					
					this.rqstsession.setEmpid(empid);
					xstream = this.rqstsession.requestSessionXStream();
					String rqsession = xstream.toXML(this.rqstsession);
					
					//Service rqservice = new Service();
					call = (Call) service.createCall();
					endpointaddress = WebMethodNames.endpointurl + WebMethodNames.requestsession_webservice;
					//System.out.println("requestsession web service: " + endpointaddress);
					call.setTargetEndpointAddress(endpointaddress);	
					
					call.setOperation(WebMethodNames.requestsession_insertRequestSession);
					//System.out.println("Operation: " + WebMethodNames.requestsession_insertRequestSession);
					rqsession = (String) call.invoke(new Object[]{rqsession});
					//System.out.println("Session string: " + rqsession);
					call.clearOperation();
					
					this.rqstsession = (RequestSession) xstream.fromXML(rqsession);
					
					//System.out.println("Request Session ID: " + this.rqstsession.getId());
					
					//HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
					//session.setMaxInactiveInterval(10);
					try
					{
						
						ctx.getExternalContext().redirect(StoredProceduresName.rootfolder +  home);
						//ctx.getExternalContext().redirect("/RealWinWinUI_DEV/calculator/hvaccalculator.jsf");
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					System.out.print("User information not found");
				}
				
			}
			
			//empid == 0
			else{
				
				try
				{
					String errorpage = ManagedBeanObject.getResourceBundleKeyValue("loginmsg", "loginerrpage");
					ctx = FacesContext.getCurrentInstance();
					ctx.getExternalContext().redirect(StoredProceduresName.rootfolder + errorpage);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
			
			
		} catch (ServiceException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "login";
				
	}
	
	public String logout()
	{
		
		String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.requestsession_webservice;
		
		XStream xstream = this.rqstsession.requestSessionXStream();
		String rqsession = xstream.toXML(this.rqstsession);
		
		Service service = new Service();
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.requestsession_LoggedOut);
			
			String empidstr = (String) call.invoke(new Object[]{rqsession});
			
			call.clearOperation();
			
			int succ = Integer.parseInt(empidstr);
			
			if(succ > 0)
			{
				System.out.println("Logout time update failed");
			}
			
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		String home = ManagedBeanObject.getResourceBundleKeyValue("loginmsg", "homepage");
		
		this.setPassword(null);
		this.setLoginsuccessful(false);
		this.setFullname(null);
		
		this.empid = 0;
		
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		//session.setMaxInactiveInterval(0);
		if(session != null)
		{
			session.invalidate();
		}
		
		try
		{
			ctx.getExternalContext().redirect(StoredProceduresName.rootfolder + home);
			//ctx.getExternalContext().redirect("/RealWinWinUI_DEV/login/login.jsf");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "logout";
	}
	
	public Employee getLoginemp()
	{
		return loginemp;
	}
	public void setLoginemp(Employee loginemp)
	{
		this.loginemp = loginemp;
	}
	public RequestSession getRqstsession() {
		return rqstsession;
	}
	public void setRqstsession(RequestSession rqstsession) {
		this.rqstsession = rqstsession;
	}
	
}
