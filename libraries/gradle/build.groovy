void call(){
    def docker_image = config.docker_image ?:
            "maven:3-alpine"

    stage("build gradle"){
        node {
            docker.image(docker_image).withRun(){ c ->
                //unstash "workspace"

                sh 'chmod +x gradlew'
                sh './gradlew clean build -DskipTests=true'
                archiveArtifacts artifacts: 'build/libs/**/*.jar'
                
                stash name: 'workspace', includes: "**"
            }            
        }       
    }   
}