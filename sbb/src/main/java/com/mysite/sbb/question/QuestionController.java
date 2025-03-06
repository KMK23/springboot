package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionRepository questionRepository;
	
	@GetMapping("/question/list")

	
	public String list(Model model) {
//		여기서 Model 이란 컨트롤러에서 뷰로 데이터를 넘겨주는 역할. 즉 화면에 보여줄 데이터를 담는 상자라고 생각.
		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questionList", questionList	);
/*		"이름"으로 된 데이터를 "값"으로 저장해둬 라는 뜻.
 * 
 *      Model = 편지 봉투
		addAttribute() = 편지 봉투 안에 편지 넣는 작업
		뷰(템플릿) = 편지를 받아서 읽는 사람
*/
		return "question_list";
	}
	
}
