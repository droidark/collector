package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.model.Genre;
import xyz.krakenkat.collector.domain.model.Publisher;
import xyz.krakenkat.collector.domain.model.PublisherSocialNetwork;
import xyz.krakenkat.collector.domain.model.Title;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.SocialNetworkDTO;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.service.MapperService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        Converter<Set<Genre>, List<String>> genreConverter = context -> Optional.ofNullable(context.getSource())
                .orElse(Collections.emptySet())
                .stream()
                .map(Genre::getGenreName)
                .toList();

        this.modelMapper
                .typeMap(Title.class, TitleDTO.class)
                .addMappings(mapper -> mapper.using(genreConverter).map(Title::getGenres, TitleDTO::setGenres));

        return this.modelMapper.map(title, TitleDTO.class);
    }
}
