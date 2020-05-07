def call(){
    stage("publish artifactory"){
        node{
            unstash "artifacts"
            def target = "${getRepo()}/${getTarget()}"
            println "publicando: ${env.ARTIFACTS_REGEX}"
            println "en: $target"

            rtUpload (
                serverId: 'Artifactory',
                spec: """{
                    "files": [
                        {
                        "pattern": "${env.ARTIFACTS_REGEX}",
                        "target": "$target"
                        }
                    ]
                }""",
                failNoOp: true,
                buildName: "$env.JOB_NAME".replaceAll("/", "-")
            )
        }
    }
}

private def getRepo(){
    def branchName = env.ARTIFACTORY_BRANCH ?:
        env.BRANCH_NAME
    println "branchName: $branchName"
    if("master".equals(branchName)){
        return config.repoName
    }else{
        return config.repoSnapshot
    }
}

private def getTarget(){
    def artifactFolder = ""
    def branchName = env.ARTIFACTORY_BRANCH ?:
        env.BRANCH_NAME
    println "branchName: $branchName"
    switch (branchName) {
        case 'develop': artifactFolder = "beta"; break;
        case ~/^[Rr]elease\/.*$/: artifactFolder = "releaseCandidate"; break;
        case 'master': artifactFolder = "release"; break;
        case ~/^[Hh]otfix\/.*$/: artifactFolder = "milestone"; break;
        default: artifactFolder = "alpha";
    }
    return "$env.JOB_NAME/$env.BUILD_NUMBER/$artifactFolder/"
}