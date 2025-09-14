package com.devops.platform.service;

import com.devops.platform.entity.Project;
import com.devops.platform.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public Project createProject(Project project) {
        if (projectRepository.existsByName(project.getName())) {
            throw new IllegalArgumentException("Project with name '" + project.getName() + "' already exists");
        }
        return projectRepository.save(project);
    }
    
    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
        
        if (!project.getName().equals(projectDetails.getName()) && 
            projectRepository.existsByName(projectDetails.getName())) {
            throw new IllegalArgumentException("Project with name '" + projectDetails.getName() + "' already exists");
        }
        
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setRepositoryUrl(projectDetails.getRepositoryUrl());
        project.setCiConfigPath(projectDetails.getCiConfigPath());
        project.setDefaultBranch(projectDetails.getDefaultBranch());
        
        return projectRepository.save(project);
    }
    
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
        projectRepository.delete(project);
    }
    
    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Project> getProjectByName(String name) {
        return projectRepository.findByName(name);
    }
    
    @Transactional(readOnly = true)
    public List<Project> searchProjects(String keyword) {
        return projectRepository.searchByKeyword(keyword);
    }
    
    @Transactional(readOnly = true)
    public List<Project> getProjectsWithRepository() {
        return projectRepository.findAllWithRepository();
    }
    
    public boolean projectExists(String name) {
        return projectRepository.existsByName(name);
    }
}