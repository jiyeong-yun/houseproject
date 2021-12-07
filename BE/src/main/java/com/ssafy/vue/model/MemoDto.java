package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemoDto : 댓글 정보", description = " 댓글의 상세 정보를 나타낸다.")
public class MemoDto {

	@ApiModelProperty(value = "메모 번호")
	private String memono;
	@ApiModelProperty(value = "작성자 아이디")
	private String userid;
	@ApiModelProperty(value = "글번호")
	private int articleno;
	@ApiModelProperty(value = "작성일")
	private String memotime;
	@ApiModelProperty(value = "코멘트")
	private String comment;
	public String getMemono() {
		return memono;
	}
	public void setMemono(String memono) {
		this.memono = memono;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getMemotime() {
		return memotime;
	}
	public void setMemotime(String memotime) {
		this.memotime = memotime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
