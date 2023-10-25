package com.core.backend.model;

import java.util.List;

public class CenaceRequest {

	private String system;
	private String process;
	private List<String> listNode;
	private String dateStart;
	private String dateEnd;
	private String typeNode;
	
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public List<String> getListNode() {
		return listNode;
	}
	public void setListNode(List<String> listNode) {
		this.listNode = listNode;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getTypeNode() {
		return typeNode;
	}
	public void setTypeNode(String typeNode) {
		this.typeNode = typeNode;
	}
	
}
