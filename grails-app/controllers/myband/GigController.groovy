package myband

import org.springframework.dao.DataIntegrityViolationException

class GigController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gigInstanceList: Gig.list(params), gigInstanceTotal: Gig.count()]
    }

    def create() {
        [gigInstance: new Gig(params)]
    }

    def save() {
        def gigInstance = new Gig(params)
        if (!gigInstance.save(flush: true)) {
            render(view: "create", model: [gigInstance: gigInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'gig.label', default: 'Gig'), gigInstance.id])
        redirect(action: "show", id: gigInstance.id)
    }

    def show() {
        def gigInstance = Gig.get(params.id)
        if (!gigInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gig.label', default: 'Gig'), params.id])
            redirect(action: "list")
            return
        }

        [gigInstance: gigInstance]
    }

    def edit() {
        def gigInstance = Gig.get(params.id)
        if (!gigInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gig.label', default: 'Gig'), params.id])
            redirect(action: "list")
            return
        }

        [gigInstance: gigInstance]
    }

    def update() {
        def gigInstance = Gig.get(params.id)
        if (!gigInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gig.label', default: 'Gig'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (gigInstance.version > version) {
                gigInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'gig.label', default: 'Gig')] as Object[],
                          "Another user has updated this Gig while you were editing")
                render(view: "edit", model: [gigInstance: gigInstance])
                return
            }
        }

        gigInstance.properties = params

        if (!gigInstance.save(flush: true)) {
            render(view: "edit", model: [gigInstance: gigInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'gig.label', default: 'Gig'), gigInstance.id])
        redirect(action: "show", id: gigInstance.id)
    }

    def delete() {
        def gigInstance = Gig.get(params.id)
        if (!gigInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gig.label', default: 'Gig'), params.id])
            redirect(action: "list")
            return
        }

        try {
            gigInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'gig.label', default: 'Gig'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'gig.label', default: 'Gig'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
