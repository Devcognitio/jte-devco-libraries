void call(){
    stage("checkout"){
        node {
            checkout scm
        }        
    }
}