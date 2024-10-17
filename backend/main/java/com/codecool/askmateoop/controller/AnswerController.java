package com.codecool.askmateoop.controller;

import com.codecool.askmateoop.controller.dto.answerDTO.AnswerDTO;
import com.codecool.askmateoop.controller.dto.answerDTO.NewAnswerDTO;
import com.codecool.askmateoop.controller.dto.answerDTO.UpdateAnswerDTO;
import com.codecool.askmateoop.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/all")
    public List<AnswerDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/{id}")
    public AnswerDTO getAnswerById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }

    @PostMapping("/")
    public int addAnswer(@RequestBody NewAnswerDTO newAnswerDTO) {
        return answerService.addAnswer(newAnswerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable int id) {
        answerService.deleteAnswer(id);
    }

    @PatchMapping("/{id}")
    public void updateAnswer(@PathVariable int id, @RequestBody UpdateAnswerDTO answerDTO) {
        answerService.updateAnswer(id, answerDTO);
    }

    @GetMapping("/question/{id}")
    public List<AnswerDTO> getQuestionAnswers(@PathVariable int id) {
       return answerService.getAnswerByQuestionId(id);
    }

}
