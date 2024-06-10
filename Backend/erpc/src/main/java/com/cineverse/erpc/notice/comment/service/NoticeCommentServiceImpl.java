package com.cineverse.erpc.notice.comment.service;

import com.cineverse.erpc.notice.comment.aggregate.NoticeComment;
import com.cineverse.erpc.notice.comment.dto.NoticeCommentDTO;
import com.cineverse.erpc.notice.comment.repository.NoticeCommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticeCommentServiceImpl implements NoticeCommentService{

    private final ModelMapper modelMapper;
    private final NoticeCommentRepository noticeCommentRepository;

    @Autowired
    public NoticeCommentServiceImpl(ModelMapper modelMapper, NoticeCommentRepository noticeCommentRepository) {
        this.modelMapper = modelMapper;
        this.noticeCommentRepository = noticeCommentRepository;
    }

    @Override
    @Transactional
    public NoticeComment registNoticeComment(NoticeCommentDTO noticeComment) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        noticeComment.setCommentDate(registDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        NoticeComment newComment = modelMapper.map(noticeComment, NoticeComment.class);
        newComment = noticeCommentRepository.save(newComment);

        return newComment;
    }

    @Override
    public NoticeComment deleteNoticeComment(long noticeCommentId) throws UsernameNotFoundException {
        Optional<NoticeComment> optionalNoticeComment = noticeCommentRepository.findById(noticeCommentId);
        if (optionalNoticeComment.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 댓글입니다.");
        }

        NoticeComment noticeComment = optionalNoticeComment.get();

        Date date = new Date();
        SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = format.format(date);

        noticeComment.setCommentDeleteDate(deleteDate);

        return noticeCommentRepository.save(noticeComment);
    }

    @Override
    public List<NoticeComment> findNoticeCommentList() {
        List<NoticeComment> noticeCommentList =
                noticeCommentRepository.findByCommentDeleteDateIsNullOrderByNoticeCommentIdDesc();

        return noticeCommentList.stream().map(noticeComment -> modelMapper
                        .map(noticeComment, NoticeComment.class))
                        .collect(Collectors.toList());
    }
}
