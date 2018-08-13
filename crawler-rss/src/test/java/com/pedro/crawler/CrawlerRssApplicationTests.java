package com.pedro.crawler;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerRssApplication.class)
@WebAppConfiguration
public class CrawlerRssApplicationTests {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON_UTF8.getType(),
			MediaType.APPLICATION_JSON_UTF8.getSubtype());
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(context).build();
    }

	@Test
	public void showItemsFromDefaultFeed() throws Exception {
		mockMvc.perform(get("/showItems")
                .contentType(contentType))
                .andExpect(status().isOk());
	}
	
	@Test
	public void showItemsFromMalformedUrl() throws Exception {
		String response = mockMvc.perform(get("/showItems?url=s")
                .contentType(contentType))
				.andReturn()
				.getResponse()
				.getContentAsString();
		assertTrue(response.equals("[]"));
	}
	
}
