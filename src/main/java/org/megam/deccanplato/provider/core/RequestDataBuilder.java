package org.megam.deccanplato.provider.core;

public class RequestDataBuilder {

	private RequestData reqData;

	public RequestDataBuilder(String jsonString) {
		parse(jsonString);
	}

	public RequestData data() {
		return reqData;
	}

	private void parse(String jsonString) {
		// parse the json string passed.
	}

}
