package com.repotool.git;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ConfigConstants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.RefSpec;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repotool.git.model.EntitiesValues;
import com.repotool.git.services.GitService;



@SpringBootApplication
public class GittoolApplication {

	public static void main(String[] args) throws GitAPIException, IOException  {
		    
		SpringApplication.run(GittoolApplication.class, args);
       
}
	
}
