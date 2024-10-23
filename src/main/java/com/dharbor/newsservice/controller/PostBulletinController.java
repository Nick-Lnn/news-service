package com.dharbor.newsservice.controller;

import com.dharbor.newsservice.api.request.CreateBulletinRequest;
import com.dharbor.newsservice.api.response.CreateBulletinResponse;
import com.dharbor.newsservice.service.PostBulletinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nicolas
 */
@RestController
@RequestMapping("/api/news-service/bulletins")
@RequiredArgsConstructor
public class PostBulletinController {

    private final PostBulletinService postBulletinService;

    @PostMapping
    public ResponseEntity<CreateBulletinResponse> createBulletin(
            @RequestHeader("Account-ID") Long accountId,
            @RequestHeader("User-ID") Long userId,
            @RequestBody CreateBulletinRequest request) {
        CreateBulletinResponse response = postBulletinService.createBulletin(request, accountId, userId);
        return ResponseEntity.ok(response);
    }
}
