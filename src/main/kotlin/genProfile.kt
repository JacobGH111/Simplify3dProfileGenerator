
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import java.math.BigDecimal
import java.math.RoundingMode
import javax.xml.parsers.DocumentBuilderFactory


fun main(args: Array<String>){
    writeProfile(nozzleSize = 0.1)
    writeProfile(nozzleSize =0.2)
    writeProfile(nozzleSize =0.4)
    writeProfile(nozzleSize =0.6)
    writeProfile(nozzleSize =1.0)
}

fun writeProfile(nozzleSize: Double){
    val homeDir = System.getProperty("user.home") + "/Desktop"
    val writeTo = "$homeDir/Ender_3_${nozzleSize}mm.fff"
    println("writing to $writeTo")
    File(writeTo).writeText(genProfile(nozzleSize))
}

data class Material(val name: String, val extruderTemp: Int, val bedTemp: Int, val extrusionMultiplier: Double)
val pla = Material(name = "PLA", extruderTemp = 200, bedTemp = 60, extrusionMultiplier = 1.05)
val plaPlus = Material(name = "PLA+", extruderTemp = 210, bedTemp = 60, extrusionMultiplier = 1.05)
val petg = Material(name = "PETG", extruderTemp = 250, bedTemp = 70, extrusionMultiplier = 1.10)


fun genProfile(nozzleSize: Double): String{
    return """<?xml version="1.0"?>
<profile name="Ender 3 - ${nozzleSize}mm" version="2019-09-01 15:04:13" app="S3D-Software 4.1.2">
  <baseProfile></baseProfile>
  <printMaterial>PETG</printMaterial>
  <printQuality>Medium</printQuality>
  <printExtruders></printExtruders>
  <extruder name="Extruder ${nozzleSize}mm">
    <toolheadNumber>0</toolheadNumber>
    <diameter>${nozzleSize}</diameter>
    <autoWidth>1</autoWidth>
    <width>${nozzleSize*1.2}</width>
    <extrusionMultiplier>1</extrusionMultiplier>
    <useRetract>1</useRetract>
    <retractionDistance>4</retractionDistance>
    <extraRestartDistance>0</extraRestartDistance>
    <retractionZLift>0</retractionZLift>
    <retractionSpeed>2400</retractionSpeed>
    <useCoasting>0</useCoasting>
    <coastingDistance>0.2</coastingDistance>
    <useWipe>0</useWipe>
    <wipeDistance>5</wipeDistance>
  </extruder>
  <primaryExtruder>0</primaryExtruder>
  <layerHeight>0.2</layerHeight>
  <topSolidLayers>3</topSolidLayers>
  <bottomSolidLayers>3</bottomSolidLayers>
  <perimeterOutlines>2</perimeterOutlines>
  <printPerimetersInsideOut>1</printPerimetersInsideOut>
  <startPointOption>2</startPointOption>
  <startPointOriginX>0</startPointOriginX>
  <startPointOriginY>0</startPointOriginY>
  <sequentialIslands>0</sequentialIslands>
  <spiralVaseMode>0</spiralVaseMode>
  <firstLayerHeightPercentage>90</firstLayerHeightPercentage>
  <firstLayerWidthPercentage>110</firstLayerWidthPercentage>
  <firstLayerUnderspeed>0.5</firstLayerUnderspeed>
  <useRaft>0</useRaft>
  <raftExtruder>0</raftExtruder>
  <raftTopLayers>3</raftTopLayers>
  <raftBaseLayers>2</raftBaseLayers>
  <raftOffset>3</raftOffset>
  <raftSeparationDistance>0.14</raftSeparationDistance>
  <raftTopInfill>100</raftTopInfill>
  <aboveRaftSpeedMultiplier>0.3</aboveRaftSpeedMultiplier>
  <useSkirt>1</useSkirt>
  <skirtExtruder>0</skirtExtruder>
  <skirtLayers>1</skirtLayers>
  <skirtOutlines>2</skirtOutlines>
  <skirtOffset>4</skirtOffset>
  <usePrimePillar>0</usePrimePillar>
  <primePillarExtruder>999</primePillarExtruder>
  <primePillarWidth>12</primePillarWidth>
  <primePillarLocation>7</primePillarLocation>
  <primePillarSpeedMultiplier>1</primePillarSpeedMultiplier>
  <useOozeShield>0</useOozeShield>
  <oozeShieldExtruder>999</oozeShieldExtruder>
  <oozeShieldOffset>2</oozeShieldOffset>
  <oozeShieldOutlines>1</oozeShieldOutlines>
  <oozeShieldSidewallShape>1</oozeShieldSidewallShape>
  <oozeShieldSidewallAngle>30</oozeShieldSidewallAngle>
  <oozeShieldSpeedMultiplier>1</oozeShieldSpeedMultiplier>
  <infillExtruder>0</infillExtruder>
  <internalInfillPattern>Rectilinear</internalInfillPattern>
  <externalInfillPattern>Rectilinear</externalInfillPattern>
  <infillPercentage>30</infillPercentage>
  <outlineOverlapPercentage>15</outlineOverlapPercentage>
  <infillExtrusionWidthPercentage>100</infillExtrusionWidthPercentage>
  <minInfillLength>5</minInfillLength>
  <infillLayerInterval>1</infillLayerInterval>
  <internalInfillAngles>45,-45</internalInfillAngles>
  <overlapInternalInfillAngles>0</overlapInternalInfillAngles>
  <externalInfillAngles>45,-45</externalInfillAngles>
  <generateSupport>1</generateSupport>
  <supportExtruder>0</supportExtruder>
  <supportInfillPercentage>30</supportInfillPercentage>
  <supportExtraInflation>0</supportExtraInflation>
  <supportBaseLayers>0</supportBaseLayers>
  <denseSupportExtruder>0</denseSupportExtruder>
  <denseSupportLayers>0</denseSupportLayers>
  <denseSupportInfillPercentage>70</denseSupportInfillPercentage>
  <supportLayerInterval>1</supportLayerInterval>
  <supportHorizontalPartOffset>0.3</supportHorizontalPartOffset>
  <supportUpperSeparationLayers>1</supportUpperSeparationLayers>
  <supportLowerSeparationLayers>1</supportLowerSeparationLayers>
  <supportType>0</supportType>
  <supportGridSpacing>4</supportGridSpacing>
  <maxOverhangAngle>45</maxOverhangAngle>
  <supportAngles>0</supportAngles>
  <temperatureController name="Primary Extruder">
    <temperatureNumber>0</temperatureNumber>
    <isHeatedBed>0</isHeatedBed>
    <stabilizeAtStartup>1</stabilizeAtStartup>
    <setpoint layer="1" temperature="250"/>
  </temperatureController>
  <temperatureController name="Heated Bed">
    <temperatureNumber>0</temperatureNumber>
    <isHeatedBed>1</isHeatedBed>
    <stabilizeAtStartup>1</stabilizeAtStartup>
    <setpoint layer="1" temperature="70"/>
  </temperatureController>
  <fanSpeed>
    <setpoint layer="1" speed="0"/>
  </fanSpeed>
  <blipFanToFullPower>0</blipFanToFullPower>
  <adjustSpeedForCooling>1</adjustSpeedForCooling>
  <minSpeedLayerTime>15</minSpeedLayerTime>
  <minCoolingSpeedSlowdown>20</minCoolingSpeedSlowdown>
  <increaseFanForCooling>0</increaseFanForCooling>
  <minFanLayerTime>45</minFanLayerTime>
  <maxCoolingFanSpeed>100</maxCoolingFanSpeed>
  <increaseFanForBridging>0</increaseFanForBridging>
  <bridgingFanSpeed>100</bridgingFanSpeed>
  <use5D>1</use5D>
  <relativeEdistances>0</relativeEdistances>
  <allowEaxisZeroing>1</allowEaxisZeroing>
  <independentExtruderAxes>0</independentExtruderAxes>
  <includeM10123>0</includeM10123>
  <stickySupport>1</stickySupport>
  <applyToolheadOffsets>0</applyToolheadOffsets>
  <gcodeXoffset>0</gcodeXoffset>
  <gcodeYoffset>0</gcodeYoffset>
  <gcodeZoffset>0</gcodeZoffset>
  <overrideMachineDefinition>1</overrideMachineDefinition>
  <machineTypeOverride>0</machineTypeOverride>
  <strokeXoverride>220</strokeXoverride>
  <strokeYoverride>220</strokeYoverride>
  <strokeZoverride>250</strokeZoverride>
  <originOffsetXoverride>-5</originOffsetXoverride>
  <originOffsetYoverride>-5</originOffsetYoverride>
  <originOffsetZoverride>0</originOffsetZoverride>
  <homeXdirOverride>-1</homeXdirOverride>
  <homeYdirOverride>-1</homeYdirOverride>
  <homeZdirOverride>-1</homeZdirOverride>
  <flipXoverride>1</flipXoverride>
  <flipYoverride>-1</flipYoverride>
  <flipZoverride>1</flipZoverride>
  <toolheadOffsets>0,0|0,0|0,0|0,0|0,0|0,0</toolheadOffsets>
  <overrideFirmwareConfiguration>1</overrideFirmwareConfiguration>
  <firmwareTypeOverride>RepRap (Marlin/Repetier/Sprinter)</firmwareTypeOverride>
  <GPXconfigOverride>r2</GPXconfigOverride>
  <baudRateOverride>115200</baudRateOverride>
  <overridePrinterModels>1</overridePrinterModels>
  <printerModelsOverride></printerModelsOverride>
  <startingGcode>G92 E0 ; Reset Extruder distance to 0,G1 E-3.5 ; Retracts filament to prevent blobs during probing,G28 ; home all axes,G28 Z ; home Z to get more accurate Z position,G29; EZABL mesh generation,G4 S15; wait for heaters to recover,M117 Purge extruder,G92 E0 ; reset extruder,G1 Z1.0 F3000 ; move z up little,G1 X0.1 Y20 Z0.3 F5000.0 ; move to start-line position,G1 X0.1 Y100.0 Z0.3 F750.0 E15 ; draw 1st line,G1 X0.4 Y100.0 Z0.3 F5000.0 ; move to side a little,G1 X0.4 Y20 Z0.3 F750.0 E30 ; draw 2nd line,G92 E0 ; reset extruder,G1 Z1.0 F3000 ; move z up little,M117 Printing.....</startingGcode>
  <layerChangeGcode></layerChangeGcode>
  <retractionGcode></retractionGcode>
  <toolChangeGcode></toolChangeGcode>
  <endingGcode>G28 X0 ; home X axis,M106 S0 ; turn off cooling fan,M104 S0 ; turn off extruder,M140 S0 ; turn off bed,M84 ; disable motors</endingGcode>
  <exportFileFormat>gcode</exportFileFormat>
  <celebration>0</celebration>
  <celebrationSong>Random Song</celebrationSong>
  <postProcessing>curl -k -H &quot;X-Api-Key: EF26B3B25476475686C597C6E8A23C00&quot; -F &quot;select=true&quot; -F &quot;print=true&quot; -F &quot;file=@[output_filepath]&quot; &quot;http://192.168.1.47/api/files/local&quot; {STRIP &quot;; postProcessing&quot;}</postProcessing>
  <defaultSpeed>3600</defaultSpeed>
  <outlineUnderspeed>0.5</outlineUnderspeed>
  <solidInfillUnderspeed>0.8</solidInfillUnderspeed>
  <supportUnderspeed>0.8</supportUnderspeed>
  <rapidXYspeed>4800</rapidXYspeed>
  <rapidZspeed>1002</rapidZspeed>
  <minBridgingArea>50</minBridgingArea>
  <bridgingExtraInflation>0</bridgingExtraInflation>
  <bridgingExtrusionMultiplier>1</bridgingExtrusionMultiplier>
  <bridgingSpeedMultiplier>1</bridgingSpeedMultiplier>
  <useFixedBridgingAngle>0</useFixedBridgingAngle>
  <fixedBridgingAngle>0</fixedBridgingAngle>
  <applyBridgingToPerimeters>0</applyBridgingToPerimeters>
  <filamentDiameters>1.75|1.75|1.75|1.75|1.75|1.75</filamentDiameters>
  <filamentPricesPerKg>46|46|46|46|46|46</filamentPricesPerKg>
  <filamentDensities>1.25|1.25|1.25|1.25|1.25|1.25</filamentDensities>
  <useMinPrintHeight>0</useMinPrintHeight>
  <minPrintHeight>0</minPrintHeight>
  <useMaxPrintHeight>0</useMaxPrintHeight>
  <maxPrintHeight>0</maxPrintHeight>
  <useDiaphragm>0</useDiaphragm>
  <diaphragmLayerInterval>20</diaphragmLayerInterval>
  <robustSlicing>1</robustSlicing>
  <mergeAllIntoSolid>0</mergeAllIntoSolid>
  <onlyRetractWhenCrossingOutline>1</onlyRetractWhenCrossingOutline>
  <retractBetweenLayers>1</retractBetweenLayers>
  <useRetractionMinTravel>0</useRetractionMinTravel>
  <retractionMinTravel>3</retractionMinTravel>
  <retractWhileWiping>0</retractWhileWiping>
  <onlyWipeOutlines>1</onlyWipeOutlines>
  <avoidCrossingOutline>0</avoidCrossingOutline>
  <maxMovementDetourFactor>3</maxMovementDetourFactor>
  <toolChangeRetractionDistance>12</toolChangeRetractionDistance>
  <toolChangeExtraRestartDistance>-0.5</toolChangeExtraRestartDistance>
  <toolChangeRetractionSpeed>600</toolChangeRetractionSpeed>
  <externalThinWallType>0</externalThinWallType>
  <internalThinWallType>2</internalThinWallType>
  <thinWallAllowedOverlapPercentage>10</thinWallAllowedOverlapPercentage>
  <singleExtrusionMinLength>1</singleExtrusionMinLength>
  <singleExtrusionMinPrintingWidthPercentage>50</singleExtrusionMinPrintingWidthPercentage>
  <singleExtrusionMaxPrintingWidthPercentage>200</singleExtrusionMaxPrintingWidthPercentage>
  <singleExtrusionEndpointExtension>0.2</singleExtrusionEndpointExtension>
  <horizontalSizeCompensation>0</horizontalSizeCompensation>
  ${genMaterial(pla)}
  ${genMaterial(plaPlus)}
  ${genMaterial(petg)}
  ${genQuality(name = "Low", layerHeight = (nozzleSize*0.75).round(4), infillPercntage = 15)}
  ${genQuality(name = "Medium", layerHeight = (nozzleSize*0.5).round(4), infillPercntage = 50)}
  ${genQuality(name = "High", layerHeight = (nozzleSize*0.25).round(4), infillPercntage = 80)}
</profile>
"""
}

fun genQuality(name: String, layerHeight: Double, infillPercntage: Int): String{
    return """<autoConfigureQuality name="$name">
    <layerHeight>$layerHeight</layerHeight>
    <topSolidLayers>3</topSolidLayers>
    <bottomSolidLayers>3</bottomSolidLayers>
    <skirtLayers>1</skirtLayers>
    <infillPercentage>$infillPercntage</infillPercentage>
    <supportInfillPercentage>25</supportInfillPercentage>
</autoConfigureQuality>"""
}

fun genMaterial(material: Material): String{
    return """<autoConfigureMaterial name="${material.name}">
    <globalExtruderTemperature>${material.extruderTemp}</globalExtruderTemperature>
    <globalBedTemperature>${material.bedTemp}</globalBedTemperature>
    <globalExtrusionMultiplier>${material.extrusionMultiplier}</globalExtrusionMultiplier>
    <fanSpeed>
      <setpoint layer="1" speed="100"/>
    </fanSpeed>
  </autoConfigureMaterial>"""
}


fun String.prettyFormat(indent: Int): String {
    val dbFactory = DocumentBuilderFactory.newInstance()
    val dBuilder = dbFactory.newDocumentBuilder()
    val xmlInput = InputSource(StringReader(this))
    val doc = dBuilder.parse(xmlInput)
    return doc.toString()
}

fun Double.round(places: Int): Double{
    if (places < 0) throw IllegalArgumentException()

    var bd = BigDecimal(java.lang.Double.toString(this))
    bd = bd.setScale(places, RoundingMode.HALF_UP)
    return bd.toDouble()

}
