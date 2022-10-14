package pro.liga.database

import io.ktor.util.reflect.instanceOf
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.jetbrains.exposed.dao.Entity
import pro.liga.data.DTO
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

interface MainEntity {

    val hashCode: Int
        get() {
            return this::class.declaredMemberProperties.fold(initial = 0) { value, property ->
                if (!property.isConst && !property.instanceOf(Entity::class)) {
                    value hashCode property.getter.call(this).hashCode()
                } else {
                    value
                }
            }
        }

    fun <T: DTO> hasEquals(other: Any?, clazz: KClass<T>): Boolean {
        return when {

            other == null -> false

            other::class == this::class -> {
                other::class.declaredMemberProperties.zip(this::class.declaredMemberProperties)
                    .map { (otherP, P) ->
                        P.isAccessible = true
                        otherP.isAccessible = true
                        otherP to P
                    }
                    .fold(initial = true) { value, (otherP, P) ->
                        value && (P.returnType == otherP.returnType && P.getter.call(this) == otherP.getter.call(
                            other
                        ))
                    }
            }

            other::class == clazz -> {
                other::class.declaredMemberProperties.filterNot { contains(it.name) }
                    .fold(initial = true) { value, otherP ->
                        value && this::class.declaredMemberProperties.firstOrNull {
                            it.name == otherP.name
                        }?.let {
                            (it.returnType == otherP.returnType && it.getter.call(this) == otherP.getter.call(
                                other
                            ))
                        } ?: run {
                            if (otherP.name == "id") {
                                ((this as Entity<*>).id.value == otherP.getter.call(other))
                            } else {
                                false
                            }
                        }
                    }
                true
            }

            else -> false
        }
    }

    val string: String
        get() {
            return this::class.declaredMemberProperties.filterNot {
                it.isAccessible = true
                containsToString(it.returnType.toString())
            }.joinToString(", ") {
                val value = when {
                    it.returnType.toString().contains(other = LocalDate::class.qualifiedName ?: "") ->
                        (it.getter.call(this) as LocalDate).format(formatterDate)

                    it.returnType.toString().contains(other = LocalDateTime::class.qualifiedName ?: "")
                    -> (it.getter.call(this) as LocalDate).format(formatterDateTime)

                    else -> it.getter.call(this)
                }
                "${it.name} = $value"
            }
        }

    fun containsToString(name: String) = name.contains(other = Transaction::class.simpleName.orEmpty())
            || name.contains(other = Entity::class.simpleName.orEmpty())

    fun contains(name: String) = name.contains("DTO") || name.contains("id")

    infix fun Int.hashCode(x: Int) = this * HASH_CODE + x

    companion object {
        private const val HASH_CODE = 31
        private val formatterDateTime: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
        private val formatterDate: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }
}

