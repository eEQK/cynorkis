package cynorkis.cache.objectbox

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
internal open class ObjectboxCacheModel(
    @Id var id: Long = 0,
    var responseId: String,
    var statusCode: Int,
    var statusCodePhrase: String,
    var headers: String,
    var body: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ObjectboxCacheModel) return false

        if (id != other.id) return false
        if (statusCode != other.statusCode) return false
        if (statusCodePhrase != other.statusCodePhrase) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + statusCode
        result = 31 * result + statusCodePhrase.hashCode()
        return result
    }
}
