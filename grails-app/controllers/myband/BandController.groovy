package myband

import org.springframework.dao.DataIntegrityViolationException

class BandController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [bandInstanceList: Band.list(params), bandInstanceTotal: Band.count()]
    }

    def create() {
        [bandInstance: new Band(params)]
    }

    def save() {
        def bandInstance = new Band(params)
        if (!bandInstance.save(flush: true)) {
            render(view: "create", model: [bandInstance: bandInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'band.label', default: 'Band'), bandInstance.id])
        redirect(action: "show", id: bandInstance.id)
    }

    def show() {
        def bandInstance = Band.get(params.id)
        if (!bandInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'band.label', default: 'Band'), params.id])
            redirect(action: "list")
            return
        }

        [bandInstance: bandInstance]
    }

    def edit() {
        def bandInstance = Band.get(params.id)
        if (!bandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'band.label', default: 'Band'), params.id])
            redirect(action: "list")
            return
        }

        [bandInstance: bandInstance]
    }

    def update() {
        def bandInstance = Band.get(params.id)
        if (!bandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'band.label', default: 'Band'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (bandInstance.version > version) {
                bandInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'band.label', default: 'Band')] as Object[],
                          "Another user has updated this Band while you were editing")
                render(view: "edit", model: [bandInstance: bandInstance])
                return
            }
        }

        bandInstance.properties = params

        if (!bandInstance.save(flush: true)) {
            render(view: "edit", model: [bandInstance: bandInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'band.label', default: 'Band'), bandInstance.id])
        redirect(action: "show", id: bandInstance.id)
    }

    def delete() {
        def bandInstance = Band.get(params.id)
        if (!bandInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'band.label', default: 'Band'), params.id])
            redirect(action: "list")
            return
        }

        try {
            bandInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'band.label', default: 'Band'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'band.label', default: 'Band'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
