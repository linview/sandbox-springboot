package com.devops.platform.service;

import com.devops.platform.entity.Project;
import com.devops.platform.entity.Requirement;
import com.devops.platform.entity.RequirementPriority;
import com.devops.platform.entity.RequirementStatus;
import com.devops.platform.entity.RequirementType;
import com.devops.platform.entity.Sprint;
import com.devops.platform.repository.ProjectRepository;
import com.devops.platform.repository.RequirementRepository;
import com.devops.platform.repository.RequirementPriorityRepository;
import com.devops.platform.repository.RequirementStatusRepository;
import com.devops.platform.repository.RequirementTypeRepository;
import com.devops.platform.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequirementService {
    
    @Autowired
    private RequirementRepository requirementRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private RequirementTypeRepository requirementTypeRepository;
    
    @Autowired
    private RequirementStatusRepository requirementStatusRepository;
    
    @Autowired
    private RequirementPriorityRepository requirementPriorityRepository;
    
    @Autowired
    private SprintRepository sprintRepository;
    
    public Requirement createRequirement(Requirement requirement, Long projectId, 
                                       Long typeId, Long statusId, Long priorityId, Long sprintId) {
        
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        
        RequirementType type = requirementTypeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Requirement type not found with id: " + typeId));
        
        RequirementStatus status = requirementStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("Requirement status not found with id: " + statusId));
        
        RequirementPriority priority = requirementPriorityRepository.findById(priorityId)
                .orElseThrow(() -> new IllegalArgumentException("Requirement priority not found with id: " + priorityId));
        
        requirement.setProject(project);
        requirement.setType(type);
        requirement.setStatus(status);
        requirement.setPriority(priority);
        
        if (sprintId != null) {
            Sprint sprint = sprintRepository.findById(sprintId)
                    .orElseThrow(() -> new IllegalArgumentException("Sprint not found with id: " + sprintId));
            requirement.setSprint(sprint);
        }
        
        return requirementRepository.save(requirement);
    }
    
    public Requirement updateRequirement(Long id, Requirement requirementDetails) {
        Requirement requirement = requirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Requirement not found with id: " + id));
        
        requirement.setTitle(requirementDetails.getTitle());
        requirement.setDescription(requirementDetails.getDescription());
        requirement.setStoryPoints(requirementDetails.getStoryPoints());
        
        return requirementRepository.save(requirement);
    }
    
    public Requirement updateRequirementStatus(Long id, Long statusId) {
        Requirement requirement = requirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Requirement not found with id: " + id));
        
        RequirementStatus status = requirementStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("Requirement status not found with id: " + statusId));
        
        requirement.setStatus(status);
        return requirementRepository.save(requirement);
    }
    
    public void deleteRequirement(Long id) {
        Requirement requirement = requirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Requirement not found with id: " + id));
        requirementRepository.delete(requirement);
    }
    
    @Transactional(readOnly = true)
    public List<Requirement> getRequirementsByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        return requirementRepository.findByProject(project);
    }
    
    @Transactional(readOnly = true)
    public List<Requirement> getRequirementsByProjectAndStatus(Long projectId, Long statusId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        
        RequirementStatus status = requirementStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("Requirement status not found with id: " + statusId));
        
        return requirementRepository.findByProjectAndStatus(project, status);
    }
    
    @Transactional(readOnly = true)
    public Optional<Requirement> getRequirementById(Long id) {
        return requirementRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Long getRequirementCountByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        return requirementRepository.countByProject(project);
    }
    
    @Transactional(readOnly = true)
    public Long getRequirementCountByProjectAndStatus(Long projectId, Long statusId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        
        RequirementStatus status = requirementStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("Requirement status not found with id: " + statusId));
        
        return requirementRepository.countByProjectAndStatus(project, status);
    }
}