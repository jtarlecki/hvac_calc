package wslistobj;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import modelbean.Employee;

public class EmployeeList {
	private List<Employee> emplist = new ArrayList<Employee>();
	
	public EmployeeList()
	{
		
	}
	
	public XStream getEmployeeListXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		
		xstream.alias("employee", Employee.class);
		xstream.alias("employeelist", EmployeeList.class);
		
		return xstream;
	}
	
	public void clearEmployeeList()
	{
		this.emplist.clear();
	}
	
	public void addEmployeeItem(Employee item)
	{
		this.emplist.add(item);
	}

	public List<Employee> getEmplist() {
		return emplist;
	}

	public void setEmplist(List<Employee> emplist) {
		this.emplist = emplist;
	}
	
	
}
