package xyz.krakenkat.collector.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.model.Issue;
import xyz.krakenkat.collector.domain.model.Publisher;
import xyz.krakenkat.collector.domain.model.Title;
import xyz.krakenkat.collector.dto.IssueDTO;
import xyz.krakenkat.collector.dto.PublisherDTO;
import xyz.krakenkat.collector.dto.TitleDTO;

@Service
@RequiredArgsConstructor
public class MapperService {

    private final ModelMapper modelMapper;

    public PublisherDTO toPublisherDTO(Publisher publisher) {
        return this.modelMapper.map(publisher, PublisherDTO.class);
    }

    public TitleDTO toTitleDTO(Title title) {
        return this.modelMapper.map(title, TitleDTO.class);
    }

    public IssueDTO toIssueDTO(Issue issue) {
        return this.modelMapper.map(issue, IssueDTO.class);
    }

}
