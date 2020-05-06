merge = true
allow_scm_jenkinsfile = false

application_environments{
    merge = true
    dev{
        long_name = "Development"
    }
    staging{
        long_name = "Staging"
    }
    prod{
        long_name = "Production"
    }
}

libraries{
    override = true

    git
    gradle
    sonarqube
    approve
}