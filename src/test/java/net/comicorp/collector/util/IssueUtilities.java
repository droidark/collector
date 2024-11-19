package net.comicorp.collector.util;

import net.comicorp.collector.dto.IssueDTO;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IssueUtilities {

    public static List<IssueDTO> buildIssueDTOList() {
        List<IssueDTO> issueDTOList = new ArrayList<>();

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #1")
                .key("1")
                .number(1.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-2202-7610-9")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #1 [Variant Cover]")
                .key("1")
                .number(1.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-2202-7610-9")
                .edition(1)
                .variant(Boolean.TRUE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #2")
                .key("2")
                .number(2.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-5590-7612-3")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #3")
                .key("3")
                .number(3.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-4987-4512-8")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #4")
                .key("4")
                .number(4.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-3647-1235-6")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #5")
                .key("5")
                .number(5.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-8886-2456-1")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #6")
                .key("6")
                .number(6.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-7763-3124-2")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #7")
                .key("7")
                .number(7.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-5550-8472-7")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #8")
                .key("8")
                .number(8.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-8901-1247-9")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #9")
                .key("9")
                .number(9.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-4356-9637-4")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        issueDTOList.add(IssueDTO
                .builder()
                .name("Future Tech Monthly #10")
                .key("10")
                .number(10.0)
                .cover("default-cover.png")
                .pages(100)
                .printedPrice(9.99)
                .digitalPrice(6.99)
                .currency("USD")
                .releaseDate(new Date())
                .shortReview("Demo short review")
                .isbn("978-1-9874-2238-5")
                .edition(1)
                .variant(Boolean.FALSE)
                .build());

        return issueDTOList;
    }

    public static List<IssueDTO> buildSingleIssueDTO(Boolean variant) {
        List<IssueDTO> issueDTOList = new ArrayList<>();

        if (variant) {

            issueDTOList.add(IssueDTO
                    .builder()
                    .name("Future Tech Monthly #1 [Variant Cover]")
                    .key("1")
                    .number(1.0)
                    .cover("default-cover.png")
                    .pages(100)
                    .printedPrice(9.99)
                    .digitalPrice(6.99)
                    .currency("USD")
                    .releaseDate(Date.from(LocalDate.of(2024, Month.NOVEMBER, 18).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .shortReview("Demo short review")
                    .isbn("978-1-2202-7610-9")
                    .edition(1)
                    .variant(Boolean.TRUE)
                    .build());
        } else {
            issueDTOList.add(IssueDTO
                    .builder()
                    .name("Future Tech Monthly #1")
                    .key("1")
                    .number(1.0)
                    .cover("default-cover.png")
                    .pages(100)
                    .printedPrice(9.99)
                    .digitalPrice(6.99)
                    .currency("USD")
                    .releaseDate(Date.from(LocalDate.of(2024, Month.NOVEMBER, 18).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .shortReview("Demo short review")
                    .isbn("978-1-2202-7610-9")
                    .edition(1)
                    .variant(Boolean.FALSE)
                    .build());
        }

        return issueDTOList;
    }

    private IssueUtilities() {}
}
