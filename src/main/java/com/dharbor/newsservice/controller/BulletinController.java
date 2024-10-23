package com.dharbor.newsservice.controller;

import com.dharbor.newsservice.api.request.CreateBulletinRequest;
import com.dharbor.newsservice.api.response.BulletinListResponse;
import com.dharbor.newsservice.api.response.CreateBulletinResponse;
import com.dharbor.newsservice.service.BulletinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nicolas
 */
@RestController
@RequestMapping("/api/bulletins")
@RequiredArgsConstructor
public class BulletinController {

    private final BulletinService bulletinService;

    @PostMapping
    public ResponseEntity<CreateBulletinResponse> createBulletin(
            @RequestHeader("Account-ID") Long accountId,
            @RequestHeader("User-ID") Long userId,
            @RequestBody CreateBulletinRequest request) {
        CreateBulletinResponse response = bulletinService.createBulletin(request, accountId, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<BulletinListResponse>> listBulletins(
            @RequestHeader("Account-ID") Long accountId,
            @RequestHeader("User-ID") Long userId,
            @RequestParam(required = false) String content,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<BulletinListResponse> bulletins = bulletinService.listBulletins(content, page, size);
        return ResponseEntity.ok(bulletins);
    }
}
