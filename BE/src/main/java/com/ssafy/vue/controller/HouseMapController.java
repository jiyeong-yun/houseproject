package com.ssafy.vue.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.MemoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.ZzimDto;
import com.ssafy.vue.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	private HouseMapService haHouseMapService;

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@ApiOperation(value = "동 정보", notes = "동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@ApiOperation(value = "아파트 정보", notes = "아파트 이름을 반환한다.", response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}

//	@ApiOperation(value = "구군 아파트 정보", notes = "구군에 있는 아파트를 반환한다.", response = List.class)
//	@GetMapping("/gugunlist")
//	public ResponseEntity<List<HouseInfoDto>> gugunlist(@RequestParam ("gugunCode") String gugunCode) throws SQLException{
//		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getGugunList(gugunCode), HttpStatus.OK);
//	}

	@ApiOperation(value = "아파트 이름 검색 정보", notes = "아파트명 검색 결과에 따른 아파트들을 반환한다.", response = List.class)
	@GetMapping("/aptNamelist")
	public ResponseEntity<List<HouseInfoDto>> aptNamelist(@RequestParam("aptName") String aptName) throws SQLException {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptNameList(aptName), HttpStatus.OK);
	}

	@ApiOperation(value = "찜 검색 정보", notes = "찜 검색 결과에 따른 아파트들을 반환한다.", response = List.class)
	@GetMapping("/zzim/{userid}")
	public ResponseEntity<List<HouseInfoDto>> zzimlist(
			@PathVariable("userid") @ApiParam(value = "얻어올 찜.", required = true) String userid) throws SQLException {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptZzimList(userid), HttpStatus.OK);
	}

	@ApiOperation(value = "찜 등록", notes = "찜 아파트들을 등록한다.", response = String.class)
	@PostMapping("/zzim")
	public ResponseEntity<String> addzzim(@RequestBody @ApiParam(value = "게시글 정보.", required = true) ZzimDto zzimDto)
			throws SQLException {
		// return new
		// ResponseEntity<List<HouseInfoDto>>(haHouseMapService.addZzim(userid,
		// aptCode), HttpStatus.OK);
		logger.info("넘어온값:" + zzimDto.getUserId() + " " + zzimDto.getAptCode());
		if (haHouseMapService.addZzim(zzimDto)) {

			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "찜 삭제", notes = "찜목록에서 parameter에 따라 아파트를 삭제한다.", response = String.class)
	@DeleteMapping("/zzim")
	public ResponseEntity<String> deleteZzim(
			@RequestParam Map<String,String> map) throws Exception {
		logger.info("map: {}",map);
		if (haHouseMapService.deleteZzim(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
