package myband
class Band {

    String name
    Date dateCreated // grails will auto timestamp
    Date lastUpdated // grails will auto timestamp

    static hasMany = [members: Member, songs: Song, gigs: Gig]

    static constraints = {
        name(blank: false, unique: true)
        dateCreated(display: false)
        lastUpdated(display: false)
    }

    static mapping = {
        members joinTable: [name: "BAND_MEMBER", column: "MEMBER_ID", key: "BAND_ID"]
        songs joinTable: [name: "BAND_SONG", column: "SONG_ID", key: "BAND_ID"]
        gigs joinTable: [name: "BAND_GIG", column: "GIG_ID", key: "BAND_ID"]
    }

    String toString() { name }
}

