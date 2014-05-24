package modelbean;

/**
 * 
 * @author mhossain
 *
 * this class represent Web_OEMs table structure
 */

public class OEM
{
	private long oemid;
	private String oemname;
	private boolean iseditable = false;
	
	public OEM()
	{
		
	}
	
	public OEM
	(
			long oemid,
			String oemname,
			boolean iseditable
	)
	{
		this.oemid = oemid;
		this.oemname = oemname;
		this.iseditable = iseditable;
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

	public boolean isIseditable()
	{
		return iseditable;
	}

	public void setIseditable(boolean iseditable)
	{
		this.iseditable = iseditable;
	}
	
	
}
