package com.repotool.git.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.repotool.git.services.GitService;

@RestController
@RequestMapping("/app")
public class GitController {
	
	
	
	@Autowired
	GitService gitService;
	
	@PostMapping("/findAndReplace")
	public String findAndReplaceGitRepo( @RequestParam String sourceBranch,@RequestParam String destinationBranchName ,@RequestParam String findKey, @RequestParam String replaceKey) 
	{
		
		
		//step1:Clone the existing repo
		gitService.cloneRepository(sourceBranch);
		
		//step2: finding and replacing the keyword form the cloned directory
		gitService.recurseOnFile("C:\\Cloning2");
		System.out.println("Find and replace is done");
		
		//step3: creating and committing the modified(after find and replace is done)directory into newly created branch 
		gitService.createAndCommitBranch(destinationBranchName);
		
		
		
		return null;
		
	   
	}

}
