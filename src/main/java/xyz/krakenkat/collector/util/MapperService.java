package xyz.krakenkat.collector.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.model.Publisher;
import xyz.krakenkat.collector.domain.model.PublisherSocialNetwork;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.SocialNetworkDTO;

@Service
@RequiredArgsConstructor
public class MapperService {

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
}
