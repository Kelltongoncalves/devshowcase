package br.com.knw.devshowcase.service;

import br.com.knw.devshowcase.dto.project.*;
import br.com.knw.devshowcase.model.*;
import br.com.knw.devshowcase.repository.*;
import org.springframework.stereotype.Service;
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
            .orElseThrow(()->new RuntimeException("Perfil não encontrado"));
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
    public List<ProjectResponseDTO> findAll(){
        return projectRepository.findAll().stream().map(this::toResponse).toList();
    }
    private ProjectResponseDTO toResponse(Project p){
        List<Long> ids=p.getTechnologies().stream().map(Technology::getId).toList();
        return new ProjectResponseDTO(p.getId(),p.getTitle(),p.getDescription(),p.getUrl(),p.getProfile().getId(),ids);
    }
}
