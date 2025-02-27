class JVMPlatform: Platform {
    override val name: String = "JVM Platform: Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()