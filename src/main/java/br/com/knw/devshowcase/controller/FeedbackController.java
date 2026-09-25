package br.com.knw.devshowcase.controller;

import br.com.knw.devshowcase.dto.feedback.FeedbackRequestDTO;
import br.com.knw.devshowcase.dto.feedback.FeedbackResponseDTO;
import br.com.knw.devshowcase.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects/{id}/feedbacks")
public class FeedbackController {
    private final FeedbackService service;
    public FeedbackController(FeedbackService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponseDTO create(@PathVariable Long id, @Valid @RequestBody FeedbackRequestDTO dto) {
        return service.create(id, dto);
    }
}
