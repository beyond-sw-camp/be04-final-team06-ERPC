package com.cineverse.erpc.notice.board.service;

import com.cineverse.erpc.file.service.FileUploadService;
import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.cineverse.erpc.notice.board.dto.NoticeBoardDTO;
import com.cineverse.erpc.notice.board.repository.NoticeBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

    private final ModelMapper modelMapper;
    private final NoticeBoardRepository noticeBoardRepository;
    private final FileUploadService fileUploadService;

    @Autowired
    public NoticeBoardServiceImpl(ModelMapper modelMapper,
                                  NoticeBoardRepository noticeBoardRepository,
                                  FileUploadService fileUploadService) {
        this.modelMapper = modelMapper;
        this.noticeBoardRepository = noticeBoardRepository;
        this.fileUploadService = fileUploadService;
    }

    @Override
    @Transactional
    public NoticeBoard registNotice(NoticeBoardDTO noticeDTO, MultipartFile[] files) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        noticeDTO.setNoticeDate(registDate);

//        String employeeCode = noticeDTO.getEmployee().getEmployeeCode();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        NoticeBoard newNotice = modelMapper.map(noticeDTO, NoticeBoard.class);
        newNotice = noticeBoardRepository.save(newNotice);

        for (MultipartFile file : files) {
            if(!file.isEmpty()) {
                String url = fileUploadService.saveNoticeFile(file, newNotice);
            }
        }

        return newNotice;
    }

    @Override
    @Transactional
    public NoticeBoard modifyNotice(long noticeId, NoticeBoardDTO notice, MultipartFile[] files) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(noticeId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 공지사항 입니다."));

        if (notice.getNoticeTitle() != null) {
            noticeBoard.setNoticeTitle(notice.getNoticeTitle());
        }
        if (notice.getNoticeContent() != null) {
            noticeBoard.setNoticeContent(notice.getNoticeContent());
        }

        if (files != null && files.length > 0) {
            fileUploadService.deleteFilesByNotice(noticeBoard);

            for (MultipartFile file : files) {
                fileUploadService.saveNoticeFile(file, noticeBoard);
            }
        }

        noticeBoardRepository.save(noticeBoard);

        return noticeBoard;
    }

    @Override
    public NoticeBoard deleteNotice(long noticeId) throws UsernameNotFoundException {

        Optional<NoticeBoard> optionalNoticeBoard = noticeBoardRepository.findById(noticeId);

        if (optionalNoticeBoard.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 공지사항입니다.");
        }

        NoticeBoard noticeBoard = optionalNoticeBoard.get();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = format.format(date);
        noticeBoard.setNoticeDeleteDate(deleteDate);

        noticeBoardRepository.save(noticeBoard);

        return noticeBoard;
    }

    @Override
    public List<NoticeBoard> findNoticeList() {
        List<NoticeBoard> noticeBoardList = noticeBoardRepository.findByNoticeDeleteDateIsNullOrderByNoticeIdDesc();

        return noticeBoardList.stream().map(notice -> modelMapper
                        .map(notice, NoticeBoard.class))
                        .collect(Collectors.toList());
    }

    @Override
    public NoticeBoardDTO findNoticeById(long noticeId) {
        NoticeBoard noticeBoard=noticeBoardRepository.findById(noticeId)
                .orElseThrow(EntityNotFoundException::new);

        noticeBoardRepository.save(noticeBoard);
        NoticeBoardDTO noticeBoardDTO = modelMapper.map(noticeBoard, NoticeBoardDTO.class);

        return noticeBoardDTO;
    }
}
