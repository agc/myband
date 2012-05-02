package myband
class Gig {

    String venue
    Date startTime
    Date dateCreated // grails will auto timestamp
    Date lastUpdated // grails will auto timestamp

    static hasMany = [setLists: SetList]
    static belongsTo = Band

    static constraints = {
        venue(blank: false)
        startTime(nullable: false, attributes: [precision: 'minutes'])
        dateCreated(display: false)
        lastUpdated(display: false)
    }

    static mapping = {
        setLists joinTable: [name: "GIG_SET_LIST", column: "SET_LIST_ID", key: "GIG_ID"]
    }

    String toString() { "${venue} - ${startTime.format('MM/dd/yyyy h:m a')}"}
}
