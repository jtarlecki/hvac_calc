package dao;

/**
 * This class represents a generic DAO exception. It should wrap any exception of the underlying
 * code, such as SQLExceptions.
 * 
 * @author mhossain
 *
 */


public class DAOException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructors
	
	/**
	 * Constructs a DAOException with the given detail message
	 * @param message - the detail message of the DAOException
	 */
	public DAOException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructs a DAOException with the given root cause
	 * @param cause - the root cause of the DAOException
	 */
	public DAOException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Constructs a DAOException with the given detail message and root cause
	 * @param message - the detail message of the DAOException
	 * @param cause - the root cause of the DAOException
	 */
	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
