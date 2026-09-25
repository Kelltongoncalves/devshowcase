package br.com.knw.devshowcase.service;

import br.com.knw.devshowcase.dto.technology.*;
import br.com.knw.devshowcase.model.Technology;
import br.com.knw.devshowcase.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import br.com.knw.devshowcase.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class TechnologyService {
    private final TechnologyRepository repository;
    public TechnologyService(TechnologyRepository repository){this.repository=repository;}

    public TechnologyResponseDTO create(TechnologyRequestDTO dto){
        Technology t=new Technology(); t.setName(dto.getName());
        return toResponse(repository.save(t));
    }
    public List<TechnologyResponseDTO> findAll(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }
    public Technology findEntityById(Long id){
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Tecnologia não encontrada"));
    }
    private TechnologyResponseDTO toResponse(Technology t){return new TechnologyResponseDTO(t.getId(),t.getName());}
}
