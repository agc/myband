package myband

import org.hibernate.criterion.CriteriaSpecification

class MemberGridService extends GridService {

    boolean transactional = true

    def processor = { results ->
        def cells = results.collect {
            [
                cell: [
                    it.firstName,
                    it.lastName,
                    it.phoneNumber,
                    it.instruments*.name
                ],
                id: it.id
            ]
        }
        return cells
    }

    // We could have just passed the closure in from the controller
    // but this seperates things a little better
    @Override
    Map generate(Map params, String entity) {
        generate(params, entity, processor)
    }


    @Override
    List query(Map params, Integer maxRows, Integer rowOffset,
               String sortIndex, String sortOrder, String domain) {
        // Stay generic and call util service to create an instance of the
        // domain object we are working with
        def instance = grailsUtilService.createDomainInstance(domain)
        // Perform query dealing with pagination params
        def results = instance.createCriteria().list(max: maxRows, offset: rowOffset) {
            and {
                // case insensitive, term can be anywhere
                if (params.firstName)
                    ilike('firstName', '%' + params.firstName + '%')

                if (params.lastName)
                    ilike('lastName', '%' + params.lastName + '%')

                if (params.phoneNumber)
                    ilike('phoneNumber', '%' + params.phoneNumber + '%')

                if (params.instruments) {
                    instruments {
                        ilike('name', '%' + params.instruments + '%')
                    }
                }
            }
            order(sortIndex, sortOrder)
            resultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
        }

        // Return the results (last statement is always the return value (unless there
        // is a specific return statement)
        results
    }
}
