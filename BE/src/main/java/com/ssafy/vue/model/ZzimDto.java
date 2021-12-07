package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseInfoDto : 아파트 찜 정보", description = "아파트의 상세 정보를 나타낸다.")
public class ZzimDto {
	@ApiModelProperty(value = "아파트일련번호")
	private int aptCode;
	@ApiModelProperty(value = "유저아이디")
	private String userId;
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
