

val plaFirstWidth = 130
val materials = listOf(
        Material(name = "PLA/PLA+", extruderTemp = 218, bedTemp = 60, extrusionMultiplier = 1.05, defaultPrintingSpeed = 60, firstLayerWidthPercentage = plaFirstWidth),
        Material(name = "PETG", extruderTemp = 250, bedTemp = 70, extrusionMultiplier = 1.10, defaultPrintingSpeed = 60),
        Material(name = "TPU", extruderTemp = 220, bedTemp = 60, extrusionMultiplier = 1.0, defaultPrintingSpeed = 30, firstLayerHeightPercentage = 200, firstLayerWidthPercentage = 75)
)

data class Material(val name: String,
                    val extruderTemp: Int,
                    val bedTemp: Int,
                    val extrusionMultiplier: Double,
                    val defaultPrintingSpeed: Int,
                    val firstLayerHeightPercentage: Int = 90,
                    val firstLayerWidthPercentage: Int = 110
)