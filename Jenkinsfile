agent any
    environment{
        SONAR_HOME = tool "sonar"
    }
    stages {
        
        stage("Code"){
            steps{
                git url: "https://github.com/aliwarou/projet-si-jenkins.git" , branch: "main"
                echo "Code Cloned Successfully"
            }
        }
       
    }
