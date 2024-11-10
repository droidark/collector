package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.model.*;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.SocialNetworkDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.service.MapperService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapperServiceImpl implements MapperService {

    private final ModelMapper modelMapper;

    @Override
    public PublisherDTO toPublisherDTO(Publisher publisher) {
        return this.modelMapper.map(publisher, PublisherDTO.class);
    }

    @Override
    public SocialNetworkDTO toSocialNetworkDTO(PublisherSocialNetwork publisherSocialNetwork) {
        return this.modelMapper.map(publisherSocialNetwork, SocialNetworkDTO.class);
    }

    @Override
    public Publisher toPublisher(PublisherDTO publisherDTO) {
        return this.modelMapper.map(publisherDTO, Publisher.class);
    }

    @Override
    public PublisherSocialNetwork toPublisherSocialNetwork(SocialNetworkDTO socialNetworkDTO) {
        return this.modelMapper.map(socialNetworkDTO, PublisherSocialNetwork.class);
    }

    @Override
    public TitleDTO toTitleDTO(Title title) {
        Converter<Set<Genre>, List<String>> genreConverter = context -> Optional
                .ofNullable(context.getSource())
                .orElse(Collections.emptySet())
                .stream()
                .map(Genre::getGenreName)
                .sorted()
                .toList();

        Converter<Set<TitleAuthorRole>, Map<String, String>> authorRoleConverter = context ->
                context.getSource() == null ? Collections.emptyMap() :
                        context.getSource().stream()
                                .collect(Collectors.toMap(
                                        role -> role.getRole().getRoleName(),
                                        role -> role.getAuthor().getName(),
                                        (existing, _) -> existing // Handles duplicate keys by keeping the first entry
                                ));

        this.modelMapper
                .typeMap(Title.class, TitleDTO.class)
                .addMappings(mapper -> {
                    mapper.using(genreConverter).map(Title::getGenres, TitleDTO::setGenres);
                    mapper.using(authorRoleConverter).map(Title::getTitleAuthorRoles, TitleDTO::setAuthors);
                });

        return this.modelMapper.map(title, TitleDTO.class);
    }
}
