package myband

class GridService {

    // Utility service that will create a domain instance based off
    // the short name of the object.
    def grailsUtilService
    def messageSource
    boolean transactional = true

    // Default closure that collects the "name" field off the results passed in
    def defaultFields = {results ->
        // Collect the row data for the grid
        def cells = results.collect {
            [
                cell: [ it.name ], // Only displaying the "Name" field
                id: it.id   // Always need to pass the id down for the grid
            ]
        }
        return cells
    }

    // Default edit closure. Used for form / inline editing, form adding and row
    // delete
    def defaultEdit = { instance ->
        def result
        def message = ""
        def state = "FAIL"
        def id

        // determine our action. Grid will pass a param called "oper"
        switch (params.oper) {
            // Delete Request
            case 'del':
                result = instance.get(params.id)
                if (result) {
                    result.delete()
                    message = "${entity} ${result.name} Deleted"
                    state = "OK"
                }
                break;
            // Add Request
            case 'add':
                instance.properties = params
                result = instance
                break;
            // Edit Request
            case 'edit':
                // add or edit instruction sent
                result = instance.get(params.id)
                result.properties = params
                break;
        }

        // If we aren't deleting the object then we need to validate and save.
        // Capture any validation messages to display on the client side
        if (result && params.oper != "del") {
            if (!result.hasErrors() && result.save(flush: true)) {
                message = "${entity}  ${result.name} " + (params.oper == 'add') ? "Added" : "Updated"
                id = result.id
                state = "OK"
            } else {
                message = "<ul>"
                result.errors.allErrors.each {
                    message += "<li>${messageSource.getMessage(it)}</li>"
                }
                message += "</ul>"
            }
        }
        
        return [message:message, state:state, id:id]
    }

    /**
     * Generate our Grid rows/cells using the default closure and no parameters
     *
     * @param entity The Domain object shortname (no package definition)
     * @return The map of information that can be converted to JSON
     */
    Map generate(String entity) {
        generate([:], entity)
    }

    /**
     * Generate our Grid rows/cells using the default closure and any parameters
     * that were passed in.
     *
     * @param params The map of parameters to use
     * @param entity The Domain object shortname (no package definition)
     * @return The map of information that can be converted to JSON
     */
    Map generate(Map params, String entity) {
        generate(params, entity, defaultFields)
    }

    /**
     * Generate our Grid rows/cells using the parameters passed in. Use the supplied
     * closure (processor) to handle gathering the cells to display from the query
     * results.
     *
     * @param params The map of parameters to use
     * @param entity The Domain object shortname (no package definition)
     * @param processor The closure to use when processing query results
     * @return The map of information that can be converted to JSON
     */
    Map generate(Map params, String entity, processor) {
        def result

        // The field we are sorting on
        def sortIndex = params.sidx ?: 'name'
        // The sort order
        def sortOrder  = params.sord ?: 'asc'
        // Deal with any pagination values passed in
        def maxRows = Integer.valueOf(params.rows ?: 25)
        def currentPage = Integer.valueOf(params.page ?: 1)
        def rowOffset = (currentPage == 1) ? 0 : (currentPage - 1) * maxRows

        // Query the datastore for the requested information
        def results = query(params, maxRows, rowOffset,
                            sortIndex, sortOrder, entity)

        // Grid needs to know the total rows and pages
        def totalRows = results.totalCount
        def numberOfPages = Math.ceil(totalRows / maxRows)

        // Call the closure to pull the rows/cells from the query results
        def jsonCells = processor(results)

        // Grid requires the following information to be returned
        [rows: jsonCells,
         page: currentPage,
         records: totalRows,
         total: numberOfPages]
    }

    /**
     * Perform editing on the object based on grid values
     *
     * @param params The map of parameters to use
     * @param entity The Domain object short name (no package)
     * @return The map of information that can be converted to JSON
     */
    Map edit(Map params, String entity) {
        edit(params, entity, defaultEdit)
    }

    /**
     * Perform editing on the object based on grid values
     *
     * @param params The map of parameters to use
     * @param entity The Domain object short name (no package)
     * @param processor The closure to use for edit processing
     * @return The map of information that can be converted to JSON
     */
    Map edit(Map params, String entity, processor) {
        def instance = grailsUtilService.createDomainInstance(entity)
        processor(instance)
    }

    /**
     * Queries the data store for the given object using the information passed in
     * ** Override this method if you want to search by things other then name
     *
     * @param params The map of parameters to use
     * @param maxRows The maximum number of rows to return
     * @param rowOffset Where we should start with the results
     * @param sortIndex The field we are sorting on
     * @param sortOrder The sorting order
     * @param domain The Domain object short name (no package)
     * @return The list of objects from the data store
     */
    List query(Map params, Integer maxRows, Integer rowOffset,
               String sortIndex, String sortOrder, String domain) {
        // Stay generic and call util service to create an instance of the
        // domain object we are working with
        def instance = grailsUtilService.createDomainInstance(domain)
        // Perform query dealing with pagination params
        def results = instance.createCriteria().list(max: maxRows, offset: rowOffset) {
            // case insensitive, term can be anywhere
            if (params.name)
                ilike('name', '%' + params.name + '%')

            order(sortIndex, sortOrder)
        }

        // Return the results (last statement is always the return value (unless there
        // is a specific return statement)
        results
    }
}
