package org.megam.deccanplato.provider.core;

import java.util.Set;

public class ResponseDataBuilder<T extends Object>  {
	
	private ResponseData<T> resp;
	
	public ResponseDataBuilder(Set<CloudOperationOutput<T>> tempOpsSet) {
		resp = new ResponseData<T>();
		for(CloudOperationOutput<T> op : tempOpsSet) {
			resp.put(op.name(), op.get());
		}
			
	}

	public ResponseData getResponseData() {
		return resp;
	}
	
	public String toJson() {
		return null;
	}

}
