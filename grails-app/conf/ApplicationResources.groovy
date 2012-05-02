modules = {
    application {
        resource url:'js/application.js'
    }

    core {
        dependsOn 'jquery, jquery-ui'
    }
    menu {
        depensOn :'core'
        resource url:'js/menu/fg.menu.js'
        resource url:'js/app/menu.js'
    }
}