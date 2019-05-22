package org.opal

import org.junit.Test
import java.lang.reflect.Constructor
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VersionTest {

    @Test
    fun shouldSetMajorPropertyCorrectly() {
        val version = newVersion("1")

        assertEquals(version.major, 1)
        assertEquals(version.minor, 0)
        assertEquals(version.patch, 0)
        assertFalse { version.snapshot }
    }

    @Test
    fun shouldSetMajorAndSnapshotPropertiesCorrectly() {
        val version = newVersion("1-SNAPSHOT")

        assertEquals(version.major, 1)
        assertEquals(version.minor, 0)
        assertEquals(version.patch, 0)
        assertTrue { version.snapshot }
    }

    @Test
    fun shouldSetMajorMinorPropertiesCorrectly() {
        val version = newVersion("1.2")

        assertEquals(version.major, 1)
        assertEquals(version.minor, 2)
        assertEquals(version.patch, 0)
        assertFalse { version.snapshot }
    }

    @Test
    fun shouldSetMajorMinorAndSnapshotPropertiesCorrectly() {
        val version = newVersion("1.2-SNAPSHOT")

        assertEquals(version.major, 1)
        assertEquals(version.minor, 2)
        assertEquals(version.patch, 0)
        assertTrue { version.snapshot }
    }

    @Test
    fun shouldSetMajorMinorPatchPropertiesCorrectly() {
        val version = newVersion("1.2.3")

        assertEquals(version.major, 1)
        assertEquals(version.minor, 2)
        assertEquals(version.patch, 3)
        assertFalse { version.snapshot }
    }

    @Test
    fun shouldSetMajorMinorPatchAndSnapshotPropertiesCorrectly() {
        val version = newVersion("1.2.3-SNAPSHOT")

        assertEquals(version.major, 1)
        assertEquals(version.minor, 2)
        assertEquals(version.patch, 3)
        assertTrue { version.snapshot }
    }

    @Suppress("UNCHECKED_CAST")
    fun newVersion(version: String): AbstractVersion {
        val className: String = System.getenv().getOrDefault("VERSION_CLASS", "DefaultVersion")
        val classObj: Class<AbstractVersion> = javaClass.classLoader.loadClass("org.opal.$className") as Class<AbstractVersion>
        val constructor: Constructor<AbstractVersion> = classObj.getDeclaredConstructor(String::class.java) as Constructor<AbstractVersion>
        return constructor.newInstance(version)
    }

}