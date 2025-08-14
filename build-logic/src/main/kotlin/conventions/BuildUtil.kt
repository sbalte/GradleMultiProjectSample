package conventions

import java.io.File
import java.io.FileInputStream
import java.util.*

object BuildUtil {
    fun property(propFile: String): Properties =
        (File(propFile) to Properties()).let { pair ->
            FileInputStream(pair.first).use { fis ->
                return pair.second.also { props ->
                    props.load(fis)
                }
            }
        }
}