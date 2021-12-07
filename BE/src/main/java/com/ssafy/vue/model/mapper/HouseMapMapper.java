package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.ZzimDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	
	List<HouseInfoDto> getGugunList(String gugunCode) throws SQLException;
	List<HouseInfoDto> getAptNameList(String aptName) throws SQLException;
	List<HouseInfoDto> getZzimList(String userid) throws SQLException;
	
	int addZzim(ZzimDto zzimDto) throws SQLException;
	List<ZzimDto> doubleCheck(ZzimDto zzimDto) throws SQLException;

	int deleteZzim(Map<String, String> map) throws SQLException;
	
	
	
}
