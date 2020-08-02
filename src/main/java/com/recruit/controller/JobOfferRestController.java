package com.recruit.controller;

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

import com.recruit.model.JobOffer;
import com.recruit.respository.JobOfferRepo;

/**
 * Job Offer Rest Controller
 * 
 * @author Abhinav Singh
 *
 */
@Validated // enable validation
@RestController
@RequestMapping("/api")
@EnableWebMvc
public class JobOfferRestController {

	@Autowired
	private JobOfferRepo JORepo;

	/**
	 * Get All job offer
	 * 
	 * @return all job offer as List
	 */
	@RequestMapping(value = "/jobOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobOffer>> getAllJobOffer() {
		return new ResponseEntity<List<JobOffer>>(JORepo.getAll(), HttpStatus.OK);
	}

	/**
	 * Get Job offer by job offer id
	 * 
	 * @param id id to get Job offer
	 * @return JobOffer
	 * @throws Exception
	 */
	@RequestMapping(value = "/jobOffer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JobOffer> getJobOffer(
			@Min(value = 1, message = "{joboffer.jobid.invalid}") @PathVariable("id") int id) throws Exception {
		return new ResponseEntity<JobOffer>(JORepo.getById(id), HttpStatus.OK);
	}

	/**
	 * Create new Job offer
	 * 
	 * @param jo Job Offer object
	 * @return Return created id
	 * @throws Exception
	 */
	@RequestMapping(value = "/jobOffer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createJobOffer(@Valid @RequestBody JobOffer jo) throws Exception {
		int id = JORepo.create(jo);
		return new ResponseEntity<String>("Job Offer Created With Id: " + id, HttpStatus.CREATED);
	}
}
