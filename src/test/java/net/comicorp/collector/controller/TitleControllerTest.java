package net.comicorp.collector.controller;

import net.comicorp.collector.service.TitleService;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.Objects;

import static net.comicorp.collector.util.IssueUtilities.buildIssueDTOList;
import static net.comicorp.collector.util.TestUtilities.fromFile;
import static net.comicorp.collector.util.TestUtilities.generateRandomKey;
import static net.comicorp.collector.util.TitleUtilities.buildTitleDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TitleController.class)
class TitleControllerTest {

    @MockBean
    private TitleService titleService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Should return title given publisher key and title key")
    @Test
    void shouldReturnTitleGivenPublisherKeyAndTitleKey() throws Exception {
        // Given
        String titleKey = generateRandomKey(5);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("publisher", generateRandomKey(5));

        // When
        when(titleService.getTitleByKeyAndPublisherKey(anyString(), anyString())).thenReturn(buildTitleDTO());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/titles/{key}", titleKey)
                .params(params)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(fromFile("response/get-title-by-publisher-key-and-title-key.json")));
    }

    @DisplayName("Should return Bad Request when publisher key is missing")
    @Test
    void shouldReturnBadRequestWhenPublisherKeyIsMissing() throws Exception {
        // Given
        String titleKey = generateRandomKey(5);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // Note: Intentionally omitting "publisher" parameter to trigger the exception.

        // When
        RequestBuilder request = MockMvcRequestBuilders
                .get("/titles/{key}", titleKey)
                .params(params) // No publisher key added here
                .accept(MediaType.APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(MissingServletRequestParameterException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals(
                        "Required request parameter 'publisher' for method parameter type String is not present",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()
                ));
    }

    @DisplayName("Should return issues given publisher key and title key")
    @Test
    void shouldReturnIssuesGivenPublisherKeyAndTitleKey() throws Exception {
        // Given
        String titleKey = generateRandomKey(5);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("publisher", generateRandomKey(5));
        params.add("variant", Boolean.FALSE.toString());

        // When
        when(titleService.getAllIssuesByPublisherKeyAndTitleKey(anyString(), anyString(), anyBoolean(), any(PageRequest.class))).thenReturn(new PageImpl<>(buildIssueDTOList(Boolean.FALSE)));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/titles/{titleKey}/issues", titleKey)
                .params(params)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(fromFile("response/get-issues-by-publisher-key-and-title-key.json")));
    }

    @DisplayName("Should return no content issues given publisher key and title key")
    @Test
    void shouldReturnNoContentIssuesGivenPublisherKeyAndTitleKey() throws Exception {
        // Given
        String titleKey = generateRandomKey(5);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("publisher", generateRandomKey(5));
        params.add("variant", Boolean.FALSE.toString());

        // When
        when(titleService.getAllIssuesByPublisherKeyAndTitleKey(anyString(), anyString(), anyBoolean(), any(PageRequest.class))).thenReturn(Page.empty());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/titles/{titleKey}/issues", titleKey)
                .params(params)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        // Then
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}