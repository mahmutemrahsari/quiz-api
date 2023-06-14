package com.quizapi.quizapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("api/")
public class QuizController {
    Path jsonFilePath = Path.of("src/main/resources/Questions.json");
    String json = Files.readString(jsonFilePath, StandardCharsets.UTF_8);
    ObjectMapper mapper = new ObjectMapper();
    Quiz quiz = mapper.readValue(json, Quiz.class);
    // Access the questions
    List<Question> questions = quiz.getQuestions();
    public QuizController() throws IOException {
    }

    @GetMapping(value = "/getQuestion/{questionIndex}")
    public Question getQuestion(@PathVariable("questionIndex") int questionIndex) {
        for (Question question : questions) {
            System.out.println("Question: " + question.getQuestion());
            System.out.println("Answers: " + question.getAnswers());
            System.out.println("Image: " + question.getImage());
            System.out.println("Correct Index: " + question.getCorrectIndex());
            System.out.println();
        }
        return questions.get(questionIndex);
    }

    @GetMapping("/getAllQuestions")
    public List<Question> getAllQuestions() throws IOException {       
        return questions;
    }
}
