package net.comicorp.collector.util;

import net.comicorp.collector.domain.model.Publisher;
import net.comicorp.collector.domain.model.Title;
import net.comicorp.collector.dto.TitleDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TitleUtilities {

    public static Title buildTitle(Publisher publisher) {
        return Title.builder()
                .publisher(publisher)
                .name("Future Innovations Weekly")
                .key("future_innovations_weekly")
                .cover("https://examplepublisher.com/covers/future_innovations_weekly.png")
                .demography("Technology Enthusiasts")
                .format("Digital")
                .frequency("Weekly")
                .releaseDate(new Date())  // January 1, 2024
                .status("Active")
                .type("Magazine")
                .totalIssues(5)
                .build();
    }

    public static List<Title> buildTitleList() {
        List<Title> titles = new ArrayList<>();

        titles.add(Title.builder()
                .name("Future Tech Monthly")
                .key("future_tech_monthly")
                .cover("https://techworld.com/covers/future_tech_monthly.png")
                .demography("Technology Enthusiasts")
                .format("Digital")
                .frequency("Monthly")
                .releaseDate(new Date(2024 - 1900, 0, 1))  // January 1, 2024
                .status("Active")
                .type("Magazine")
                .totalIssues(50)
                .build());

        titles.add(Title.builder()
                .name("Gadget Weekly")
                .key("gadget_weekly")
                .cover("https://techworld.com/covers/gadget_weekly.png")
                .demography("Tech Gadget Fans")
                .format("Digital")
                .frequency("Weekly")
                .releaseDate(new Date(2023 - 1900, 0, 1))  // January 1, 2023
                .status("Active")
                .type("Magazine")
                .totalIssues(200)
                .build());

        titles.add(Title.builder()
                .name("AI Innovations")
                .key("ai_innovations")
                .cover("https://techworld.com/covers/ai_innovations.png")
                .demography("AI Researchers")
                .format("Digital")
                .frequency("Quarterly")
                .releaseDate(new Date(2022 - 1900, 5, 1))  // June 1, 2022
                .status("Active")
                .type("Journal")
                .totalIssues(10)
                .build());

        titles.add(Title.builder()
                .name("Future Trends")
                .key("future_trends")
                .cover("https://techworld.com/covers/future_trends.png")
                .demography("Trend Enthusiasts")
                .format("Print & Digital")
                .frequency("Monthly")
                .releaseDate(new Date(2020 - 1900, 0, 1))  // January 1, 2020
                .status("Active")
                .type("Magazine")
                .totalIssues(48)
                .build());

        titles.add(Title.builder()
                .name("TechWorld Daily")
                .key("techworld_daily")
                .cover("https://techworld.com/covers/techworld_daily.png")
                .demography("General Audience")
                .format("Digital")
                .frequency("Daily")
                .releaseDate(new Date(2021 - 1900, 0, 1))  // January 1, 2021
                .status("Active")
                .type("Newsletter")
                .totalIssues(1000)
                .build());

        return titles;
    }

    // SERVICE LAYER

    public static List<TitleDTO> buildTitleDTOList() {
        List<TitleDTO> titles = new ArrayList<>();

        // Titles for TechWorld
        titles.add(TitleDTO.builder()
                .name("Future Tech Monthly")
                .key("future-tech-monthly")
                .cover("https://techworld.com/covers/future_tech_monthly.png")
                .demography("Technology Enthusiasts")
                .format("Digital")
                .frequency("Monthly")
                .releaseDate(new Date(2024 - 1900, 0, 1))  // January 1, 2024
                .status("Active")
                .type("Magazine")
                .totalIssues(50)
                .build());

        titles.add(TitleDTO.builder()
                .name("Gadget Weekly")
                .key("gadget_weekly")
                .cover("https://techworld.com/covers/gadget_weekly.png")
                .demography("Tech Gadget Fans")
                .format("Digital")
                .frequency("Weekly")
                .releaseDate(new Date(2023 - 1900, 0, 1))  // January 1, 2023
                .status("Active")
                .type("Magazine")
                .totalIssues(200)
                .build());

        titles.add(TitleDTO.builder()
                .name("AI Innovations")
                .key("ai_innovations")
                .cover("https://techworld.com/covers/ai_innovations.png")
                .demography("AI Researchers")
                .format("Digital")
                .frequency("Quarterly")
                .releaseDate(new Date(2022 - 1900, 5, 1))  // June 1, 2022
                .status("Active")
                .type("Journal")
                .totalIssues(10)
                .build());

        titles.add(TitleDTO.builder()
                .name("Future Trends")
                .key("future_trends")
                .cover("https://techworld.com/covers/future_trends.png")
                .demography("Trend Enthusiasts")
                .format("Print & Digital")
                .frequency("Monthly")
                .releaseDate(new Date(2020 - 1900, 0, 1))  // January 1, 2020
                .status("Active")
                .type("Magazine")
                .totalIssues(48)
                .build());

        titles.add(TitleDTO.builder()
                .name("TechWorld Daily")
                .key("techworld_daily")
                .cover("https://techworld.com/covers/techworld_daily.png")
                .demography("General Audience")
                .format("Digital")
                .frequency("Daily")
                .releaseDate(new Date(2021 - 1900, 0, 1))  // January 1, 2021
                .status("Active")
                .type("Newsletter")
                .totalIssues(1000)
                .build());

        // Repeat similar entries for FoodiesDaily, Travelista, SportsArena, and EcoLife

        return titles;
    }

    private TitleUtilities() {}
}
