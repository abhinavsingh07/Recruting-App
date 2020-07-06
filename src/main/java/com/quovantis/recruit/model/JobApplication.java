package com.quovantis.recruit.model;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import com.quovantis.recruit.util.ApplicationStatus;
import com.quovantis.recruit.util.LocalDateDeserializer;
import com.quovantis.recruit.util.LocalDateSerializer;

public class JobApplication {
	//made transient to not to include in json body in junit test
	@JsonProperty(value = "job_app_id")
	private transient int jobAppId;

	@SerializedName("job_offer_id")//to make json key otherwise it will take realtedOffer in Junit test module
	@Min(value =1, message = "{jobapp.relatedOffer.invalid}")
	@NotNull(message = "{jobapp.relatedOffer.empty}")
	@JsonProperty(value = "job_offer_id")
	private int realtedOffer;

	@SerializedName("candidate_email")
	@NotEmpty(message = "{jobapp.candidateEmail.empty}")
	@JsonProperty(value = "candidate_email")
	private String candidateEmail;
	
	@SerializedName("resume_text")
	@JsonProperty(value = "resume_text")
	private String resumeText;
	
	@SerializedName("job_application_status")
	@JsonProperty(value = "job_application_status")
	private ApplicationStatus applicationStatus;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonProperty(value = "job_applied_date")
	private LocalDate appliedDate;

	public JobApplication(int realtedOffer, String candidateEmail, String resumeText,
			ApplicationStatus applicationStatus) {
		super();
		this.realtedOffer = realtedOffer;
		this.candidateEmail = candidateEmail;
		this.resumeText = resumeText;
		this.applicationStatus = applicationStatus;
	}

	public JobApplication() {
		// TODO Auto-generated constructor stub
	}

	public int getRealtedOffer() {
		return realtedOffer;
	}

	public void setRealtedOffer(int realtedOffer) {
		this.realtedOffer = realtedOffer;
	}

	public String getResumeText() {
		return resumeText;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public int getJobAppId() {
		return jobAppId;
	}

	public void setJobAppId(int jobAppId) {
		this.jobAppId = jobAppId;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	@Override
	public String toString() {
		return "JobApplication [realtedOffer=" + realtedOffer + ", candItateEmail=" + candidateEmail + ", resumeText="
				+ resumeText + ", applicationStatus=" + applicationStatus + "]";
	}

}
