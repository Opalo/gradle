package org.opal

import java.lang.Integer.parseInt
import java.util.regex.Pattern.compile


open class AbstractVersion(version: String) : Version {

    protected val VERSION_PATTERN = compile("\\d+(\\.\\d+){0,2}(-SNAPSHOT)?")
    protected val SNAPSHOT_SUFFIX = "-SNAPSHOT"
    protected val EMPTY = ""
    protected val ZERO = "0"

    override val major: Int
    override val minor: Int
    override val patch: Int
    override val snapshot: Boolean

    init {
        checkIfMatchesPattern(version)
        this.snapshot = version.endsWith(SNAPSHOT_SUFFIX)
        val splitted = version.replace(SNAPSHOT_SUFFIX, EMPTY).split(".")
        this.major = parseInt(splitted[0])
        this.minor = parseInt(if (splitted.size >= 2) splitted[1] else ZERO)
        this.patch = parseInt(if (splitted.size == 3) splitted[2] else ZERO)
    }

    private fun checkIfMatchesPattern(version: String) {
        if (!VERSION_PATTERN.matcher(version).matches()) {
            throw IllegalArgumentException("'version' must match: 'major.minor.patch(-SNAPSHOT)'!")
        }
    }

}
