package com.example.adp.model;

import java.util.Map;

public class Change {
	
	private Map<Double,Long> changeMap;
	
	private boolean isError;
	
	private String errorMsg;

	public Map<Double, Long> getChangeMap() {
		return changeMap;
	}

	public void setChangeMap(Map<Double, Long> changeMap) {
		this.changeMap = changeMap;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	
}
