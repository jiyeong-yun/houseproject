package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.MemoDto;

public interface BoardService {
	public boolean writeArticle(BoardDto boardDto) throws Exception;
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
//	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;
	public int getTotalCount(BoardParameterDto boardParameterDto) throws SQLException;
	
	public BoardDto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(BoardDto boardDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
	public List<MemoDto> listMemo(int articleno) throws Exception;
	public boolean writeMemo(MemoDto memoDto) throws Exception;
	
	public boolean deleteMemo(int memono)throws Exception;
}
