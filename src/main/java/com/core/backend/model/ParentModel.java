package com.core.backend.model;

import java.util.List;

public class ParentModel {
	
	private String headerName;
	private String sheetName;
	private List <ChildModel> listChildModel;
	
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public List<ChildModel> getListChildModel() {
		return listChildModel;
	}
	public void setListChildModel(List<ChildModel> listChildModel) {
		this.listChildModel = listChildModel;
	}
	
}
