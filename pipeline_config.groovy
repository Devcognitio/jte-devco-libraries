libraries{
    merge = true
    hola_mundo{
        branch_conditional = "master"
    }
}

application_environments{
    merge = true
    dev{
        long_name = "Desarrollo"
    }
    qa{
        long_name = "Certificacion"
    }
    prod{
        long_name = "Production"
    }
}

merge = true
allow_scm_jenkinsfile = false