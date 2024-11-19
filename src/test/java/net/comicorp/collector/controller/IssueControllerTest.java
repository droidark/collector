package net.comicorp.collector.controller;

import net.comicorp.collector.exception.FieldNotValidException;
import net.comicorp.collector.service.IssueService;
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

import static net.comicorp.collector.util.IssueUtilities.buildSingleIssueDTO;
import static net.comicorp.collector.util.TestUtilities.fromFile;
import static net.comicorp.collector.util.TestUtilities.generateRandomKey;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IssueController.class)
class IssueControllerTest {

    @MockBean
    private IssueService issueService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return all issues given the publisher key and title key")
    void shouldReturnAllIssuesGivenThePublisherKeyAndTitleKey() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("publisher", generateRandomKey(5));
        params.add("title", generateRandomKey(5));
        params.add("variant", Boolean.FALSE.toString());

        when(issueService.getByKeyAndPublisherKeyAndTitleKey(anyString(),
                anyString(), anyString(), anyBoolean(), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(buildSingleIssueDTO(Boolean.FALSE)));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/issues/{issueKey}", generateRandomKey(2))
                .params(params)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(fromFile("response/get-issues-by-publisher-key-and-title-key-publisher.json")));
    }

    @Test
    @DisplayName("Should return No Content given the publisher key and title key")
    void shouldReturnNoContentGivenThePublisherKeyAndTitleKey() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("publisher", generateRandomKey(5));
        params.add("title", generateRandomKey(5));
        params.add("variant", Boolean.FALSE.toString());

        when(issueService.getByKeyAndPublisherKeyAndTitleKey(anyString(),
                anyString(), anyString(), anyBoolean(), any(PageRequest.class)))
                .thenReturn(Page.empty());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/issues/{issueKey}", generateRandomKey(2))
                .params(params)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should throw FieldNotValidException given the publisher key and title key")
    void shouldThrowFieldNotValidExceptionThePublisherKeyAndTitleKey() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("publisher", generateRandomKey(5));
        params.add("title", generateRandomKey(5));
        params.add("variant", Boolean.FALSE.toString());

        when(issueService.getByKeyAndPublisherKeyAndTitleKey(anyString(),
                anyString(), anyString(), anyBoolean(), any(PageRequest.class)))
                .thenThrow(FieldNotValidException.class);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/issues/{issueKey}", generateRandomKey(2))
                .params(params)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }
}