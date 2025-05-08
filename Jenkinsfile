pipeline {
  agent any
  environment {
    SONARQUBE_ENV = 'sonar'            // Nom du serveur Sonar configuré dans Jenkins
  }
  stages {
    // stage('Checkout') {
    //   steps { checkout scm }
    // }

    stage("Code"){
            steps{
                git url: "https://github.com/aliwarou/projet-si-jenkins" , branch: "main"
                echo "Code Cloned Successfully"
            }
        }

   

    stage('Analyse SonarQube (SAST)') {
      steps {
        withSonarQubeEnv(SONARQUBE_ENV) {
          echo "Analysise Successfully"
          sh 'mvn clean verify sonar:sonar'
        }
      }
      
    }

   
  }
}
