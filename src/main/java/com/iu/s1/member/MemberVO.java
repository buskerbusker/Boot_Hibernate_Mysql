package com.iu.s1.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "member")
@DynamicUpdate
public class MemberVO {

	@Id // pk
	@NotEmpty
	private String id;

	@Column // 일반 컬럼명
	@NotEmpty
	@Size(max = 10, min = 4)
	private String pw;

	@Transient // 테이블에서 제외
	private String pwCheck;

	@Column
	@NotEmpty
	private String name;

	@Column
	@NotEmpty
	@Email
	private String email;

	@Column
	@NotEmpty
	private String phone;

	@Column
	@NotNull
	private Integer age;

	// mappedBy="Join하는 entity에 선언된 자기 자신의 entity 변수명"
	@OneToOne(mappedBy = "memberVO", cascade = CascadeType.ALL)
	private MemberFileVO memberFileVO;

}
