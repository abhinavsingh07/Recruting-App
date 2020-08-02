package com.recruit.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import com.recruit.util.LocalDateDeserializer;
import com.recruit.util.LocalDateSerializer;

public class JobOffer {
	@JsonProperty(value = "job_id")
	private transient int jobId;

	@SerializedName("job_title") // for unit testing
	@JsonProperty(value = "job_title")
	@Size(max = 20, min = 3, message = "{joboffer.jobtitle.invalid}")
	@NotEmpty(message = "{joboffer.jobtitle.empty}")
	private String jobTitle;

	@JsonProperty(value = "start_date")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate startDate;

	@JsonProperty(value = "job_application")
	private List<JobApplication> numberOfApplications;

	public JobOffer(String jobTitle) {
		super();
		this.jobTitle = jobTitle;
	}

	public JobOffer() {
		// TODO Auto-generated constructor stub
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public List<JobApplication> getNumberOfApplications() {
		return numberOfApplications;
	}

	public void setNumberOfApplications(List<JobApplication> numberOfApplications) {
		this.numberOfApplications = numberOfApplications;
	}

	@Override
	public String toString() {
		return "JobOffer [jobId=" + jobId + ", jobTitle=" + jobTitle + ", startDate=" + startDate
				+ ", numberOfApplications=" + numberOfApplications + "]";
	}

}
