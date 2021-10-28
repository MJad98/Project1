package com.example.models;

public class TicketModel {

	private int ticketid;
	private float amount;
	private	int authorid;
	private int resolverid;
	private String submitted;
	private String resolved;
	private int statusid;
	private int typeid;
	private String description;
	
	
	public TicketModel() {} //no args constructor

	public TicketModel(int ticketid, float amount, String submitted, String resolved, int authorid, int resolverid, int statusid, int typeid, String description) { //all fields ticket constructor
		super();
		this.ticketid = ticketid;
		this.amount = amount;
		this.description = description;
		this.submitted=submitted;
		this.resolved=resolved;
		this.authorid = authorid;
		this.resolverid = resolverid;
		this.statusid = statusid;
		this.typeid = typeid;
		
	}
	
	public TicketModel(float amount, String description, int authorid, int statusid, int typeid) { //creating new ticket constructor
		this.amount=amount;
		this.authorid=authorid;
		this.typeid=typeid;
		this.statusid=statusid;
		this.description=description;
	}
	
	public TicketModel(int ticketid, float amount, int authorid, int statusid, int typeid, String description) {//Unresolved Ticket Constructor
		this.ticketid = ticketid;
		this.amount = amount;
		this.authorid = authorid;
		this.statusid = statusid;
		this.typeid = typeid;
		this.description = description;
	}
	
	public TicketModel(int ticketid, float amount, int authorid, int resolverid, int statusid, int typeid, String description) {//submitTicketUpdate constructor
		this.ticketid = ticketid;
		this.amount=amount;
		this.authorid=authorid;
		this.resolverid=resolverid;
		this.statusid=statusid;
		this.typeid=typeid;
		this.description=description;
	}

	public int getTicketid() {
		return ticketid;
	}

	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public int getResolverid() {
		return resolverid;
	}

	public void setResolverid(int resolverid) {
		this.resolverid = resolverid;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubmitted() {
		return submitted;
	}

	public String getResolved() {
		return resolved;
	}

	@Override
	public String toString() {
		return "TicketModel [ticketid=" + ticketid + ", amount=" + amount + ", authorid=" + authorid + ", resolverid="
				+ resolverid + ", submitted=" + submitted + ", resolved=" + resolved + ", statusid=" + statusid
				+ ", typeid=" + typeid + ", description=" + description + "]";
	}

	

	
	
	
	
}
