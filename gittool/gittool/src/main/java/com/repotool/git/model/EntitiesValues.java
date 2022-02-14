package com.repotool.git.model;

public class EntitiesValues {

	private String username;
    private String password;
    private String branchName;
    private String localPath;
    
    


public EntitiesValues() {
}

public EntitiesValues(String username, String password) {
    this.username = username;
    this.password = password;
}

public EntitiesValues(String username, String password, 
        String branchName, String localPath) {
    this.username = username;
    this.password = password;
    this.branchName = branchName;
    this.localPath = localPath;
    

}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}


public String getBranchName() {
    return branchName;
}

public void setBranchName(String branchName) {
    this.branchName = branchName;
}

public String getLocalPath() {
    return localPath;
}

public void setLocalPath(String localPath) {
    this.localPath = localPath;
}



@Override
public String toString() {
    return "EntitiesValues{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", branchName='" + branchName + '\'' +
            ", localPath='" + localPath + '\'' +
            
            '}';
}
}

