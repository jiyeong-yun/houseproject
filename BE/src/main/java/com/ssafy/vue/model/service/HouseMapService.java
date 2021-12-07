package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.ZzimDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
//	List<HouseInfoDto> getGugunList(String gugunCode) throws SQLException;
	List<HouseInfoDto> getAptNameList(String aptName) throws SQLException;
	List<HouseInfoDto> getAptZzimList(String userid) throws SQLException;
	
	boolean addZzim(ZzimDto zzimDto)throws SQLException;
	
	boolean deleteZzim(Map<String, String> map) throws SQLException;
	
}
