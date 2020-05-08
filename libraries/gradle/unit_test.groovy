void call(){
    def docker_image = config.docker_image ?:
            "maven:3-alpine"

    stage("unit test gradle"){
        node{
            docker.image(docker_image){ c ->
                unstash "workspace"

                sh './gradlew test'
                
                stash name: 'workspace', includes: "**"
            }
        }                
    }  
}