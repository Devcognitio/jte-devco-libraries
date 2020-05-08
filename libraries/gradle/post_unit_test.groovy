@AfterStep({ context.step.equals("unit_test") })
void call(context){
    def docker_image = config.docker_image ?:
            "maven:3-alpine"
    node{
        docker.image(docker_image){ c ->
            unstash "workspace"
            junit 'build/test-results/**/*.xml'
            archiveArtifacts artifacts: 'build/reports/jacoco/**/*.xml'
        }
    }    
}