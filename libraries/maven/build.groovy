void call(){
    stage("build gradle"){
        node {            
            sh 'chmod +x mvnw'
            sh './mvnw clean package -DskipTests=true'
            archiveArtifacts artifacts: 'target/*.jar'
        }       
    }   
}