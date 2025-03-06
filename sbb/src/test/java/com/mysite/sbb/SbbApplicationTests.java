package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	
//	@Test
//	void testJpaSave() {
//		Question q1 = new Question();
//		q1.setSubject("sbb가 무엇인가요?");
//		q1.setContent("sbb에 대해서 알고 싶습니다.");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		
//		Question q2 = new Question();
//		q2.setSubject("스프링 부트 모델 질문입니다.");
//		q2.setContent("id는 자동으로 생성되나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
//		@Test
//		void testJpaFindAll() {
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(2, all.size());
//		Question q = all.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());
		
//		@Test
//		void testJpaFindById() {
//		
//		Optional<Question> oq = this.questionRepository.findById(1);
//		if(oq.isPresent()) {
//			Question q = oq.get();
//			assertEquals("sbb가 무엇인가요?", q.getSubject());
//		}
		
	
	
//		@Test
//		void testJpaFindBySubject() {
//		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//		assertEquals(1, q.getId());			
//		}
	
	
//	
//		@Test
//		void TestJpaAnd() {
//			Question q= this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
//			assertEquals(19, q.getId());
//		}
	
	
//	질문에 특정 문자열을 포함한것 찾기
//		@Test
//		void TestJpaLike() {
//		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
//		Question q = qList.get(0);
//		assertEquals("sbb가 무엇인가요?",q.getSubject() );
//		}
	
	
//	질문 수정하기
//	@Test
//	void testJpaEdit() {
//		Optional<Question> oq = this.questionRepository.findById(19);
//		assertTrue(oq.isPresent());
		
//		assertTrue 는 괄호 안의 값이 true or false 인지 테스트. false 라면 리턴하여 테스트 종료
	
//		Question q = oq.get();
//		q.setSubject("수정된 제목");
//		this.questionRepository.save(q);
//	}
	
	
//	질문 삭제하기
//	@Test
//	void testJpaDelete() {
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> oq = this.questionRepository.findById(19);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		this.questionRepository.delete(q);
//		assertEquals(1, this.questionRepository.count());
//	}
	
	
//	답변 데이터 저장하기
//	@Test
//	void TestJpaAnswerSave() {
//		Optional<Question> oq = this.questionRepository.findById(20);
//		assertTrue(oq.isPresent());
//		Question q= oq.get();
//		
//		Answer a = new Answer();
//		a.setContent("네 자동으로 생성됩니다.");
//		a.setQuestion(q);
//		a.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(a);
//		
//	}
	
	
//	답변데이터 조회하기(아이디로)
//	@Test
//	void testJpaAnswerFindById() {
//		Optional<Answer> oa = this.answerRepository.findById(1);
//		assertTrue(oa.isPresent());
//		Answer a = oa.get();
//		assertEquals(20, a.getQuestion().getId());
//		
//	}
	
//	질문 데이터에서 답변 데이터 찾기
//	이렇게 해버리면 (Transactional을 안쓰면)findById 메서드를 통해 Question 객체를 조회하고 DB 연결이 끊어진다. 그래서 테스트 코드를 수행할때는 transactional 을 쓴다.
	@Transactional
	@Test
	void testJpaFindAnswersToQuestions() {
		Optional<Question> oq = this.questionRepository.findById(20);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
		
	}
	
}
