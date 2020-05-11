@AfterStep({ context.step.equals("unit_test") })
void call(context){
    node{
        junit 'target/surefire-reports/*.xml'
        archiveArtifacts artifacts: 'target/site/jacoco-aggregate/*.xml'
    }    
}