package de.uni_passau.facultyinfo.server.dto;

public class Metadata {

	public static final String NAME_BUSINESSHOURS = "businesshours";
	public static final String NAME_BUSLINES = "busline";
	public static final String NAME_CONTACTPERSONS = "contactperson";
	public static final String NAME_EVENTS = "event";
	public static final String NAME_FAQS = "faq";
	public static final String NAME_MAPMARKER = "mapmarker";
	public static final String NAME_MENU = "menu";
	public static final String NAME_NEWS = "news";
	public static final String NAME_SPORTSCOURSE = "sportscourse";

	private String name;
	private boolean isThirdPartyData;
	private int lastStatuscode;
	private String sourceUrl;

	public Metadata(String name, boolean isThirdPartyData, int lastStatuscode,
			String sourceUrl) {
		super();
		this.name = name;
		this.isThirdPartyData = isThirdPartyData;
		this.lastStatuscode = lastStatuscode;
		this.sourceUrl = sourceUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isThirdPartyData() {
		return isThirdPartyData;
	}

	public void setThirdPartyData(boolean isThirdPartyData) {
		this.isThirdPartyData = isThirdPartyData;
	}

	public int getLastStatuscode() {
		return lastStatuscode;
	}

	public void setLastStatuscode(int lastStatuscode) {
		this.lastStatuscode = lastStatuscode;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

}
