package org.hs.mainz.praktikum.finalversion;

public class DataStorage {
	private Integer id;
	private String foreName;
	private String patronym;
	private String lastName;
	private String dateGreg;
	private Integer dateGregYear;
	private Integer dateJewish;
	private String monthName;
	private String text;
	private String url;
	private String idEpidat;
	private Double xCoord;
	private Double yCoord;

	public DataStorage() {

	}

	public DataStorage(Integer id, String foreName, String patronym, String lastName, String dateGreg,
			Integer dateGregYear, Integer dateJewish, String monthName, String text, String url, String idEpidat,
			Double xCoord, Double yCoord) {
		super();
		this.id = id;
		this.foreName = foreName;
		this.patronym = patronym;
		this.lastName = lastName;
		this.dateGreg = dateGreg;
		this.dateGregYear = dateGregYear;
		this.dateJewish = dateJewish;
		this.monthName = monthName;
		this.text = text;
		this.url = url;
		this.idEpidat = idEpidat;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getForeName() {
		return foreName;
	}

	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public String getPatronym() {
		return patronym;
	}

	public void setPatronym(String patronym) {
		this.patronym = patronym;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateGreg() {
		return dateGreg;
	}

	public void setDateGreg(String dateGreg) {
		this.dateGreg = dateGreg;
	}

	public Integer getDateGregYear() {
		return dateGregYear;
	}

	public void setDateGregYear(Integer dateGregYear) {
		this.dateGregYear = dateGregYear;
	}

	public Integer getDateJewish() {
		return dateJewish;
	}

	public void setDateJewish(Integer dateJewish) {
		this.dateJewish = dateJewish;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIdEpidat() {
		return idEpidat;
	}

	public void setIdEpidat(String idEpidat) {
		this.idEpidat = idEpidat;
	}

	public Double getxCoord() {
		return xCoord;
	}

	public void setxCoord(Double xCoord) {
		this.xCoord = xCoord;
	}

	public Double getyCoord() {
		return yCoord;
	}

	public void setyCoord(Double yCoord) {
		this.yCoord = yCoord;
	}

	@Override
	public String toString() {
		return "ID" + " " + id + " " + foreName + " " + patronym + " " + lastName + " " + "[Datierung greg.:" + dateGreg
				+ "|" + "Datierung jued.:" + dateJewish + "]" + " " + "Monatsname: " + monthName + " " + text + "\n";
	}
}
