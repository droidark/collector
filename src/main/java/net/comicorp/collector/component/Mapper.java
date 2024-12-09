package net.comicorp.collector.component;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.domain.model.*;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.dto.SocialNetworkDTO;
import net.comicorp.collector.dto.TitleDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    public PublisherDTO toPublisherDTO(Publisher publisher) {
        return this.modelMapper.map(publisher, PublisherDTO.class);
    }

    public SocialNetworkDTO toSocialNetworkDTO(PublisherSocialNetwork publisherSocialNetwork) {
        return this.modelMapper.map(publisherSocialNetwork, SocialNetworkDTO.class);
    }

    public Publisher toPublisher(PublisherDTO publisherDTO) {
        return this.modelMapper.map(publisherDTO, Publisher.class);
    }

    public PublisherSocialNetwork toPublisherSocialNetwork(SocialNetworkDTO socialNetworkDTO) {
        return this.modelMapper.map(socialNetworkDTO, PublisherSocialNetwork.class);
    }

    public TitleDTO toTitleDTO(Title title) {
        // Convert genres from Set<Genre> to List<String>
        Converter<Set<Genre>, List<String>> genreConverter = context ->
                Optional.ofNullable(context.getSource())
                        .stream()
                        .flatMap(Set::stream)
                        .map(Genre::getGenreName)
                        .sorted()
                        .toList();

        // Convert TitleAuthorRole from Set<TitleAuthorRole> to Map<String, List<String>>
        Converter<Set<TitleAuthorRole>, Map<String, List<String>>> authorRoleConverter = context ->
                Optional.ofNullable(context.getSource())
                        .stream()
                        .flatMap(Set::stream)
                        .collect(Collectors.groupingBy(
                                role -> role.getRole().getRoleName(),
                                Collectors.mapping(
                                        role -> role.getAuthor().getName(),
                                        Collectors.toList()
                                )
                        ));

        // Configure ModelMapper with custom converters
        this.modelMapper
                .typeMap(Title.class, TitleDTO.class)
                .addMappings(mapper -> {
                    mapper.using(genreConverter).map(Title::getGenres, TitleDTO::setGenres);
                    mapper.using(authorRoleConverter).map(Title::getTitleAuthorRoles, TitleDTO::setAuthors);
                });

        return this.modelMapper.map(title, TitleDTO.class);
    }

    public IssueDTO toIssueDTO(Issue issue) {
        return this.modelMapper.map(issue, IssueDTO.class);
    }
}
