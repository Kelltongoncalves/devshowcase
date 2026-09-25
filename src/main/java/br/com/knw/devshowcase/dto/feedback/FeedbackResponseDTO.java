package br.com.knw.devshowcase.dto.feedback;

public class FeedbackResponseDTO {
    private Long id;
    private Integer rating;
    private String comment;
    private Long projectId;
    private Double projectAverageRating;
    public FeedbackResponseDTO(Long id, Integer rating, String comment, Long projectId, Double projectAverageRating) {
        this.id=id; this.rating=rating; this.comment=comment; this.projectId=projectId; this.projectAverageRating=projectAverageRating;
    }
    public Long getId(){return id;}
    public Integer getRating(){return rating;}
    public String getComment(){return comment;}
    public Long getProjectId(){return projectId;}
    public Double getProjectAverageRating(){return projectAverageRating;}
}
