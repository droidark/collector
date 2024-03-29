package xyz.krakenkat.collector.util;

import xyz.krakenkat.collector.domain.model.Issue;
import xyz.krakenkat.collector.domain.model.Publisher;
import xyz.krakenkat.collector.domain.model.SocialNetwork;
import xyz.krakenkat.collector.domain.model.Title;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.SocialNetworkDTO;
import xyz.krakenkat.collector.dto.TitleDTO;

import java.util.List;
import java.util.Optional;

public final class Mapper {
    public static PublisherDTO toPublisherDTO(Publisher publisher) {
        return PublisherDTO
                .builder()
                .name(publisher.getName())
                .key(publisher.getKey())
                .url(publisher.getUrl())
                .logo(publisher.getLogo())
                .information(publisher.getInformation())
                .socialNetworks(publisher
                        .getSocialNetworks()
                        .stream()
                        .map(Mapper::toSocialNetworkDTO)
                        .toList())
                .build();
    }

    public static TitleDTO toTitleDTO(Title title) {
        return TitleDTO
                .builder()
                .key(title.getKey())
                .name(title.getName())
                .cover(title.getCover())
                .demography(title.getDemography())
                .format(title.getFormat())
                .type(title.getType())
                .frequency(title.getFrequency())
                .status(title.getStatus())
                .genres(Optional.ofNullable(title.getGenres()).orElse(List.of()))
                .authors(title.getAuthors())
                .releaseDate(title.getReleaseDate())
                .totalIssues(title.getTotalIssues())
                .build();
    }

    public static IssueDTO toIssueDTO(Issue issue) {
        return IssueDTO
                .builder()
                .name(issue.getName())
                .key(issue.getKey())
                .number(issue.getNumber())
                .cover(issue.getCover())
                .pages(issue.getPages())
                .printedPrice(issue.getPrintedPrice())
                .digitalPrice(issue.getDigitalPrice())
                .currency(issue.getCurrency())
                .releaseDate(issue.getReleaseDate())
                .shortReview(issue.getShortReview())
                .event(issue.getEvent())
                .storyArch(issue.getStoryArch())
                .isbn(issue.getIsbn())
                .barcode(issue.getBarcode())
                .edition(issue.getEdition())
                .variant(issue.getVariant())
                .likesCounter(issue.getLikesCounter())
                .dislikesCounter(issue.getDislikesCounter())
                .build();
    }

    public static SocialNetworkDTO toSocialNetworkDTO(SocialNetwork socialNetwork) {
        return SocialNetworkDTO
                .builder()
                .key(socialNetwork.getKey())
                .nickname(socialNetwork.getNickname())
                .url(socialNetwork.getUrl())
                .build();
    }
    private Mapper() {}
}
