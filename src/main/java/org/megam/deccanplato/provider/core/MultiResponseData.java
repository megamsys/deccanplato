package org.megam.deccanplato.provider.core;

import java.util.ArrayList;
import java.util.List;

public class MultiResponseData implements SendBackResponse {
	
	private List<SendBackResponse> respList = new ArrayList<>();

	
	public void add(SendBackResponse tempResp) {
		respList.add(tempResp);		

	}

}
