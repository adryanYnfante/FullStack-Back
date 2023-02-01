package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListPagedUseCase {
    private final QuestionRepository questionRepository;
    private final MapperUtils mapperUtils;

    public ListPagedUseCase(MapperUtils mapperUtils, QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.mapperUtils = mapperUtils;
    }

    public Flux<QuestionDTO> get(int page) {
        return questionRepository.findAllByIdNotNullOrderByIdAsc(PageRequest.of(page, 2))
                .map(mapperUtils.mapEntityToQuestion());
    }

}
