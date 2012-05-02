package myband
class Instrument {

    String name
    Date dateCreated // grails will auto timestamp
    Date lastUpdated // grails will auto timestamp

    static constraints = {
        name(blank: false, unique: true)
        dateCreated(display: false)
        lastUpdated(display: false)
    }

    String toString() { name }
}
