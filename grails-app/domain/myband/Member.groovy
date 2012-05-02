package myband
class Member {

    String firstName
    String lastName
    String phoneNumber
    Date dateCreated // grails will auto timestamp
    Date lastUpdated // grails will auto timestamp

    static hasMany = [instruments: Instrument]

    static constraints = {
        firstName(blank: false)
        lastName(blank: false)
        phoneNumber(blank: false, matches: "^\\(?(\\d{3})\\)?[- .]?(\\d{3})[- .]?(\\d{4})\$")
        dateCreated(display: false)
        lastUpdated(display: false)
    }

    static mapping = {
        instruments joinTable: [name: "MEMBER_INSTRUMENT", column: "INSTRUMENT_ID", key: "MEMBER_ID"]
    }

    String toString() { "${firstName} ${lastName}" }
}
