package modelbean;


public class Rebator
{
	private long rebatorid;
	private String rebatorname;
	private String state;
	private long utilityid;
	private String utilityname;
	
	private boolean editable = false;
	
	public Rebator()
	{
		
	}
	
	public Rebator
	(
			long rebatorid,
			String rebatorname,
			String state,
			long utilityid,
			String utilityname,
			boolean editable
	)
	{
		this.rebatorid = rebatorid;
		this.rebatorname = rebatorname;
		this.state = state;
		this.utilityid = utilityid;
		this.utilityname = utilityname;
		this.editable = editable;
	}

	public long getRebatorid()
	{
		return rebatorid;
	}

	public void setRebatorid(long rebatorid)
	{
		this.rebatorid = rebatorid;
	}

	public String getRebatorname()
	{
		return rebatorname;
	}

	public void setRebatorname(String rebatorname)
	{
		this.rebatorname = rebatorname;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public long getUtilityid()
	{
		return utilityid;
	}

	public void setUtilityid(long utilityid)
	{
		this.utilityid = utilityid;
	}

	public String getUtilityname()
	{
		return utilityname;
	}

	public void setUtilityname(String utilityname)
	{
		this.utilityname = utilityname;
	}

	public boolean isEditable()
	{
		return editable;
	}

	public void setEditable(boolean editable)
	{
		this.editable = editable;
	}
}
