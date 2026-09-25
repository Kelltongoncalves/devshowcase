package br.com.knw.devshowcase.service;

import br.com.knw.devshowcase.dto.feedback.FeedbackRequestDTO;
import br.com.knw.devshowcase.dto.feedback.FeedbackResponseDTO;
import br.com.knw.devshowcase.model.Feedback;
import br.com.knw.devshowcase.model.Project;
import br.com.knw.devshowcase.repository.FeedbackRepository;
import br.com.knw.devshowcase.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ProjectRepository projectRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, ProjectRepository projectRepository) {
        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
    }

    public FeedbackResponseDTO create(Long projectId, FeedbackRequestDTO dto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new br.com.knw.devshowcase.exception.ResourceNotFoundException("Projeto não encontrado"));

        Feedback feedback = new Feedback();
        feedback.setRating(dto.getRating());
        feedback.setComment(dto.getComment());
        feedback.setProject(project);
        feedbackRepository.save(feedback);

        List<Feedback> feedbacks = feedbackRepository.findByProjectId(projectId);
        double average = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);
        project.setAverageRating(Math.round(average * 100.0) / 100.0);
        projectRepository.save(project);

        return new FeedbackResponseDTO(feedback.getId(), feedback.getRating(), feedback.getComment(),
                project.getId(), project.getAverageRating());
    }
}
