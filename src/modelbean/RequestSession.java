package modelbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class RequestSession {
	private long id;
	private long empid;

	public RequestSession()
	{
		
	}
	
	public XStream requestSessionXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		
		xstream.alias("requestsession", RequestSession.class);
		
		return xstream;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmpid() {
		return empid;
	}

	public void setEmpid(long empid) {
		this.empid = empid;
	}
	
	
}
