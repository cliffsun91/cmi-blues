package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileLoader {

	private static PropertiesFileLoader loaderInstance;
	
	private Properties prop;
	
	private PropertiesFileLoader(){
		prop = new Properties();
	}
	
	private void loadDefault() throws FileNotFoundException, IOException{
		prop.load(new FileInputStream("melodyconfig.properties"));
	}
	
	public String queryProperties(String attr){
		return prop.getProperty(attr);
	}
	
	public static PropertiesFileLoader getDefaultInstance() throws FileNotFoundException, IOException{
		if(loaderInstance == null){
			PropertiesFileLoader loader = new PropertiesFileLoader();
			loader.loadDefault();
			return loader;
		}
		return loaderInstance;
	}
}
