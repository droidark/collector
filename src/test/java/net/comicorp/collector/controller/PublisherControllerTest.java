package net.comicorp.collector.controller;

import net.comicorp.collector.exception.NoContentException;
import net.comicorp.collector.service.PublisherService;
import net.comicorp.collector.service.TitleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static net.comicorp.collector.util.PublisherUtilities.buildPublisherDTOList;
import static net.comicorp.collector.util.TestUtilities.fromFile;
import static net.comicorp.collector.util.TestUtilities.generateRandomKey;
import static net.comicorp.collector.util.TitleUtilities.buildTitleDTOList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void shouldReturnAllPublishers() throws Exception {
        // Given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", "0");
        params.add("size", "10");
        params.add("sort", "name:asc");
        params.add("sort", "key:asc");

        when(publisherService.getPublishers(any(PageRequest.class))).thenReturn(new PageImpl<>(buildPublisherDTOList()));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers")
                .params(params)
                .accept(APPLICATION_JSON_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(content().json(fromFile("response/get-all-publishers.json")));
    }

    @Test
    @DisplayName("Should return No Content Exception for all publishers")
    void shouldReturnNoContentExceptionForAllPublishers() throws Exception {
        when(publisherService.getPublishers(any(PageRequest.class))).thenReturn(Page.empty());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers").accept(APPLICATION_JSON_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should return a publisher by key")
    void shouldReturnPublisherByKey() throws Exception {
        // Given
        String key = generateRandomKey(5);

        // When
        when(publisherService.getPublisherByKey(anyString())).thenReturn(buildPublisherDTOList().getFirst());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers/{key}", key)
                .accept(APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(content().json(fromFile("response/get-publisher.json")));
    }

    @Test
    @DisplayName("Should return NoContentException for a search by publisher by key")
    void shouldReturnNoContentExceptionForPublisherByKey() throws Exception {
        // Given
        String key = generateRandomKey(5);

        // When
        when(publisherService.getPublisherByKey(anyString())).thenThrow(NoContentException.class);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers/{key}", key)
                .accept(APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should return titles given a publisher key")
    void shouldReturnTitlesGivenPublisherKey() throws Exception {
        // Given
        String key = generateRandomKey(5);

        // When
        when(titleService.getAllTitlesByPublisherKey(anyString(), any(PageRequest.class))).thenReturn(new PageImpl<>(buildTitleDTOList()));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers/{key}/titles", key)
                .accept(APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(content().json(fromFile("response/get-titles-by-publisher-key.json")));
    }

    @Test
    @DisplayName("Should return NoContentException titles given a publisher key")
    void shouldReturnNoContentExceptionGivenPublisherKey() throws Exception {
        // Given
        String key = generateRandomKey(5);

        // When
        when(titleService.getAllTitlesByPublisherKey(anyString(), any(PageRequest.class))).thenReturn(Page.empty());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/publishers/{key}/titles", key)
                .accept(APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}