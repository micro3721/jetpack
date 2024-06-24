pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "jetpack"
include(":app")

// 动态包含所有模块
val moduleDir = file(".")
fun calculateModuleCount(): Int {
    val pattern = Regex("^module\\d{4}$")
    return moduleDir.listFiles()?.count { it.isDirectory && pattern.matches(it.name) } ?: 0
}

val moduleCount = calculateModuleCount()

for (i in 1..moduleCount) {
    include(":module%04d".format(i))
}

include(":module0002")
