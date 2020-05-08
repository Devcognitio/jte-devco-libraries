void call(){
    String docker_image = config.docker_image ?:
            "maven:3-alpine"

    stage("build gradle"){
        node {
            echo "docker_image: $docker_image"
            docker.image(docker_image){ c ->
                unstash "workspace"

                sh 'chmod +x gradlew'
                sh './gradlew clean build -DskipTests=true'
                archiveArtifacts artifacts: 'build/libs/**/*.jar'
                
                stash name: 'workspace', includes: "**"
            }            
        }       
    }   
}