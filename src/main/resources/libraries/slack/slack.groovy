def call(){
    switch(currentBuild.result){
        case null:
        case "SUCCESS":
          slackSend color: "good", message: "Build Successful: ${env.JOB_URL}"
          break;
        case "FAILURE":
          slackSend color: '#ff0000', message: "Build Failure: ${env.JOB_URL}"
          break;
        default:
          echo "Slack Notifier doing nothing: ${currentBuild.result}"
    }
}

def alert_message(message){
  slackSend color: "#ffa500", message: message
}