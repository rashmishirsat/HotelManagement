package com.newlearn.Exception;



public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String message)

	{
		
		super(message);
	}

	
	ResourceNotFoundException()

	{
		
		super("Resource Not found Exception :");
	}
	
}
