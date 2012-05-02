package myband



import org.junit.*
import grails.test.mixin.*

@TestFor(GigController)
@Mock(Gig)
class GigControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gig/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gigInstanceList.size() == 0
        assert model.gigInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.gigInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gigInstance != null
        assert view == '/gig/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gig/show/1'
        assert controller.flash.message != null
        assert Gig.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gig/list'


        populateValidParams(params)
        def gig = new Gig(params)

        assert gig.save() != null

        params.id = gig.id

        def model = controller.show()

        assert model.gigInstance == gig
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gig/list'


        populateValidParams(params)
        def gig = new Gig(params)

        assert gig.save() != null

        params.id = gig.id

        def model = controller.edit()

        assert model.gigInstance == gig
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gig/list'

        response.reset()


        populateValidParams(params)
        def gig = new Gig(params)

        assert gig.save() != null

        // test invalid parameters in update
        params.id = gig.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gig/edit"
        assert model.gigInstance != null

        gig.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gig/show/$gig.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gig.clearErrors()

        populateValidParams(params)
        params.id = gig.id
        params.version = -1
        controller.update()

        assert view == "/gig/edit"
        assert model.gigInstance != null
        assert model.gigInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gig/list'

        response.reset()

        populateValidParams(params)
        def gig = new Gig(params)

        assert gig.save() != null
        assert Gig.count() == 1

        params.id = gig.id

        controller.delete()

        assert Gig.count() == 0
        assert Gig.get(gig.id) == null
        assert response.redirectedUrl == '/gig/list'
    }
}
