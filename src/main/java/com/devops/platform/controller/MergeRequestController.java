package com.devops.platform.controller;

import com.devops.platform.entity.MergeRequest;
import com.devops.platform.service.MergeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/merge-requests")
public class MergeRequestController {
    
    @Autowired
    private MergeRequestService mergeRequestService;
    
    @PostMapping
    public ResponseEntity<MergeRequest> createMergeRequest(
            @RequestBody MergeRequest mergeRequest,
            @RequestParam Long projectId,
            @RequestParam Long statusId) {
        
        try {
            MergeRequest createdMR = mergeRequestService.createMergeRequest(mergeRequest, projectId, statusId);
            return ResponseEntity.ok(createdMR);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MergeRequest> updateMergeRequest(
            @PathVariable Long id, @RequestBody MergeRequest mergeRequestDetails) {
        
        try {
            MergeRequest updatedMR = mergeRequestService.updateMergeRequest(id, mergeRequestDetails);
            return ResponseEntity.ok(updatedMR);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/status/{statusId}")
    public ResponseEntity<MergeRequest> updateMergeRequestStatus(
            @PathVariable Long id, @PathVariable Long statusId) {
        
        try {
            MergeRequest updatedMR = mergeRequestService.updateMergeRequestStatus(id, statusId);
            return ResponseEntity.ok(updatedMR);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMergeRequest(@PathVariable Long id) {
        try {
            mergeRequestService.deleteMergeRequest(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<MergeRequest>> getMergeRequestsByProject(@PathVariable Long projectId) {
        try {
            List<MergeRequest> mergeRequests = mergeRequestService.getMergeRequestsByProject(projectId);
            return ResponseEntity.ok(mergeRequests);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/project/{projectId}/status/{statusId}")
    public ResponseEntity<List<MergeRequest>> getMergeRequestsByProjectAndStatus(
            @PathVariable Long projectId, @PathVariable Long statusId) {
        
        try {
            List<MergeRequest> mergeRequests = mergeRequestService.getMergeRequestsByProjectAndStatus(projectId, statusId);
            return ResponseEntity.ok(mergeRequests);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MergeRequest> getMergeRequestById(@PathVariable Long id) {
        Optional<MergeRequest> mergeRequest = mergeRequestService.getMergeRequestById(id);
        return mergeRequest.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/branch/{branchName}")
    public ResponseEntity<List<MergeRequest>> getMergeRequestsByBranch(@PathVariable String branchName) {
        List<MergeRequest> mergeRequests = mergeRequestService.getMergeRequestsByBranch(branchName);
        return ResponseEntity.ok(mergeRequests);
    }
    
    @GetMapping("/project/{projectId}/count")
    public ResponseEntity<Long> getMergeRequestCountByProject(@PathVariable Long projectId) {
        try {
            Long count = mergeRequestService.getMergeRequestCountByProject(projectId);
            return ResponseEntity.ok(count);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/project/{projectId}/status/{statusId}/count")
    public ResponseEntity<Long> getMergeRequestCountByProjectAndStatus(
            @PathVariable Long projectId, @PathVariable Long statusId) {
        
        try {
            Long count = mergeRequestService.getMergeRequestCountByProjectAndStatus(projectId, statusId);
            return ResponseEntity.ok(count);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}