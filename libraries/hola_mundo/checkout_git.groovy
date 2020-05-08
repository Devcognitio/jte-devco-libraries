void call(){
    stage("checkout"){
        node {
            cleanWs()
            checkout scm 
            stash name: 'workspace', allowEmpty: true, useDefaultExcludes: false
        }        
    }
}