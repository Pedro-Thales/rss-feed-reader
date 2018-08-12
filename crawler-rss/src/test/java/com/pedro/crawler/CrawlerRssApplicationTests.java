package com.pedro.crawler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.pedro.crawler.model.FeedItem;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerRssApplication.class)
@WebAppConfiguration
public class CrawlerRssApplicationTests {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON_UTF8.getType(),
			MediaType.APPLICATION_JSON_UTF8.getSubtype());
	
	private MockMvc mockMvc;
	
	private HttpMessageConverter<FeedItem> mappingJackson2HttpMessageConverter;
	
	@Autowired
	private WebApplicationContext context;
	
	protected String json(FeedItem feedItem) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJackson2HttpMessageConverter.write(
        		feedItem, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
	
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
	
}
