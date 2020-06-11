package com.iu.s1.board.qna;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "qnaFile")
public class QnaFileVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fileNum;

	@Column
	private String fileName;
	@Column
	private String oriName;

	@ManyToOne
	@JoinColumn(name = "num")
	private QnaVO qnaVO;

}
