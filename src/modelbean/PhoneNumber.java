package modelbean;

import java.io.Serializable;

public class PhoneNumber implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String countryCode;
	private String areaCode;
	private String prefix;
	private String number;
	private String original;
	
	public PhoneNumber()
	{
		
	}
	
	public String toString(){
		System.out.println("Country code: " + this.countryCode);
		if (countryCode.equals("1")){
			return countryCode + " " + areaCode + " " + prefix + " " + number;
		}else{
			return number;
		}
	}

	public String getCountryCode()
	{
		return countryCode;
	}

	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getOriginal()
	{
		return original;
	}

	public void setOriginal(String original)
	{
		this.original = original;
	}
	

}
