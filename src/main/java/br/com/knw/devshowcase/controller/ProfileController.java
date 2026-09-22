package br.com.knw.devshowcase.controller;
import br.com.knw.devshowcase.dto.profile.*;
import br.com.knw.devshowcase.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final ProfileService service;
    public ProfileController(ProfileService service){this.service=service;}

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponseDTO create(@Valid @RequestBody ProfileRequestDTO dto){return service.create(dto);}

    @GetMapping("/{id}")
    public ProfileResponseDTO findById(@PathVariable Long id){return service.findById(id);}
}
