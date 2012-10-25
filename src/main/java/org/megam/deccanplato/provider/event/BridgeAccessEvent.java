package org.megam.deccanplato.provider.event;

import java.util.Map;

public class BridgeAccessEvent extends BridgeMediationEvent<Map<String,String>> {

	public BridgeAccessEvent(Map<String,String> tempPassedObj) {
		super(tempPassedObj);
	}
	

}
