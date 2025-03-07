package com.mysite.sbb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller

public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;

	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id,
			@Valid AnswerForm answerForm, BindingResult bindingResult) {
		Question question = this.questionService.getQuestion(id);
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		this.answerService.create(question, answerForm.getContent());
		return String.format("redirect:/question/detail/%s", id);
	}
}
	
/*	어노테이션	설명
	@PathVariable ==> URL 경로의 일부를 메서드 파라미터로 받음
	("id") ==> URL에서 {id} 자리 값이 여기로 들어옴 , Integer id 실제로 이 값은 정수로 변환되어 id에 저장
*/
	
/*
 *  @RequestParam(value="content") String content
	요청 파라미터 중에 "content"라는 이름의 값을 받아서 content라는 변수에 저장하는 거예요.
	폼에서 보낸 데이터 중에 name="content"인 input의 값이 여기로 들어옵니다.
	예를 들어, 답변 내용 입력란의 name이 content라면, 거기에 입력한 값이 content 변수에 들어가는 거죠.
	
	@RequestParam	요청 파라미터 값을 메서드 파라미터로 받음
	value="content"	요청 파라미터 이름이 content인 값이 매핑됨
	String content	해당 값은 문자열로 저장됨
 * 
 */
	
/*
 	* 순서	요청 예시	설명
	1.사용자 입력 후 "답변 작성" 버튼 클릭 => 답변 내용과 함께 POST 요청 전송
	2.요청 URL => /answer/create/5 (5번 질문에 대한 답변)
	3.요청 데이터 => content=이게 답변입니다
	4.컨트롤러 메서드 실행 => createAnswer(모델, 5, "이게 답변입니다")
	5.내부 처리 => 5번 질문 가져와서 (아직 저장은 안함)
	6.리다이렉트 => /question/detail/5로 이동
 */
	
	/*키워드	설명
	@PostMapping	POST 요청 처리
	@PathVariable	URL 경로 값 매핑
	@RequestParam	요청 파라미터 값 매핑
	Model	뷰로 데이터를 넘기는 객체 (화면에 뿌릴 데이터 저장)
	*/
	
/*
 	📌 참고 - GET vs POST
		요청 방식	      특징	          언제 사용?
		GET =>	URL에 데이터 포함	      조회할 때 (ex. 검색)
		POST => 요청 바디에 데이터 포함	  저장/수정할 때 (ex. 회원가입, 댓글 작성)
 */

