package org.megam.deccanplato.provider.core;

public interface CloudOperation {

	/** This is a conditional exectution interface which decides the fitness of an operation to run 
	 *
	 * @return {@link Boolean}
	 */
	public boolean isFitToRun();	
	
	
	public void preOperation();
	
	
	public void postOperation();


	public boolean canProceed();


	public <T extends Object> CloudOperationOutput<T> handle();
	

	
}
