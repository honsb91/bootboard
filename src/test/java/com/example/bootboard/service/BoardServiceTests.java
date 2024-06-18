package com.example.bootboard.service;

import com.example.bootboard.dto.BoardDTO;
import com.example.bootboard.dto.PageRequestDTO;
import com.example.bootboard.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService service;

    @Test
    public void testRegister(){
        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test...")
                .writerEmail("user55@aaa.com") // 현재 데이터베이스에 존재하는 회원 이메일
                .build();

        Long bno = service.register(dto);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = service.getList(pageRequestDTO);

        for (BoardDTO boardDTO : result.getDtoList()){
            System.out.println(boardDTO);
        }
    }

    //조회 TEST
    @Test
    public void testGet(){
        Long bno = 100L;
        BoardDTO boardDTO = service.get(bno);
        System.out.println(boardDTO);
    }

    // 삭제 TEST
    @Test
    public void testRemove(){
        Long bno = 26L;
        service.removeWithReplies(bno);
    }

    // 수정 TEST
    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경합니다.")
                .content("내용 변경합니다.")
                .build();

        service.modify(boardDTO);
    }
}
