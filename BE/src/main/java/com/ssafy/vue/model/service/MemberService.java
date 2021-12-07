package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	
	
	public void modify(MemberDto memberDto)throws Exception;
	public void join(MemberDto memberDto) throws Exception;
	public void cancel(String userid)throws Exception; 
	
	public List<MemberDto> listMember() throws Exception;
	
}
