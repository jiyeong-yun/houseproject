package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public void modify(MemberDto memberDto)throws SQLException;
	public void join(MemberDto memberDto)throws SQLException;
	public void cancel(String userid)throws SQLException;
	
	List<MemberDto> listMember() throws Exception;
	
}
