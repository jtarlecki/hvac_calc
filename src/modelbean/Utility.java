package modelbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Utility
{
	// init
	private String state;
	private Long id;
	private String utilityname;
	private String datasource;
	private boolean editable;
	
	// Constructors
	
	/**
	 * default constructor
	 */

	public Utility()
	{
		
	}
	
	public Utility(String utilityname)
	{
		this.utilityname = utilityname;
	}
	
	public Utility
	(
			Long id, 
			String utilityname,
			String datasource
	)
	{
		this(utilityname);
		this.id = id;
		this.datasource = datasource;
	}
	
	/**
	 * Overloaded constructor
	 * @param state
	 * @param id
	 * @param utilityname
	 * @param datasource
	 * @param rwwutilityname
	 */
	public Utility
	(
			String state, 
			Long id, 
			String utilityname,
			String datasource
	)
	{
		this(utilityname);
		this.state = state;
		this.id = id;
		this.datasource = datasource;
	}
	
	/**
	 * Overloaded constructor
	 * @param state
	 * @param id
	 * @param utilityname
	 * @param datasource
	 * @param rwwutilityname
	 * @param editable
	 */
	public Utility
	(
		String state, 
		Long id, 
		String utilityname,
		String datasource,
		boolean editable
	)
	{
		this(state, id, utilityname, datasource);
		this.editable = editable;
	}
	
	public XStream getUtilityXStream()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Utility", Utility.class);
		
		return xstream;
	}
	
	// Getters
	/**
	 * return state
	 * @return
	 */
	public String getState()
	{
		return state;
	}
	
	/**
	 * return utility id
	 * @return
	 */
	public Long getId()
	{
		return id;
	}
	
	/**
	 * return utility name platts/intellimap
	 * @return
	 */
	public String getUtilityname()
	{
		return utilityname;
	}
	
	/**
	 * return data source platts/intellimap
	 * @return
	 */
	public String getDatasource()
	{
		return datasource;
	}
	
	/**
	 * return is editable record
	 * @return
	 */
	public boolean isEditable()
	{
		return editable;
	}

	// Setters
	/**
	 * set state
	 * @param state
	 */
	public void setState(String state)
	{
		this.state = state;
	}
	
	/**
	 * set utility id
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id;
	}
	
	/**
	 * set utility name
	 * @param utilityname
	 */
	public void setUtilityname(String utilityname)
	{
		this.utilityname = utilityname;
	}
	
	/**
	 * set data source
	 * @param datasource
	 */
	public void setDatasource(String datasource)
	{
		this.datasource = datasource;
	}
	
	/**
	 * set record editable
	 * @param editable
	 */
	public void setEditable(boolean editable)
	{
		this.editable = editable;
	}
	
	
}
