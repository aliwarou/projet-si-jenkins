agent any
    environment{
        SONAR_HOME = tool "Sonar"
    }
    stages {
        
        stage("Code"){
            steps{
                git url: "https://github.com/aliwarou/projet-si-jenkins.git" , branch: "master"
                echo "Code Cloned Successfully"
            }
        }
       
    }
