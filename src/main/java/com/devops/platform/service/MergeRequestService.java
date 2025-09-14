package com.devops.platform.service;

import com.devops.platform.entity.MergeRequest;
import com.devops.platform.entity.MRStatus;
import com.devops.platform.entity.Project;
import com.devops.platform.repository.MergeRequestRepository;
import com.devops.platform.repository.ProjectRepository;
import com.devops.platform.repository.MRStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MergeRequestService {
    
    @Autowired
    private MergeRequestRepository mergeRequestRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private MRStatusRepository mrStatusRepository;
    
    public MergeRequest createMergeRequest(MergeRequest mergeRequest, Long projectId, Long statusId) {
        
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        
        MRStatus status = mrStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("MR status not found with id: " + statusId));
        
        mergeRequest.setProject(project);
        mergeRequest.setStatus(status);
        
        return mergeRequestRepository.save(mergeRequest);
    }
    
    public MergeRequest updateMergeRequest(Long id, MergeRequest mergeRequestDetails) {
        MergeRequest mergeRequest = mergeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Merge request not found with id: " + id));
        
        mergeRequest.setTitle(mergeRequestDetails.getTitle());
        mergeRequest.setDescription(mergeRequestDetails.getDescription());
        mergeRequest.setSourceBranch(mergeRequestDetails.getSourceBranch());
        mergeRequest.setTargetBranch(mergeRequestDetails.getTargetBranch());
        
        return mergeRequestRepository.save(mergeRequest);
    }
    
    public MergeRequest updateMergeRequestStatus(Long id, Long statusId) {
        MergeRequest mergeRequest = mergeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Merge request not found with id: " + id));
        
        MRStatus status = mrStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("MR status not found with id: " + statusId));
        
        mergeRequest.setStatus(status);
        
        if (status.getName().equals("MERGED")) {
            mergeRequest.setMergedAt(java.time.LocalDateTime.now());
        } else if (status.getName().equals("CLOSED")) {
            mergeRequest.setClosedAt(java.time.LocalDateTime.now());
        }
        
        return mergeRequestRepository.save(mergeRequest);
    }
    
    public void deleteMergeRequest(Long id) {
        MergeRequest mergeRequest = mergeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Merge request not found with id: " + id));
        mergeRequestRepository.delete(mergeRequest);
    }
    
    @Transactional(readOnly = true)
    public List<MergeRequest> getMergeRequestsByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        return mergeRequestRepository.findByProject(project);
    }
    
    @Transactional(readOnly = true)
    public List<MergeRequest> getMergeRequestsByProjectAndStatus(Long projectId, Long statusId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        
        MRStatus status = mrStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("MR status not found with id: " + statusId));
        
        return mergeRequestRepository.findByProjectAndStatus(project, status);
    }
    
    @Transactional(readOnly = true)
    public Optional<MergeRequest> getMergeRequestById(Long id) {
        return mergeRequestRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<MergeRequest> getMergeRequestsByBranch(String branchName) {
        return mergeRequestRepository.findByBranch(branchName);
    }
    
    @Transactional(readOnly = true)
    public Long getMergeRequestCountByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        return mergeRequestRepository.countByProject(project);
    }
    
    @Transactional(readOnly = true)
    public Long getMergeRequestCountByProjectAndStatus(Long projectId, Long statusId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        
        MRStatus status = mrStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("MR status not found with id: " + statusId));
        
        return mergeRequestRepository.countByProjectAndStatus(project, status);
    }
}