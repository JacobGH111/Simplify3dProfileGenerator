import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode


fun main(args: Array<String>) {
    nozzles.forEach {
        writeProfile(it)
    }
}

/**
 * @param extruderTempOffSet For a larger diameter you might want to increase the temperature
 */
data class Nozzle(val diameter: Double, val extruderTempOffSet: Int = 0, val defaultSpeedMultiplier: Double = 1.0)

val nozzles = listOf(
//        Nozzle(diameter = 0.1),
//        Nozzle(diameter = 0.2),
        Nozzle(diameter = 0.4)
//        Nozzle(diameter = 0.6),
//        Nozzle(diameter = 1.0, extruderTempOffSet = 30, defaultSpeedMultiplier = .5)
)


fun writeProfile(nozzle: Nozzle) {
    val homeDir = System.getProperty("user.home")
    val writeTo = "$homeDir/s3dprofiles/Ender_3_${nozzle.diameter}mm.fff"
    println("writing to $writeTo")
    File(writeTo).writeText(genProfile(nozzle))
}










fun genQuality(name: String, layerHeight: Double, infillPercntage: Int): String {
    return """<autoConfigureQuality name="$name">
    <layerHeight>$layerHeight</layerHeight>
    <topSolidLayers>3</topSolidLayers>
    <bottomSolidLayers>3</bottomSolidLayers>
    <skirtLayers>1</skirtLayers>
    <infillPercentage>$infillPercntage</infillPercentage>
    <supportInfillPercentage>25</supportInfillPercentage>
</autoConfigureQuality>"""
}

fun genMaterial(material: Material, nozzle: Nozzle): String {

    val purgeZHeight = "0.4"
    val xPurgeLine1 = "6.0"
    val xLine2 = "7.0"
    return """<autoConfigureMaterial name="${material.name}">
    <globalExtruderTemperature>${material.extruderTemp + nozzle.extruderTempOffSet}</globalExtruderTemperature>
    <globalBedTemperature>${material.bedTemp}</globalBedTemperature>
    <globalExtrusionMultiplier>${material.extrusionMultiplier}</globalExtrusionMultiplier>
     <startingGcode>
        G92 E0 ; Reset Extruder distance to 0,
        G1 E-3.5 ; Retracts filament to prevent blobs during probing,
        G28 ; home all axes,
        G28 Z ; home Z to get more accurate Z position,
        BED_MESH_CALIBRATE,
        M117 Purge extruder,
        G92 E0 ; reset extruder,
        G1 Z1.0 F3000 ; move z up little,
        G1 X$xPurgeLine1 Y20 Z$purgeZHeight F5000.0 ; move to start-line position,
        G1 X$xPurgeLine1 Y100.0 Z$purgeZHeight F750.0 E15 ; draw 1st line,
        G1 X$xLine2 Y100.0 Z$purgeZHeight F5000.0 ; move to side a little,
        G1 X$xLine2 Y20 Z$purgeZHeight F750.0 E30 ; draw 2nd line,
        G92 E0 ; reset extruder,
        G1 Z1.0 F3000 ; move z up little,
        M117 Printing.....</startingGcode>
    <fanSpeed>
      <setpoint layer="1" speed="100"/>
    </fanSpeed>
    <defaultSpeed>${material.defaultPrintingSpeed*60*nozzle.defaultSpeedMultiplier}</defaultSpeed>
    <firstLayerHeightPercentage>${material.firstLayerHeightPercentage}</firstLayerHeightPercentage>
    <firstLayerWidthPercentage>${material.firstLayerWidthPercentage}</firstLayerWidthPercentage>
  <useSkirt>${material.skirtBoolNum}</useSkirt>
  <skirtLayers>${material.skirt?.layers ?: 0}</skirtLayers>
  <skirtOutlines>${material.skirt?.outlines ?: 0}</skirtOutlines>
  <skirtOffset>${material.skirt?.offSetMM ?: 0}</skirtOffset>
  </autoConfigureMaterial>"""
}



fun Double.round(places: Int): Double {
    if (places < 0) throw IllegalArgumentException()

    var bd = BigDecimal(java.lang.Double.toString(this))
    bd = bd.setScale(places, RoundingMode.HALF_UP)
    return bd.toDouble()

}
