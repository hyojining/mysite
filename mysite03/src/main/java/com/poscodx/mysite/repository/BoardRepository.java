package com.poscodx.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

    @Autowired
    private SqlSession sqlSession;

    // 글쓰기
    public int insert(BoardVo boardVo) {
        return sqlSession.insert("board.insert", boardVo);
    }

    // page, keyword에 해당하는 list 찾기
    public List<BoardVo> findAllByPageAndKeword(String keyword, Integer page, Integer size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword", keyword);
        map.put("startIndex", (page - 1) * size);
        map.put("size", size);

        return sqlSession.selectList("board.findAllByPageAndKeword", map);
    }

    // 글 수정
    public int update(BoardVo boardVo) {
        return sqlSession.update("board.update", boardVo);
    }

    // 글 삭제 (본인의 글만 삭제 가능하기 때문에 userNo도 함께 사용)
    public int delete(Long no, Long userNo) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("no", no);
        map.put("userNo", userNo);

        return sqlSession.delete("board.delete", map);
    }

    // 글 보기 위한 해당 글 찾기
    public BoardVo findByNo(Long no) {
        return sqlSession.selectOne("board.findByNo", no);
    }

    // 글 수정하기 위한 해당 글 찾기 (본인의 글만 수정 가능하기 때문에 userNo도 함께 사용)
    public BoardVo findByNoAndUserNo(Long no, Long userNo) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("no", no);
        map.put("userNo", userNo);

        return sqlSession.selectOne("board.findByNoAndUserNo", map);
    }

    // 조회수 업데이트
    public int updateHit(Long no) {
        return sqlSession.update("board.updateHit", no);
    }

    // 답글 insert 전, orderNo 업데이트
    public int updateOrderNo(Integer groupNo, Integer orderNo) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("groupNo", groupNo);
        map.put("orderNo", orderNo);

        return sqlSession.update("board.updateOrederNo", map);
    }

    // 페이징을 위한 게시물 총 개수 구하기
    public int getTotalCount(String keyword) {
        return sqlSession.selectOne("board.totalCount", keyword);
    }
}