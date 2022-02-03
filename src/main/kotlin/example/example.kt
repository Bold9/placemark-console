package example

import jdk.javadoc.internal.doclets.toolkit.util.DocFinder.search
import mu.KotlinLogging
import org.models.PlacemarkModel.PlacemarkModel


private val logger = KotlinLogging.logger {}

var placemark = PlacemarkModel()

fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 2.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listPlacemarks()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun menu() : Int {
    var option : Int
    var input: String?

    println("MAIN MENU")
    println(" 1. Add Placemark")
    println(" 2. Update Placemark")
    println(" 3. List All Placemarks")
    println("-1. Exit")
    println()
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addPlacemark(){
    println("Add Placemark")
    println()
    print("Enter a Title : ")
    placemark.title = readLine()!!
    print("Enter a Description : ")
    placemark.description = readLine()!!
    println("You entered [ " + placemark.title + " ] for title " +
            "and [ " + placemark.description + " ] for description")
}

fun updatePlacemark() {
    println("Update Placemark")
    println()
    listPlacemarks()
    val searchId = getId()
    val aPlacemark = search(searchId)
    var tempTitle : String?
    var tempDescription : String?

    if(aPlacemark != null) {
        print("Enter a new Title for [ " + aPlacemark.title + " ] : ")
        tempTitle = readLine()!!
        print("Enter a new Description for [ " + aPlacemark.description + " ] : ")
        tempDescription = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
            aPlacemark.title = tempTitle
            aPlacemark.description = tempDescription
            println(
                "You updated [ " + aPlacemark.title + " ] for title " +
                        "and [ " + aPlacemark.description + " ] for description")
            logger.info("Placemark Updated : [ $aPlacemark ]")
        }
        else
            logger.info("Placemark Not Updated")
    }
    else
        println("Placemark Not Updated...")
}

fun listPlacemarks() {
    println("List All Placemarks")
    println()
    print("Enter a new forEach for [ " + placemark.forEach + " ] : ")
    placemark.forEach = readLine()!!
    placemarks.forEach { logger.info("${it}") }
}
