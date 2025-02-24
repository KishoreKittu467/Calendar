class JVMPlatform: Platform {
    override val name: String = "JVM Platform"
}

actual fun getPlatform(): Platform = JVMPlatform()