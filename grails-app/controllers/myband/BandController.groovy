package myband

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
class BandController {



    def bandGridService

    def defaultAction = "list"
    def scaffold = true

    def listJSON = {
        // Instead of repeating this code in all controllers, all logic is handled in a service
        render bandGridService.generate(params, "Band") as JSON
    }

    def editJSON = {
        // Instead of repeating this code in all controllers, all logic is handled in a service
        render bandGridService.edit(params, "Band") as JSON
    }
}
