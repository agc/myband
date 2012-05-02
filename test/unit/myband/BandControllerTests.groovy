package myband



import org.junit.*
import grails.test.mixin.*

@TestFor(BandController)
@Mock(Band)
class BandControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/band/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bandInstanceList.size() == 0
        assert model.bandInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.bandInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bandInstance != null
        assert view == '/band/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/band/show/1'
        assert controller.flash.message != null
        assert Band.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/band/list'


        populateValidParams(params)
        def band = new Band(params)

        assert band.save() != null

        params.id = band.id

        def model = controller.show()

        assert model.bandInstance == band
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/band/list'


        populateValidParams(params)
        def band = new Band(params)

        assert band.save() != null

        params.id = band.id

        def model = controller.edit()

        assert model.bandInstance == band
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/band/list'

        response.reset()


        populateValidParams(params)
        def band = new Band(params)

        assert band.save() != null

        // test invalid parameters in update
        params.id = band.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/band/edit"
        assert model.bandInstance != null

        band.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/band/show/$band.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        band.clearErrors()

        populateValidParams(params)
        params.id = band.id
        params.version = -1
        controller.update()

        assert view == "/band/edit"
        assert model.bandInstance != null
        assert model.bandInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/band/list'

        response.reset()

        populateValidParams(params)
        def band = new Band(params)

        assert band.save() != null
        assert Band.count() == 1

        params.id = band.id

        controller.delete()

        assert Band.count() == 0
        assert Band.get(band.id) == null
        assert response.redirectedUrl == '/band/list'
    }
}
