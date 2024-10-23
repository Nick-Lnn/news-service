package com.dharbor.newsservice.api.response;

import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Nicolas
 */
@Data
public class CreateBulletinResponse {
    private Long id;
    private Long accountId;
    private Long senderUserId;
    private String senderUsername;
    private String body;
    private Instant createdDate;
    private List<String> fileIds;
}
