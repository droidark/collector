package net.comicorp.collector.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "titles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lookup_key")
    private String key;

    @Column(name = "cover")
    private String cover;

    @Column(name = "demography")
    private String demography;

    @Column(name = "format")
    private String format;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "total_issues")
    private Integer totalIssues;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany(mappedBy = "titles")
    private Set<Genre> genres;

    @OneToMany(mappedBy = "title")
    private Set<TitleAuthorRole> titleAuthorRoles;

    @JsonManagedReference
    @JsonIgnoreProperties("title")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "title", cascade = CascadeType.ALL)
    private Set<Issue> issues;
}
