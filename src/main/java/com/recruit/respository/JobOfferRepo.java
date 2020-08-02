package com.recruit.respository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

import com.recruit.exceptionHandler.ResourceNotFoundException;
import com.recruit.model.JobOffer;

/**
 * Respository class for database related operations.For Now using Hashmap to
 * store data temporarily.
 * 
 * @author Hp
 *
 */
@Repository
public class JobOfferRepo implements ObjectRepository<JobOffer> {

	static Map<Integer, JobOffer> jobOfferRepo;
	private Integer jobOfferId = new Integer(1);
	static {
		jobOfferRepo = new HashMap<>();
	}

	public JobOfferRepo() {
	}

	/**
	 * Create Job Offer.
	 */
	public Integer create(JobOffer jo) throws Exception {
		try {
			if (!jobOfferRepo.isEmpty()) {
				// Auto generated id
				jobOfferId = jobOfferRepo.entrySet().stream()
						.max((entry1, entry2) -> entry1.getKey() > entry1.getKey() ? 1 : -1).get().getKey() + 1;
			}
			jo.setJobId(jobOfferId);
			// Auto generated start date
			jo.setStartDate(LocalDate.now());
			jobOfferRepo.put(jobOfferId, jo);
			if(new Random().nextBoolean()) {
				Thread.sleep(800);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return jobOfferId;
	}

	/**
	 * Get Job Offer By ID
	 */
	public JobOffer getById(int jobId) throws Exception {
		JobOffer jobOffer = null;
		try {
			jobOffer = jobOfferRepo.get(jobId);
			// Setting job application on Offer
			jobOffer.setNumberOfApplications(JobApplicationRepo.jobAppRepo.values().stream()
					.filter(i -> i.getRealtedOffer() == jobId).collect(Collectors.toList()));

		} catch (NullPointerException e) {
			throw new ResourceNotFoundException("Job Id Not Found");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return jobOffer;
	}

	/**
	 * Get All Job Offers and their respective Job Applications
	 */
	public List<JobOffer> getAll() {
		List<JobOffer> joffer = new ArrayList<>();
		jobOfferRepo.values().stream().forEach((joObj) -> {
			joObj.setNumberOfApplications(JobApplicationRepo.jobAppRepo.values().stream()
					.filter(jaObj -> jaObj.getRealtedOffer() == joObj.getJobId()).collect(Collectors.toList()));
			joffer.add(joObj);
		});
		if(new Random().nextBoolean())
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return joffer;
	}

	/**
	 * Update method to be implemented in future
	 */
	public void updateById(JobOffer jo, int id) {
	}

}
