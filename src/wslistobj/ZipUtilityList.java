package wslistobj;

import java.util.ArrayList;
import java.util.List;

import modelbean.ZipUtilities;

import com.thoughtworks.xstream.XStream;


public class ZipUtilityList
{
	private List<ZipUtilities> ziputl = new ArrayList<ZipUtilities>();
	
	public ZipUtilityList()
	{
		
	}
	
	public void addZipUilities(ZipUtilities item)
	{
		this.ziputl.add(item);
	}
	
	public void clearZipUtlities()
	{
		this.ziputl.clear();
	}
	
	public XStream getZipUtilitiesXStream()
	{
		XStream xstream = new XStream();
		
		xstream.alias("ZipUtility", ZipUtilities.class);
		xstream.alias("ZipUtlities", ZipUtilityList.class);
		
		return xstream;
	}

	public List<ZipUtilities> getZiputl()
	{
		return ziputl;
	}

	public void setZiputl(List<ZipUtilities> ziputl)
	{
		this.ziputl = ziputl;
	}
	
	
}
