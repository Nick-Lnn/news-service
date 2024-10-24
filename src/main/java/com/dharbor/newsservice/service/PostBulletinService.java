package com.dharbor.newsservice.service;

import com.dharbor.newsservice.api.request.CreateBulletinRequest;
import com.dharbor.newsservice.api.response.CreateBulletinResponse;
import com.dharbor.newsservice.model.domain.Attachment;
import com.dharbor.newsservice.model.domain.Bulletin;
import com.dharbor.newsservice.model.repository.BulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author Nicolas
 */
@Service
@RequiredArgsConstructor
public class PostBulletinService {

    private final BulletinRepository bulletinRepository;

    public CreateBulletinResponse createBulletin(CreateBulletinRequest request, Long accountId, Long userId) {
        Bulletin bulletin = new Bulletin();
        bulletin.setAccountId(accountId);
        bulletin.setSenderUserId(userId);
        bulletin.setSenderUsername(request.getSenderUsername());
        bulletin.setBody(request.getContent());

        if (request.getFileIds() != null && !request.getFileIds().isEmpty()) {
            bulletin.setAttachments(request.getFileIds().stream()
                    .map(fileId -> {
                        Attachment attachment = new Attachment();
                        attachment.setAccountId(accountId);
                        attachment.setFieldId(fileId);
                        attachment.setBulletin(bulletin);
                        return attachment;
                    })
                    .collect(Collectors.toList()));
        }

        Bulletin savedBulletin = bulletinRepository.save(bulletin);

        return mapToResponse(savedBulletin);
    }

    private CreateBulletinResponse mapToResponse(Bulletin bulletin) {
        CreateBulletinResponse response = new CreateBulletinResponse();
        response.setId(bulletin.getId());
        response.setAccountId(bulletin.getAccountId());
        response.setSenderUserId(bulletin.getSenderUserId());
        response.setSenderUsername(bulletin.getSenderUsername());
        response.setBody(bulletin.getBody());
        response.setCreatedDate(bulletin.getCreatedDate());
        if (bulletin.getAttachments() != null) {
            response.setFileIds(bulletin.getAttachments().stream()
                    .map(Attachment::getFieldId)
                    .collect(Collectors.toList()));
        }
        return response;
    }
}
