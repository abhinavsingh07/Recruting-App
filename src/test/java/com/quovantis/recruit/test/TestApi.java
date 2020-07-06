package com.quovantis.recruit.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.quovantis.recruit.model.JobApplication;
import com.quovantis.recruit.model.JobOffer;

/**
 * Junit Test Class To Test Rest Controllers
 * 
 * @author Abhinav Singh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring-dispatcher-servlet.xml" })
@WebAppConfiguration
public class TestApi {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		// alwaysDo(MockMvcResultHandlers.print()) to print test request response
	}

	@Test
	public void testCreateOffer() throws Exception {
		JobOffer jobOffer = new JobOffer("Test Title");
		String json = new Gson().toJson(jobOffer);
		System.out.println(json);
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/jobOffer")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
//		String content = result.andReturn().getResponse().getContentAsString();
//		System.out.println("testCreateOffer " + content);
	}

	@Test
	public void testGetAllOffers() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/jobOffer"))
				.andExpect(MockMvcResultMatchers.status().isOk());
//		String content = result.andReturn().getResponse().getContentAsString();
//		System.out.println("testGetAllOffers " + content);
	}

	@Test
	public void testGetOfferById() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/jobOffer/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
//		String content = result.andReturn().getResponse().getContentAsString();
//		System.out.println("testGetOfferById " + content);
	}

	@Test
	public void testCreateJobApplication() throws Exception {
		JobApplication jobApp = new JobApplication();
		jobApp.setCandidateEmail("singhabhinav844@gmail.com");
		jobApp.setRealtedOffer(1);
		jobApp.setResumeText("Sample text");
		String json = new Gson().toJson(jobApp);

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/jobApplication")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
//		String content = result.andReturn().getResponse().getContentAsString();
//		System.out.println("testCreateJobApplication " + content);

	}

	@Test
	public void testGetAllApplication() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/jobApplication"))
				.andExpect(MockMvcResultMatchers.status().isOk());
//		String content = result.andReturn().getResponse().getContentAsString();
//		System.out.println("testGetAllApplication " + content);
	}

	@Test
	public void testGetAppById() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/jobApplication/1"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
//		String content = result.andReturn().getResponse().getContentAsString();
//		System.out.println("testGetAppById " + content);
	}

}
