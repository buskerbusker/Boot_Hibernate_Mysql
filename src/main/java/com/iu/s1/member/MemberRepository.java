package com.iu.s1.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//<변수타입, 들어갈 PK의 타입>
public interface MemberRepository extends JpaRepository<MemberVO, String> {

	// 쿼리문 대신 메서드가 제공
	// save(): insert
	// 메서드를 통해서 쿼리문이 자동으로 생성
	// 기본 제공 메서드
	// T = 타입
	// S save(T) : insert, update
	// <S extends T> S save(iterable<? extends T>) : 다중 insert, update
	// void deleteById(ID) : PK를 통한 삭제
	// void delete(T) : 주어진 entity를 삭제
	// void deleteAll(iterable<? extends T>) : 주어진 모든 Entity 삭제
	// void deleteAll() : 모든 Entity 삭제
	// List<T> findAll() : 모든 Entity 조회
	// Optional<T> findById(): PK로 단일 Entity selecet
	// long count() : Entity의 모든 갯수
	// boolean existsById(ID) : PK로 해당 엔티티의 존재 여부 확인
	// 사용자가 생성하는 메서드 : 쿼리 메서드
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence

	// public abstract ...
	// select * from member where id = ? and pw = ?
	public MemberVO findByIdAndPw(String id, String pw);

}
