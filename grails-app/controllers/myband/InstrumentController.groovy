package myband

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class InstrumentController {
    def gridService

    def scaffold = true
    def defaultAction = 'list'

    def listJSON = {
        // Instead of repeating this code in all controllers, all logic is handled
        // in a service
        render gridService.generate(params, "Instrument") as JSON
    }

    def editJSON = {
        // Instead of repeating this code in all controllers, all logic is handled
        // in a service
        render gridService.edit(params, "Instrument") as JSON
    }


}
