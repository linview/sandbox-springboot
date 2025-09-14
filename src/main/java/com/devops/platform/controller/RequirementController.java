package com.devops.platform.controller;

import com.devops.platform.entity.Requirement;
import com.devops.platform.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requirements")
public class RequirementController {
    
    @Autowired
    private RequirementService requirementService;
    
    @PostMapping
    public ResponseEntity<Requirement> createRequirement(
            @RequestBody Requirement requirement,
            @RequestParam Long projectId,
            @RequestParam Long typeId,
            @RequestParam Long statusId,
            @RequestParam Long priorityId,
            @RequestParam(required = false) Long sprintId) {
        
        try {
            Requirement createdRequirement = requirementService.createRequirement(
                requirement, projectId, typeId, statusId, priorityId, sprintId);
            return ResponseEntity.ok(createdRequirement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Requirement> updateRequirement(
            @PathVariable Long id, @RequestBody Requirement requirementDetails) {
        
        try {
            Requirement updatedRequirement = requirementService.updateRequirement(id, requirementDetails);
            return ResponseEntity.ok(updatedRequirement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/status/{statusId}")
    public ResponseEntity<Requirement> updateRequirementStatus(
            @PathVariable Long id, @PathVariable Long statusId) {
        
        try {
            Requirement updatedRequirement = requirementService.updateRequirementStatus(id, statusId);
            return ResponseEntity.ok(updatedRequirement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequirement(@PathVariable Long id) {
        try {
            requirementService.deleteRequirement(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Requirement>> getRequirementsByProject(@PathVariable Long projectId) {
        try {
            List<Requirement> requirements = requirementService.getRequirementsByProject(projectId);
            return ResponseEntity.ok(requirements);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/project/{projectId}/status/{statusId}")
    public ResponseEntity<List<Requirement>> getRequirementsByProjectAndStatus(
            @PathVariable Long projectId, @PathVariable Long statusId) {
        
        try {
            List<Requirement> requirements = requirementService.getRequirementsByProjectAndStatus(projectId, statusId);
            return ResponseEntity.ok(requirements);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Requirement> getRequirementById(@PathVariable Long id) {
        Optional<Requirement> requirement = requirementService.getRequirementById(id);
        return requirement.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/project/{projectId}/count")
    public ResponseEntity<Long> getRequirementCountByProject(@PathVariable Long projectId) {
        try {
            Long count = requirementService.getRequirementCountByProject(projectId);
            return ResponseEntity.ok(count);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/project/{projectId}/status/{statusId}/count")
    public ResponseEntity<Long> getRequirementCountByProjectAndStatus(
            @PathVariable Long projectId, @PathVariable Long statusId) {
        
        try {
            Long count = requirementService.getRequirementCountByProjectAndStatus(projectId, statusId);
            return ResponseEntity.ok(count);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}