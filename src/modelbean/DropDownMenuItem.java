package modelbean;


import org.richfaces.component.html.HtmlDropDownMenu;
import org.richfaces.component.html.HtmlMenuItem;


public class DropDownMenuItem
{
	private HtmlDropDownMenu drpmnu = new HtmlDropDownMenu();
	
	public DropDownMenuItem()
	{
		
	}

	public void addMenuIem(HtmlMenuItem item)
	{
		this.drpmnu.getChildren().add(item);
	}
	
	public HtmlDropDownMenu getDrpmnu()
	{
		return drpmnu;
	}

	public void setDrpmnu(HtmlDropDownMenu drpmnu)
	{
		this.drpmnu = drpmnu;
	}

	
}
