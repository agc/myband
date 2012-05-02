package myband
class SetList {

    String name
    Date dateCreated // grails will auto timestamp
    Date lastUpdated // grails will auto timestamp

    static hasMany = [songs: Song]

    static constraints = {
        name(blank: false, unique: true)
        dateCreated(display: false)
        lastUpdated(display: false)
    }

    static mapping = {
        songs joinTable: [name: "SET_LIST_SONG", column: "SONG_ID", key: "SET_LIST_ID"]
    }

    String toString() { name }
}