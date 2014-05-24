package controllerbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import modelbean.Rebator;

import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;


public class RebatorManager
{
	private List<Rebator> rebatorsbyzip = new ArrayList<Rebator>();
	private String sortMode="single";
	private String selectionMode="multi";
	private Selection selectionrbtbyzip = new SimpleSelection();
	private ExtendedTableDataModel<Rebator> datamodelrbtbyzip;
	
	private List<Rebator> selectedrebatorbyzip = new ArrayList<Rebator>();
	
	public RebatorManager()
	{
		
	}
	

	
	public ExtendedTableDataModel<Rebator> getRebatorByZip()
	{
		this.datamodelrbtbyzip = new ExtendedTableDataModel<Rebator>
		(
				new DataProvider<Rebator>()
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Rebator getItemByKey(Object key)
					{
						// TODO Auto-generated method stub
						for(Rebator rb : rebatorsbyzip)
						{
							if(key.equals(getKey(rb)))
							{
								return rb;
							}
						}
						return null;
					}

					@Override
					public List<Rebator> getItemsByRange(int firstrow, int lastrow)
					{
						// TODO Auto-generated method stub
						return rebatorsbyzip.subList(firstrow, lastrow);
					}

					@Override
					public Object getKey(Rebator item)
					{
						// TODO Auto-generated method stub
						return "" + item.getRebatorid()
								+ item.getState()
								+ item.getUtilityid();
					}

					@Override
					public int getRowCount()
					{
						// TODO Auto-generated method stub
						return rebatorsbyzip.size();
					}
						
				}
				
		);
		return this.datamodelrbtbyzip;
	}
	
	public void rebatorByZipSelectionChanged()
	{
		this.selectedrebatorbyzip.clear();
		Iterator<Object> it = this.getSelectionrbtbyzip().getKeys();
		while(it.hasNext())
		{
			Object key = it.next();
			this.selectedrebatorbyzip.add(this.datamodelrbtbyzip.getObjectByKey(key));
		}
	}

	public List<Rebator> getRebatorsbyzip()
	{
		return rebatorsbyzip;
	}

	public void setRebatorsbyzip(List<Rebator> rebatorsbyzip)
	{
		this.rebatorsbyzip = rebatorsbyzip;
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

	public Selection getSelectionrbtbyzip()
	{
		return selectionrbtbyzip;
	}

	public void setSelectionrbtbyzip(Selection selectionrbtbyzip)
	{
		this.selectionrbtbyzip = selectionrbtbyzip;
	}

	public ExtendedTableDataModel<Rebator> getDatamodelrbtbyzip()
	{
		return datamodelrbtbyzip;
	}

	public void setDatamodelrbtbyzip(
			ExtendedTableDataModel<Rebator> datamodelrbtbyzip)
	{
		this.datamodelrbtbyzip = datamodelrbtbyzip;
	}

	public List<Rebator> getSelectedrebatorbyzip()
	{
		return selectedrebatorbyzip;
	}

	public void setSelectedrebatorbyzip(List<Rebator> selectedrebatorbyzip)
	{
		this.selectedrebatorbyzip = selectedrebatorbyzip;
	}
	
	
}
