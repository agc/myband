package myband

import org.springframework.dao.DataIntegrityViolationException

class SongController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [songInstanceList: Song.list(params), songInstanceTotal: Song.count()]
    }

    def create() {
        [songInstance: new Song(params)]
    }

    def save() {
        def songInstance = new Song(params)
        if (!songInstance.save(flush: true)) {
            render(view: "create", model: [songInstance: songInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'song.label', default: 'Song'), songInstance.id])
        redirect(action: "show", id: songInstance.id)
    }

    def show() {
        def songInstance = Song.get(params.id)
        if (!songInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'song.label', default: 'Song'), params.id])
            redirect(action: "list")
            return
        }

        [songInstance: songInstance]
    }

    def edit() {
        def songInstance = Song.get(params.id)
        if (!songInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'song.label', default: 'Song'), params.id])
            redirect(action: "list")
            return
        }

        [songInstance: songInstance]
    }

    def update() {
        def songInstance = Song.get(params.id)
        if (!songInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'song.label', default: 'Song'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (songInstance.version > version) {
                songInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'song.label', default: 'Song')] as Object[],
                          "Another user has updated this Song while you were editing")
                render(view: "edit", model: [songInstance: songInstance])
                return
            }
        }

        songInstance.properties = params

        if (!songInstance.save(flush: true)) {
            render(view: "edit", model: [songInstance: songInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'song.label', default: 'Song'), songInstance.id])
        redirect(action: "show", id: songInstance.id)
    }

    def delete() {
        def songInstance = Song.get(params.id)
        if (!songInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'song.label', default: 'Song'), params.id])
            redirect(action: "list")
            return
        }

        try {
            songInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'song.label', default: 'Song'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'song.label', default: 'Song'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
