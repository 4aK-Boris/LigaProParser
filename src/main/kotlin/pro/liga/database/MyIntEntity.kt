package pro.liga.database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.data.DTO
import kotlin.reflect.KClass

open class MyIntEntity<T: DTO>(id: EntityID<Int>, private val clazz: KClass<T>) : IntEntity(id = id), MainEntity {

    override fun toString(): String {
        return string
    }

    override fun equals(other: Any?): Boolean {
        return this.hasEquals(other = other, clazz = clazz)
    }

    override fun hashCode(): Int {
        return hashCode
    }
}