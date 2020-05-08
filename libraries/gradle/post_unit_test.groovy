@AfterStep({ context.step.equals("unit_test") })
void call(context){
    node{
        junit 'build/test-results/**/*.xml'
        archiveArtifacts artifacts: 'build/reports/jacoco/**/*.xml'        
    }    
}