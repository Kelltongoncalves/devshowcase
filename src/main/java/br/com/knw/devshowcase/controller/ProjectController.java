package br.com.knw.devshowcase.controller;

import br.com.knw.devshowcase.dto.project.*;
import br.com.knw.devshowcase.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService service;
    public ProjectController(ProjectService service){this.service=service;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDTO create(@Valid @RequestBody ProjectRequestDTO dto){return service.create(dto);}

    @GetMapping
    public Page<ProjectResponseDTO> findAll(
            @RequestParam(required=false) String technology,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="10") int size){
        return service.findAll(technology, PageRequest.of(page, size));
    }

    @PutMapping("/{id}/upvote")
    public ProjectResponseDTO upvote(@PathVariable Long id){
        return service.upvote(id);
    }
}
