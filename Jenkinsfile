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


    stage('Analyse dépendances (OWASP Dependency‑Check)') {
      steps {
        // Génère un rapport HTML et XML
        sh """
          vd \
            --project my-app \
            --format ALL \
            --out ${env.DEP_CHECK_OUT}
        """
      }
      post {
        always {
          // Archive les rapports pour consultation dans Jenkins
          archiveArtifacts artifacts: "${env.DEP_CHECK_OUT}/**/*.html, ${env.DEP_CHECK_OUT}/**/*.xml", fingerprint: true
        }
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



