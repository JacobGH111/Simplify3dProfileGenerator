import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import java.math.BigDecimal
import java.math.RoundingMode
import javax.xml.parsers.DocumentBuilderFactory


fun main(args: Array<String>) {
    nozzles.forEach {
        writeProfile(it)
    }
}

/**
 * @param extruderTempOffSet For a larger diameter you might want to increase the temperature
 */
data class Nozzle(val diameter: Double, val extruderTempOffSet: Int)

val nozzles = listOf(
        Nozzle(diameter = 0.1, extruderTempOffSet = 0),
        Nozzle(diameter = 0.2, extruderTempOffSet = 0),
        Nozzle(diameter = 0.4, extruderTempOffSet = 0),
        Nozzle(diameter = 0.6, extruderTempOffSet = 0),
        Nozzle(diameter = 1.0, extruderTempOffSet = 15)
)


fun writeProfile(nozzle: Nozzle) {
    val homeDir = System.getProperty("user.home") + "/Desktop"
    val writeTo = "$homeDir/Ender_3_${nozzle.diameter}mm.fff"
    println("writing to $writeTo")
    File(writeTo).writeText(genProfile(nozzle))
}

data class Material(val name: String, val extruderTemp: Int, val bedTemp: Int, val extrusionMultiplier: Double, val defaultPrintingSpeed: Int)

val materials = listOf(
        Material(name = "PLA", extruderTemp = 210, bedTemp = 60, extrusionMultiplier = 1.05, defaultPrintingSpeed = 60),
        Material(name = "PLA+", extruderTemp = 210, bedTemp = 60, extrusionMultiplier = 1.05, defaultPrintingSpeed = 60),
        Material(name = "PETG", extruderTemp = 250, bedTemp = 70, extrusionMultiplier = 1.10, defaultPrintingSpeed = 60),
        Material(name = "TPU", extruderTemp = 220, bedTemp = 60, extrusionMultiplier = 1.0, defaultPrintingSpeed = 30)
)





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
    return """<autoConfigureMaterial name="${material.name}">
    <globalExtruderTemperature>${material.extruderTemp + nozzle.extruderTempOffSet}</globalExtruderTemperature>
    <globalBedTemperature>${material.bedTemp}</globalBedTemperature>
    <globalExtrusionMultiplier>${material.extrusionMultiplier}</globalExtrusionMultiplier>
    <fanSpeed>
      <setpoint layer="1" speed="100"/>
    </fanSpeed>
    <defaultSpeed>${material.defaultPrintingSpeed*60}</defaultSpeed>
  </autoConfigureMaterial>"""
}


fun String.prettyFormat(indent: Int): String {
    val dbFactory = DocumentBuilderFactory.newInstance()
    val dBuilder = dbFactory.newDocumentBuilder()
    val xmlInput = InputSource(StringReader(this))
    val doc = dBuilder.parse(xmlInput)
    return doc.toString()
}

fun Double.round(places: Int): Double {
    if (places < 0) throw IllegalArgumentException()

    var bd = BigDecimal(java.lang.Double.toString(this))
    bd = bd.setScale(places, RoundingMode.HALF_UP)
    return bd.toDouble()

}
