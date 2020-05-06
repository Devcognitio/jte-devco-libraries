import groovy.json.JsonSlurper

void call(){

}

def get_source_branch(){

    def cred_id = env.GIT_CREDENTIAL_ID;

    withCredentials([usernamePassword(credentialsId: cred_id, passwordVariable: 'PAT', usernameVariable: 'USER')]) {
        def url = "https://api.bitbucket.org/2.0/repositories/${env.ORG_NAME}/${env.REPO_NAME}/pullrequests/${env.CHANGE_ID}";
        def get = new URL(url).openConnection();

        String userCredentials = "$USER:$PAT";
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
        
        get.setRequestProperty("Authorization", basicAuth);
        get.setRequestMethod("GET");        
        get.setUseCaches(false);
        get.setDoInput(true);
        get.setDoOutput(true);
        
        get.connect();
        
        if((get.responseCode).equals(200)) {
            def contenido = get.getInputStream().getText();
            
            def jsonSlurper = new JsonSlurper();
            def object = jsonSlurper.parseText(contenido);
            
            return object.source.branch.name;
        }else{
            return "branch_no_determinado"
        }
    }
}