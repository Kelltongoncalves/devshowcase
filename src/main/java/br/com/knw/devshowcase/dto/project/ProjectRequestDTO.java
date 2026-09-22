package br.com.knw.devshowcase.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public class ProjectRequestDTO {
    @NotBlank(message="Título é obrigatório") private String title;
    @NotBlank(message="Descrição é obrigatória") private String description;
    @NotBlank(message="URL é obrigatória")
    @Pattern(regexp="^(https?://).+", message="URL inválida") private String url;
    @NotNull(message="Profile é obrigatório") private Long profileId;
    private List<Long> technologyIds;

    public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public String getUrl(){return url;} public void setUrl(String url){this.url=url;}
    public Long getProfileId(){return profileId;} public void setProfileId(Long profileId){this.profileId=profileId;}
    public List<Long> getTechnologyIds(){return technologyIds;} public void setTechnologyIds(List<Long> technologyIds){this.technologyIds=technologyIds;}
}
