package com.repotool.git.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("config")
public class ConfigGit {

	private String giturl;
	private String gitUsername;
	private String gitPassword;
    private String gitcloneDirectoryPath;
	
	
	public String getGitcloneDirectoryPath() {
		return gitcloneDirectoryPath;
	}

	public void setGitcloneDirectoryPath(String gitcloneDirectoryPath) {
		this.gitcloneDirectoryPath = gitcloneDirectoryPath;
	}

	public String getGitUsername() {
		return gitUsername;
	}

	public void setGitUsername(String gitUsername) {
		this.gitUsername = gitUsername;
	}

	public String getGitPassword() {
		return gitPassword;
	}

	public void setGitPassword(String gitPassword) {
		this.gitPassword = gitPassword;
	}

	public String getGiturl() {
		return giturl;
	}

	public void setGiturl(String giturl) {
		this.giturl = giturl;
	}
	

}
