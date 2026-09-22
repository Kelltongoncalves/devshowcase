package br.com.knw.devshowcase.controller;
import br.com.knw.devshowcase.dto.project.*;
import br.com.knw.devshowcase.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService service;
    public ProjectController(ProjectService service){this.service=service;}

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDTO create(@Valid @RequestBody ProjectRequestDTO dto){return service.create(dto);}

    @GetMapping
    public List<ProjectResponseDTO> findAll(){return service.findAll();}
}
