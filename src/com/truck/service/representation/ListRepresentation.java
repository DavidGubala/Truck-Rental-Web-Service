package com.truck.service.representation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "List")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ListRepresentation {
	private List<AbstractRepresentation> list = new ArrayList<AbstractRepresentation>();
	
	public List<AbstractRepresentation> getList() {
		return list;
	}
	
	public void setToList(AbstractRepresentation ab) {
		this.list.add(ab);
	}
}
