package com.mohit.ppmtool.services;

import com.mohit.ppmtool.domain.Project;
import com.mohit.ppmtool.exceptions.ProjectIdException;
import com.mohit.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServices {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        //Lot of logic pending
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e) {
            throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project Id '" + projectId  + "' does not exists");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase() );

        if(project == null){
            throw new ProjectIdException("Cannot find Project with ID '"+ projectId +"'. This Project does not exists.");
        }
        projectRepository.delete(project);
    }
}
