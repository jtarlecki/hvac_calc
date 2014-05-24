package controllerbean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.xml.rpc.ServiceException;


import loginbean.User;
import modelbean.Employee;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.thoughtworks.xstream.XStream;

import utilitybean.ManagedBeanObject;
import wslistobj.EmployeeList;
import wsobj.WebMethodNames;



public class EmployeeManager
{
	private List<Employee> employees = new ArrayList<Employee>();
	//private EmployeeDAO employeedao = new EmployeeDAO();
	
	private String selectedInitials;
	
	private List<Employee> umemployee = new ArrayList<Employee>();
	private List<Employee> amemployee = new ArrayList<Employee>();
	private List<Employee> vendoremployee = new ArrayList<Employee>();
	private List<SelectItem> vendorempcombo = new ArrayList<SelectItem>();
	
	
	
	{
		loadEmployeesByType("");
		loadEmployeesByType("AM");
		loadEmployeesByType("UM");	
		loadVendorEmployees();
	}
	
	public EmployeeManager()
	{
		
	}
	
	
	private void loadEmployeesByType(String emptype)
	{
		//System.out.println("um list: " + this.employees.size());
			//System.out.println("um list: " + this.employees.size());
			if(emptype.length() > 0)
			{
				if(emptype.equals("AM"))
				{
					this.amemployee.clear();
					this.amemployee = this.getEmployeesByEmployeeTypes(emptype);
				}
				else if(emptype.equals("UM"))
				{
					this.umemployee.clear();
					this.umemployee = this.getEmployeesByEmployeeTypes(emptype);
				}
				else
				{
					this.vendoremployee.clear();
					this.vendoremployee = this.getEmployeesByEmployeeTypes(emptype);
					this.generateVendorEmployeeCombo();
				}
			}
			else
			{
				this.employees.clear();
				this.employees = this.getAllEmployees();
			}
			//System.out.println("um list: " + this.employees.size());
		
	}

	private void loadVendorEmployees()
	{
		User user = (User) ManagedBeanObject.getManagedBean("userBean");
		this.loadEmployeesByType(user.getLoginemp().getEmployeetypecode());
	}
	
	public List<Employee> getEmployeesByEmployeeTypes(String emptype)
	{
		List<Employee> empbytype = new ArrayList<Employee>();
		
		String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.emp_webservice;
		Service service = new Service();
		
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.emp_getEmployeesByEmployeeType);
			
			String empliststr = (String) call.invoke(new Object[]{emptype});
			
			if(empliststr.length() > 0)
			{
				EmployeeList emplist = new EmployeeList();
				XStream xstream = emplist.getEmployeeListXStream();
				emplist = (EmployeeList) xstream.fromXML(empliststr);
				empbytype = emplist.getEmplist();
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return empbytype;
	}
	
	public List<Employee> getAllEmployees()
	{
		List<Employee> empbytype = new ArrayList<Employee>();
		
		String endpointaddress = WebMethodNames.endpointurl + WebMethodNames.emp_webservice;
		Service service = new Service();
		
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpointaddress);
			call.setOperation(WebMethodNames.emp_getAllEmployees);
			
			String empliststr = (String) call.invoke(new Object[]{});
			
			if(empliststr.length() > 0)
			{
				EmployeeList emplist = new EmployeeList();
				XStream xstream = emplist.getEmployeeListXStream();
				emplist = (EmployeeList) xstream.fromXML(empliststr);
				empbytype = emplist.getEmplist();
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return empbytype;
	}
	
	public List<Employee> getEmployees()
	{
		return employees;
	}

	public void setEmployees(List<Employee> employees)
	{
		this.employees = employees;
	}
	
	
	public List<SelectItem> getEmpInitCombo(){
		
		List<SelectItem> tempCombo = new ArrayList<SelectItem>(); 
		Iterator<Employee> it = employees.iterator();
		while(it.hasNext()){
			Employee temp = it.next();
			
			tempCombo.add(new SelectItem(temp.getEmpinit(), ""+temp.getId()));
		}
		
		
		return tempCombo;
	}
	
	public List<SelectItem> getAMEmpInitCombo(){
		
		List<SelectItem> tempCombo = new ArrayList<SelectItem>(); 
		Iterator<Employee> it = this.amemployee.iterator();
		while(it.hasNext()){
			Employee temp = it.next();
			
			tempCombo.add(new SelectItem(temp.getEmpinit(), ""+temp.getId()));
		}
		
		
		return tempCombo;
	}
	
	public List<SelectItem> getUMEmpInitCombo(){
		
		List<SelectItem> tempCombo = new ArrayList<SelectItem>(); 
		Iterator<Employee> it = this.umemployee.iterator();
		while(it.hasNext()){
			Employee temp = it.next();
			
			tempCombo.add(new SelectItem(temp.getEmpinit(), ""+temp.getId()));
		}
		
		
		return tempCombo;
	}
	
	public void generateVendorEmployeeCombo()
	{
		
		this.vendorempcombo.clear();
		
		Iterator<Employee> it = this.vendoremployee.iterator();
		while(it.hasNext()){
			Employee temp = it.next();
			
			this.vendorempcombo.add(new SelectItem(temp.getEmpname(), ""+temp.getId()));
		}
	}
	
	//Helper
	public long getEmployeeId(String employeeinit)
	{
		long employeeid = -1;
		Iterator<Employee> it = this.employees.iterator();
		while(it.hasNext())
		{
			Employee tmpemp = (Employee) it.next();
			if(tmpemp.getEmpinit().equals(employeeinit))
			{
				employeeid = tmpemp.getId();
			}
		}
		return employeeid;
	}

	public String getSelectedInitials() {
		return selectedInitials;
	}

	public void setSelectedInitials(String selectedInitials) {
		this.selectedInitials = selectedInitials;
	}


	public List<Employee> getUmemployee() {
		return umemployee;
	}


	public void setUmemployee(List<Employee> umemployee) {
		this.umemployee = umemployee;
	}


	public List<Employee> getAmemployee() {
		return amemployee;
	}


	public void setAmemployee(List<Employee> amemployee) {
		this.amemployee = amemployee;
	}


	public List<Employee> getVendoremployee() {
		return vendoremployee;
	}


	public void setVendoremployee(List<Employee> vendoremployee) {
		this.vendoremployee = vendoremployee;
	}


	public List<SelectItem> getVendorempcombo() {
		return vendorempcombo;
	}


	public void setVendorempcombo(List<SelectItem> vendorempcombo) {
		this.vendorempcombo = vendorempcombo;
	}
	
}
