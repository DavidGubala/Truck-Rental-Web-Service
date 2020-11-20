package com.truck.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Link")
public class Link {
	private String action;
	private String url;
	private String mediaType;

	public Link() {}
	
	public Link(String action, String url, String mediaType) {
		this.action = action;
		this.url = url;
		this.mediaType = mediaType;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
}
