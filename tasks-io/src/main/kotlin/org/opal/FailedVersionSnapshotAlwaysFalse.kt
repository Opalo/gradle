package org.opal

class FailedVersionSnapshotAlwaysFalse(version: String) : AbstractVersion(version) {

    override val snapshot: Boolean
        get() = false
    
}
