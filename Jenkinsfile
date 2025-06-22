pipeline {
  agent any

  

  environment {
    SONARQUBE_ENV = 'sonar'            
    
    
  }
  tools {
    maven 'maven'
  }
  stages {

    stage("OWASP") {
      steps {
      
        dependencyCheck(
          odcInstallation: 'dependency-check',
          additionalArguments: '--scan ./ --format ALL',
          nvdCredentialsId: 'nvd-api-key'    
        )
    
        dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
      }
      post {
        always {
          archiveArtifacts artifacts: 'dependency-check-report.*', fingerprint: true
        }
      }
    }
        
    stage('Analyse SonarQube') {
      steps {
        withSonarQubeEnv(SONARQUBE_ENV) {
        
          sh 'mvn clean package sonar:sonar'
        }
      }
      post {
        always {
          archiveArtifacts artifacts: 'target/sonar/report-task.txt', fingerprint: true
        }
      }
    }

    // stage('Build') {
    //   steps {
    //     sh 'mvn -B clean package'
    //   }
     
    // }

     stage('Quality Gate') {
      steps {
        timeout(time: 5, unit: 'MINUTES') {
          waitForQualityGate abortPipeline: true
        }
      }
    }



      
  }


  
  post {
      success {
        echo 'Pipeline complété avec succès !'
      }
      unstable {
        echo 'Pipeline terminé en état instable (tests ou Quality Gate).'
      }
      failure {
        echo 'Pipeline échoué ! Vérifie les rapports et logs.'
      }
    }

  
}



