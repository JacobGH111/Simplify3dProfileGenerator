

val plaFirstWidth = 100
val materials = listOf(
        Material(name = "PLA/PLA+", extruderTemp = 218, bedTemp = 60, extrusionMultiplier = 1.0, defaultPrintingSpeed = 60, firstLayerWidthPercentage = plaFirstWidth),
        Material(name = "PETG", extruderTemp = 250, bedTemp = 70, extrusionMultiplier = 1.0, defaultPrintingSpeed = 60, firstLayerWidthPercentage = 100),
        Material(name = "TPU", extruderTemp = 200, bedTemp = 60, extrusionMultiplier = 1.15, defaultPrintingSpeed = 30, firstLayerHeightPercentage = 200, firstLayerWidthPercentage = 75),
        Material(name = "Polyproylene", extruderTemp = 220, bedTemp = 85, extrusionMultiplier = 1.0, defaultPrintingSpeed = 40, firstLayerWidthPercentage = 500, firstLayerHeightPercentage = 70,
                skirt = Skirt(layers = 3, offSetMM = 0, outlines = 9)
        ),
        Material(name = "Nylon - CF Reinforced", extruderTemp = 280, bedTemp = 50, extrusionMultiplier = 1.0, defaultPrintingSpeed = 40, firstLayerWidthPercentage = 100)
)

data class Skirt(
        val layers: Int,
        val offSetMM: Int,
        val outlines: Int
)
data class Material(val name: String,
                    val extruderTemp: Int,
                    val bedTemp: Int,
                    val extrusionMultiplier: Double,
                    val defaultPrintingSpeed: Int,
                    val firstLayerHeightPercentage: Int = 90,
                    val firstLayerWidthPercentage: Int,
                    val skirt: Skirt? = null
){
    val skirtBoolNum = if(skirt == null) 0 else 1
}