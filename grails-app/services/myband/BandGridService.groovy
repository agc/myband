package myband

import org.hibernate.criterion.CriteriaSpecification

class BandGridService extends GridService {

    boolean transactional = true

    def processor = { results ->
        def cells = results.collect {
            [
                cell: [
                    it.name,
                    it.members*.toString()
                ],
                id: it.id
            ]
        }
        return cells
    }

    @Override
    Map generate(Map params, String entity) {
        generate(params, entity, processor)
    }


    @Override
    List query(Map params, Integer maxRows, Integer rowOffset,
               String sortIndex, String sortOrder, String domain) {
        def instance = grailsUtilService.createDomainInstance(domain)
        def results = instance.createCriteria().list(max: maxRows, offset: rowOffset) {
            and {
                // case insensitive, term can be anywhere
                if (params.name)
                    ilike('name', '%' + params.name + '%')

                if (params.members) {
                    members {
                        or {
                            ilike('firstName', '%' + params.members + '%')
                            ilike('lastName', '%' + params.members + '%')
                        }
                    }
                }
            }
            order(sortIndex, sortOrder)
            resultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
        }
        results
    }
}
