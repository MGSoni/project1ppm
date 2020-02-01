package com.mohit.ppmtool.exceptions;

public class ProjectIdExceptionResponse {

    public String projectIdentifier;
    public ProjectIdExceptionResponse(String projectIdentifier){
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
