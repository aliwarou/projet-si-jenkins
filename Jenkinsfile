pipeline {
  agent any

  environment {
    SONARQUBE_ENV = 'sonar'            
    DEP_CHECK_OUT = 'reports/dependency-check-report' 
  }
  tools {
    maven 'maven'
  }
  stages {


 stage("OWASP") {
  steps {
    // Invoke Dependency‑Check en lui passant votre credential NVD API Key
    dependencyCheck(
      odcInstallation: 'vd',
      additionalArguments: '--scan ./',
      nvdCredentialsId: 'nvd-api-key'    // ← votre ID de credential Jenkins
    )
    // Puis publication du rapport
    dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
  }
}
    
    stage('Analyse SonarQube (SAST)') {
      steps {
        withSonarQubeEnv(SONARQUBE_ENV) {
          // Pour un projet Maven :
          sh 'mvn clean package sonar:sonar'
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



