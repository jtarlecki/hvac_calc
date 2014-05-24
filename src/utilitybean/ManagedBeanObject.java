package utilitybean;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class ManagedBeanObject
{
	public static Object getManagedBean(String beanname)
	{
		FacesContext facescontext = FacesContext.getCurrentInstance();
		Object obj = facescontext.getExternalContext().getSessionMap().get(beanname);
		return obj;
	}
	
	public static Object getRequestManagedBean(String beanname)
	{
		FacesContext facescontext = FacesContext.getCurrentInstance();
		Object obj = facescontext.getExternalContext().getRequestMap().get(beanname);
		return obj;
	}
	
	public static String getResourceBundleKeyValue(String bundlename, String key)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, bundlename);
		String message = bundle.getString(key);
		
		return message;
	}
}
