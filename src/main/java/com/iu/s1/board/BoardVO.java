package com.iu.s1.board;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BoardVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;
	@Column
	private String title;
	@Column
	private String writer;
	@Column
	private String contents;
	@Column
	@CreationTimestamp
	// @UpdateTimestamp
	private Date regDate;
	@Column
	private Long hit;

}
