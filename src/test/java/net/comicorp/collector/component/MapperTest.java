package net.comicorp.collector.component;

import net.comicorp.collector.domain.model.Issue;
import net.comicorp.collector.domain.model.Publisher;
import net.comicorp.collector.domain.model.PublisherSocialNetwork;
import net.comicorp.collector.domain.model.Title;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.dto.SocialNetworkDTO;
import net.comicorp.collector.dto.TitleDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static net.comicorp.collector.util.PublisherUtilities.buildPublisher;
import static net.comicorp.collector.util.TitleUtilities.buildTitle;
import static net.comicorp.collector.util.TitleUtilities.buildTitleDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MapperTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private Mapper mapper;

    @Test
    void shouldMapPublisherToPublisherDTO() {
        // Arrange
        Publisher publisher = new Publisher();
        PublisherDTO expectedDTO = new PublisherDTO();
        when(modelMapper.map(publisher, PublisherDTO.class)).thenReturn(expectedDTO);

        // Act
        PublisherDTO actualDTO = mapper.toPublisherDTO(publisher);

        // Assert
        assertEquals(expectedDTO, actualDTO);
        verify(modelMapper).map(publisher, PublisherDTO.class);
    }

    @Test
    void shouldMapSocialNetworkToSocialNetworkDTO() {
        // Arrange
        PublisherSocialNetwork socialNetwork = new PublisherSocialNetwork();
        SocialNetworkDTO expectedDTO = new SocialNetworkDTO();
        when(modelMapper.map(socialNetwork, SocialNetworkDTO.class)).thenReturn(expectedDTO);

        // Act
        SocialNetworkDTO actualDTO = mapper.toSocialNetworkDTO(socialNetwork);

        // Assert
        assertEquals(expectedDTO, actualDTO);
        verify(modelMapper).map(socialNetwork, SocialNetworkDTO.class);
    }

    @Test
    void shouldMapPublisherDTOToPublisher() {
        // Arrange
        PublisherDTO publisherDTO = new PublisherDTO();
        Publisher expectedPublisher = new Publisher();
        when(modelMapper.map(publisherDTO, Publisher.class)).thenReturn(expectedPublisher);

        // Act
        Publisher actualPublisher = mapper.toPublisher(publisherDTO);

        // Assert
        assertEquals(expectedPublisher, actualPublisher);
        verify(modelMapper).map(publisherDTO, Publisher.class);
    }

    @Test
    void shouldMapSocialNetworkDTOToPublisherSocialNetwork() {
        // Arrange
        SocialNetworkDTO socialNetworkDTO = new SocialNetworkDTO();
        PublisherSocialNetwork expectedSocialNetwork = new PublisherSocialNetwork();
        when(modelMapper.map(socialNetworkDTO, PublisherSocialNetwork.class)).thenReturn(expectedSocialNetwork);

        // Act
        PublisherSocialNetwork actualSocialNetwork = mapper.toPublisherSocialNetwork(socialNetworkDTO);

        // Assert
        assertEquals(expectedSocialNetwork, actualSocialNetwork);
        verify(modelMapper).map(socialNetworkDTO, PublisherSocialNetwork.class);
    }

    @Test
    void shouldMapTitleToTitleDTO() {
        // Arrange
        Title title = buildTitle(buildPublisher());
        TitleDTO expectedDTO = buildTitleDTO();
        when(modelMapper.map(title, TitleDTO.class)).thenReturn(expectedDTO);

        // Act
        mapper = new Mapper(new ModelMapper()); // Inject real ModelMapper
        TitleDTO actualDTO = mapper.toTitleDTO(title);

        // Assert
        assertEquals(expectedDTO.getGenres(), actualDTO.getGenres());
        assertEquals(expectedDTO.getAuthors().size(), actualDTO.getAuthors().size());
    }

    @Test
    void shouldMapIssueToIssueDTO() {
        // Arrange
        Issue issue = new Issue();
        IssueDTO expectedDTO = new IssueDTO();
        when(modelMapper.map(issue, IssueDTO.class)).thenReturn(expectedDTO);

        // Act
        IssueDTO actualDTO = mapper.toIssueDTO(issue);

        // Assert
        assertEquals(expectedDTO, actualDTO);
        verify(modelMapper).map(issue, IssueDTO.class);
    }
}