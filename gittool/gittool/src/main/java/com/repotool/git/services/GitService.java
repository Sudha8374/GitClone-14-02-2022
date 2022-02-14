package com.repotool.git.services;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ConfigConstants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repotool.git.configuration.ConfigGit;
import com.repotool.git.model.EntitiesValues;




@Service
public class GitService {
	
	@Autowired
	ConfigGit config;
	
	public void cloneRepository(String sourceBranch)
	{
		
		
      
		
		/*System.out.println(config.getGiturl());
		System.out.println(config.getGitUsername());
		System.out.println(config.getGitPassword());*/
		
		String repoUrl = config.getGiturl();
		String cloneDirectoryPath = config.getGitcloneDirectoryPath(); // Ex.in windows c:\\gitProjects\SpringBootMongoDbCRUD\
		
		try {
		    System.out.println("Cloning "+repoUrl+" into "+cloneDirectoryPath);
		    Git result = Git.cloneRepository()
		        .setURI(repoUrl)
		        .setDirectory(Paths.get(cloneDirectoryPath).toFile())
		        .setCredentialsProvider((new UsernamePasswordCredentialsProvider(config.getGitUsername(), config.getGitPassword())))
		        .call();
		    
		   // System.out.println("Completed Cloning"+result.status());
		} catch (GitAPIException e) {
		    System.out.println("Exception occurred while cloning repo");
		    e.printStackTrace();
		}
		
		
		
	}
	
	

	
	//recursing folder inside a folder getting the list of all files in all folders
	public  void recurseOnFile(String path) {
		
		  File passedFile = new File(path);
		  if (passedFile.isFile()) {
		    replaceInFile(passedFile);
		  }else if (passedFile.isDirectory()) {
		     File[] listOfFiles = passedFile.listFiles();
		    for (File inDir : listOfFiles) {
		    	
		    	if(!inDir.isHidden())
		    	{
		    		recurseOnFile(inDir.getAbsolutePath());	
		    	}
		      
		    }
		  }
		}
	
	//from the list of all files finding and replacing the key words
	private void replaceInFile(File file)
	{
		try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
            }
            reader.close();

            String replacedtext = oldtext.replaceAll("<eol> ", "");
            replacedtext = replacedtext.replaceAll("UNIVERSALHS_MILLENNIUM", "UNIVERSALHS_EDW_MILL_CDS");
            
            

            FileWriter writer = new FileWriter(file);
            writer.write(replacedtext);

            writer.close();
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
		
		
	}
	
	
		public  boolean createAndCommitBranch(final String destinationBranchName) {
			
			
			String cloneDirectoryPath = config.getGitcloneDirectoryPath();
			CheckoutCommand checkout;
	        Git git;
	  
	        File src = new File(cloneDirectoryPath);
	        
            
	        try {
	        	
	        	Repository repo = new FileRepositoryBuilder().readEnvironment().findGitDir(src).build();
	            git = new Git(repo);
	            git.branchCreate().setName(destinationBranchName).call();
	            checkout = git.checkout();
	            checkout.setName(destinationBranchName);
	            checkout.call();
	           
	            git.commit().setAll(true).setMessage("replacement done").call();
	            
	           
	        	
	           
	            
	            System.out.println("New Branch created");
	           
	            PushCommand pushCommand = git.push();
	            pushCommand.setRemote("origin");
	            pushCommand.setRefSpecs(new RefSpec(destinationBranchName + ":" + destinationBranchName));
	            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(
	            		config.getGitUsername(), config.getGitPassword()));
	            pushCommand.call();
	            git.getRepository().close();
                git.close();
                git.gc().call();
                git=null;
	            System.out.println("pushed successfully");
	            //deleteDirectory(src);
	            
	            return true;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
		
		/*public static boolean deleteDirectory(File file) {
		    File[] children = file.listFiles();
		    if (children != null) {
		        for (File child : children) {
		            deleteDirectory(child);
		        }
		    }
		    
		    
		    return file.delete();
		}*/
		
	
		
	}
	

