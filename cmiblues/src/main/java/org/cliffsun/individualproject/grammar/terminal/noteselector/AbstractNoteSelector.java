package org.cliffsun.individualproject.grammar.terminal.noteselector;

import java.io.FileNotFoundException;
import java.io.IOException;


public abstract class AbstractNoteSelector{

	PropertiesFileLoader properties;
	
	public AbstractNoteSelector() throws FileNotFoundException, IOException {
		properties = PropertiesFileLoader.getDefaultInstance();
	}
	
	public String getStringPropertyForAttribute(String attr){
		return properties.queryProperties(attr);
	}
	
	public int getIntegerPropertyForAttribute(String attr){
		return Integer.parseInt(getStringPropertyForAttribute(attr));
	}
	
	public double getDoublePropertyForAttribute(String attr){
		return Double.parseDouble(getStringPropertyForAttribute(attr));
	}
}
