package validatorbean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class UserNameValidator implements Validator
{
	private final String ALPHANUMERIC_PATTERN = "^([a-z]|[A-Z]|[0-9])+$";
	
	private Pattern pattern;
	private Matcher matcher;
	
	public UserNameValidator()
	{
		pattern = Pattern.compile(ALPHANUMERIC_PATTERN);
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException
	{
		String tmp = arg2.toString();
		
		if(tmp.length() > 0)
		{
			matcher = pattern.matcher(tmp);
			
			if(!matcher.matches())
			{
				FacesMessage msg = new FacesMessage("Invalid user name");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		}
	}

}
