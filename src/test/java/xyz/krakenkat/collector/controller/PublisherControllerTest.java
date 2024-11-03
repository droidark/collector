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
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.PublisherService;
import xyz.krakenkat.collector.service.TitleService;
import xyz.krakenkat.collector.util.TitleUtilities;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static xyz.krakenkat.collector.util.PublisherUtilities.buildPublisherDTOList;
import static xyz.krakenkat.collector.util.TestUtilities.fromFile;
import static xyz.krakenkat.collector.util.TestUtilities.generateRandomKey;

@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    @MockBean
    private PublisherService publisherService;

    @MockBean
    private TitleService titleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return all publishers")
    void should_return_all_publishers() throws Exception {
        when(publisherService.getPublishers(any(PageRequest.class))).thenReturn(new PageImpl<>(buildPublisherDTOList()));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers")
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(fromFile("response/get-all-publishers.json")));
    }

    @Test
    @DisplayName("Should return no content for all publishers")
    void should_return_no_content_for_all_publishers() throws Exception {
        when(publisherService.getPublishers(any(PageRequest.class))).thenReturn(Page.empty());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers").accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should return a publisher by key")
    void should_return_a_publisher_by_key() throws Exception {
        // Given
        String key = generateRandomKey(5);

        // When
        when(publisherService.getPublisherByKey(anyString())).thenReturn(buildPublisherDTOList().getFirst());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers/{key}", key)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(fromFile("response/get-publisher.json")));
    }

    @Test
    @DisplayName("Should return no content for a search by publisher by key")
    void should_return_no_content_for_a_publisher_by_key() throws Exception {
        // Given
        String key = generateRandomKey(5);

        // When
        when(publisherService.getPublisherByKey(anyString())).thenThrow(NoContentException.class);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers/{key}", key)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should return titles given a publisher key")
    void should_return_titles_given_a_publisher_key() throws Exception {
        // Given
        String key = generateRandomKey(5);

        // When
        when(titleService.getAllTitlesByPublisherKey(anyString(), any(PageRequest.class))).thenReturn(new PageImpl<>(TitleUtilities.buildTitleDTOList()));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers/{key}/titles", key)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(fromFile("response/get-titles-by-publisher-key.json")));
    }

    @Test
    @DisplayName("Should return no content titles given a publisher key")
    void should_return_no_content_titles_given_a_publisher_key() throws Exception {
        // Given
        String key = generateRandomKey(5);

        // When
        when(titleService.getAllTitlesByPublisherKey(anyString(), any(PageRequest.class))).thenReturn(Page.empty());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers/{key}/titles", key)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}