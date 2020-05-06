def call(repoPattern, target){    

    rtDownload (
        serverId: 'Artifactory',
        spec: """{
            "files": [
                {
                "pattern": "$repoPattern",
                "target": "$target"
                }
            ]
        }""",
        failNoOp: true
    )
       
}