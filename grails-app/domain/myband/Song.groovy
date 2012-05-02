package myband
class Song {

    String name
    byte[] content
    Date dateCreated // grails will auto timestamp
    Date lastUpdated // grails will auto timestamp

    static belongsTo = Band

    static constraints = {
        name(blank: false, unique: true)
        content(nullable: true)
        dateCreated(display: false)
        lastUpdated(display: false)
    }

    String toString() { name }
}
