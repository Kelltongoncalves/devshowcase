package br.com.knw.devshowcase.service;

import br.com.knw.devshowcase.dto.project.*;
import br.com.knw.devshowcase.model.*;
import br.com.knw.devshowcase.repository.*;
import org.springframework.stereotype.Service;
import br.com.knw.devshowcase.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyService technologyService;

    public ProjectService(ProjectRepository projectRepository, ProfileRepository profileRepository, TechnologyService technologyService){
        this.projectRepository=projectRepository; this.profileRepository=profileRepository; this.technologyService=technologyService;
    }

    public ProjectResponseDTO create(ProjectRequestDTO dto){
        Profile profile=profileRepository.findById(dto.getProfileId())
            .orElseThrow(()->new ResourceNotFoundException("Perfil não encontrado"));
        Project project=new Project();
        project.setTitle(dto.getTitle()); project.setDescription(dto.getDescription());
        project.setUrl(dto.getUrl()); project.setProfile(profile);
        List<Technology> technologies=new ArrayList<>();
        if(dto.getTechnologyIds()!=null){
            for(Long id:dto.getTechnologyIds()) technologies.add(technologyService.findEntityById(id));
        }
        project.setTechnologies(technologies);
        return toResponse(projectRepository.save(project));
    }
    public ProjectResponseDTO upvote(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));
        project.setUpvotes(project.getUpvotes() + 1);
        return toResponse(projectRepository.save(project));
    }

    public Page<ProjectResponseDTO> findAll(String technology, Pageable pageable){
        Page<Project> page = (technology == null || technology.isBlank())
                ? projectRepository.findAll(pageable)
                : projectRepository.findDistinctByTechnologies_NameIgnoreCase(technology, pageable);
        return page.map(this::toResponse);
    }
    public ProjectResponseDTO toResponsePublic(Project p){ return toResponse(p); }

    private ProjectResponseDTO toResponse(Project p){
        List<Long> ids=p.getTechnologies().stream().map(Technology::getId).toList();
        return new ProjectResponseDTO(p.getId(),p.getTitle(),p.getDescription(),p.getUrl(),p.getProfile().getId(),ids,p.getUpvotes(),p.getAverageRating());
    }
}
