import grails.util.Environment
import myband.Band
import myband.Member
import myband.Instrument
import myband.Song
import myband.SetList
import myband.Gig
class BootStrap {

    def init = { servletContext ->
	         // Only want to insert for dev testing
	         if (Environment.isDevelopmentMode()) {
	             def acousticGuitar = new Instrument(name: "Acoustic Guitar").save()
	             def electricGuitar = new Instrument(name: "Electric Guitar").save()
	             def drums = new Instrument(name: "Drums").save()
	             def piano = new Instrument(name: "Piano").save()

	             def singer = new Member(firstName: "Joe",
	                                     lastName: "Singer",
	                                     phoneNumber: "515.595.1234",
	                                     instruments: [electricGuitar]).save()

	             def guitarist = new Member(firstName: "Tim",
	                                        lastName: "Guitarist",
	                                        phoneNumber: "515.595.1235",
	                                        instruments: [acousticGuitar, piano]).save()

	             def drummer = new Member(firstName: "Adam",
	                                      lastName: "Drummer",
	                                      phoneNumber: "515.595.1236",
	                                      instruments: [drums]).save()

	             def song1 = new Song(name: "Long Lost").save()
	             def song2 = new Song(name: "No Home").save()

	             def gig1 = new Gig(venue: "Valair Ballroom", startTime: new Date()).save()

	             def myBand = new Band(name: "My Band",
	                                   members: [singer, guitarist, drummer],
	                                   songs: [song1, song2],
	                                   gigs: [gig1]).save()

	         }
	     }
    def destroy = {
    }
}
