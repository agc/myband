package myband



import org.junit.*
import grails.test.mixin.*

@TestFor(InstrumentController)
@Mock(Instrument)
class InstrumentControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/instrument/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.instrumentInstanceList.size() == 0
        assert model.instrumentInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.instrumentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.instrumentInstance != null
        assert view == '/instrument/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/instrument/show/1'
        assert controller.flash.message != null
        assert Instrument.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/instrument/list'


        populateValidParams(params)
        def instrument = new Instrument(params)

        assert instrument.save() != null

        params.id = instrument.id

        def model = controller.show()

        assert model.instrumentInstance == instrument
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/instrument/list'


        populateValidParams(params)
        def instrument = new Instrument(params)

        assert instrument.save() != null

        params.id = instrument.id

        def model = controller.edit()

        assert model.instrumentInstance == instrument
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/instrument/list'

        response.reset()


        populateValidParams(params)
        def instrument = new Instrument(params)

        assert instrument.save() != null

        // test invalid parameters in update
        params.id = instrument.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/instrument/edit"
        assert model.instrumentInstance != null

        instrument.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/instrument/show/$instrument.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        instrument.clearErrors()

        populateValidParams(params)
        params.id = instrument.id
        params.version = -1
        controller.update()

        assert view == "/instrument/edit"
        assert model.instrumentInstance != null
        assert model.instrumentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/instrument/list'

        response.reset()

        populateValidParams(params)
        def instrument = new Instrument(params)

        assert instrument.save() != null
        assert Instrument.count() == 1

        params.id = instrument.id

        controller.delete()

        assert Instrument.count() == 0
        assert Instrument.get(instrument.id) == null
        assert response.redirectedUrl == '/instrument/list'
    }
}
