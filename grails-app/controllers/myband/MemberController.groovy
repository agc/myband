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

    def create = {
        def memberInstance = new Member()
        memberInstance.properties = params
      [memberInstance: memberInstance,instruments:Instrument.list()]
    }

    def save = {
        def memberInstance = new Member(params)
        if (memberInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'member.label', default: 'Member'), memberInstance.id])}"
            redirect(action: "show", id: memberInstance.id)
        }
        else {
            render(view: "create", model: [memberInstance: memberInstance,instruments:Instrument.list(params)])
        }
    }

    def show = {
        def memberInstance = Member.get(params.id)
        if (!memberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
        else {
            [memberInstance: memberInstance]
        }
    }

    def edit = {
        def memberInstance = Member.get(params.id)
        if (!memberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [memberInstance: memberInstance]
        }
    }

    def update = {
        def memberInstance = Member.get(params.id)
        if (memberInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (memberInstance.version > version) {

                    memberInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'member.label', default: 'Member')] as Object[], "Another user has updated this Member while you were editing")
                    render(view: "edit", model: [memberInstance: memberInstance])
                    return
                }
            }
            memberInstance.properties = params
            if (!memberInstance.hasErrors() && memberInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'member.label', default: 'Member'), memberInstance.id])}"
                redirect(action: "show", id: memberInstance.id)
            }
            else {
                render(view: "edit", model: [memberInstance: memberInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
    }
}