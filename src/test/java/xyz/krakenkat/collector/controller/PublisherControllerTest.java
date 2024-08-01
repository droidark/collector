package xyz.krakenkat.collector.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import xyz.krakenkat.collector.service.PublisherService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static xyz.krakenkat.collector.util.TestUtilities.buildPublisherDTOs;

@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    @MockBean
    private PublisherService publisherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return all publishers")
    void should_return_all_publishers() throws Exception {
        when(publisherService.getPublishers(any(PageRequest.class))).thenReturn(new PageImpl<>(buildPublisherDTOs()));

        RequestBuilder request = MockMvcRequestBuilders.get("/publishers").accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return no content")
    void should_return_no_content() throws Exception {
        when(publisherService.getPublishers(any(PageRequest.class))).thenReturn(Page.empty());

        RequestBuilder request = MockMvcRequestBuilders.get("/publishers").accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(request).andExpect(status().isNoContent());
    }
}