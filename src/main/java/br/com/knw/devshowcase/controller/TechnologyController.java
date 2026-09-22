package br.com.knw.devshowcase.controller;
import br.com.knw.devshowcase.dto.technology.*;
import br.com.knw.devshowcase.service.TechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {
    private final TechnologyService service;
    public TechnologyController(TechnologyService service){this.service=service;}

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public TechnologyResponseDTO create(@Valid @RequestBody TechnologyRequestDTO dto){return service.create(dto);}

    @GetMapping
    public List<TechnologyResponseDTO> findAll(){return service.findAll();}
}
