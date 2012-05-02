package myband



import org.junit.*
import grails.test.mixin.*

@TestFor(SongController)
@Mock(Song)
class SongControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/song/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.songInstanceList.size() == 0
        assert model.songInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.songInstance != null
    }

    void testSave() {
        controller.save()

        assert model.songInstance != null
        assert view == '/song/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/song/show/1'
        assert controller.flash.message != null
        assert Song.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/song/list'


        populateValidParams(params)
        def song = new Song(params)

        assert song.save() != null

        params.id = song.id

        def model = controller.show()

        assert model.songInstance == song
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/song/list'


        populateValidParams(params)
        def song = new Song(params)

        assert song.save() != null

        params.id = song.id

        def model = controller.edit()

        assert model.songInstance == song
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/song/list'

        response.reset()


        populateValidParams(params)
        def song = new Song(params)

        assert song.save() != null

        // test invalid parameters in update
        params.id = song.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/song/edit"
        assert model.songInstance != null

        song.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/song/show/$song.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        song.clearErrors()

        populateValidParams(params)
        params.id = song.id
        params.version = -1
        controller.update()

        assert view == "/song/edit"
        assert model.songInstance != null
        assert model.songInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/song/list'

        response.reset()

        populateValidParams(params)
        def song = new Song(params)

        assert song.save() != null
        assert Song.count() == 1

        params.id = song.id

        controller.delete()

        assert Song.count() == 0
        assert Song.get(song.id) == null
        assert response.redirectedUrl == '/song/list'
    }
}
