package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	@GetMapping("/list")

/*
 * 컨트롤러 역할 요약
✅ URL 요청 받기
✅ 서비스 호출해서 필요한 데이터 얻기
✅ 화면(템플릿)으로 데이터 넘겨주기
 */
	
	
	public String list(Model model) {
		
//		여기서 Model 이란 컨트롤러에서 뷰로 데이터를 넘겨주는 역할. 즉 화면에 보여줄 데이터를 담는 상자라고 생각.
//		List<Question> questionList = this.questionRepository.findAll();
		
		List<Question> questionList = this.questionService.getList();
		
		model.addAttribute("questionList", questionList	);
/*		"이름"으로 된 데이터를 "값"으로 저장해둬 라는 뜻.
 * 
 *      Model = 편지 봉투
		addAttribute() = 편지 봉투 안에 편지 넣는 작업
		뷰(템플릿) = 편지를 받아서 읽는 사람
*/
		return "question_list";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}

	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "question_form";
		}
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";
	}
}
