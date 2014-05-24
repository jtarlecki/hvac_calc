package wslistobj;

import java.util.ArrayList;
import java.util.List;

import modelbean.HVACCalcResult;

import com.thoughtworks.xstream.XStream;


public class HVACCalcResultList
{
	private List<HVACCalcResult> hresult = new ArrayList<HVACCalcResult>();
	
	public HVACCalcResultList()
	{
		
	}
	
	public void addHVACCalcResultItems(HVACCalcResult item)
	{
		this.hresult.add(item);		
	}
	
	public XStream getHVACCalcResultXStream()
	{
		XStream xstream = new XStream();
		
		xstream.alias("hvacresultitem", HVACCalcResult.class);
		xstream.alias("hvacresul", HVACCalcResultList.class);
		
		/*
		System.out.println("HERE: getHVACCalcResultXStream()");
		String xml = xstream.toXML("hvacresultitem");
		System.out.println(xml);
		*/
		
		return xstream;
	}

	public List<HVACCalcResult> getHresult()
	{
		return hresult;
	}

	public void setHresult(List<HVACCalcResult> hresult)
	{
		this.hresult = hresult;
	}
}
