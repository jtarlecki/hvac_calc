package validatorbean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import modelbean.PhoneNumber;




public class PhoneValidator implements Validator
{

	@Override
	public void validate(FacesContext context, UIComponent component, Object pvalue)
			throws ValidatorException
	{
		// TODO Auto-generated method stub
		System.out.println("Phone validator 1");		
		 
		 String value = ((PhoneNumber) pvalue).getOriginal();
		 String countryCode = "^[0-9]{1,2}";
		 String areaCode = "( |-|\\(){1}[0-9]{3}( |-|\\)){1}";
		 String prefix = "( |-)?[0-9]{3}";
		 String number = "( |-)[0-9]{4}$";
		 Pattern mask 	=  Pattern.compile(countryCode+areaCode+prefix+number);
		 
		 Matcher matcher = mask.matcher(value);
		 
			System.out.println("Phone validator 2");		
		 
		 if (!matcher.find())
		 {	     	
	     	FacesMessage message = new FacesMessage();
	     	message.setDetail("Phone number not valid");
	     	message.setSummary("Phone number not valid");
	     	message.setSeverity(FacesMessage.SEVERITY_ERROR);
	     	throw new ValidatorException(message);
	     }
	     
			System.out.println("Phone validator 3");		
	}

}
