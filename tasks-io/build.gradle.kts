plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

val test by tasks
printFiles("'test' task inputs: ", test.inputs.files)
printFiles("'test' task outputs: ", test.outputs.files)

fun printFiles(header: String, files: FileCollection) {
    logger.lifecycle(header)
    files.forEach { f -> logger.lifecycle(" -> $f") }
}
