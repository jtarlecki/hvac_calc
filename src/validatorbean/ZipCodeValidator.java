package validatorbean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ZipCodeValidator implements Validator
{
	private boolean plus4Required;
	private boolean plus4Optional;

	/** Accepts zip codes like 85710 */
	private static final String ZIP_REGEX = "[0-9]{5}";
	
	/** Accepts zip code plus 4 extensions like "-1119" or " 1119" */
	private static final String PLUS4_REQUIRED_REGEX = "[ |-]{1}[0-9]{4}";
	
	/** Optionally accepts a plus 4 */
	private static final String PLUS4_OPTIONAL_REGEX = "([ |-]{1}[0-9]{4})?";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException
	{
		// TODO Auto-generated method stub
		/* Create the correct mask */
		 Pattern mask 	=  null; 
		 
		 initProps(component);

		 if (plus4Required){
		 	mask =  Pattern.compile(ZIP_REGEX + PLUS4_REQUIRED_REGEX);
		 } else if (plus4Optional){
			mask = Pattern.compile(ZIP_REGEX + PLUS4_OPTIONAL_REGEX);
		 } else if (plus4Required && plus4Optional){
		 	throw new IllegalStateException("Plus 4 is either optional or required");
		 }
		 else {
			mask = Pattern.compile(ZIP_REGEX);
		 }

			 /* Get the string value of the current field */
		 String zipField = (String)value; 
		 	
		 	/* Check to see if the value is a zip code */
	     Matcher matcher = mask.matcher(zipField);
	     
	     if (!matcher.matches())
	     {
	     	
	     	FacesMessage message = new FacesMessage();
	     	message.setDetail("Zip code not valid");
	     	message.setSummary("Zip code not valid");
	     	message.setSeverity(FacesMessage.SEVERITY_ERROR);
	     	throw new ValidatorException(message);
	     }
	}
	
	private void initProps(UIComponent component) 
	{
		Boolean optional = Boolean.valueOf((String) component.getAttributes().get("plus4Optional"));
		Boolean required = Boolean.valueOf((String) component.getAttributes().get("plus4Required"));
		plus4Optional = optional==null ? plus4Optional : optional.booleanValue();
		plus4Required = required==null ? plus4Optional : required.booleanValue();
	}

	/**
	 * @param plus4Optional The plus4Optional to set.
	 */
	public void setPlus4Optional(boolean plus4Optional) {
		this.plus4Optional = plus4Optional;
	}
	/**
	 * @param plus4Required The plus4Required to set.
	 */
	public void setPlus4Required(boolean plus4Required) {
		this.plus4Required = plus4Required;
	}

}
