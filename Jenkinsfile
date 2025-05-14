// // pipeline {
// //   agent any

// //   environment {
// //     SONARQUBE_ENV = 'sonar'            
// //     DEP_CHECK_OUT = 'reports/dependency-check-report' 
// //   }
// //   tools {
// //     maven 'maven'
// //   }
// //   stages {


// //  stage("OWASP") {
// //   steps {
// //     // Invoke Dependency‑Check en lui passant votre credential NVD API Key
// //     dependencyCheck(
// //       odcInstallation: 'vd',
// //       additionalArguments: '--scan ./',
// //       nvdCredentialsId: 'nvd-api-key'    // ← votre ID de credential Jenkins
// //     )
// //     // Puis publication du rapport
// //     dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
// //   }
// // }
    
// //     stage('Analyse SonarQube (SAST)') {
// //       steps {
// //         withSonarQubeEnv(SONARQUBE_ENV) {
// //           // Pour un projet Maven :
// //           sh 'mvn clean package sonar:sonar'
// //         }
// //       }
// //       post {
// //         always {
// //           // on peut archiver le résumé Sonar (scanner-report.json) si besoin
// //           archiveArtifacts artifacts: 'target/sonar/report-task.txt', fingerprint: true
// //         }
// //       }
// //     }



    
// //   }

  
// // }


// pipeline {
//   agent any

//   environment {
//     SONARQUBE_ENV = 'sonar'
//     NVD_API_KEY   = credentials('nvd-api-key') 
//   }

//   tools {
//      maven 'maven'                      
//   }

  
//   stages {

//     stage('Checkout') {
//       steps {
//         checkout scm
//       }
//     }

//     stage('OWASP Dependency-Check') {
//       steps {
//         dependencyCheck(
//           odcInstallation: 'vd',                        
//           additionalArguments: '--scan ./ --format ALL', 
//           nvdCredentialsId: 'nvd-api-key'  )              
//       }
//       post {
//         always {
//           archiveArtifacts artifacts: 'dependency-check-report.*', fingerprint: true
//         }
//       }
//     }

//     stage('Build') {
//       steps {
//         sh 'mvn -B clean package'
//       }
     
//     }

//     stage('SonarQube Analysis') {
//       steps {
//         withSonarQubeEnv(SONARQUBE_ENV) {
//           sh 'mvn clean package sonar:sonar'
//         }
//       }
//       post {
//         always {
//           archiveArtifacts artifacts: 'target/sonar/report-task.txt', fingerprint: true
//         }
//       }
//     }

//     stage('Quality Gate') {
//       steps {
//         timeout(time: 5, unit: 'MINUTES') {
//           waitForQualityGate abortPipeline: true
//         }
//       }
//     }

//   }

//   post {
//     success {
//       echo '✅ Pipeline complété avec succès !'
//     }
//     unstable {
//       echo '⚠️ Pipeline terminé en état instable (tests ou Quality Gate).'
//     }
//     failure {
//       echo '❌ Pipeline échoué ! Vérifie les rapports et logs.'
//     }
//   }
// }


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
    
      dependencyCheck(
        odcInstallation: 'vd',
        additionalArguments: '--scan ./ --format ALL',
        nvdCredentialsId: 'nvd-api-key'    
      )
  
      dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
    }
  }
      
    stage('Analyse SonarQube (SAST)') {
      steps {
        withSonarQubeEnv(SONARQUBE_ENV) {
        
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



