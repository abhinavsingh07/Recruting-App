package com.quovantis.recruit.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.quovantis.recruit.model.JobApplication;
import com.quovantis.recruit.respository.JobApplicationRepo;

/**
 * Job Application Rest Controller
 * 
 * @author Abhinav Singh
 *
 */
@Validated // enable validation
@RestController
@RequestMapping("/api")
@EnableWebMvc
public class JobAppRestController {
	@Autowired
	private JobApplicationRepo JARepo;

	/**
	 * Get all job applications
	 * 
	 * @return An array of all job application
	 */
	@RequestMapping(value = "/jobApplication", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobApplication>> getAllJobApplication() {
		return new ResponseEntity<List<JobApplication>>(JARepo.getAll(), HttpStatus.OK);
	}

	/**
	 * Get Job application by job id
	 * 
	 * @param jobAppId Job application id
	 * @return return Job Application object
	 * @throws Exception
	 */
	@RequestMapping(value = "/jobApplication/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JobApplication> getAllJobApplication(
			@Min(value = 1, message = "{jobapp.jobappid.invalid}") @PathVariable("id") int jobAppId) throws Exception {
		JobApplication jobApplication = JARepo.getById(jobAppId);
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}

	/**
	 * Create Job application
	 * 
	 * @param ja Job Application object
	 * @return return created id
	 * @throws Exception
	 */
	@RequestMapping(value = "/jobApplication", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createJobApp(@Valid @RequestBody JobApplication ja) throws Exception {
		int id = JARepo.create(ja);
		return new ResponseEntity<String>("Job Application Created With Id: " + id, HttpStatus.CREATED);
	}
}
