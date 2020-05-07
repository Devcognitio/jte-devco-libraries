import org.codehaus.groovy.runtime.GStringImpl

void call(Map args){

  actions = [
    "add": { files ->
      if (files instanceof String) sh "git add ${files}"
      else if (files instanceof GStringImpl) sh "git add ${files}"
      else sh "git add ${files.join(" ")}"
    }, 
    "commit": { message ->
      sh "git config user.email 'jenkins@nothing.com' && git config user.name 'Jenkins'"
      sh script: "git commit -m \"${message}\"", returnStatus: true
    },
    "push": { flags ->
      sh "git push ${flags ?: ""} ${env.git_url_with_creds}"
    }
  ]

  invalid_args = args.findAll{ !(it.getKey() in actions.keySet()) }
  if (invalid_args) error "Unknown git actions: ${invalid_args.collect{ it.getKey() }.join(", ")}"
  
  if (!(args.subMap(actions.keySet()))) error "git: You must use an action: ${actions.keySet().join(", ")}"
  
  args.each{ action, value ->
    def c = actions.get(action)
    c.resolveStrategy = Closure.DELEGATE_FIRST
    c.delegate = this        
    c.call(value)
  }
}

void call(String action){
  String a = action.toString()
  this.call([
    (a): null
  ])
}