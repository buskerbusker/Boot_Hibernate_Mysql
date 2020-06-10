package com.iu.s1.board.qna;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.iu.s1.board.BoardVO;

import lombok.Data;

//@Data
@Table(name = "qna")
@Entity
public class QnaVO extends BoardVO {

	@Column
	private int ref;
	@Column
	private int depth;
	@Column
	private int step;

}
