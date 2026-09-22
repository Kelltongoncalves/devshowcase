package br.com.knw.devshowcase.dto.technology;
import jakarta.validation.constraints.NotBlank;
public class TechnologyRequestDTO {
    @NotBlank(message = "Nome da tecnologia é obrigatório") private String name;
    public String getName(){return name;} public void setName(String name){this.name=name;}
}
