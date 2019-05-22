package org.opal

interface Version {

    val major: Int
    val minor: Int
    val patch: Int
    val snapshot: Boolean
    
}