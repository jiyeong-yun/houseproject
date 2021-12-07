package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.ZzimDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}
	
//	@Override
//	public List<HouseInfoDto> getGugunList(String gugunCode) throws SQLException {
//		return sqlSession.getMapper(HouseMapMapper.class).getGugunList(gugunCode);
//	}

	@Override
	public List<HouseInfoDto> getAptNameList(String aptName) throws SQLException {
		System.out.println(aptName);
		return sqlSession.getMapper(HouseMapMapper.class).getAptNameList(aptName);
	}

	@Override
	public List<HouseInfoDto> getAptZzimList(String userid) throws SQLException {
		
		return sqlSession.getMapper(HouseMapMapper.class).getZzimList(userid);
	}


	@Override
    public boolean addZzim(ZzimDto zzimDto) throws SQLException {
        if(sqlSession.getMapper(HouseMapMapper.class).doubleCheck(zzimDto).size()!=0)return false;
        return sqlSession.getMapper(HouseMapMapper.class).addZzim(zzimDto) == 1;
    }

	@Override
	public boolean deleteZzim(Map<String, String> map) throws SQLException {
		return sqlSession.getMapper(HouseMapMapper.class).deleteZzim(map) > 0;
	}


	

}
