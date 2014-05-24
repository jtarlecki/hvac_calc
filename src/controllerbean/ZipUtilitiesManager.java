package controllerbean;

import java.rmi.RemoteException; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;


import modelbean.ZipUtilities;


import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;
import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.component.html.HtmlExtendedDataTable;
import org.richfaces.component.html.HtmlTabPanel;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;


import com.thoughtworks.xstream.XStream;

import wslistobj.UtilityList;
import wsobj.WebMethodNames;

public class ZipUtilitiesManager
{
	private String sortMode="single";
	private String selectionMode="multi";
	private Object tablestate;
	
	private Selection selection = new SimpleSelection();
	private Selection sourceselection = new SimpleSelection();
	private Selection targetselection = new SimpleSelection();
	private Selection utlbyrwwidselection = new SimpleSelection();
	private Selection alldisplayselection = new SimpleSelection();
	private Selection ziputilitiesbyutilityselection = new SimpleSelection();
	
	private ExtendedTableDataModel<ZipUtilities> datamodel;
	

	private ExtendedTableDataModel<ZipUtilities> sourcedatamodel;
	private ExtendedTableDataModel<ZipUtilities> targetdatamodel;
	private ExtendedTableDataModel<ZipUtilities> utlmodelbyrwwid;
	private ExtendedTableDataModel<ZipUtilities> alldisplaymodel;
	private ExtendedTableDataModel<ZipUtilities> ziputilitiesbyutilitymodel;
	
	private List<ZipUtilities> ziputilities = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> ziputilitiesrww = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> selectedziputilities = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> selectedutilitystates = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> listsuttlesource = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> listsuttletarget = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> listsuttletargetupd = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> selectedlistsuttlesource = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> selectedlistsuttletarget = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> utilitiesbyrwwid = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> selectedutlsbyrwwid = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> alldisplayutilities = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> selectedalldisplayutilities = new ArrayList<ZipUtilities>();
	
	private List<ZipUtilities> ziputilitiesbyutility = new ArrayList<ZipUtilities>();
	private List<ZipUtilities> selectedziputilitybyutility = new ArrayList<ZipUtilities>();
	
	//added list
	private List<ZipUtilities> employeestateutility = new ArrayList<ZipUtilities>();
	
	private long selectedrwwutilityid = -1;
	private boolean showonlyziputilworww = false;
	private long navigatedisplayid = -1;
	private String navigateddisplayname = "";
	
	
	private HtmlExtendedDataTable rwwexttable = new HtmlExtendedDataTable();
	private HtmlDataTable ziputldatatable = new HtmlDataTable();
	private HtmlDataTable withoutrwwdatatable = new HtmlDataTable();
	private HtmlDataTable withrwwdatatable = new HtmlDataTable();
	
	//added table
	private HtmlDataTable employeestateutiltable = new HtmlDataTable();
	private HtmlTabPanel utilitiestabpanel;
	
	
	private String selectedutilitiestab = "matchandupdate";
	private String selectedutilityiesstate;
	private String selectedutilitiesid;
	private String selectedutilitiesenergy;
	private String selectedutilitieszipcodes;
	private long numofselectedsourcerows = 0;
	private long numofselectedtargetrows = 0;
	
	private List<ZipUtilities> hvaczulist = new ArrayList<ZipUtilities>();
	private HtmlDataTable hvaczulistdatatable = new HtmlDataTable();
	
	private List<ZipUtilities> hvaccustomzulist = new ArrayList<ZipUtilities>();
	private HtmlDataTable hvaccustomzulistdatatable = new HtmlDataTable();

	private Logger logger = Logger.getLogger(this.getClass());

	public ZipUtilitiesManager()
	{
		logger.debug("ZipUtility manager initiated");
	}
	
	public void parseHVACZipUtility(String selzips, String seperator, String fieldseperator, String zulistname)
	{
		String[] parserecord = {};
		String[] parsefields = {};
		String[] parsezipcode = {};
		String sitenum = "";
		String zipcode = "";
		
		//System.out.println("hvac parse option: " + seperator);
		
		if(seperator.endsWith("new line"))
		{
			//System.out.println("hvac parse option by new line: " + seperator);
			parserecord = selzips.split("\\r\\n|\r\n|\n");
		}
		else if(seperator.equals("tab"))
		{
			parserecord = selzips.split("\\t");
		}
		else
		{
			parserecord = selzips.split(seperator);
		}
		
		this.hvaczulist.clear();
		
		if(parserecord.length > 0)
		{
			
			try
			{
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(WebMethodNames.endpointurl
						+ WebMethodNames.utility_webservice);
				call.setOperation(WebMethodNames.utility_loadUtilityByZipCode);
				UtilityList utllist = new UtilityList();
				XStream xstream = utllist.getUtilityXStream();
				
				for(String st : parserecord)
				{
					
					String tmpstr = "";
					if(seperator.equals("new line"))
					{
						tmpstr = st.replaceAll("\\r|\\n|\r|\n", "");
					}
					else if(seperator.equals("tab"))
					{
						tmpstr = st.replaceAll("\\t|\\n|\n", "");
					}
					else
					{
						tmpstr = st.replaceAll(seperator, "");
					}
					
					if(tmpstr.length() > 0)
					{
						if(fieldseperator.equals("tab"))
						{
							parsefields = tmpstr.split("\\t");
						}
						else
						{
							parsefields = tmpstr.split(fieldseperator);
						}
						//	System.out.println("hvac tmpstr: " + tmpstr + "|" + tmpstr.length());
					
				
						if(parsefields.length > 1)
						{
							sitenum = parsefields[0];
							if(fieldseperator.equals("tab"))
							{
								sitenum = sitenum.replaceAll("\\t", "");
							}
							else
							{
								sitenum = sitenum.replaceAll(fieldseperator, "");
							}
							zipcode = parsefields[1];
							if(fieldseperator.equals("tab"))
							{
								zipcode = zipcode.replaceAll("\\t", "");
							}
							else
							{
								zipcode = zipcode.replaceAll(fieldseperator, "");
							}
						}
						else
						{
							zipcode = parsefields[0];
							zipcode = zipcode.replaceAll("\\t", "");
						}
					
						parsezipcode = zipcode.split("-"); 
						if(parsezipcode.length > 1)
						{
							zipcode = parsezipcode[0];
						}
					
						zipcode = zipcode.trim();
						
						if(zipcode.length() < 5)
						{
							zipcode = "00000" + zipcode;
							zipcode = zipcode.substring(zipcode.length() - 5);
						}
					
					//if(zipcode.length() > 4)
					//{
						ZipUtilities zu = new ZipUtilities();
						zu.setSitenum(sitenum);
						zu.setZipcode(zipcode);
						zu.setUtilityid(0);
						zu.setPlattsutilityname("");
						
						String utlxmlstr = (String) call.invoke(new Object[]{zipcode});
						
						call.clearOperation();
						
						
						utllist = (UtilityList) xstream.fromXML(utlxmlstr);
						
						zu.setUtilitybyzip(utllist.getUtilities());
						
						
						if(zu.getUtilitybyzip().size() > 0)
						{
							zu.setPlattsutilityname(zu.getUtilitybyzip().get(0).getUtilityname());
						
							zu.generateUtilityCombo();
						
						}
						if(zulistname.equals("customhvac"))
						{
							this.hvaccustomzulist.add(zu);
						}
						else
						{
							this.hvaczulist.add(zu);
						}
					//}
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
		
			
		}
	}
	
	public void setSelctedZipUtilities()
	{
		
		this.selectedziputilities.clear();
		Iterator<Object> it = getSelection().getKeys();
		while(it.hasNext())
		{
			Object key = it.next();
			ZipUtilities zu = this.datamodel.getObjectByKey(key);
			this.selectedziputilities.add(zu);
		}
	}

	public ExtendedTableDataModel<ZipUtilities> getZipUtilitiesByUtilityDataModel()
	{	
		this.ziputilitiesbyutilitymodel = new ExtendedTableDataModel<ZipUtilities>
		(
				new DataProvider<ZipUtilities>()
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public ZipUtilities getItemByKey(Object key)
					{
						// TODO Auto-generated method stub
						for(ZipUtilities zu : ziputilitiesbyutility)
						{
							if(key.equals(getKey(zu)))
							{
								return zu;
							}
						}
						return null;
					}

					@Override
					public List<ZipUtilities> getItemsByRange(int firstrow, int lastrow)
					{
						// TODO Auto-generated method stub
						return ziputilitiesbyutility.subList(firstrow, lastrow);
					}

					@Override
					public Object getKey(ZipUtilities item)
					{
						// TODO Auto-generated method stub
						return item.getMapinfoid();
					}

					@Override
					public int getRowCount()
					{
						// TODO Auto-generated method stub
						return ziputilitiesbyutility.size();
					}
					
				}
		);
		return this.ziputilitiesbyutilitymodel;
	}
	
	public void ziputilitiesByUtilitySelectionChanged()
	{
		this.selectedziputilitybyutility.clear();
		Iterator<Object> it = this.getZiputilitiesbyutilityselection().getKeys();
		while(it.hasNext())
		{
			Object key = it.next();
			ZipUtilities zu = this.ziputilitiesbyutilitymodel.getObjectByKey(key);
			this.selectedziputilitybyutility.add(zu);
		}
	}
	
	public void rwwSelctionChanged(boolean hasrww)
	{
		
		
		
		if(hasrww == false)
		{
			this.selectedlistsuttlesource.clear();
			Iterator<Object> it = this.getSourceselection().getKeys();
			while(it.hasNext())
			{
				Object key = it.next();
				ZipUtilities zu = this.sourcedatamodel.getObjectByKey(key);
				this.selectedlistsuttlesource.add(zu);
			}
		}
		else
		{
			
			this.selectedlistsuttletarget.clear();
			Iterator<Object> it = this.getTargetselection().getKeys();
			while(it.hasNext())
			{
				Object key = it.next();
				ZipUtilities zu = this.targetdatamodel.getObjectByKey(key);
				this.selectedlistsuttletarget.add(zu);
				//this.navigatedisplayid = zu.getRwwutilityid();
				//this.navigateddisplayname = zu.getRwwutilityname();
			}
		}
		
	}

	
	public ExtendedTableDataModel<ZipUtilities> getZipUtilitiesDataModelWithRWWName()
	{
		this.targetdatamodel = new ExtendedTableDataModel<ZipUtilities>
		(
				new DataProvider<ZipUtilities>()
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public ZipUtilities getItemByKey(Object key)
					{
						// TODO Auto-generated method stub
						for(ZipUtilities zu : listsuttletarget)
						{
							if(key.equals(getKey(zu)))
							{
								return zu;
							}
						}
						return null;
					}

					@Override
					public List<ZipUtilities> getItemsByRange(int firstrow, int lastrow)
					{
						// TODO Auto-generated method stub
						return listsuttletarget.subList(firstrow, lastrow);
					}

					@Override
					public Object getKey(ZipUtilities item)
					{
						// TODO Auto-generated method stub
						return "" + item.getUtilityid() + item.getState() 
								+ item.getEnergytypeid()
								+ item.getDatasorcedesc()
								+ item.getRwwutilityid();
					}

					@Override
					public int getRowCount()
					{
						// TODO Auto-generated method stub
						return listsuttletarget.size();
					}
					
				}
		);
				
		return this.targetdatamodel;
	}
	
	public void allDisplayNameSelectionChanged()
	{
		this.selectedalldisplayutilities.clear();
		Iterator<Object> it = this.getAlldisplayselection().getKeys();
		while(it.hasNext())
		{
			Object key = it.next();
			ZipUtilities zu = this.alldisplaymodel.getObjectByKey(key);
			this.selectedalldisplayutilities.add(zu);
		}
	}
	
	public ExtendedTableDataModel<ZipUtilities> getAllDisplayNameDataModel()
	{
		this.alldisplaymodel = new ExtendedTableDataModel<ZipUtilities>
		(
				new DataProvider<ZipUtilities>()
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public ZipUtilities getItemByKey(Object key)
					{
						// TODO Auto-generated method stub
						for(ZipUtilities zu : alldisplayutilities)
						{
							if(key.equals(getKey(zu)))
							{
								return zu;
							}
						}
						return null;
					}

					@Override
					public List<ZipUtilities> getItemsByRange(int firstrow, int lastrow)
					{
						// TODO Auto-generated method stub
						return alldisplayutilities.subList(firstrow, lastrow);
					}

					@Override
					public Object getKey(ZipUtilities item)
					{
						// TODO Auto-generated method stub
						return "" + item.getUtilityid() + item.getState() 
								+ item.getEnergytypeid()
								+ item.getDatasorcedesc()
								+ item.getRwwutilityid();
					}

					@Override
					public int getRowCount()
					{
						// TODO Auto-generated method stub
						return alldisplayutilities.size();
					}
					
				}
		);
				
		return this.alldisplaymodel;
	}

	public ExtendedTableDataModel<ZipUtilities> getZipUtilitiesDataModelWithoutRWWName()
	{
		this.sourcedatamodel = new ExtendedTableDataModel<ZipUtilities>
		(
				new DataProvider<ZipUtilities>()
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public ZipUtilities getItemByKey(Object key)
					{
						// TODO Auto-generated method stub
						for(ZipUtilities zu : listsuttlesource)
						{
							if(key.equals(getKey(zu)))
							{
								return zu;
							}
						}
						return null;
					}

					@Override
					public List<ZipUtilities> getItemsByRange(int firstrow, int lastrow)
					{
						// TODO Auto-generated method stub
						return listsuttlesource.subList(firstrow, lastrow);
					}

					@Override
					public Object getKey(ZipUtilities item)
					{
						// TODO Auto-generated method stub
						return "" + item.getUtilityid() + item.getState() 
								+ item.getEnergytypeid()
								+ item.getDatasorcedesc()
								+ item.getRwwutilityid();
					}

					@Override
					public int getRowCount()
					{
						// TODO Auto-generated method stub
						return listsuttlesource.size();
					}
					
				}
		);
		
		return this.sourcedatamodel;
	}
	
	public void ZipUtilitiesByRWWIdSelectionChanged()
	{
		this.selectedutlsbyrwwid.clear();
		Iterator<Object> it = this.getUtlbyrwwidselection().getKeys();
		while(it.hasNext())
		{
			Object key = it.next();
			ZipUtilities zu = this.utlmodelbyrwwid.getObjectByKey(key);
			this.selectedutlsbyrwwid.add(zu);
		}
	}
	
	public ExtendedTableDataModel<ZipUtilities> getZipUtilitiesByRWWIdDataModel()
	{
		this.utlmodelbyrwwid = new ExtendedTableDataModel<ZipUtilities>
		(
				new DataProvider<ZipUtilities>()
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public ZipUtilities getItemByKey(Object key)
					{
						// TODO Auto-generated method stub
						for(ZipUtilities zu : utilitiesbyrwwid)
						{
							if(key.equals(getKey(zu)))
							{
								return zu;
							}
						}
						return null;
					}

					@Override
					public List<ZipUtilities> getItemsByRange(int firstrow, int lastrow)
					{
						// TODO Auto-generated method stub
						return utilitiesbyrwwid.subList(firstrow, lastrow);
					}

					@Override
					public Object getKey(ZipUtilities item)
					{
						// TODO Auto-generated method stub
						return item.getMapinfoid();
					}

					@Override
					public int getRowCount()
					{
						// TODO Auto-generated method stub
						return utilitiesbyrwwid.size();
					}
					
				}
		);
		
		return this.utlmodelbyrwwid;
	}
	
	
	
	public void setAllItemsToIsEditable(boolean iseditable)
	{
		if(this.ziputilities != null)
		{
			Iterator<ZipUtilities> it = this.ziputilities.iterator();
			while(it.hasNext())
			{
				ZipUtilities zu = (ZipUtilities) it.next();
				zu.setEditable(iseditable);
			}
		}
	}
	
	public String clearAllUtilitiesWithDisplayNames()
	{
		this.alldisplayutilities.clear();
		this.alldisplayselection = null;
		this.alldisplayselection = new SimpleSelection();
		this.selectedalldisplayutilities.clear();
		
		return "clear";
	}
	
	public List<ZipUtilities> getZiputilities()
	{
		return ziputilities;
	}

	public void setZiputilities(List<ZipUtilities> ziputilities)
	{
		this.ziputilities = ziputilities;
	}

	public String getSortMode()
	{
		return sortMode;
	}

	public void setSortMode(String sortMode)
	{
		this.sortMode = sortMode;
	}

	public String getSelectionMode()
	{
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode)
	{
		this.selectionMode = selectionMode;
	}

	public Object getTablestate()
	{
		return tablestate;
	}

	public void setTablestate(Object tablestate)
	{
		this.tablestate = tablestate;
	}

	public Selection getSelection()
	{
		return selection;
	}

	public void setSelection(Selection selection)
	{
		this.selection = selection;
	}
	
	public ExtendedTableDataModel<ZipUtilities> getDatamodel()
	{
		return datamodel;
	}

	public void setDatamodel(ExtendedTableDataModel<ZipUtilities> datamodel)
	{
		this.datamodel = datamodel;
	}

	public List<ZipUtilities> getSelectedziputilities()
	{
		return selectedziputilities;
	}

	public void setSelectedziputilities(List<ZipUtilities> selectedziputilities)
	{
		this.selectedziputilities = selectedziputilities;
	}

	public long getSelectedrwwutilityid()
	{
		return selectedrwwutilityid;
	}

	public void setSelectedrwwutilityid(long selectedrwwutilityid)
	{
		this.selectedrwwutilityid = selectedrwwutilityid;
	}

	public List<ZipUtilities> getSelectedutilitystates()
	{
		return selectedutilitystates;
	}

	public void setSelectedutilitystates(List<ZipUtilities> selectedutilitystates)
	{
		this.selectedutilitystates = selectedutilitystates;
	}

	public boolean isShowonlyziputilworww()
	{
		return showonlyziputilworww;
	}

	public void setShowonlyziputilworww(boolean showonlyziputilworww)
	{
		this.showonlyziputilworww = showonlyziputilworww;
	}

	public HtmlDataTable getZiputldatatable()
	{
		return ziputldatatable;
	}

	public void setZiputldatatable(HtmlDataTable ziputldatatable)
	{
		this.ziputldatatable = ziputldatatable;
	}

	public HtmlTabPanel getUtilitiestabpanel()
	{
		return utilitiestabpanel;
	}

	public void setUtilitiestabpanel(HtmlTabPanel utilitiestabpanel)
	{
		this.utilitiestabpanel = utilitiestabpanel;
	}

	public String getSelectedutilitiestab()
	{
		return selectedutilitiestab;
	}

	public void setSelectedutilitiestab(String selectedutilitiestab)
	{
		this.selectedutilitiestab = selectedutilitiestab;
	}

	public String getSelectedutilityiesstate()
	{
		return selectedutilityiesstate;
	}

	public void setSelectedutilityiesstate(String selectedutilityiesstate)
	{
		this.selectedutilityiesstate = selectedutilityiesstate;
	}

	public String getSelectedutilitiesid()
	{
		return selectedutilitiesid;
	}

	public void setSelectedutilitiesid(String selectedutilitiesid)
	{
		this.selectedutilitiesid = selectedutilitiesid;
	}

	public String getSelectedutilitiesenergy()
	{
		return selectedutilitiesenergy;
	}

	public void setSelectedutilitiesenergy(String selectedutilitiesenergy)
	{
		this.selectedutilitiesenergy = selectedutilitiesenergy;
	}

	public String getSelectedutilitieszipcodes()
	{
		return selectedutilitieszipcodes;
	}

	public void setSelectedutilitieszipcodes(String selectedutilitieszipcodes)
	{
		this.selectedutilitieszipcodes = selectedutilitieszipcodes;
	}

	public List<ZipUtilities> getListsuttlesource()
	{
		return listsuttlesource;
	}

	public void setListsuttlesource(List<ZipUtilities> listsuttlesource)
	{
		this.listsuttlesource = listsuttlesource;
	}

	public List<ZipUtilities> getListsuttletarget()
	{
		return listsuttletarget;
	}

	public void setListsuttletarget(List<ZipUtilities> listsuttletarget)
	{
		this.listsuttletarget = listsuttletarget;
	}

	public List<ZipUtilities> getListsuttletargetupd()
	{
		return listsuttletargetupd;
	}

	public void setListsuttletargetupd(List<ZipUtilities> listsuttletargetupd)
	{
		this.listsuttletargetupd = listsuttletargetupd;
	}

	public Selection getSourceselection()
	{
		return sourceselection;
	}

	public void setSourceselection(Selection sourceselection)
	{
		this.sourceselection = sourceselection;
	}

	public Selection getTargetselection()
	{
		return targetselection;
	}

	public void setTargetselection(Selection targetselection)
	{
		this.targetselection = targetselection;
	}

	public ExtendedTableDataModel<ZipUtilities> getSourcedatamodel()
	{
		return sourcedatamodel;
	}

	public void setSourcedatamodel(
			ExtendedTableDataModel<ZipUtilities> sourcedatamodel)
	{
		this.sourcedatamodel = sourcedatamodel;
	}

	public ExtendedTableDataModel<ZipUtilities> getTargetdatamodel()
	{
		return targetdatamodel;
	}

	public void setTargetdatamodel(
			ExtendedTableDataModel<ZipUtilities> targetdatamodel)
	{
		this.targetdatamodel = targetdatamodel;
	}

	public long getNumofselectedsourcerows()
	{
		return numofselectedsourcerows;
	}

	public void setNumofselectedsourcerows(long numofselectedsourcerows)
	{
		this.numofselectedsourcerows = numofselectedsourcerows;
	}

	public long getNumofselectedtargetrows()
	{
		return numofselectedtargetrows;
	}

	public void setNumofselectedtargetrows(long numofselectedtargetrows)
	{
		this.numofselectedtargetrows = numofselectedtargetrows;
	}

	public HtmlDataTable getWithoutrwwdatatable()
	{
		return withoutrwwdatatable;
	}

	public void setWithoutrwwdatatable(HtmlDataTable withoutrwwdatatable)
	{
		this.withoutrwwdatatable = withoutrwwdatatable;
	}

	public HtmlDataTable getWithrwwdatatable()
	{
		return withrwwdatatable;
	}

	public void setWithrwwdatatable(HtmlDataTable withrwwdatatable)
	{
		this.withrwwdatatable = withrwwdatatable;
	}

	public List<ZipUtilities> getSelectedlistsuttlesource()
	{
		return selectedlistsuttlesource;
	}

	public void setSelectedlistsuttlesource(
			List<ZipUtilities> selectedlistsuttlesource)
	{
		this.selectedlistsuttlesource = selectedlistsuttlesource;
	}

	public List<ZipUtilities> getSelectedlistsuttletarget()
	{
		return selectedlistsuttletarget;
	}

	public void setSelectedlistsuttletarget(
			List<ZipUtilities> selectedlistsuttletarget)
	{
		this.selectedlistsuttletarget = selectedlistsuttletarget;
	}
	
	public long getSelectedZipUtilitiesWithRWWName()
	{
		return this.selectedlistsuttletarget.size();
	}
	
	public long getSelectedZipUtilitiesWithoutRWWName()
	{
		return this.selectedlistsuttlesource.size();
	}

	public List<ZipUtilities> getZiputilitiesrww()
	{
		return ziputilitiesrww;
	}

	public void setZiputilitiesrww(List<ZipUtilities> ziputilitiesrww)
	{
		this.ziputilitiesrww = ziputilitiesrww;
	}

	public HtmlExtendedDataTable getRwwexttable()
	{
		return rwwexttable;
	}

	public void setRwwexttable(HtmlExtendedDataTable rwwexttable)
	{
		this.rwwexttable = rwwexttable;
	}

	public Selection getUtlbyrwwidselection()
	{
		return utlbyrwwidselection;
	}

	public void setUtlbyrwwidselection(Selection utlbyrwwidselection)
	{
		this.utlbyrwwidselection = utlbyrwwidselection;
	}

	public ExtendedTableDataModel<ZipUtilities> getUtlmodelbyrwwid()
	{
		return utlmodelbyrwwid;
	}

	public void setUtlmodelbyrwwid(
			ExtendedTableDataModel<ZipUtilities> utlmodelbyrwwid)
	{
		this.utlmodelbyrwwid = utlmodelbyrwwid;
	}

	public List<ZipUtilities> getUtilitiesbyrwwid()
	{
		return utilitiesbyrwwid;
	}

	public void setUtilitiesbyrwwid(List<ZipUtilities> utilitiesbyrwwid)
	{
		this.utilitiesbyrwwid = utilitiesbyrwwid;
	}

	public List<ZipUtilities> getSelectedutlsbyrwwid()
	{
		return selectedutlsbyrwwid;
	}

	public void setSelectedutlsbyrwwid(List<ZipUtilities> selectedutlsbyrwwid)
	{
		this.selectedutlsbyrwwid = selectedutlsbyrwwid;
	}

	public long getNavigatedisplayid()
	{
		return navigatedisplayid;
	}

	public void setNavigatedisplayid(long navigatedisplayid)
	{
		this.navigatedisplayid = navigatedisplayid;
	}

	public String getNavigateddisplayname()
	{
		return navigateddisplayname;
	}

	public void setNavigateddisplayname(String navigateddisplayname)
	{
		this.navigateddisplayname = navigateddisplayname;
	}

	public Selection getAlldisplayselection()
	{
		return alldisplayselection;
	}

	public void setAlldisplayselection(Selection alldisplayselection)
	{
		this.alldisplayselection = alldisplayselection;
	}

	public ExtendedTableDataModel<ZipUtilities> getAlldisplaymodel()
	{
		return alldisplaymodel;
	}

	public void setAlldisplaymodel(
			ExtendedTableDataModel<ZipUtilities> alldisplaymodel)
	{
		this.alldisplaymodel = alldisplaymodel;
	}

	public List<ZipUtilities> getAlldisplayutilities()
	{
		return alldisplayutilities;
	}

	public void setAlldisplayutilities(List<ZipUtilities> alldisplayutilities)
	{
		this.alldisplayutilities = alldisplayutilities;
	}

	public List<ZipUtilities> getSelectedalldisplayutilities()
	{
		return selectedalldisplayutilities;
	}

	public void setSelectedalldisplayutilities(
			List<ZipUtilities> selectedalldisplayutilities)
	{
		this.selectedalldisplayutilities = selectedalldisplayutilities;
	}

	public Selection getZiputilitiesbyutilityselection()
	{
		return ziputilitiesbyutilityselection;
	}

	public void setZiputilitiesbyutilityselection(
			Selection ziputilitiesbyutilityselection)
	{
		this.ziputilitiesbyutilityselection = ziputilitiesbyutilityselection;
	}

	public ExtendedTableDataModel<ZipUtilities> getZiputilitiesbyutilitymodel()
	{
		return ziputilitiesbyutilitymodel;
	}

	public void setZiputilitiesbyutilitymodel(
			ExtendedTableDataModel<ZipUtilities> ziputilitiesbyutilitymodel)
	{
		this.ziputilitiesbyutilitymodel = ziputilitiesbyutilitymodel;
	}

	public List<ZipUtilities> getZiputilitiesbyutility()
	{
		return ziputilitiesbyutility;
	}

	public void setZiputilitiesbyutility(List<ZipUtilities> ziputilitiesbyutility)
	{
		this.ziputilitiesbyutility = ziputilitiesbyutility;
	}

	public List<ZipUtilities> getSelectedziputilitybyutility()
	{
		return selectedziputilitybyutility;
	}

	public void setSelectedziputilitybyutility(
			List<ZipUtilities> selectedziputilitybyutility)
	{
		this.selectedziputilitybyutility = selectedziputilitybyutility;
	}

	public List<ZipUtilities> getHvaczulist()
	{
		return hvaczulist;
	}

	public void setHvaczulist(List<ZipUtilities> hvaczulist)
	{
		this.hvaczulist = hvaczulist;
	}

	public HtmlDataTable getHvaczulistdatatable()
	{
		return hvaczulistdatatable;
	}

	public void setHvaczulistdatatable(HtmlDataTable hvaczulistdatatable)
	{
		this.hvaczulistdatatable = hvaczulistdatatable;
	}
	
	public List<ZipUtilities> getEmployeestateutility() {
		return employeestateutility;
	}


	public void setEmployeestateutility(List<ZipUtilities> employeestateutility) {
		this.employeestateutility = employeestateutility;
	}


	public HtmlDataTable getEmployeestateutiltable() {
		return employeestateutiltable;
	}


	public void setEmployeestateutiltable(HtmlDataTable employeestateutiltable) {
		this.employeestateutiltable = employeestateutiltable;
	}

	public List<ZipUtilities> getHvaccustomzulist() {
		return hvaccustomzulist;
	}
	
	public void setHvaccustomzulist(List<ZipUtilities> hvaccustomzulist) {
		this.hvaccustomzulist = hvaccustomzulist;
	}

	public HtmlDataTable getHvaccustomzulistdatatable() {
		return hvaccustomzulistdatatable;
	}

	public void setHvaccustomzulistdatatable(HtmlDataTable hvaccustomzulistdatatable) {
		this.hvaccustomzulistdatatable = hvaccustomzulistdatatable;
	}
	
}
