package xyz.krakenkat.collector.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeysDTO {
    private String publisher;
    private String title;
    private List<String> issues;
}
