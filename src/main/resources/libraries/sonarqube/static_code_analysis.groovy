def call(){
    stage('sonarqube analysis'){
        cred_id = config.credential_id ?:
            "sonarqube"

        enforce = config.enforce_quality_gate ?:
            true

        dirWS = config.appWorkSpace ?:
            "."
        
        nodeSonar = config.nodeSonar ?:
            "any"

        node("${nodeSonar}"){
            dir("$dirWS"){
                scannerHome = tool 'SonarQubeScanner'

                withCredentials([usernamePassword(credentialsId: cred_id, passwordVariable: 'token', usernameVariable: 'user')]) {
                    withSonarQubeEnv("SonarQube"){
                        //unstash "workspace"
                        //try{ unstash "test-results" }catch(ex){}
                        sh "mkdir -p empty"
                        branchName = "$env.BRANCH_NAME"
                        def script = """${scannerHome}/bin/sonar-scanner -X -Dsonar.login="${user}" -Dsonar.password="${token}" -Dsonar.projectBaseDir="." -Dsonar.branch.name="${env.ARTIFACTORY_BRANCH}" """
                        
                        if (!fileExists("sonar-project.properties")){
                            script += "-Dsonar.sources=\"./src\""
                        }
                        sh script
                        
                    }
                    timeout(time: 5, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK' && enforce) {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }        
                }
            }            
        }
    }   
}