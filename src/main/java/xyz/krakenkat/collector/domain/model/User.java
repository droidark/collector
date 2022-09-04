package xyz.krakenkat.collector.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private String cover;
    private String aboutYou;
    private Date signUpDate;
    private String status;
    private String profile;

    @JsonIgnore
    private List<Like> likes;

    @JsonIgnore
    private List<Item> items;
}