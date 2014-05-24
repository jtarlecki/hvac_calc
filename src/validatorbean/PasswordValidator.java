package validatorbean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class PasswordValidator implements Validator
{
	private static final String PASSWORD_REGEX = "((?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,20})";
	
	@Override
	public void validate(FacesContext facescontext, UIComponent uicomponent, Object value)
			throws ValidatorException
	{
		// TODO Auto-generated method stub
		Pattern mask = Pattern.compile(PASSWORD_REGEX);
		
		String passvalue = (String) value;
		Matcher match = mask.matcher(passvalue);
		if(!match.matches())
		{
			FacesMessage facesmessage = new FacesMessage();
			facesmessage.setDetail("Invalid password");
			facesmessage.setSummary("Invalid password");
			facesmessage.setSeverity(FacesMessage.SEVERITY_ERROR);
	     	throw new ValidatorException(facesmessage);
		}
	}

}
