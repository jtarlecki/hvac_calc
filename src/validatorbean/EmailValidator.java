package validatorbean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class EmailValidator implements Validator
{
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	private Matcher matcher;
	
	public EmailValidator()
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException
	{
		// TODO Auto-generated method stub
		String tmp = arg2.toString().trim();
		tmp = tmp.replaceAll("-", "");
		tmp = tmp.replaceAll("&", "");
		//System.out.println("email pat: " + EMAIL_PATTERN);
		//System.out.println("email addr: " + tmp);
		matcher = pattern.matcher(tmp);
		if(!matcher.matches())
		{
			FacesMessage msg = new FacesMessage("E-mail validation failed.", "Invalid E-mail format");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
			
		}
	}
}
