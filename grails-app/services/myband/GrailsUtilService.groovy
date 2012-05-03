package myband

import org.codehaus.groovy.grails.commons.GrailsClass

class GrailsUtilService {

    boolean transactional = false

    def grailsApplication

    static Map<String, GrailsClass> domainCache = new HashMap<String, GrailsClass>()
    
    /**
     * Create an instance of the Domain class. Since we aren't passing in package
     * names we need to loop over the artefacts and find the one we are looking for.
     * If we were passing in package names we could just call the getDomainClass method
     *
     * @param name The short name of the Domain class we want to create
     * @return Instance of the requested Domain class
     */
    Object createDomainInstance(String name) {
        if (domainCache[name])
            return domainCache[name].newInstance()

        def artefacts = grailsApplication.getArtefacts("Domain")
        def result = artefacts.find { item ->
            item.name == name
        }
        
        if (!domainCache[name])
            domainCache[name] = result

        return result.newInstance()
    }
}
