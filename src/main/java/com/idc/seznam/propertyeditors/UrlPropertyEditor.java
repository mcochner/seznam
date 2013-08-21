package com.idc.seznam.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.net.URL;

public class UrlPropertyEditor extends PropertyEditorSupport {
	
	// converts a property into a string value - 
//	public String getAsText() {
//		URL url = (URL) getValue();
//		return product.getClass().getName() + "," + product.getName() + ","
//				+ product.getPrice();
//	}
// converts a string into a property
	
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			URL url = new URL(text);
			setValue(url);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
