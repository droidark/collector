package xyz.krakenkat.collector.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "publisher_social_networks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PublisherSocialNetwork extends SocialNetwork {

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
}
