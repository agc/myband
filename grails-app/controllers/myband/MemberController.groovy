package myband

import grails.converters.JSON

class MemberController {

    def memberGridService

    def scaffold = true
    def defaultAction = 'list'

    def listJSON = {
        // Instead of repeating this code in all controllers, all logic is handled in a service
        render memberGridService.generate(params, "Member") as JSON
    }

    def editJSON = {
        // Instead of repeating this code in all controllers, all logic is handled in a service
        render memberGridService.edit(params, "Member") as JSON
    }
}