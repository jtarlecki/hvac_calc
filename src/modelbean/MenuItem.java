package modelbean;

import org.richfaces.component.html.HtmlMenuItem;

import dbobj.StoredProceduresName;

public class MenuItem
{
	private HtmlMenuItem mnuitem = new HtmlMenuItem();
	private ToolbarItem tlbaritem = new ToolbarItem();
	
	public MenuItem()
	{
		
	}
	
	public void createMenuItem()
	{
		/*
		HtmlOutputLink clink = new HtmlOutputLink();
		clink.setValue(this.tlbaritem.getMenuitemurl());
		clink.setTarget("_blank");
		System.out.println("output link initiated: " + this.tlbaritem.getMenuitemurl());
		HtmlOutputLabel label =  new HtmlOutputLabel();
		label.setValue(this.tlbaritem.getMenuitemlabel());
		System.out.println("Output label created: " + this.tlbaritem.getMenuitemlabel());
		clink.getChildren().add(label);
		System.out.println("Out put label added to the link: " + this.tlbaritem.getMenuitemlabel()
				+ ": " + this.tlbaritem.getMenuitemurl());
		 */
		this.mnuitem.setId("menu" + this.tlbaritem.getMenuitemid());
		//System.out.println("Menu id is set: " + this.tlbaritem.getMenuitemid());
		this.mnuitem.setIcon(this.tlbaritem.getMenuitemimage());
		//System.out.println("Menu image is set: " + this.tlbaritem.getMenuitemimage());
		this.mnuitem.setSubmitMode("ajax");
		this.mnuitem.setValue(this.tlbaritem.getMenuitemlabel());
		this.mnuitem.setTarget("_blank");
		//this.mnuitem.setStyleClass("toolbarclass");
		this.mnuitem.setOnclick("document.location.href='" + StoredProceduresName.rootfolder + this.tlbaritem.getMenuitemurl() + "'");
		//this.mnuitem.getChildren().add(clink);
		//System.out.println("Output link added to the menu");
	}

	public HtmlMenuItem getMnuitem()
	{
		return mnuitem;
	}

	public void setMnuitem(HtmlMenuItem mnuitem)
	{
		this.mnuitem = mnuitem;
	}

	public ToolbarItem getTlbaritem()
	{
		return tlbaritem;
	}

	public void setTlbaritem(ToolbarItem tlbaritem)
	{
		this.tlbaritem = tlbaritem;
	}
	
}
