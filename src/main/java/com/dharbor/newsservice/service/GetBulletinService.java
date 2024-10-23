package com.dharbor.newsservice.service;

import com.dharbor.newsservice.api.response.BulletinListResponse;
import com.dharbor.newsservice.model.domain.Attachment;
import com.dharbor.newsservice.model.domain.Bulletin;
import com.dharbor.newsservice.model.repository.BulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author Nicolas
 */
@Service
@RequiredArgsConstructor
public class GetBulletinService {

    private final BulletinRepository bulletinRepository;

    private BulletinListResponse mapToBulletinListResponse(Bulletin bulletin) {
        BulletinListResponse response = new BulletinListResponse();
        response.setId(bulletin.getId());
        response.setAccountId(bulletin.getAccountId());
        response.setSenderUserId(bulletin.getSenderUserId());
        response.setSenderUsername(bulletin.getSenderUsername());
        response.setBody(bulletin.getBody());
        response.setCreatedDate(bulletin.getCreatedDate());
        response.setCommentsCounter(bulletin.getCommentsCounter());
        if (bulletin.getAttachments() != null) {
            response.setFileIds(bulletin.getAttachments().stream()
                    .map(Attachment::getFieldId)
                    .collect(Collectors.toList()));
        }
        return response;
    }

    public Page<BulletinListResponse> listAllBulletinsSortedByDate(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdDate")
        );
        return bulletinRepository.findAll(sortedPageable)
                .map(this::mapToBulletinListResponse);
    }
}
