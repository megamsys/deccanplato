package org.megam.deccanplato.provider.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiResponseData implements SendBackResponse {
	
	private List<SendBackResponse> respList = new ArrayList<>();

	
	public void add(SendBackResponse tempResp) {
		respList.add(tempResp);		

	}


	@Override
	public Map map() {
		return null;
	}

}
