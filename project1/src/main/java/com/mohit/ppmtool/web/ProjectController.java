package com.mohit.ppmtool.web;

import com.mohit.ppmtool.domain.Project;
import com.mohit.ppmtool.services.MapValidationErrorService;
import com.mohit.ppmtool.services.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectServices projectServices;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
//        if(result.hasErrors()){
//            //explored bindingresult, But its too much logic in controller
//            Map<String,String> errorMap = new HashMap<>();
//            for(FieldError error: result.getFieldErrors()) {
//                errorMap.put(error.getField(),error.getDefaultMessage());
//            }
//            return  new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
//        }
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null){
             return errorMap;
        }
        Project project1 = projectServices.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){

        Project project = projectServices.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectServices.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@ PathVariable String projectId){
        projectServices.deleteProjectByIdentifier(projectId);

        return  new ResponseEntity<String>("Project with  ID: '"+ projectId +"' is deleted Successfully ",HttpStatus.OK);
    }
}
