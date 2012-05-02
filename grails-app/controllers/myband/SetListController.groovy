package myband

import org.springframework.dao.DataIntegrityViolationException

class SetListController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [setListInstanceList: SetList.list(params), setListInstanceTotal: SetList.count()]
    }

    def create() {
        [setListInstance: new SetList(params)]
    }

    def save() {
        def setListInstance = new SetList(params)
        if (!setListInstance.save(flush: true)) {
            render(view: "create", model: [setListInstance: setListInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'setList.label', default: 'SetList'), setListInstance.id])
        redirect(action: "show", id: setListInstance.id)
    }

    def show() {
        def setListInstance = SetList.get(params.id)
        if (!setListInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'setList.label', default: 'SetList'), params.id])
            redirect(action: "list")
            return
        }

        [setListInstance: setListInstance]
    }

    def edit() {
        def setListInstance = SetList.get(params.id)
        if (!setListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'setList.label', default: 'SetList'), params.id])
            redirect(action: "list")
            return
        }

        [setListInstance: setListInstance]
    }

    def update() {
        def setListInstance = SetList.get(params.id)
        if (!setListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'setList.label', default: 'SetList'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (setListInstance.version > version) {
                setListInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'setList.label', default: 'SetList')] as Object[],
                          "Another user has updated this SetList while you were editing")
                render(view: "edit", model: [setListInstance: setListInstance])
                return
            }
        }

        setListInstance.properties = params

        if (!setListInstance.save(flush: true)) {
            render(view: "edit", model: [setListInstance: setListInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'setList.label', default: 'SetList'), setListInstance.id])
        redirect(action: "show", id: setListInstance.id)
    }

    def delete() {
        def setListInstance = SetList.get(params.id)
        if (!setListInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'setList.label', default: 'SetList'), params.id])
            redirect(action: "list")
            return
        }

        try {
            setListInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'setList.label', default: 'SetList'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'setList.label', default: 'SetList'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
