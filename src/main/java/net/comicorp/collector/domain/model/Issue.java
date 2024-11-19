package net.comicorp.collector.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "issues")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lookup_key")
    private String key;

    @Column(name = "number")
    private Float number;

    @Column(name = "cover")
    private String cover;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "printed_price")
    private Float printedPrice;

    @Column(name = "digital_price")
    private Float digitalPrice;

    @Column(name = "currency")
    private String currency;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "short_review")
    private String shortReview;

    @Column(name = "event")
    private String event;

    @Column(name = "story_arch")
    private String storyArch;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "barcode")
    private Long barcode;

    @Column(name = "edition")
    private Integer edition;

    @Column(name = "variant")
    private Boolean variant;

    @Column(name = "variant_of")
    private String variantOf;

    @Column(name = "likes_counter")
    private Integer likesCounter;

    @Column(name = "dislikes_counter")
    private Integer dislikesCounter;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;
}
