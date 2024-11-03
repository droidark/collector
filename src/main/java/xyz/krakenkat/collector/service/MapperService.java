package xyz.krakenkat.collector.service;

import xyz.krakenkat.collector.domain.model.Publisher;
import xyz.krakenkat.collector.domain.model.PublisherSocialNetwork;
import xyz.krakenkat.collector.domain.model.Title;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.SocialNetworkDTO;
import xyz.krakenkat.collector.dto.TitleDTO;

public interface MapperService {

    PublisherDTO toPublisherDTO(Publisher publisher);

    SocialNetworkDTO toSocialNetworkDTO(PublisherSocialNetwork publisherSocialNetwork);

    Publisher toPublisher(PublisherDTO publisherDTO);

    PublisherSocialNetwork toPublisherSocialNetwork(SocialNetworkDTO socialNetworkDTO);

    TitleDTO toTitleDTO(Title title);
}
