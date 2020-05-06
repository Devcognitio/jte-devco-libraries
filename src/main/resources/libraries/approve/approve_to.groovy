void call(app_env){
    stage ("Deploy Approve to ${app_env.long_name}") {
        if(app_env.long_name.equals('Production') ){
            timeout(time:1, unit:'DAYS'){                                                              
                try{
                    input message: 'Can you approve for IT ??', submitter: app_env.approversIT
                } catch(err) {
                    currentBuild.result = 'SUCCESS'
                    return
                }
            }
            timeout(time:1, unit:'DAYS'){
                try{
                    input message: 'Can you approve for Bussiness ??', submitter: app_env.approversBussiness
                } catch(err) {
                    currentBuild.result = 'SUCCESS'
                    return
                }
            }
        }else{
            timeout(time:1, unit:'HOURS'){
                try{
                    input message: 'Can you approve for IT ??', submitter: app_env.approversIT
                } catch(err) {
                    currentBuild.result = 'SUCCESS'
                    return
                }
            }
        }
    }
}