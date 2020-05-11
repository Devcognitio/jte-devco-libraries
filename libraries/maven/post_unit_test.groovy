@AfterStep({ context.step.equals("unit_test") })
void call(context){
    node{
        junit 'surefire-reports/*.xml'
        archiveArtifacts artifacts: 'site/jacoco-aggregate/*.xml'
    }    
}