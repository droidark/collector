package xyz.krakenkat.collector.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @JoinColumn(name = "id_publisher")
    private Publisher publisher;

    @ManyToMany(mappedBy = "titles")
    private Set<Genre> genres;

    @OneToMany(mappedBy = "title")
    private Set<TitleAuthorRole> titleAuthorRoles;
}
