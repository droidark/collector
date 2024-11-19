package net.comicorp.collector.util;

import net.comicorp.collector.constant.SocialNetworkType;
import net.comicorp.collector.domain.model.Publisher;
import net.comicorp.collector.domain.model.PublisherSocialNetwork;
import net.comicorp.collector.dto.PublisherDTO;
import net.comicorp.collector.dto.SocialNetworkDTO;

import java.util.List;

public class PublisherUtilities {

    // DATA LAYER
    public static Publisher buildPublisher() {
        // Create a new Publisher instance with different dummy data
        Publisher publisher = Publisher.builder()
                .name("GreenLiving")
                .key("greenliving")
                .url("https://greenliving.com")
                .logo("https://greenliving.com/logo.png")
                .information("Your source for sustainable living tips")
                .build();

        // Create related PublisherSocialNetwork instances
        PublisherSocialNetwork facebook = PublisherSocialNetwork.builder()
                .type(SocialNetworkType.FACEBOOK)
                .username("greenlivingFB")
                .url("https://facebook.com/greenliving")
                .publisher(publisher)  // set publisher reference for bi-directional relationship
                .build();

        PublisherSocialNetwork instagram = PublisherSocialNetwork.builder()
                .type(SocialNetworkType.INSTAGRAM)
                .username("greenlivingIG")
                .url("https://instagram.com/greenliving")
                .publisher(publisher)  // set publisher reference for bi-directional relationship
                .build();

        PublisherSocialNetwork tiktok = PublisherSocialNetwork.builder()
                .type(SocialNetworkType.TIKTOK)
                .username("greenlivingTT")
                .url("https://tiktok.com/@greenliving")
                .publisher(publisher)  // set publisher reference for bi-directional relationship
                .build();

        // Add social networks to publisher
        publisher.setPublisherSocialNetworks(List.of(facebook, instagram, tiktok));

        return publisher;
    }

    public static List<Publisher> buildPublisherList() {
        Publisher techWorld = Publisher.builder()
                .name("TechWorld")
                .key("techworld")
                .url("https://techworld.com")
                .logo("https://techworld.com/logo.png")
                .information("Leading tech news publisher")
                .publisherSocialNetworks(List.of(
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.FACEBOOK)
                                .username("techworldFB")
                                .url("https://facebook.com/techworld")
                                .build(),
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.INSTAGRAM)
                                .username("techworldIG")
                                .url("https://instagram.com/techworld")
                                .build()
                ))
                .build();

        Publisher foodiesDaily = Publisher.builder()
                .name("FoodiesDaily")
                .key("foodiesdaily")
                .url("https://foodiesdaily.com")
                .logo("https://foodiesdaily.com/logo.png")
                .information("Daily food and recipe tips")
                .publisherSocialNetworks(List.of(
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.INSTAGRAM)
                                .username("foodiesdailyIG")
                                .url("https://instagram.com/foodiesdaily")
                                .build(),
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.X)
                                .username("foodiesdailyX")
                                .url("https://x.com/foodiesdaily")
                                .build()
                ))
                .build();

        Publisher travelista = Publisher.builder()
                .name("Travelista")
                .key("travelista")
                .url("https://travelista.com")
                .logo("https://travelista.com/logo.png")
                .information("Inspiring travel stories and guides")
                .publisherSocialNetworks(List.of(
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.INSTAGRAM)
                                .username("travelistaIG")
                                .url("https://instagram.com/travelista")
                                .build(),
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.FACEBOOK)
                                .username("travelistaFB")
                                .url("https://facebook.com/travelista")
                                .build()
                ))
                .build();

        Publisher sportsArena = Publisher.builder()
                .name("SportsArena")
                .key("sportsarena")
                .url("https://sportsarena.com")
                .logo("https://sportsarena.com/logo.png")
                .information("Latest sports news and updates")
                .publisherSocialNetworks(List.of(
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.X)
                                .username("sportsarenaX")
                                .url("https://x.com/sportsarena")
                                .build(),
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.TIKTOK)
                                .username("sportsarenaTT")
                                .url("https://tiktok.com/@sportsarena")
                                .build()
                ))
                .build();

        Publisher ecoLife = Publisher.builder()
                .name("EcoLife")
                .key("ecolife")
                .url("https://ecolife.com")
                .logo("https://ecolife.com/logo.png")
                .information("Sustainable living tips and news")
                .publisherSocialNetworks(List.of(
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.FACEBOOK)
                                .username("ecolifeFB")
                                .url("https://facebook.com/ecolife")
                                .build(),
                        PublisherSocialNetwork.builder()
                                .type(SocialNetworkType.INSTAGRAM)
                                .username("ecolifeIG")
                                .url("https://instagram.com/ecolife")
                                .build()
                ))
                .build();

        return List.of(techWorld, foodiesDaily, travelista, sportsArena, ecoLife);
    }

    // SERVICE LAYER
    public static List<PublisherDTO> buildPublisherDTOList() {
        PublisherDTO techWorld = PublisherDTO.builder()
                .name("TechWorld")
                .key("techworld")
                .url("https://techworld.com")
                .logo("https://techworld.com/logo.png")
                .information("Leading tech news publisher")
                .socialNetworks(List.of(
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.FACEBOOK)
                                .username("techworldFB")
                                .url("https://facebook.com/techworld")
                                .build(),
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.INSTAGRAM)
                                .username("techworldIG")
                                .url("https://instagram.com/techworld")
                                .build()
                ))
                .build();

        PublisherDTO foodiesDaily = PublisherDTO.builder()
                .name("FoodiesDaily")
                .key("foodiesdaily")
                .url("https://foodiesdaily.com")
                .logo("https://foodiesdaily.com/logo.png")
                .information("Daily food and recipe tips")
                .socialNetworks(List.of(
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.INSTAGRAM)
                                .username("foodiesdailyIG")
                                .url("https://instagram.com/foodiesdaily")
                                .build(),
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.X)
                                .username("foodiesdailyX")
                                .url("https://x.com/foodiesdaily")
                                .build()
                ))
                .build();

        PublisherDTO travelista = PublisherDTO.builder()
                .name("Travelista")
                .key("travelista")
                .url("https://travelista.com")
                .logo("https://travelista.com/logo.png")
                .information("Inspiring travel stories and guides")
                .socialNetworks(List.of(
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.INSTAGRAM)
                                .username("travelistaIG")
                                .url("https://instagram.com/travelista")
                                .build(),
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.FACEBOOK)
                                .username("travelistaFB")
                                .url("https://facebook.com/travelista")
                                .build()
                ))
                .build();

        PublisherDTO sportsArena = PublisherDTO.builder()
                .name("SportsArena")
                .key("sportsarena")
                .url("https://sportsarena.com")
                .logo("https://sportsarena.com/logo.png")
                .information("Latest sports news and updates")
                .socialNetworks(List.of(
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.X)
                                .username("sportsarenaX")
                                .url("https://x.com/sportsarena")
                                .build(),
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.TIKTOK)
                                .username("sportsarenaTT")
                                .url("https://tiktok.com/@sportsarena")
                                .build()
                ))
                .build();

        PublisherDTO ecoLife = PublisherDTO.builder()
                .name("EcoLife")
                .key("ecolife")
                .url("https://ecolife.com")
                .logo("https://ecolife.com/logo.png")
                .information("Sustainable living tips and news")
                .socialNetworks(List.of(
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.FACEBOOK)
                                .username("ecolifeFB")
                                .url("https://facebook.com/ecolife")
                                .build(),
                        SocialNetworkDTO.builder()
                                .type(SocialNetworkType.INSTAGRAM)
                                .username("ecolifeIG")
                                .url("https://instagram.com/ecolife")
                                .build()
                ))
                .build();

        return List.of(techWorld, foodiesDaily, travelista, sportsArena, ecoLife);
    }

    private PublisherUtilities() {}
}
