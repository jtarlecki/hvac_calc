package utilitybean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import modelbean.ZipUtilities;


public class ZUListsuttleConverter implements javax.faces.convert.Converter
{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		// TODO Auto-generated method stub
		long utilityid;
		String plattsutilityname;
		String state;
		long energytypeid;
		String energy;
		String datasource;
		long rwwid;
		String rwwname;
		
		String[] temp = value.split("|");
		
		utilityid = (new Long(temp[0])).longValue();
		plattsutilityname = temp[1];
		state = temp[2];
		energytypeid = (new Long(temp[3]).longValue());
		energy = temp[4];
		datasource = temp[5];
		rwwid = (new Long(temp[6]).longValue());
		rwwname = temp[7];
		
		return new ZipUtilities
				(
						-1,
						"",
						utilityid,
						0.0,
						energytypeid,
						rwwid,
						state,
						plattsutilityname,
						datasource,
						energy,
						"",
						rwwname
				);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
	{
		// TODO Auto-generated method stub
		ZipUtilities zu = (ZipUtilities) value;
		return "" + zu.getUtilityid() + "|" 
				+ zu.getPlattsutilityname() + "|" 
				+ zu.getState() + "|" 
				+ zu.getEnergytypeid() + "|"
				+ zu.getEnergy() + "|"
				+ zu.getDatasorcedesc() + "|" 
				+ zu.getRwwutilityid() + "|" 
				+ zu.getRwwutilityname();
	}

}
