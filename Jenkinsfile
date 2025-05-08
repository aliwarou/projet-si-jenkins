pipeline {
  agent any
  environment {
    SONARQUBE_ENV = 'sonar'            // Nom du serveur Sonar configuré dans Jenkins
    // DEP_CHECK_OUT = 'reports/dependency-check-report' 
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

   

    stage('Analyse SonarQube (SAST)') {
      steps {
        withSonarQubeEnv(SONARQUBE_ENV) {
          // Pour un projet Maven :
          sh './mvnw clean verify sonar:sonar'
        }
      }
      post {
        always {
          // on peut archiver le résumé Sonar (scanner-report.json) si besoin
          archiveArtifacts artifacts: 'target/sonar/report-task.txt', fingerprint: true
        }
      }
    }

   
  }
}
