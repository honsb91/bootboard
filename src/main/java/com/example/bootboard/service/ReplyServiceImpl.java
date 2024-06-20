package com.example.bootboard.service;

import com.example.bootboard.dto.ReplyDTO;
import com.example.bootboard.entity.Board;
import com.example.bootboard.entity.Reply;
import com.example.bootboard.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;


    //댓글등록 처리
    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);
        return reply.getRno();
    }

    //댓글목록 처리
    @Override
    public List<ReplyDTO> getList(Long bno) {

        List<Reply> result = replyRepository
                .getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());
        return result.stream().map(reply-> entityToDTO(reply)).collect(Collectors.toList());
    }

    //댓글수정 처리
    @Override
    public void modify(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);
    }

    //댓글삭제 처리
    @Override
    public void remove(Long rno) {

        replyRepository.deleteById(rno);
    }
}
