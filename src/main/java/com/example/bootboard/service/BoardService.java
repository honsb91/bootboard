package com.example.bootboard.service;

import com.example.bootboard.dto.BoardDTO;
import com.example.bootboard.dto.PageRequestDTO;
import com.example.bootboard.dto.PageResultDTO;
import com.example.bootboard.entity.Board;
import com.example.bootboard.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    //목록처리 ↓
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    //조회처리
    BoardDTO get(Long bno);

    //삭제처리
    void removeWithReplies(Long bno);

    //수정처리
    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto){
        Member member = Member.builder().email(dto.getWriterEmail()).build();
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, Member member, Long replyCount){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) // Long으로 나오므로 int로 처리하도록
                .build();
        return boardDTO;
    }


}
