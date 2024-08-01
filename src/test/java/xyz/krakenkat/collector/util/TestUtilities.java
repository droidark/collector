package xyz.krakenkat.collector.util;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import xyz.krakenkat.collector.constant.SocialNetworkType;
import xyz.krakenkat.collector.domain.model.Publisher;
import xyz.krakenkat.collector.domain.model.PublisherSocialNetwork;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.SocialNetworkDTO;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestUtilities {

    // DATA
    public static List<Publisher> buildPublishers() {
        return List.of(buildPublisher());
    }

    public static Publisher buildPublisher() {
        return Publisher
                .builder()
                .name("MangaLine México")
                .key("mangaline-mx")
                .url("https://mangaline.com.mx")
                .logo("default.png")
                .information("Mangaline México, editorial de fans para fans. Amamos la cultura japonesa y lo difundimos a través del manga")
                .publisherSocialNetworks(buildPublisherSocialNetworks())
                .build();
    }

    public static List<PublisherSocialNetwork> buildPublisherSocialNetworks() {
        var x = PublisherSocialNetwork
                .builder()
                .type(SocialNetworkType.X)
                .username("MangaLineMX")
                .url("https://twitter.com/MangaLineMX")
                .build();

        var facebook = PublisherSocialNetwork
                .builder()
                .type(SocialNetworkType.FACEBOOK)
                .username("MangaLineMxOficial")
                .url("https://www.facebook.com/MangaLineMxOficial")
                .build();

        var instagram = PublisherSocialNetwork
                .builder()
                .type(SocialNetworkType.INSTAGRAM)
                .username("MangaLineMxOficial")
                .url("https://www.facebook.com/MangaLineMxOficial")
                .build();

        var tiktok = PublisherSocialNetwork
                .builder()
                .type(SocialNetworkType.TIKTOK)
                .username("MangaLineMxOficial")
                .url("https://www.facebook.com/MangaLineMxOficial")
                .build();

        return List.of(x, facebook, instagram, tiktok);
    }

    // SERVICE
    public static List<PublisherDTO> buildPublisherDTOs() {
        List<SocialNetworkDTO> paniniMxSocialNetworks = List.of(
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.X)
                        .username("PaniniMangaMx")
                        .url("https://twitter.com/PaniniMangaMx")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.FACEBOOK)
                        .username("PaniniMangaMx")
                        .url("https://www.facebook.com/PaniniMangaMx")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.INSTAGRAM)
                        .username("paninimangamx")
                        .url("https://www.instagram.com/paninimangamx/")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.TIKTOK)
                        .username("paninimangamx")
                        .url("https://www.tiktok.com/@paninimangamx")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.X)
                        .username("PaniniComicsMx")
                        .url("https://twitter.com/PaniniComicsMx")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.FACEBOOK)
                        .username("PaniniComicsMx")
                        .url("https://www.facebook.com/PaniniComicsMx")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.INSTAGRAM)
                        .username("paninicomicsmx")
                        .url("https://www.instagram.com/paninicomicsmx/")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.TIKTOK)
                        .username("paninicomicsmx")
                        .url("https://www.tiktok.com/@paninimangamx")
                        .build()
        );

        PublisherDTO paniniMx = PublisherDTO
                .builder()
                .name("Panini México")
                .key("panini-mx")
                .information("Subsidiaria mexicana de Panini")
                .logo("default.png")
                .url("https://tiendapanini.com.mx/")
                .socialNetworks(paniniMxSocialNetworks)
                .build();

        List<SocialNetworkDTO> kamiteSocialNetworks = List.of(
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.X)
                        .username("kamite_manga")
                        .url("https://twitter.com/kamite_manga")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.FACEBOOK)
                        .username("kamitemanga")
                        .url("https://www.facebook.com/kamitemanga")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.INSTAGRAM)
                        .username("kamite_manga")
                        .url("https://www.instagram.com/kamite_manga/")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.TIKTOK)
                        .username("editorialkamiteoficial")
                        .url("https://www.tiktok.com/@editorialkamiteoficial")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.X)
                        .username("Kamite5")
                        .url("https://twitter.com/Kamite5")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.FACEBOOK)
                        .username("EditorialKamite")
                        .url("https://www.facebook.com/EditorialKamite")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.INSTAGRAM)
                        .username("editorial_kamite")
                        .url("https://www.instagram.com/editorial_kamite/")
                        .build()
        );

        PublisherDTO kamite = PublisherDTO
                .builder()
                .name("Kamite")
                .key("kamite")
                .information("Somos una empresa 100% mexicana integrada por profesionales con una experiencia de más de 20 años en el medio editorial y especialistas en el ramo de los cómics, mangas e historietas.")
                .logo("default.png")
                .url("https://kamite.com.mx")
                .socialNetworks(kamiteSocialNetworks)
                .build();

        List<SocialNetworkDTO> distritoMangaMxSocialNetworks = List.of(
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.X)
                        .username("DistritoMangaMx")
                        .url("https://twitter.com/DistritoMangaMx")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.FACEBOOK)
                        .username("DistritoMangaMx")
                        .url("https://www.facebook.com/DistritoMangaMx")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.INSTAGRAM)
                        .username("distritomangamx")
                        .url("https://www.instagram.com/distritomangamx/")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.TIKTOK)
                        .username("distritomangamx")
                        .url("https://www.tiktok.com/@distritomangamx")
                        .build()
        );

        PublisherDTO distritoMangaMx = PublisherDTO
                .builder()
                .name("Distrito Manga MX")
                .key("distrito-manga-mx")
                .information("Somos el sello especializado en manga de Penguin Libros México.")
                .logo("default.png")
                .url("https://www.penguinlibros.com/mx/211002-distrito-manga")
                .socialNetworks(distritoMangaMxSocialNetworks)
                .build();

        List<SocialNetworkDTO> vizSocialNetworks = List.of(
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.X)
                        .username("VIZMedia")
                        .url("https://twitter.com/VIZMedia")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.FACEBOOK)
                        .username("OfficialVIZMedia")
                        .url("https://www.facebook.com/OfficialVIZMedia")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.INSTAGRAM)
                        .username("vizmedia")
                        .url("https://www.instagram.com/vizmedia/")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.TIKTOK)
                        .username("vizmedia")
                        .url("https://www.tiktok.com/@vizmedia")
                        .build()
        );

        PublisherDTO viz = PublisherDTO
                .builder()
                .name("Viz")
                .key("viz")
                .information("VIZ Media is leading the way in what’s now, new and next. Reaching one in four millennials and half of all GenZ manga readers, VIZ is at the forefront of America’s Japanese pop-culture phenomenon, which today dominates multiple industries from publishing and animation to film and gaming.")
                .logo("default.png")
                .url("https://www.viz.com")
                .socialNetworks(vizSocialNetworks)
                .build();

        List<SocialNetworkDTO> kodanshaSocialNetworks = List.of(
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.X)
                        .username("KODANSHA_EN")
                        .url("https://twitter.com/KODANSHA_EN")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.FACEBOOK)
                        .username("KodanshaManga")
                        .url("https://www.facebook.com/KodanshaManga")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.INSTAGRAM)
                        .username("kodanshamanga")
                        .url("https://www.instagram.com/kodanshamanga/")
                        .build(),
                SocialNetworkDTO
                        .builder()
                        .type(SocialNetworkType.TIKTOK)
                        .username("kodanshaus")
                        .url("https://www.tiktok.com/@kodanshaus")
                        .build()
        );

        PublisherDTO kodansha = PublisherDTO
                .builder()
                .name("Kodansha")
                .key("kodansha")
                .information("For over a century, Kodansha has relentlessly pursued quality and creativity, which allows us to continuously reimagine what could be. We see the world without restrictions. A place of diverse passions, profound perspectives and limitless potential. Filled with curious minds and unexpected voices. And as we continue to shine a light on them, there is no telling how much further we can go.")
                .logo("default.png")
                .url("https://kodansha.us/")
                .socialNetworks(kodanshaSocialNetworks)
                .build();

        return List.of(paniniMx, kamite, distritoMangaMx, viz, kodansha);
    }

    public static PublisherDTO buildPublisherDTO() {
        return PublisherDTO
                .builder()
                .name("MangaLine México")
                .key("mangaline-mx")
                .url("https://mangaline.com.mx")
                .logo("default.png")
                .information("Mangaline México, editorial de fans para fans. Amamos la cultura japonesa y lo difundimos a través del manga")
                .socialNetworks(buildPSocialNetworkDTOs())
                .build();
    }

    public static List<SocialNetworkDTO> buildPSocialNetworkDTOs() {
        var x = SocialNetworkDTO
                .builder()
                .type(SocialNetworkType.X)
                .username("MangaLineMX")
                .url("https://twitter.com/MangaLineMX")
                .build();

        var facebook = SocialNetworkDTO
                .builder()
                .type(SocialNetworkType.FACEBOOK)
                .username("MangaLineMxOficial")
                .url("https://www.facebook.com/MangaLineMxOficial")
                .build();

        var instagram = SocialNetworkDTO
                .builder()
                .type(SocialNetworkType.INSTAGRAM)
                .username("MangaLineMxOficial")
                .url("https://www.facebook.com/MangaLineMxOficial")
                .build();

        var tiktok = SocialNetworkDTO
                .builder()
                .type(SocialNetworkType.TIKTOK)
                .username("MangaLineMxOficial")
                .url("https://www.facebook.com/MangaLineMxOficial")
                .build();

        return List.of(x, facebook, instagram, tiktok);
    }

    // Method to get json content from json file and parse as String to compare against actual result.
    @SneakyThrows
    public static String fromFile(String path) {
        return new String(new ClassPathResource(path).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
