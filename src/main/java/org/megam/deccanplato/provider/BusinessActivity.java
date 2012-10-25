package org.megam.deccanplato.provider;

import java.util.Map;

public interface BusinessActivity {

	void setArguments(Map<String, String> args);

	Map<String, String> run();

	String name();

}
