package xyz.krakenkat.collector.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.controller.TitleController;
import xyz.krakenkat.collector.dto.TitleDTO;
import xyz.krakenkat.collector.exception.NoContentException;
import xyz.krakenkat.collector.service.TitleService;

@RestController
@RequiredArgsConstructor
public class TitleControllerImpl implements TitleController {

    private final TitleService titleService;

    @Override
    public ResponseEntity<TitleDTO> retrieveTitleById(Long titleId) throws NoContentException {
        return ResponseEntity.ok(titleService.getTitleById(titleId));
    }
}
