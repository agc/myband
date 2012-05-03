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
        resource url:'js/app/acordeon.js'
    }

    jqgrid {
        dependsOn :'core'
        resource url:'js/app/instrument/list.js'
        resource url:'js/i18n/grid.locale-en.js'
        resource url:'js/grid/jquery.jqGrid.min.js'
        resource url:'js/grid/jquery.jqGrid.fluid.js'
    }
}


