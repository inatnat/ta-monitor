package com.manulife.top5website.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="sites")
public class Site {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	Date date;

	String website;
	@Min(1)
	int visits;
	@NotNull
	@Column(name="created_date_time")
	Date createdDateTime;
	@NotNull
	@Column(name="created_by")
	String createdBy;
	@NotNull
	@Column(name="last_update_date_time")
	Date lastUpdateDateTime;
	@NotNull
	@Column(name="last_update_by")
	String last_update_by;

		
	public Site() {
		
	}
	
	public Site(String date, String website, String visits) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        try {

            this.date = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.website = website;
		this.visits = Integer.valueOf(visits);
		this.createdBy = "Monitor";
		this.createdDateTime = new Date();
		
		this.last_update_by = "Monitor";
		this.lastUpdateDateTime = new Date();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	@Override
	public String toString() {
		return "Site [id=" + id + ", date=" + date + ", website=" + website + ", visits=" + visits + "]";
	}
	
	
	
	
}
