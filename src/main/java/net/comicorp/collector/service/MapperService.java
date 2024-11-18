package net.comicorp.collector.service;

import net.comicorp.collector.domain.model.Issue;
import net.comicorp.collector.domain.model.Publisher;
import net.comicorp.collector.domain.model.PublisherSocialNetwork;
import net.comicorp.collector.domain.model.Title;
import net.comicorp.collector.dto.IssueDTO;
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.dto.SocialNetworkDTO;
import net.comicorp.collector.dto.TitleDTO;

public interface MapperService {

    PublisherDTO toPublisherDTO(Publisher publisher);

    SocialNetworkDTO toSocialNetworkDTO(PublisherSocialNetwork publisherSocialNetwork);

    Publisher toPublisher(PublisherDTO publisherDTO);

    PublisherSocialNetwork toPublisherSocialNetwork(SocialNetworkDTO socialNetworkDTO);

    TitleDTO toTitleDTO(Title title);

    IssueDTO toIssueDTO(Issue issue);
}
