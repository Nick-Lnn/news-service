package com.dharbor.newsservice.api.request;

import lombok.Data;

import java.util.List;

/**
 * @author Nicolas
 */
@Data
public class CreateBulletinRequest {
    private String content;
    private List<String> fileIds;
}
