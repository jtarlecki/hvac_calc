package validatorbean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class AlphaNumericValidator implements Validator
{
	private final String ALPHANUMERIC_PATTERN = "^([a-z]|[A-Z]|[0-9])+$";
	
	private Pattern pattern;
	private Matcher matcher;
	
	public AlphaNumericValidator()
	{
		pattern = Pattern.compile(ALPHANUMERIC_PATTERN);
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException
	{
		String tmp = arg2.toString();
		tmp = tmp.replaceAll(" ", "");
		tmp = tmp.replaceAll(",", "");
		tmp = tmp.replaceAll("'", "");
		tmp = tmp.replaceAll("\\(", "");
		tmp = tmp.replaceAll("\\)", "");
		tmp = tmp.replaceAll("-", "");
		tmp = tmp.replaceAll("\\.", "");
		tmp = tmp.replaceAll("\\&", "");
		//System.out.println("tmp: " + tmp);
		//System.out.println("pattern: " + pattern.pattern());
		//System.out.println("Validator value: " + arg2.toString());
		if(tmp.length() > 0)
		{
			matcher = pattern.matcher(tmp);
			//System.out.print("is matched" + matcher.matches());
			if(!matcher.matches())
			{
				FacesMessage msg = new FacesMessage("Character Validation Failed", "Invalid Character Value.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		}
	}
	
}
