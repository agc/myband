package myband



import org.junit.*
import grails.test.mixin.*

@TestFor(SetListController)
@Mock(SetList)
class SetListControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/setList/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.setListInstanceList.size() == 0
        assert model.setListInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.setListInstance != null
    }

    void testSave() {
        controller.save()

        assert model.setListInstance != null
        assert view == '/setList/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/setList/show/1'
        assert controller.flash.message != null
        assert SetList.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/setList/list'


        populateValidParams(params)
        def setList = new SetList(params)

        assert setList.save() != null

        params.id = setList.id

        def model = controller.show()

        assert model.setListInstance == setList
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/setList/list'


        populateValidParams(params)
        def setList = new SetList(params)

        assert setList.save() != null

        params.id = setList.id

        def model = controller.edit()

        assert model.setListInstance == setList
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/setList/list'

        response.reset()


        populateValidParams(params)
        def setList = new SetList(params)

        assert setList.save() != null

        // test invalid parameters in update
        params.id = setList.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/setList/edit"
        assert model.setListInstance != null

        setList.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/setList/show/$setList.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        setList.clearErrors()

        populateValidParams(params)
        params.id = setList.id
        params.version = -1
        controller.update()

        assert view == "/setList/edit"
        assert model.setListInstance != null
        assert model.setListInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/setList/list'

        response.reset()

        populateValidParams(params)
        def setList = new SetList(params)

        assert setList.save() != null
        assert SetList.count() == 1

        params.id = setList.id

        controller.delete()

        assert SetList.count() == 0
        assert SetList.get(setList.id) == null
        assert response.redirectedUrl == '/setList/list'
    }
}
