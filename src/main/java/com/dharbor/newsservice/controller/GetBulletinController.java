package com.dharbor.newsservice.controller;

import com.dharbor.newsservice.api.response.BulletinListResponse;
import com.dharbor.newsservice.service.GetBulletinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nicolas
 */
@RestController
@RequestMapping("/api/news-service/bulletins")
@RequiredArgsConstructor
public class GetBulletinController {

    private final GetBulletinService bulletinService;

    @GetMapping
    public ResponseEntity<Page<BulletinListResponse>> listBulletins(
            Pageable pageable) {
        Page<BulletinListResponse> bulletins = bulletinService.listAllBulletinsSortedByDate(pageable);
        return ResponseEntity.ok(bulletins);
    }
}
