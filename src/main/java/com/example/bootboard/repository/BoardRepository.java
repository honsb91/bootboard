package com.example.bootboard.repository;

import com.example.bootboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //[@Query 어노테이션] -> JPA에서 사용자 정의 JPQL (Java Persistence Query Language) 쿼리를 정의할 때 사용
    //[@Query 어노테이션] -> 데이터베이스에서 특정 데이터를 가져오기 위해 사용됩니다.
    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
    //[select b, w] -> Board 엔티티 (b)와 그와 관련된 writer 엔티티 (w)를 선택합니다.
    //[from Board b] -> Board 엔티티를 "b라는 별칭"으로 지정합니다.
    //[left join b.writer w] -> Board 엔티티의 writer 속성과 왼쪽 조인(LEFT JOIN)을 수행하여 writer 엔티티를 w라는 별칭으로 지정합니다.
    //왼쪽 조인은 Board 엔티티가 존재하는 한 writer 엔티티가 없어도 결과에 포함됩니다.
    //[where b.bno =: b.bno]가 :bno (메서드 파라미터로 전달된 값)와 같은 조건을 만족하는 데이터를 필터링합니다.
    Object getBoardWithWriter(@Param("bno") Long bno);
    //[@Param("bno")] -> JPQL 쿼리에서 사용된 :bno 파라미터를 메서드 파라미터 bno와 매핑합니다. 이는 쿼리에 전달된 bno 값을 사용할 수 있게 합니다.
    //[Long bno] -> 메서드의 입력 파라미터로, 게시글 번호를 의미합니다.
    //[Object] -> 반환 타입으로, 쿼리 결과는 Board 엔티티와 그와 관련된 writer 엔티티가 포함된 객체로 반환됩니다.

    @Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno")
        List<Object[]> getBoardWithReply(@Param("bno") Long bno);
}
