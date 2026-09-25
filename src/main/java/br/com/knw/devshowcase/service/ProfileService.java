package br.com.knw.devshowcase.service;

import br.com.knw.devshowcase.dto.profile.*;
import br.com.knw.devshowcase.model.Profile;
import br.com.knw.devshowcase.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import br.com.knw.devshowcase.exception.ResourceNotFoundException;

@Service
public class ProfileService {
    private final ProfileRepository repository;
    public ProfileService(ProfileRepository repository){this.repository=repository;}

    public ProfileResponseDTO create(ProfileRequestDTO dto){
        Profile p=new Profile(); p.setName(dto.getName()); p.setEmail(dto.getEmail()); p.setBio(dto.getBio());
        return toResponse(repository.save(p));
    }
    public ProfileResponseDTO findById(Long id){
        return repository.findById(id).map(this::toResponse)
            .orElseThrow(()->new ResourceNotFoundException("Perfil não encontrado"));
    }
    private ProfileResponseDTO toResponse(Profile p){
        return new ProfileResponseDTO(p.getId(),p.getName(),p.getEmail(),p.getBio());
    }
}
