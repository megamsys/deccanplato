package org.megam.deccanplato.provider.core;

public class OutputOperation extends AbstractCloudOperation {

	private OutputInfo info;

	public OutputOperation(OutputInfo tempInfo, CloudMediator tempParent) {
		super(tempParent);
		this.info = tempInfo;
	}

	@Override
	public boolean isFitToRun() {
		return false;
	}

	@Override
	public void preOperation() {

	}

	@Override
	public <T> CloudOperationOutput<T> handle() {
		if (!info.isDefault()) {
		}
		return null;
	}

	@Override
	public boolean canProceed() {
		return false;
	}

	@Override
	public void postOperation() {

	}

}
