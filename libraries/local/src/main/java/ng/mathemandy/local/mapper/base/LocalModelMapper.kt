package ng.mathemandy.local.mapper.base

interface LocalModelMapper<M, E> {

    fun mapToModel(entity: E): M

    fun mapToEntity(model: M): E

    fun mapToEntityList(models: List<M>): List<E> {
        return models.mapTo(mutableListOf(), ::mapToEntity)
    }

    fun mapToModelList(entities: List<E>): List<M> {
        return entities.mapTo(mutableListOf(), ::mapToModel)
    }
}
